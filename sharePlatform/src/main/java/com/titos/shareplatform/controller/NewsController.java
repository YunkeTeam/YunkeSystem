package com.titos.shareplatform.controller;

import com.titos.info.global.CommonResult;
import com.titos.info.shareplatform.vo.ConditionVO;
import com.titos.info.shareplatform.vo.NewsDetailVO;
import com.titos.info.shareplatform.vo.NewsVO;
import com.titos.shareplatform.service.NewsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName NewsController
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/4/9 17:46
 **/
@RestController
@RequestMapping("/news")
public class NewsController {

    @Resource
    private NewsService newsService;

    /**
     * 按条件查询新闻
     *
     * @param conditionVO 查询条件
     * @param pageNum     当前页
     * @param pageSize    每页的数量
     * @return 新闻列表
     */
    @GetMapping(value = "/list")
    public CommonResult<List<NewsVO>> listNews(
            ConditionVO conditionVO,
            @RequestParam(defaultValue = "1", value = "pageNum") Long pageNum,
            @RequestParam(defaultValue = "10", value = "pageSize") Long pageSize) {
        return newsService.listNews(conditionVO, pageNum, pageSize);
    }

    /**
     * 查询新闻详情
     *
     * @param newsId 新闻主键ID
     * @return 新闻详情
     */
    @GetMapping(value = "/list/{id}")
    public CommonResult<NewsDetailVO> listNewsById(@PathVariable("id") Integer newsId) {
        return newsService.listNewsById(newsId);
    }

    /**
     * 搜索新闻
     *
     * @param keywords 关键词
     * @return 新闻列表
     */
    @GetMapping(value = "/search")
    public CommonResult<List<NewsVO>> searchNews(String keywords) {
        return newsService.searchNews(keywords);
    }

}