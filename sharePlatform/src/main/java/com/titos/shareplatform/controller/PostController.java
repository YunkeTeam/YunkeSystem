package com.titos.shareplatform.controller;

import com.titos.info.global.CommonResult;
import com.titos.info.shareplatform.vo.SharePlatformVO;
import com.titos.info.user.vo.TalentVO;
import com.titos.shareplatform.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * @param pageNum  当前页
     * @param pageSize 每页的数量
     * @return 结果
     */
    @GetMapping("/list")
    public CommonResult<List<SharePlatformVO>> listConversation(
            @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
            @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize) {
        return postService.listSharePlatform(pageNum, pageSize);
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

}
