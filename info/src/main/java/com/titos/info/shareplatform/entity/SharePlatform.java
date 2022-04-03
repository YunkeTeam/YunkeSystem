package com.titos.info.shareplatform.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @ClassName Conversation
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/3/30 22:08
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SharePlatform {

    /**
     * 帖子ID
     */
    private Integer id;

    /**
     * 帖子标题
     */
    private String title;

    /**
     * 帖子内容
     */
    private String content;

    /**
     * 帖子封面图片
     */
    private String imageAddr;

    /**
     * 发布帖子的用户ID
     */
    private Integer userId;

    /**
     * 帖子发表时间
     */
    private LocalDateTime releaseTime;

    /**
     * 帖子是否违规，默认否
     */
    private Boolean isViolation;

}
