package com.titos.shareplatform.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.titos.info.global.CommonResult;
import com.titos.info.global.enums.StatusEnum;
import com.titos.info.redis.constant.RedisPrefixConst;
import com.titos.info.shareplatform.dto.CommentDTO;
import com.titos.info.shareplatform.entity.Likes;
import com.titos.info.shareplatform.entity.Post;
import com.titos.info.shareplatform.vo.*;
import com.titos.info.shareplatform.vo.TalentVO;
import com.titos.info.user.entity.User;
import com.titos.shareplatform.dao.CommentDao;
import com.titos.shareplatform.dao.LikesDao;
import com.titos.shareplatform.dao.PostDao;
import com.titos.shareplatform.dao.UserDao;
import com.titos.shareplatform.service.PostService;
import com.titos.tool.BeanCopyUtils.BeanCopyUtils;
import com.titos.tool.token.CustomStatement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @ClassName PostServiceImpl
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/3/30 21:51
 **/
@Service
@Slf4j
public class PostServiceImpl extends ServiceImpl<PostDao, Post> implements PostService {

    @Resource
    private PostDao postDao;

    @Resource
    private LikesDao likesDao;

    @Resource
    private CommentDao commentDao;

    @Resource
    private UserDao userDao;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public CommonResult<List<PostVO>> listPost(CustomStatement customStatement, Long pageNum, Long pageSize) {
        List<PostVO> postList = postDao.listPost((pageNum - 1) * pageSize, pageSize);
        for (PostVO post : postList) {
            List<String> likes = likesDao.likesUserAvatar(post.getId());
            post.setLikesUserAvatar(likes);

            Boolean isLike = likesDao.selectOne(new LambdaQueryWrapper<Likes>()
                    .eq(Likes::getUserId, customStatement.getId())
                    .eq(Likes::getPostId, post.getId())) != null;
            post.setIsLike(isLike);

            List<CommentDTO> commentList = commentDao.listUserByPostId(post.getId());
            post.setCommentList(commentList);

            post.setCommentBox("");
        }
        return CommonResult.success(postList);
    }

    @Override
    public CommonResult<List<MyPostVO>> listMyPost(CustomStatement customStatement, Long pageNum, Long pageSize) {
        Page<Post> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Post> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Post::getId, Post::getTitle, Post::getContent, Post::getPostCover, Post::getLikes, Post::getCreateTime)
                .orderByDesc(Post::getCreateTime)
                .eq(Post::getUserId, customStatement.getId());
        //Page<Post> postPage = postDao.selectPage(page, queryWrapper);
        //List<MyPostVO> postList = BeanCopyUtils.copyList(postPage.getRecords(), MyPostVO.class);
        return CommonResult.success(BeanCopyUtils.copyList(postDao.selectList(queryWrapper), MyPostVO.class));
    }

    @Override
    public CommonResult<List<TalentVO>> listTalent(Long pageNum, Long pageSize) {
        Set<ZSetOperations.TypedTuple<Object>> tupleSet = redisTemplate.opsForZSet().reverseRangeWithScores(
                RedisPrefixConst.TALENT, (pageNum - 1) * pageSize, pageNum * pageSize - 1);

        if (tupleSet == null) {
            return CommonResult.success(null);
        }
        List<TalentVO> talentList = new ArrayList<>(tupleSet.size());
        long rank = 1;
        for (ZSetOperations.TypedTuple<Object> sub : tupleSet) {
            User user = JSON.parseObject((String) sub.getValue(), User.class);
            talentList.add(TalentVO.builder()
                    .rank(rank++)
                    .postCount(sub.getScore().intValue())
                    .userId(user.getId())
                    .username(user.getUsername())
                    .headImage(user.getHeadImage())
                    .build());
        }
        return CommonResult.success(talentList);

    }

    @Async
    @Override
    public void addPost(CustomStatement customStatement, AddPostVO addPostVO) {
        Post post = BeanCopyUtils.copyObject(addPostVO, Post.class);
        post.setUserId(customStatement.getId());
        post.setCreateTime(addPostVO.getCreateTime());
        postDao.insert(post);

        User user = userDao.selectOne(new LambdaQueryWrapper<User>()
                .select(User::getId, User::getUsername, User::getHeadImage)
                .eq(User::getId, customStatement.getId()));
        log.info(JSON.toJSONString(user));
        redisTemplate.opsForZSet().incrementScore(RedisPrefixConst.TALENT, JSON.toJSONString(user), 1.0D);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Boolean> deletePosts(CustomStatement customStatement, DeleteVO deleteVO) {
        for (Integer postId : deleteVO.getIdList()) {
            if (!customStatement.getId().equals(postDao.selectById(postId).getUserId())) {
                return CommonResult.fail(StatusEnum.FAIL_DEL_POST.getCode(), StatusEnum.FAIL_DEL_POST.getMsg());
            }
        }
        postDao.deleteBatchIds(deleteVO.getIdList());
        return CommonResult.success(Boolean.TRUE);
    }

    @Override
    public CommonResult<Boolean> savePostLike(CustomStatement customStatement, LikesVO likesVO) {
        Likes likes = likesDao.selectOne(new LambdaQueryWrapper<Likes>()
                .eq(Likes::getUserId, customStatement.getId())
                .eq(Likes::getPostId, likesVO.getPostId()));
        if (likes != null) {
            likesDao.deleteById(likes.getId());
            Post post = new Post();
            post.setId(likesVO.getPostId());
            postDao.update(post, Wrappers.update(post).setSql("`likes`=`likes`-1"));
        } else {
            likesDao.insert(Likes.builder()
                    .userId(customStatement.getId())
                    .postId(likesVO.getPostId())
                    .build());
            Post post = new Post();
            post.setId(likesVO.getPostId());
            postDao.update(post, Wrappers.update(post).setSql("`likes`=`likes`+1"));
        }
        return CommonResult.success(Boolean.TRUE);
    }

}
