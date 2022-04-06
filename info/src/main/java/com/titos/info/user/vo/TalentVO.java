package com.titos.info.user.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName TalentVO
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/4/3 21:40
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TalentVO {

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户头像
     */
    private String headImage;

    /**
     * 用户发表的帖子数量
     */
    private Integer postCount;


}
