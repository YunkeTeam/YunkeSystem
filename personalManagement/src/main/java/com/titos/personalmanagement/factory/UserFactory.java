package com.titos.personalmanagement.factory;

import com.titos.personalmanagement.model.User;

import java.time.LocalDateTime;

/**
 * 根据修改后的user信息创建新的User
 * @author Titos
 */
public class UserFactory {
    public User build(User user) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setPersonType(1);
        newUser.setRegistryTime(LocalDateTime.now());
        return newUser;
    }
}
