package com.titos.info.global;

import com.titos.info.global.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公共返回类(与前端或与其它系统进行交互时返回的统一参数格式)
 *
 * @author Titos
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonResult<T> {

    /**
     * 返回状态
     */
    private Boolean flag;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 什么都不带的成功
     *
     * @param <T> 数据类型
     * @return restResult
     */
    public static <T> CommonResult<T> ok() {
        return restResult(true, null, StatusEnum.SUCCESS.getCode(), StatusEnum.SUCCESS.getMsg());
    }

    /**
     * 带数据的成功
     *
     * @param data 数据
     * @param <T>  数据类型
     * @return restResult
     */
    public static <T> CommonResult<T> ok(T data) {
        return restResult(true, data, StatusEnum.SUCCESS.getCode(), StatusEnum.SUCCESS.getMsg());
    }

    /**
     * 带数据和消息的成功
     *
     * @param data    数据
     * @param message 消息
     * @param <T>     数据类型
     * @return restResult
     */
    public static <T> CommonResult<T> ok(T data, String message) {
        return restResult(true, data, StatusEnum.SUCCESS.getCode(), message);
    }

    /**
     * 什么都不带的失败
     *
     * @param <T> 数据类型
     * @return restResult
     */
    public static <T> CommonResult<T> fail() {
        return restResult(false, null, StatusEnum.FAIL.getCode(), StatusEnum.FAIL.getMsg());
    }

    /**
     * 带状态码的失败
     *
     * @param statusEnum 状态码
     * @param <T>        数据类型
     * @return restResult
     */
    public static <T> CommonResult<T> fail(StatusEnum statusEnum) {
        return restResult(false, null, statusEnum.getCode(), statusEnum.getMsg());
    }

    /**
     * 带数据的失败
     *
     * @param data 数据
     * @param <T>  数据类型
     * @return restResult
     */
    public static <T> CommonResult<T> fail(T data) {
        return restResult(false, data, StatusEnum.FAIL.getCode(), StatusEnum.FAIL.getMsg());
    }

    /**
     * 带数据和消息的失败
     *
     * @param data    数据
     * @param message 消息
     * @param <T>     数据类型
     * @return restResult
     */
    public static <T> CommonResult<T> fail(T data, String message) {
        return restResult(false, data, StatusEnum.FAIL.getCode(), message);
    }

    /**
     * 带状态码和消息的失败
     *
     * @param code    状态码
     * @param message 消息
     * @param <T>     数据类型
     * @return restResult
     */
    public static <T> CommonResult<T> fail(Integer code, String message) {
        return restResult(false, null, code, message);
    }

    /**
     * 返回结果
     *
     * @param flag    状态
     * @param data    数据
     * @param code    状态码
     * @param message 消息
     * @param <T>     数据类型
     * @return CommonResult
     */
    private static <T> CommonResult<T> restResult(Boolean flag, T data, Integer code, String message) {
        CommonResult<T> apiResult = new CommonResult<>();
        apiResult.setFlag(flag);
        apiResult.setData(data);
        apiResult.setCode(code);
        apiResult.setMessage(message);
        return apiResult;
    }

}
