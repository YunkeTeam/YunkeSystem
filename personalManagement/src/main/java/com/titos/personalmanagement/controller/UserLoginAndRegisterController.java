//package com.titos.personalmanagement.controller;
//
//import com.titos.info.global.CommonResult;
//import com.titos.info.user.query.LoginQuery;
//import com.titos.info.user.query.UserVerifyQuery;
//import com.titos.info.user.vo.LoginVo;
//import com.titos.personalmanagement.model.User;
//import com.titos.personalmanagement.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
///**
// * 普通用户的登录、注册操作
// * @author Titos
// */
//@RestController
//@RequestMapping("/user")
//public class UserLoginAndRegisterController {
//    @Autowired
//    private UserService userService;
//
//    /**
//     * 注册，将用户信息缓存到redis中并发送邮件
//     * @param user
//     * @return
//     */
//    @PostMapping("/signUp")
//    public CommonResult register(@RequestBody User user) {
//        return userService.register(user);
//    }
//
//    /**
//     * 邮箱验证注册的用户
//     * @return 验证的结果
//     */
//    @GetMapping("/verifyEmail/{key}/{username}")
//    public CommonResult<LoginVo> signUpVerify(@PathVariable("key") String key, @PathVariable("username") String username) {
//        return userService.verifyEmail(username, key);
//    }
//
//    /**
//     * 登录时，需要验证码验证
//     * @param loginQuery 接收前端的参数
//     * @return 登录的结果
//     */
//    @PostMapping("/login")
//    public CommonResult<LoginVo> login(@RequestBody LoginQuery loginQuery) {
//        return userService.login(loginQuery);
//    }
//}
