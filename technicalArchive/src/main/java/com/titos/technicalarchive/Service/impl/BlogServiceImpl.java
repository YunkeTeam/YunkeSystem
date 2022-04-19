package com.titos.technicalarchive.Service.impl;

import com.titos.technicalarchive.Service.BlogService;
import com.titos.technicalarchive.dao.BlogDao;
import com.titos.technicalarchive.po.BlogPO;
import com.titos.technicalarchive.utils.CheckUtil;
import com.titos.technicalarchive.vo.BlogNumVO;
import com.titos.technicalarchive.vo.BlogVO;
import com.titos.technicalarchive.vo.DetailBlogVO;
import com.titos.technicalarchive.vo.SimpleBlogVO;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ddgo
 * @DateTime: 2022/4/11 20:59
 * @Version: 1.0.0
 * @Description:
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogDao blogDao;

    @Override
    public int insertBlog(Integer userId, BlogVO blogVO) {
        BlogPO blogPO = new BlogPO();
        blogPO.setUserId(userId);
        blogPO.setArticleText(blogVO.getArticleText());
        blogPO.setArticleTitle(blogVO.getArticleTitle());
        blogPO.setArticleContent(blogVO.getArticleContent());
        blogPO.setArticleCover(blogVO.getArticleCover());
        blogPO.setCategory(blogVO.getCategory());
        blogPO.setStatus(blogVO.getStatus());

        int cnt = 0;
        try {
            cnt = blogDao.insertBlog(blogPO);
        } catch (DataAccessException e) {
            e.printStackTrace();
            cnt = 0;
        }
        return cnt;
    }

    @Override
    public int deleteBlog(Integer blogId) {
        int cnt = 0;
        try {
            cnt = blogDao.deleteBlog(blogId);
        } catch (DataAccessException e) {
            e.printStackTrace();
            cnt = 0;
        }
        return cnt;
    }

    @Override
    public List<SimpleBlogVO> selectBlog(Integer id, String pattern, String category) {
        List<SimpleBlogVO> blogVOList = null;
        try {
            if(category == null && pattern == null) {
                blogVOList = blogDao.selectBlog(id);
            } else if(category == null) {
                blogVOList = blogDao.selectBlogByPattern(id, pattern);
            } else if(pattern == null) {
                blogVOList = blogDao.selectBlogByCategory(id, category);
            } else {
                blogVOList = blogDao.selectBlogByPatternAndCategory(id, pattern, category);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            blogVOList = CheckUtil.defaultErrorBlogVOList;
        }
        return blogVOList;
    }

    @Override
    public DetailBlogVO selectBlogDetail(Integer blogId) {
        DetailBlogVO detailBlogVO = null;
        try {
            detailBlogVO = blogDao.selectBlogDetail(blogId);
        } catch (DataAccessException e) {
            detailBlogVO = CheckUtil.defaultErrorDetailBlogVO;
        }
        return detailBlogVO;
    }

    @Override
    public List<String> selectAllCategory(Integer id) {
        List<String> stringList = null;
        try {
            stringList = blogDao.selectAllCategory(id);
        } catch (DataAccessException e) {
            e.printStackTrace();
            stringList = CheckUtil.defaultErrorStringList;
        }
        return stringList;
    }

    @Override
    public BlogNumVO selectBlogNum(Integer id) {
        BlogNumVO blogNumVO = null;
        try {
            blogNumVO = blogDao.selectBlogNum(id);
        } catch (DataAccessException e) {
            blogNumVO = null;
            e.printStackTrace();
        }
        return blogNumVO;
    }
}
