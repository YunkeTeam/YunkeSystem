package com.titos.info.shareplatform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Notice
 * @Description 通告实体类
 * @Author Kurihada
 * @Date 2022/4/9 17:52
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notice {

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 通告内容
     */
    private String noticeContent;

}
