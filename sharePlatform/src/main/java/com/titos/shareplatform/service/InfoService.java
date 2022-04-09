package com.titos.shareplatform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.titos.info.global.CommonResult;
import com.titos.info.shareplatform.entity.Info;
import com.titos.info.shareplatform.vo.*;
import com.titos.tool.token.CustomStatement;

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

    /**
     * 获取当前用户的信息
     *
     * @param customStatement 用户信息
     * @param pageNum         当前页
     * @param pageSize        每页的数量
     * @return 信息列表
     */
    CommonResult<List<MyInfoVO>> listMyInfo(CustomStatement customStatement, Integer pageNum, Integer pageSize);

    /**
     * 发布信息
     *
     * @param customStatement 用户信息
     * @param addInfoVO       需要发布的信息
     * @return 是否发布成功
     */
    CommonResult<Boolean> addInfo(CustomStatement customStatement, AddInfoVO addInfoVO);

    /**
     * 更新信息状态
     *
     * @param customStatement 用户信息
     * @param updateInfoVO    更新的消息
     * @return
     */
    CommonResult<Boolean> updateInfo(CustomStatement customStatement, UpdateInfoVO updateInfoVO);

    /**
     * 查询消息
     *
     * @param keywords 关键词
     * @return 消息列表
     */
    CommonResult<List<InfoVO>> searchInfo(String keywords);

}
