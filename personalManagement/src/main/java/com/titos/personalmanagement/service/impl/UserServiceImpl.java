package com.titos.personalmanagement.service.impl;

import com.titos.info.global.CommonResult;
import com.titos.info.global.enums.StatusEnum;
import com.titos.personalmanagement.cache.UserInfoCache;
import com.titos.personalmanagement.config.YkSysConf;
import com.titos.personalmanagement.dao.UserDao;
import com.titos.personalmanagement.model.User;
import com.titos.personalmanagement.service.UserService;
import com.titos.tool.check.VerifyPasswordUtil;
import com.titos.tool.check.VerifyStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 操作用户数据的service层
 * @author Titos
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private YkSysConf ykSysConf;
    @Autowired
    private UserInfoCache userInfoCache;

    @Override
    public CommonResult register(User user) {
        // 验证用户密码是否符合要求，用户名或邮箱是否存在
        Optional<CommonResult> res = verifyUser(user);
        // 不满足，直接返回结果
        if (res.isPresent()) {
            return res.get();
        }
        // 如果开启了邮箱注册
        if (ykSysConf.getEnableMailRegister()) {
            // 将用户数据暂时存储到redis
            String key = userInfoCache.cacheInfo(user);
            boolean isSuccess = false;
        }
        return null;
    }

    /**
     * 根据username查询数据库中是否已经存在数据
     * @param username 用户名
     * @return 查询结果
     */
    @Override
    public boolean isUsernameExisted(String username) {
        User userQuery = new User();
        userQuery.setUsername(username);
        Integer userId = userDao.selectIdDynamic(userQuery);
        return userId != null;
    }
    /**
     * 根据email查询数据库中是否已经存在数据
     * @param email 邮箱
     * @return 查询结果
     */
    @Override
    public boolean isEmailExisted(String email) {
        User userQuery = new User();
        userQuery.setUsername(email);
        Integer userId = userDao.selectIdDynamic(userQuery);
        return userId != null;
    }

    /**
     * 验证用户的密码是否符合要求，数据库中是否有相同的用户名和邮箱
     * @param user 用户对象
     * @return 如果返回的对象不为空，则表明不符合指定要求，反之则满足条件
     */
    private Optional<CommonResult> verifyUser(User user) {
        // 密码不符合要求
        if (!VerifyPasswordUtil.isPwdStrong(user.getPassword())) {
            return Optional.of(new CommonResult(StatusEnum.PASSWORD_IS_NOT_STRONG.getCode(), "密码强度不足"));
        } else {
            // 验证用户名和邮箱是否已经存在
            return verifyRepeat(user.getUsername(), user.getEmail());
        }
    }

    /**
     * 验证用户名和邮箱在数据库中是否已经存在
     * @param username 用户名
     * @param email 邮箱
     * @return 验证结果，如果不存在返回空对象，存在返回CommonResult
     */
    private Optional<CommonResult> verifyRepeat(String username, String email) {
        CommonResult res = null;
        // 验证数据是否重复
        if (!VerifyStringUtil.isStringNull(username) && isUsernameExisted(username)) {
            res = new CommonResult(StatusEnum.USERNAME_EXISTED.getCode(), "用户名已经存在");
        }
        if (!VerifyStringUtil.isStringNull(email) && isEmailExisted(email)) {
            res = new CommonResult(StatusEnum.EMAIL_EXISTED.getCode(), "邮箱已经存在");
        }
        return Optional.ofNullable(res);
    }
}
