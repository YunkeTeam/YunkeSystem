package com.titos.personalmanagement.dao;

import com.titos.personalmanagement.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作用户数据的Dao
 * @author Titos
 */
@Mapper
public interface UserDao {
    /**
     * 用户注册时，添加新用户
     * @param user 接收的参数封装为User对象
     * @return 添加的结果
     */
    Integer addNewUser(User user);

    /**
     * 根据username、email动态查询用户id(判断数据是否存在)
     * 使用动态sql来查询是否存在
     * @param user 用户对象
     * @return 用户id
     */
    Integer selectIdDynamic(User user);
}
