package com.titos.personalmanagement.controller;

import com.titos.info.global.CommonResult;
import com.titos.info.user.entity.UserInfoDomain;
import com.titos.info.user.query.LoginQuery;
import com.titos.info.user.vo.LoginVo;
import com.titos.personalmanagement.model.User;
import com.titos.personalmanagement.service.UserService;
import com.titos.tool.annotions.InjectToken;
import com.titos.tool.annotions.ParamVerify;
import com.titos.tool.token.CustomStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 普通用户的登录、注册操作
 * @author Titos
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 注册，将用户信息缓存到redis中并发送邮件
     * @param user
     * @return
     */
    @ParamVerify(notNull = {"user.username", "user.email", "user.password"})
    @PostMapping("/signUp")
    public CommonResult register(@RequestBody User user) {
        return userService.register(user);
    }

    /**
     * 邮箱验证注册的用户
     * @return 验证的结果
     */
    @GetMapping("/verifyEmail/{key}/{username}")
    public CommonResult<LoginVo> signUpVerify(@PathVariable("key") String key, @PathVariable("username") String username) {
        return userService.verifyEmail(username, key);
    }

    /**
     * 登录时，需要验证码验证
     * @param loginQuery 接收前端的参数
     * @return 登录的结果
     */
    @PostMapping("/login")
    public CommonResult<LoginVo> login(@RequestBody LoginQuery loginQuery) {
        return userService.login(loginQuery);
    }

    /**
     * 根据用户id来修改用户的信息
     * @param customStatement 用户在token中的信息
     * @param userInfoDomain 用户信息（不含密码）的实体类
     * @return 更改后的用户信息
     */
    @InjectToken
    @PostMapping("/modifyInfo")
    public CommonResult<UserInfoDomain> modifyUserInfo(CustomStatement customStatement, @RequestBody UserInfoDomain userInfoDomain) {
        if (userInfoDomain.getId() == null) {
            userInfoDomain.setId(customStatement.getId());
        }
        return userService.modifyUserInfo(userInfoDomain, customStatement);
    }
}
