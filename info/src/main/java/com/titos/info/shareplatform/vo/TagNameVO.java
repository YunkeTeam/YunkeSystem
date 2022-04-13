package com.titos.info.shareplatform.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName TagNameVO
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/4/13 0:27
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagNameVO {

    /**
     * 标签名列表
     */
    private String tagName;

}
