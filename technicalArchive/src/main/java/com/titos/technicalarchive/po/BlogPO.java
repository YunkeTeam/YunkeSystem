package com.titos.technicalarchive.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: ddgo
 * @DateTime: 2022/4/11 20:59
 * @Version: 1.0.0
 * @Description:
 */
@Data
public class BlogPO {
    /**
     * 博客id
     */
    Integer id;
    /**
     * 用户id
     */
    Integer userId;
    /**
     * 文章标题
     */
    String articleTitle;
    /**
     * 文章内容
     */
    String articleContent;
    /**
     * 文章封面地址
     */
    String articleCover;
    /**
     * 分类
     */
    String category;
    /**
     * 状态值, 1公开，2私密
     */
    Integer status;
    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    LocalDateTime createTime;
}
