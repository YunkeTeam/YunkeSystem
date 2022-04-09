package com.titos.shareplatform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.titos.info.global.CommonResult;
import com.titos.info.shareplatform.entity.Info;
import com.titos.info.shareplatform.vo.FilterInfoVO;
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
     * 按条件分页查询信息
     *
     * @param filterInfo 条件
     * @param pageNum    当前页
     * @param pageSize   每页的数量
     * @return 信息列表
     */
    CommonResult<List<InfoVO>> listInfo(FilterInfoVO filterInfo, Long pageNum, Long pageSize);

}
