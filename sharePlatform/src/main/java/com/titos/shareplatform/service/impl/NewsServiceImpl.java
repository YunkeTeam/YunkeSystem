package com.titos.shareplatform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.titos.info.global.CommonResult;
import com.titos.info.global.constant.CommonConst;
import com.titos.info.shareplatform.entity.News;
import com.titos.info.shareplatform.vo.ConditionVO;
import com.titos.info.shareplatform.vo.NewsDetailVO;
import com.titos.info.shareplatform.vo.NewsVO;
import com.titos.shareplatform.dao.NewsDao;
import com.titos.shareplatform.service.NewsService;
import com.titos.tool.BeanCopyUtils.BeanCopyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName NewsServiceImpl
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/4/9 21:50
 **/
@Service
public class NewsServiceImpl extends ServiceImpl<NewsDao, News> implements NewsService {

    @Resource
    private NewsDao newsDao;

    @Override
    public CommonResult<List<NewsVO>> listNews(ConditionVO conditionVO, Long pageNum, Long pageSize) {
        Page<News> newsPage = newsDao.selectPage(new Page<>(pageNum, pageSize), new LambdaQueryWrapper<News>()
                .select(News::getId, News::getNewsCover, News::getNewsTitle, News::getNewsContent)
                .gt(conditionVO.getStartTime() != null, News::getCreateTime, conditionVO.getStartTime())
                .le(conditionVO.getEndTime() != null, News::getCreateTime, conditionVO.getEndTime())
                .orderByDesc(News::getCreateTime));
        List<NewsVO> newsVOList = newsPage.getRecords().stream().map(
                item -> NewsVO.builder()
                        .id(item.getId())
                        .newsCover(item.getNewsCover())
                        .newsTitle(StringUtils.substring(item.getNewsTitle(), 0, 10))
                        .newsContent(StringUtils.substring(item.getNewsContent(), 0, 50))
                        .build()).collect(Collectors.toList());
        return CommonResult.success(newsVOList);
    }

    @Override
    public CommonResult<NewsDetailVO> listNewsById(Integer newsId) {
        return CommonResult.success(newsDao.listNewsById(newsId));
    }

    @Override
    public CommonResult<List<NewsVO>> searchNews(String keywords) {
        List<News> newsList = newsDao.selectList(new LambdaQueryWrapper<News>()
                .like(News::getNewsTitle, keywords)
                .or()
                .like(News::getNewsContent, keywords));
        List<NewsVO> newsVOList = newsList.stream().map(item -> {
            String newsContent = item.getNewsContent();
            int index = item.getNewsContent().indexOf(keywords);
            if (index != -1) {
                int preIndex = index > 25 ? index - 25 : 0;
                String preText = StringUtils.substring(item.getNewsContent(), preIndex, index);

                int lastIndex = index + keywords.length();
                int postLength = item.getNewsContent().length() - lastIndex;
                int postIndex = postLength > 50 ? lastIndex + 50 : lastIndex + postLength;
                String postText = StringUtils.substring(item.getNewsContent(), index, postIndex);

                newsContent = (preText + postText).replaceAll(keywords,
                        CommonConst.PRE_TAG + keywords + CommonConst.POST_TAG);
            }
            String newsTitle = item.getNewsTitle().replaceAll(keywords,
                    CommonConst.PRE_TAG + keywords + CommonConst.POST_TAG);
            return NewsVO.builder()
                    .id(item.getId())
                    .newsCover(item.getNewsCover())
                    .newsTitle(newsTitle)
                    .newsContent(newsContent)
                    .build();
        }).collect(Collectors.toList());
        return CommonResult.success(newsVOList);
    }
}
