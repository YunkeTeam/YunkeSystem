package com.titos.shareplatform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.titos.info.global.CommonResult;
import com.titos.info.shareplatform.entity.Info;
import com.titos.info.shareplatform.vo.InfoVO;
import com.titos.shareplatform.dao.InfoDao;
import com.titos.shareplatform.service.InfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    public CommonResult<List<InfoVO>> listInfo(Long pageNum, Long pageSize) {
        List<InfoVO> infoList = infoDao.listInfo(pageNum-1, pageSize);
        return CommonResult.success(infoList);
    }
}
