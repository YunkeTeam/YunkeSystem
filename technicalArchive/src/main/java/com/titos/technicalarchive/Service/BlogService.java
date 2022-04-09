package com.titos.technicalarchive.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.titos.info.global.CommonResult;
import com.titos.info.technicalarchive.entity.Blog;
import com.titos.info.technicalarchive.vo.BlogVO;

import java.util.List;

/**
 * @ClassName BlogService
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/4/8 14:38
 **/
public interface BlogService extends IService<Blog> {

    /**
     * 分页查询所有的博客文章
     *
     * @param pageNum  当前页
     * @param pageSize 每页的数量
     * @return 博客文章列表
     */
    CommonResult<List<BlogVO>> listBlog(Integer pageNum, Integer pageSize);

}
