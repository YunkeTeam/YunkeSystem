package com.titos.shareplatform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.titos.info.global.CommonResult;
import com.titos.info.global.constant.CommonConst;
import com.titos.info.global.enums.StatusEnum;
import com.titos.info.shareplatform.entity.Info;
import com.titos.info.shareplatform.vo.*;
import com.titos.shareplatform.dao.InfoDao;
import com.titos.shareplatform.service.InfoService;
import com.titos.tool.BeanCopyUtils.BeanCopyUtils;
import com.titos.tool.token.CustomStatement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName InfoServiceImpl
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/4/9 0:39
 **/
@Service
public class InfoServiceImpl extends ServiceImpl<InfoDao, Info> implements InfoService {

    @Resource
    private InfoDao infoDao;

    @Override
    public CommonResult<List<InfoVO>> listInfo(FilterInfoVO filterInfo, Long pageNum, Long pageSize) {
        List<InfoVO> infoList = infoDao.listInfo(filterInfo, pageNum - 1, pageSize);
        return CommonResult.success(infoList);
    }

    @Override
    public CommonResult<List<MyInfoVO>> listMyInfo(CustomStatement customStatement, Integer pageNum, Integer pageSize) {
        Page<Info> infoPage = infoDao.selectPage(new Page<>(pageNum, pageSize), new LambdaQueryWrapper<Info>()
                .select(Info::getId, Info::getType, Info::getStatus, Info::getCreateTime)
                .orderByDesc(Info::getCreateTime)
                .eq(Info::getUserId, customStatement.getId()));
        return CommonResult.success(BeanCopyUtils.copyList(infoPage.getRecords(), MyInfoVO.class));
    }

    @Override
    public CommonResult<Boolean> addInfo(CustomStatement customStatement, AddInfoVO addInfoVO) {
        Info info = BeanCopyUtils.copyObject(addInfoVO, Info.class);
        info.setUserId(customStatement.getId());
        infoDao.insert(info);
        return CommonResult.success(Boolean.TRUE);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Boolean> updateInfo(CustomStatement customStatement, UpdateInfoVO updateInfoVO) {
        if (!infoDao.selectById(updateInfoVO.getInfoId()).getUserId().equals(customStatement.getId())) {
            return CommonResult.fail(StatusEnum.FAIL_DEL_POST.getCode(), StatusEnum.FAIL_DEL_POST.getMsg());
        }
        Info info = Info.builder()
                .id(updateInfoVO.getInfoId())
                .status(updateInfoVO.getInfoStatus())
                .build();
        infoDao.updateById(info);
        return CommonResult.success(Boolean.TRUE);
    }

    @Override
    public CommonResult<List<InfoVO>> searchInfo(String keywords, Integer userId) {
        List<Info> infoList = infoDao.selectList(new LambdaQueryWrapper<Info>()
                .eq(userId != null, Info::getUserId, userId)
                .eq(Info::getIsViolation, 0)
                .and(i -> i.like(Info::getInfoTitle, keywords)));
        List<InfoVO> infoVOList = infoList.stream().map(item -> {
            String infoTitle = item.getInfoTitle().replaceAll(keywords,
                    CommonConst.PRE_TAG + keywords + CommonConst.POST_TAG);
            InfoVO infoVO = BeanCopyUtils.copyObject(item, InfoVO.class);
            infoVO.setInfoTitle(infoTitle);
            return infoVO;
        }).collect(Collectors.toList());
        return CommonResult.success(infoVOList);
    }
}
