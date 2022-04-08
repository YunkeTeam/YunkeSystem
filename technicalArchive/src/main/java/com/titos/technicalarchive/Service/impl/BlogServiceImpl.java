package com.titos.technicalarchive.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.titos.info.global.CommonResult;
import com.titos.info.technicalarchive.entity.Blog;
import com.titos.info.technicalarchive.vo.BlogVO;
import com.titos.technicalarchive.Service.BlogService;
import com.titos.technicalarchive.dao.BlogDao;
import com.titos.tool.BeanCopyUtils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName BlogServiceImpl
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/4/8 14:50
 **/
@Service
public class BlogServiceImpl extends ServiceImpl<BlogDao, Blog> implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Override
    public CommonResult<List<BlogVO>> listBlog(Integer pageNum, Integer pageSize) {
        List<Blog> articleList = blogDao.listBlog(pageNum, pageSize);
        return null;
    }
}
