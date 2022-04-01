package com.titos.shareplatform.service;

import com.titos.info.global.CommonResult;
import com.titos.info.shareplatform.entity.SharePlatform;

import java.util.List;

/**
 * @ClassName ConversationService
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/3/30 21:51
 **/
public interface SharePlatformService {

    /**
     * 分页查询所有的帖子
     * @param pageNum 当前页
     * @param pageSize 每页的数量
     * @return
     */
    CommonResult<List<SharePlatform>> listSharePlatform(Integer pageNum, Integer pageSize);

}
