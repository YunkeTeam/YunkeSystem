package com.titos.technicalarchive.dao;

import com.titos.technicalarchive.po.BlogPO;
import com.titos.technicalarchive.vo.DetailBlogVO;
import com.titos.technicalarchive.vo.SimpleBlogVO;
import org.springframework.dao.DataAccessException;

import javax.xml.crypto.Data;
import java.util.List;

/**
 * @Author: ddgo
 * @DateTime: 2022/4/11 20:45
 * @Version: 1.0.0
 * @Description:
 */

public interface BlogDao {

    /**
     * 插入一篇博客
     * @param blogPO
     * @return
     * @throws DataAccessException
     */
    int insertBlog(BlogPO blogPO) throws DataAccessException;

    /**
     * 删除某篇博客
     * @param blogId
     * @return
     * @throws DataAccessException
     */
    int deleteBlog(Integer blogId) throws DataAccessException;

    /**
     * 按pattern模糊查询用户的文章
     * @param id
     * @param pattern
     * @return
     * @throws DataAccessException
     */
    List<SimpleBlogVO> selectBlogByPattern(Integer id, String pattern) throws DataAccessException;

    /**
     * 按id 查询当前用户的文章
     * @param id
     * @return
     * @throws DataAccessException
     */
    List<SimpleBlogVO> selectBlog(Integer id) throws DataAccessException;

    /**
     * 按分类查询文章
     * @param id
     * @param category
     * @return
     * @throws DataAccessException
     */
    List<SimpleBlogVO> selectBlogByCategory(Integer id, String category) throws DataAccessException;

    /**
     * 按分类和Pattern查询文章
     * @param id
     * @param pattern
     * @param category
     * @return
     * @throws DataAccessException
     */
    List<SimpleBlogVO> selectBlogByPatternAndCategory(Integer id, String pattern, String category) throws DataAccessException;

    /**
     * 查询当前博客的详细信息
     * @param blogId
     * @return
     * @throws DataAccessException
     */
    DetailBlogVO selectBlogDetail(Integer blogId) throws DataAccessException;

}
