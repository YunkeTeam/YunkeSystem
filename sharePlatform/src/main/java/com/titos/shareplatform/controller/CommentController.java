package com.titos.shareplatform.controller;

import com.titos.info.global.CommonResult;
import com.titos.info.shareplatform.vo.AddCommentVO;
import com.titos.info.shareplatform.vo.DeleteVO;
import com.titos.shareplatform.service.CommentService;
import com.titos.tool.annotions.InjectToken;
import com.titos.tool.token.CustomStatement;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @ClassName CommentController
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/4/10 11:48
 **/
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    /**
     * 新增评论
     *
     * @param customStatement 用户消息
     * @param addCommentVO    评论内容
     * @return 是否新增成功
     */
    @InjectToken
    @PostMapping(value = "/add")
    public CommonResult<Boolean> addComment(
            CustomStatement customStatement,
            @Valid @RequestBody AddCommentVO addCommentVO) {
        return commentService.addComment(customStatement, addCommentVO);
    }

    /**
     * 批量删除评论
     *
     * @param customStatement 用户消息
     * @param deleteVO   需要删除的评论ID列表
     * @return 是否删除成功
     */
    @InjectToken
    @DeleteMapping(value = "/delete")
    public CommonResult<Boolean> deleteComments(
            CustomStatement customStatement,
            @RequestBody DeleteVO deleteVO) {
        return commentService.deleteComments(customStatement, deleteVO);
    }

}
