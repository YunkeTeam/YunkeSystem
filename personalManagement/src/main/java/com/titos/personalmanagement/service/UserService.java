package com.titos.personalmanagement.service;

import com.titos.info.global.CommonResult;
import com.titos.personalmanagement.model.User;

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
}
