package com.titos.info.shareplatform.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @ClassName ConversationVO
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/3/30 22:06
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SharePlatformVO {

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
     * 帖子发表时间
     */
    private LocalDateTime releaseTime;

    /**
     * 发布帖子的用户ID
     */
    private Integer userId;

    /**
     * 发布帖子的用户ID
     */
    private Integer username;

}
