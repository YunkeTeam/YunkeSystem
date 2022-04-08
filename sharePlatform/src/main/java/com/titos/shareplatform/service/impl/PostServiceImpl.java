package com.titos.shareplatform.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.titos.info.global.CommonResult;
import com.titos.info.global.enums.StatusEnum;
import com.titos.info.redis.constant.RedisPrefixConst;
import com.titos.info.redis.vo.RedisVO;
import com.titos.info.shareplatform.dto.CommentDTO;
import com.titos.info.shareplatform.entity.Post;
import com.titos.info.shareplatform.vo.MyPostVO;
import com.titos.info.shareplatform.vo.PostVO;
import com.titos.info.shareplatform.vo.SharePlatformVO;
import com.titos.info.user.vo.TalentVO;
import com.titos.rpc.redis.RedisRpc;
import com.titos.shareplatform.dao.CommentDao;
import com.titos.shareplatform.dao.LikesDao;
import com.titos.shareplatform.dao.PostDao;
import com.titos.shareplatform.service.PostService;
import com.titos.tool.BeanCopyUtils.BeanCopyUtils;
import com.titos.tool.token.CustomStatement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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

    @Autowired
    private LikesDao likesDao;

    @Autowired
    private CommentDao commentDao;

    @Resource
    private RedisRpc redisRpc;

    @Override
    public CommonResult<List<SharePlatformVO>> listPost(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SharePlatformVO> listSharePlatform = postDao.listSharePlatform();
        for (SharePlatformVO sharePlatform : listSharePlatform) {
            List<String> likes = likesDao.likesUserAvatar(sharePlatform.getId());
            sharePlatform.setLikesUserAvatar(likes);

            List<CommentDTO> commentList = commentDao.listUserByPostId(sharePlatform.getId());
            sharePlatform.setCommentList(commentList);
        }
        return CommonResult.success(listSharePlatform);
    }

    @Override
    public CommonResult<List<MyPostVO>> listMyPost(CustomStatement customStatement, Integer pageNum, Integer pageSize) {
        Page<Post> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Post> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Post::getId, Post::getTitle, Post::getContent, Post::getPostCover, Post::getLikes, Post::getCreateTime)
                .orderByDesc(Post::getCreateTime)
                .eq(Post::getUserId, customStatement.getId());
        Page<Post> postPage = postDao.selectPage(page, queryWrapper);
        List<MyPostVO> postList = BeanCopyUtils.copyList(postPage.getRecords(), MyPostVO.class);
        return CommonResult.success(postList);
    }

    @Override
    public CommonResult<List<TalentVO>> listTalent(Integer pageNum, Integer pageSize) {
        List<TalentVO> listTalentUser = null;
        if (!redisRpc.hasKey(RedisPrefixConst.TALENT).getData()) {
            PageHelper.startPage(pageNum, pageSize);
            listTalentUser = postDao.listTalentUserId();
            redisRpc.set(new RedisVO(RedisPrefixConst.TALENT, JSON.toJSONString(listTalentUser), null));
        } else {
            listTalentUser = JSON.parseObject(redisRpc.get(RedisPrefixConst.TALENT).getData().toString(), List.class);
        }
        return CommonResult.success(listTalentUser);
    }

    @Override
    public CommonResult<Boolean> addPost(CustomStatement customStatement, PostVO postVO) {
        Post post = BeanCopyUtils.copyObject(postVO, Post.class);
        post.setUserId(customStatement.getId());
        postDao.insert(post);
        return CommonResult.success(Boolean.TRUE);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Boolean> deletePosts(CustomStatement customStatement, List<Integer> postIdList) {
        for (Integer postId : postIdList) {
            if (!customStatement.getId().equals(postDao.selectById(postId).getUserId())) {
                return CommonResult.fail(StatusEnum.FAIL_DEL_POST.getCode(), StatusEnum.FAIL_DEL_POST.getMsg());
            }
        }
        postDao.deleteBatchIds(postIdList);
        return CommonResult.success(Boolean.TRUE);
    }

}
