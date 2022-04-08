package com.titos.technicalarchive.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.titos.info.technicalarchive.entity.Blog;
import org.springframework.stereotype.Repository;

/**
 * @ClassName BlogDao
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/4/8 14:50
 **/
@Repository
public interface BlogDao extends BaseMapper<Blog> {
}
