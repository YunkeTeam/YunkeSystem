package com.titos.info.shareplatform.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @ClassName Likes
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/4/3 10:12
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Likes {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 点赞的用户ID
     */
    private Integer userId;

    /**
     * 被点赞的帖子id
     */
    private Integer postId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
