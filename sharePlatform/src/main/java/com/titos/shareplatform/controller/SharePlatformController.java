package com.titos.shareplatform.controller;

import com.titos.info.global.CommonResult;
import com.titos.info.shareplatform.entity.SharePlatform;
import com.titos.shareplatform.service.SharePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName SharePlatformController
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/3/30 21:45
 **/
@RestController
@RequestMapping("/shareplatform")
public class SharePlatformController {

    @Autowired
    private SharePlatformService sharePlatformService;

    /**
     * 分页查询所有的帖子
     * @param pageNum 当前页
     * @param pageSize 每页的数量
     * @return
     */
    @GetMapping("/list")
    public CommonResult<List<SharePlatform>> listConversation(
            @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
            @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize) {
        return sharePlatformService.listSharePlatform(pageNum, pageSize);
    }

}
