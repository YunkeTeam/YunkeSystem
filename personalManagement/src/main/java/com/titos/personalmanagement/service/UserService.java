package com.titos.personalmanagement.service;

import com.titos.info.global.CommonResult;
import com.titos.info.user.entity.UserInfoDomain;
import com.titos.info.user.query.LoginQuery;
import com.titos.info.user.vo.LoginVo;
import com.titos.personalmanagement.model.User;
import com.titos.tool.token.CustomStatement;

/**
 * 用户个人登录、退出、个人信息管理操作
 * @author Titos
 */
public interface UserService {
    /**
     * 注册用户
     * @param user 用户对象
     * @return 状态信息
     */
    CommonResult register(User user);

    /**
     * 检验用户名是否存在
     * @param username 用户名
     * @return 检验结果
     */
    boolean isUsernameExisted(String username);

    /**
     * 检验邮箱是否存在
     * @param email 邮箱
     * @return 检验结果
     */
    boolean isEmailExisted(String email);

    /**
     * 登录操作
     * @param loginQuery
     * @return
     */
    CommonResult<LoginVo> login(LoginQuery loginQuery);

    /**
     * 注册时邮箱验证操作
     * @param username 用户名
     * @param key redis中用户的key
     * @return 验证的结果
     */
    CommonResult<LoginVo> verifyEmail(String username, String key);

    /**
     * 修改用户信息
     * @param userInfoDomain 接收用户参数封装的实体类
     * @param customStatement 用户在token中的信息
     * @return 修改后的用户信息
     */
    CommonResult<UserInfoDomain> modifyUserInfo(UserInfoDomain userInfoDomain, CustomStatement customStatement);

}
