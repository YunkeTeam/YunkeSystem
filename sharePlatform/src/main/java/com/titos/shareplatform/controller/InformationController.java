package com.titos.shareplatform.controller;

import com.titos.info.global.CommonResult;
import com.titos.info.shareplatform.vo.InfoVO;
import com.titos.shareplatform.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
     * 分页查询所有的信息
     *
     * @param pageNum  当前页
     * @param pageSize 每页的数量
     * @return 信息列表
     */
    @GetMapping(value = "/list")
    public CommonResult<List<InfoVO>> listInfo(
            @RequestParam(defaultValue = "1", value = "pageNum") Long pageNum,
            @RequestParam(defaultValue = "10", value = "pageSize") Long pageSize) {
        return infoService.listInfo(pageNum, pageSize);
    }

}
