package com.titos.technicalarchive.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.titos.info.technicalarchive.entity.Blog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName BlogDao
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/4/8 14:50
 **/
@Repository
public interface BlogDao extends BaseMapper<Blog> {

    /**
     * 分页查询所有的博客文章
     *
     * @param pageNum  当前页
     * @param pageSize 每页的数量
     * @return 博客文章列表
     */
    List<Blog> listBlog(Integer pageNum, Integer pageSize);

}
