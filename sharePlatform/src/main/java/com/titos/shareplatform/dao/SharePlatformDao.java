package com.titos.shareplatform.dao;

import com.titos.info.shareplatform.entity.SharePlatform;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName SharePlatformDao
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/3/31 23:10
 **/
@Repository
public interface SharePlatformDao {

    /**
     * 查询全部的帖子
     *
     * @return
     */
    List<SharePlatform> listSharePlatform();

}
