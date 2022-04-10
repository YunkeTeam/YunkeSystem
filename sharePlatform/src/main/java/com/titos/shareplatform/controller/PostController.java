package com.titos.shareplatform.controller;

import com.titos.info.global.CommonResult;
import com.titos.info.shareplatform.vo.LikesVO;
import com.titos.info.shareplatform.vo.MyPostVO;
import com.titos.info.shareplatform.vo.AddPostVO;
import com.titos.info.shareplatform.vo.PostVO;
import com.titos.info.user.vo.TalentVO;
import com.titos.shareplatform.service.PostService;
import com.titos.tool.annotions.InjectToken;
import com.titos.tool.token.CustomStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName PostController
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/3/30 21:45
 **/
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    /**
     * 分页查询所有的帖子
     *
     * @param customStatement 用户信息
     * @param pageNum         当前页
     * @param pageSize        每页的数量
     * @return 帖子列表
     */
    @InjectToken
    @GetMapping("/list")
    public CommonResult<List<PostVO>> listPost(
            CustomStatement customStatement,
            @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
            @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize) {
        return postService.listPost(customStatement, pageNum, pageSize);
    }

    /**
     * 获取当前用户的帖子
     *
     * @param customStatement 用户信息
     * @param pageNum         当前页
     * @param pageSize        每页的数量
     * @return 帖子列表
     */
    @InjectToken
    @GetMapping("/listme")
    public CommonResult<List<MyPostVO>> listMyPost(
            CustomStatement customStatement,
            @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
            @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize
    ) {
        return postService.listMyPost(customStatement, pageNum, pageSize);
    }

    /**
     * 查询活跃达人
     *
     * @param pageNum  当前页
     * @param pageSize 每页的数量
     * @return 结果
     */
    @GetMapping("/talent")
    public CommonResult<List<TalentVO>> listTalent(
            @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
            @RequestParam(defaultValue = "8", value = "pageSize") Integer pageSize) {
        return postService.listTalent(pageNum, pageSize);
    }

    /**
     * 新增帖子
     *
     * @param customStatement 用户信息
     * @param addPostVO       帖子信息
     * @return 发布是否成功
     */
    @InjectToken
    @PostMapping("/add")
    public CommonResult<Boolean> addPost(CustomStatement customStatement, @Valid @RequestBody AddPostVO addPostVO) {
        return postService.addPost(customStatement, addPostVO);
    }

    /**
     * 批量删除帖子
     *
     * @param customStatement 用户信息
     * @param postIdList      帖子列表
     * @return 删除是否成功
     */
    @InjectToken
    @PostMapping("/delete")
    public CommonResult<Boolean> deletePosts(CustomStatement customStatement, @RequestBody List<Integer> postIdList) {
        return postService.deletePosts(customStatement, postIdList);
    }

    /**
     * 点赞帖子
     *
     * @param customStatement 点赞用户信息
     * @param likesVO         被点赞的帖子信息
     * @return 是否点赞成功
     */
    @InjectToken
    @PostMapping(value = "/like")
    public CommonResult<Boolean> saveInfoLike(CustomStatement customStatement, @RequestBody LikesVO likesVO) {
        return postService.savePostLike(customStatement, likesVO);
    }

}
