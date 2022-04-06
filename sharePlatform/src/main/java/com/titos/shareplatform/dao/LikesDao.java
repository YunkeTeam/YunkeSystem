package com.titos.shareplatform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.titos.info.shareplatform.entity.Likes;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName LikesDao
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/4/3 10:11
 **/
@Repository
public interface LikesDao extends BaseMapper<Likes> {

    /**
     * 根据帖子ID查询点赞该帖子的用户头像
     *
     * @param postId 帖子ID
     * @return 用户头像列表
     */
    List<String> likesUserAvatar(Integer postId);

}
