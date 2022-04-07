package com.titos.shareplatform.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.titos.info.global.CommonResult;
import com.titos.info.redis.constant.RedisPrefixConst;
import com.titos.info.redis.vo.RedisVO;
import com.titos.info.shareplatform.dto.CommentDTO;
<<<<<<< Updated upstream
import com.titos.info.shareplatform.entity.Post;
=======
>>>>>>> Stashed changes
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
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Autowired
    private PostService postService;

    @Override
    public CommonResult<List<SharePlatformVO>> listSharePlatform(Integer pageNum, Integer pageSize) {
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
<<<<<<< Updated upstream
    public CommonResult<Boolean> addPost(CustomStatement customStatement, PostVO postVO) {
        Post post = BeanCopyUtils.copyObject(postVO, Post.class);
        post.setUserId(customStatement.getId());
        postDao.insert(post);
        return CommonResult.success(Boolean.TRUE);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Boolean> deletePosts(List<Integer> postIdList) {
        postDao.deleteBatchIds(postIdList);
        return CommonResult.success(Boolean.TRUE);
=======
    public void addPost(PostVO postVO) {

>>>>>>> Stashed changes
    }

}
