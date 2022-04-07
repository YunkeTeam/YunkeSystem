package com.titos.info.user.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户修改密码接收前端封装的类
 * @author Titos
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserPassword {
    /**
     * 用户id
     */
    private Integer userID;
    /**
     * 用户旧密码
     */
    private String oldPassword;
    /**
     * 用户新密码
     */
    private String newPassword;
}
