package com.titos.shareplatform.service;

import com.titos.info.global.CommonResult;
import com.titos.info.shareplatform.vo.SharePlatformVO;
import com.titos.info.user.vo.TalentVO;

import java.util.List;

/**
 * @ClassName PostService
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/3/30 21:51
 **/
public interface PostService {

    /**
     * 分页查询所有的帖子
     * @param pageNum 当前页
     * @param pageSize 每页的数量
     * @return 结果
     */
    CommonResult<List<SharePlatformVO>> listSharePlatform(Integer pageNum, Integer pageSize);

    /**
     * 查询活跃达人
     *
     * @param pageNum  当前页
     * @param pageSize 每页的数量
     * @return 结果
     */
    CommonResult<List<TalentVO>> listTalent(Integer pageNum, Integer pageSize);
}