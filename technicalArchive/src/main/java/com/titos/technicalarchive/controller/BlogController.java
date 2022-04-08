package com.titos.technicalarchive.controller;

import com.titos.info.global.CommonResult;
import com.titos.info.technicalarchive.vo.BlogVO;
import com.titos.technicalarchive.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName BlogController
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/4/8 14:36
 **/
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    /**
     * 分页查询所有的博客文章
     *
     * @param pageNum  当前页
     * @param pageSize 每页的数量
     * @return 博客文章列表
     */
    @GetMapping(value = "/list")
    public CommonResult<List<BlogVO>> listBlog(
            @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
            @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize) {
        return blogService.listBlog(pageNum, pageSize);
    }

}
