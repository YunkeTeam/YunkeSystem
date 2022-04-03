package com.titos.shareplatform.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.titos.info.global.CommonResult;
import com.titos.info.global.enums.StatusEnum;
import com.titos.info.shareplatform.entity.SharePlatform;
import com.titos.shareplatform.dao.SharePlatformDao;
import com.titos.shareplatform.service.SharePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ConversationServiceImpl
 * @Description TODO
 * @Author Kurihada`
 * @Date 2022/3/30 21:51
 **/
@Service
public class SharePlatformServiceImpl implements SharePlatformService {

    @Autowired
    private SharePlatformDao sharePlatformDao;

    @Override
    public CommonResult<List<SharePlatform>> listSharePlatform(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SharePlatform> list = sharePlatformDao.listSharePlatform();
        PageInfo<SharePlatform> pageInfo = (PageInfo<SharePlatform>) list;

        return new CommonResult<List<SharePlatform>>(StatusEnum.SUCCESS.getCode(), (List<SharePlatform>) pageInfo);
    }

}
