package com.titos.shareplatform.controller;

import com.titos.info.global.CommonResult;
import com.titos.info.shareplatform.vo.*;
import com.titos.shareplatform.service.InfoService;
import com.titos.tool.annotions.InjectToken;
import com.titos.tool.token.CustomStatement;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName InfoController
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/4/9 0:25
 **/
@RestController
@RequestMapping("/info")
public class InfoController {

    @Resource
    private InfoService infoService;

    /**
     * 按条件分页查询信息
     *
     * @param filterInfo 条件
     * @param pageNum    当前页
     * @param pageSize   每页的数量
     * @return 信息列表
     */
    @GetMapping(value = "/list")
    public CommonResult<List<InfoVO>> listInfo(
            FilterInfoVO filterInfo,
            @RequestParam(defaultValue = "1", value = "pageNum") Long pageNum,
            @RequestParam(defaultValue = "10", value = "pageSize") Long pageSize) {
        return infoService.listInfo(filterInfo, pageNum, pageSize);
    }

    /**
     * 获取当前用户的信息
     *
     * @param customStatement 用户信息
     * @param pageNum         当前页
     * @param pageSize        每页的数量
     * @return 信息列表
     */
    @InjectToken
    @GetMapping(value = "/list/me")
    public CommonResult<List<MyInfoVO>> listMyInfo(
            CustomStatement customStatement,
            @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
            @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize) {
        return infoService.listMyInfo(customStatement, pageNum, pageSize);
    }

    /**
     * 发布信息
     *
     * @param customStatement 用户信息
     * @param addInfoVO       需要发布的信息
     * @return 是否发布成功
     */
    @InjectToken
    @PostMapping(value = "/add")
    public CommonResult<Boolean> addInfo(CustomStatement customStatement, @Valid @RequestBody AddInfoVO addInfoVO) {
        return infoService.addInfo(customStatement, addInfoVO);
    }

    /**
     * 更新信息状态
     *
     * @param customStatement 用户信息
     * @param updateInfoVO    更新的消息
     * @return 是否更新成功
     */
    @InjectToken
    @PostMapping(value = "/update")
    public CommonResult<Boolean> updateInfo(CustomStatement customStatement, @Valid @RequestBody UpdateInfoVO updateInfoVO) {
        return infoService.updateInfo(customStatement, updateInfoVO);
    }

    /**
     * 查询消息
     *
     * @param keywords 关键词
     * @param userId   用户Id
     * @return 消息列表
     */
    @GetMapping(value = "/search")
    public CommonResult<List<InfoVO>> searchInfo(String keywords, Integer userId) {
        return infoService.searchInfo(keywords, userId);
    }

    /**
     * 批量删除消息
     *
     * @param customStatement 用户消息
     * @param deleteVO        需要删除的消息的ID列表
     * @return 是否删除成功
     */
    @InjectToken
    @DeleteMapping(value = "/delete")
    public CommonResult<Boolean> deleteInfo(
            CustomStatement customStatement,
            @RequestBody DeleteVO deleteVO) {
        return infoService.deleteInfo(customStatement, deleteVO);
    }

}
