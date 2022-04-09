package com.titos.info.shareplatform.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName UpdateInfoVO
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/4/9 16:17
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateInfoVO {

    /**
     * 消息Id
     */
    @NotBlank(message = "消息ID不能为空")
    private Integer infoId;

    /**
     * 消息状态
     */
    @NotBlank(message = "消息状态不能为空")
    private Integer infoStatus;

}
