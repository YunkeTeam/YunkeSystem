package com.titos.shareplatform.controller;

import com.titos.info.global.CommonResult;
import com.titos.info.shareplatform.vo.FilterInfoVO;
import com.titos.info.shareplatform.vo.InfoVO;
import com.titos.shareplatform.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName InformationController
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/4/9 0:25
 **/
@RestController
@RequestMapping("/info")
public class InformationController {

    @Autowired
    private InfoService infoService;

    /**
     * 按条件分页查询信息
     *
     * @param filterInfo 条件
     * @param pageNum    当前页
     * @param pageSize   每页的数量
     * @return 信息列表
     */
    @GetMapping(value = "/list/filter")
    public CommonResult<List<InfoVO>> listInfo(
            FilterInfoVO filterInfo,
            @RequestParam(defaultValue = "1", value = "pageNum") Long pageNum,
            @RequestParam(defaultValue = "10", value = "pageSize") Long pageSize) {
        return infoService.listInfo(filterInfo, pageNum, pageSize);
    }

}
