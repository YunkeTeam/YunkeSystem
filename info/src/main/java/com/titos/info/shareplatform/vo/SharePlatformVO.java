package com.titos.info.shareplatform.vo;

import com.titos.info.shareplatform.dto.CommentDTO;
import com.titos.info.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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
    private String postCover;

    /**
     * 评论量
     */
    private Integer comments;

    /**
     * 点赞量
     */
    private Integer likes;

    /**
     * 帖子发表时间
     */
    private LocalDateTime createTime;

    /**
     * 发布帖子的用户消息
     */
    private UserDTO user;

    /**
     * 帖子点赞用户头像（最近五名）
     */
    private List<String> likesUserAvatar;

    /**
     * 评论信息
     */
    private List<CommentDTO> commentList;

}
