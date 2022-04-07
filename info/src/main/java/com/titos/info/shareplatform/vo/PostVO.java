package com.titos.info.shareplatform.vo;

<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName PostVO
 * @Description TODO
 * @Author Kurihada
<<<<<<< Updated upstream
 * @Date 2022/4/7 17:28
=======
 * @Date 2022/4/7 17:05
>>>>>>> Stashed changes
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostVO {

    /**
<<<<<<< Updated upstream
     * 帖子ID
     */
    private Integer id;

    /**
=======
>>>>>>> Stashed changes
     * 帖子标题
     */
    @NotBlank(message = "帖子标题不能为空")
    private String title;

    /**
     * 帖子内容
     */
    @NotBlank(message = "帖子内容不能为空")
    private String content;

    /**
     * 帖子封面图
     */
    private String postCover;

}
