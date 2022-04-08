package com.titos.shareplatform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.titos.info.global.CommonResult;
import com.titos.info.shareplatform.entity.Info;
import com.titos.info.shareplatform.vo.InfoVO;

import java.util.List;

/**
 * @ClassName InfoService
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/4/9 0:38
 **/
public interface InfoService extends IService<Info> {
    /**
     * 分页查询所有的信息
     *
     * @param pageNum  当前页
     * @param pageSize 每页的数量
     * @return 信息列表
     */
    CommonResult<List<InfoVO>> listInfo(Long pageNum, Long pageSize);
}
