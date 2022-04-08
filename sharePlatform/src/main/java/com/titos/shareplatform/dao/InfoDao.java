package com.titos.shareplatform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.titos.info.shareplatform.entity.Info;
import com.titos.info.shareplatform.vo.InfoVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName InfoDao
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/4/9 0:39
 **/
@Repository
public interface InfoDao extends BaseMapper<Info> {

    /**
     * 分页查询所有的信息
     *
     * @param pageNum  当前页
     * @param pageSize 每页的数量
     * @return 信息列表
     */
    List<InfoVO> listInfo(Long pageNum, Long pageSize);

}
