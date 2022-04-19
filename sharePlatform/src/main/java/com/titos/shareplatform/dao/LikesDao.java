package com.titos.shareplatform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.titos.info.shareplatform.entity.Likes;
import org.springframework.stereotype.Repository;

/**
 * @ClassName LikesDao
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/4/19 17:27
 **/
@Repository
public interface LikesDao extends BaseMapper<Likes> {
}
