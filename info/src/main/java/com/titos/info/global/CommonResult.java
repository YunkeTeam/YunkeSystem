package com.titos.info.global;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公共返回类(与前端或与其它系统进行交互时返回的统一参数格式)
 * @author Titos
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonResult<T> {
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 返回数据
     */
    private T data;
    /**
     * 错误提示
     */
    private String msg;

    public CommonResult(Integer code, String msg) {
        this(code, null, msg);
    }

    public CommonResult(Integer code, T data) {
        this(code, data, null);
    }



}
