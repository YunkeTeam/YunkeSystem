package com.titos.info.global.enums;

/**
 * 状态枚举类
 * @author Titos
 */
public enum StatusEnum {
    /**
     * 成功
     */
    SUCCESS(200),
    /**
     * 参数错误
     */
    PARAM_ERROR(400),
    /**
     * 没有登录
     */
    UN_LOGIN(401),
    /**
     * token错误
     */
    TOKEN_ERROR(402),
    /**
     *  禁止访问
     */
    FORBIDDEN(403),
    /**
     * 密码太弱
     */
    PASSWORD_IS_NOT_STRONG(404),
    /**
     *  用户名已经存在
     */
    USERNAME_EXISTED(405),
    /**
     *  邮箱已经存在
     */
    EMAIL_EXISTED(405),
    /**
     * 邮箱错误
     */
    MAIL_ERROR(406),
    /**
     * 密码错误
     */
    PASSWORD_WRONG(407),
    /**
     * 文件大小错误
     */
    FILE_SIZE_ERROR(408),
    /**
     * 验证错误
     */
    VERIFY_ERROR(409),
    /**
     * 文件保存错误
     */
    FILE_SAVE_ERROR(500);
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 私有构造，防止被外部调用
     * @param code 状态码
     */
    private StatusEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    /**
     * 根据状态码返回枚举成员
     * @param code 状态码
     * @return 枚举成员
     */
    public static StatusEnum valueOf(int code) {
        for (StatusEnum value: StatusEnum.values()) {
            if(value.code == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("argument out of range");
    }
}
