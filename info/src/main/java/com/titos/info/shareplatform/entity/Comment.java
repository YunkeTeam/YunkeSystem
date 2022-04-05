package com.titos.info.shareplatform.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @ClassName Comment
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/4/2 22:33
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    /**
     * 评论ID
     */
    private Integer id;

    /**
     * 评论的用户ID
     */
    private Integer userId;

    /**
     * 被评论的帖子ID
     */
    private Integer postId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    private LocalDateTime createTime;

}
