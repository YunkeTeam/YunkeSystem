package com.titos.shareplatform.controller;

import com.titos.info.global.CommonResult;
import com.titos.info.shareplatform.vo.ConditionVO;
import com.titos.info.shareplatform.vo.TagNameVO;
import com.titos.info.shareplatform.vo.TaskVO;
import com.titos.shareplatform.service.TaskService;
import com.titos.tool.annotions.InjectToken;
import com.titos.tool.token.CustomStatement;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName TaskController
 * @Description 任务Controller
 * @Author Kurihada
 * @Date 2022/4/12 20:24
 **/
@RestController
@RequestMapping("/task")
public class TaskController {

    @Resource
    private TaskService taskService;

    /**
     * 分页查询当前用户所有的任务
     *
     * @param customStatement 用户信息
     * @param pageNum         当前页
     * @param pageSize        每页的数量
     * @return 任务列表
     */
    @InjectToken
    @GetMapping("/list")
    public CommonResult<List<TaskVO>> listTask(
            CustomStatement customStatement,
            @RequestParam(defaultValue = "1", value = "pageNum") Long pageNum,
            @RequestParam(defaultValue = "10", value = "pageSize") Long pageSize) {
        return taskService.listTask(customStatement, pageNum, pageSize);
    }

    /**
     * 按属性分页查询当前用户的任务
     *
     * @param customStatement 用户信息
     * @param keywords        查询类别
     * @param pageNum         当前页
     * @param pageSize        每页的数量
     * @return 任务列表
     */
    @InjectToken
    @GetMapping("/condition")
    public CommonResult<List<TaskVO>> listTaskByCondition(
            CustomStatement customStatement,
            String keywords,
            @RequestParam(defaultValue = "1", value = "pageNum") Long pageNum,
            @RequestParam(defaultValue = "10", value = "pageSize") Long pageSize) {
        return taskService.listTaskByCondition(customStatement, keywords, pageNum, pageSize);
    }

    /**
     * 根据分类名查询任务
     *
     * @param customStatement 用户信息
     * @param tagNameVO       分类名
     * @return 任务列表
     */
    @InjectToken
    @GetMapping("/tags")
    public CommonResult<List<TaskVO>> listTaskByTagName(
            CustomStatement customStatement,
            @RequestBody TagNameVO tagNameVO,
            @RequestParam(defaultValue = "1", value = "pageNum") Long pageNum,
            @RequestParam(defaultValue = "10", value = "pageSize") Long pageSize) {
        return taskService.listTaskByTagName(customStatement, tagNameVO, pageNum, pageSize);
    }

}
