package com.titos.shareplatform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.titos.info.global.CommonResult;
import com.titos.info.shareplatform.entity.Task;
import com.titos.info.shareplatform.enums.TaskAttributes;
import com.titos.info.shareplatform.vo.TaskVO;
import com.titos.shareplatform.dao.TagDao;
import com.titos.shareplatform.dao.TaskDao;
import com.titos.shareplatform.service.TaskService;
import com.titos.tool.BeanCopyUtils.BeanCopyUtils;
import com.titos.tool.token.CustomStatement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName TaskServiceImpl
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/4/12 23:02
 **/
@Service
@Slf4j
public class TaskServiceImpl extends ServiceImpl<TaskDao, Task> implements TaskService {

    @Resource
    private TaskDao taskDao;

    @Resource
    private TagDao tagDao;

    @Override
    public CommonResult<List<TaskVO>> listTask(CustomStatement customStatement, Long pageNum, Long pageSize) {
        Page<Task> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Task> queryWrapper = new LambdaQueryWrapper<Task>()
                .select(Task::getId, Task::getTaskTitle, Task::getTaskDesc,
                        Task::getIsImportant, Task::getIsStarred, Task::getIsTrashed)
                .orderByDesc(Task::getCreateTime);
        Page<Task> taskPage = taskDao.selectPage(page, queryWrapper);
        List<TaskVO> taskVoList = BeanCopyUtils.copyList(taskPage.getRecords(), TaskVO.class);
        taskVoList.forEach(item -> {
            item.setTagNameList(tagDao.listTagNameByTaskId(item.getId()));
        });
        return CommonResult.success(taskVoList);
    }

    @Override
    public CommonResult<List<TaskVO>> listTaskByTagName(CustomStatement customStatement, String tagName,
                                                        Long pageNum, Long pageSize) {
        List<TaskVO> taskVoList = taskDao.listTaskByTagName(customStatement.getId(), tagName,
                (pageNum - 1) * pageSize, pageSize);
        taskVoList.forEach(item -> {
            item.setTagNameList(tagDao.listTagNameByTaskId(item.getId()));
        });
        return CommonResult.success(taskVoList);
    }

    @Override
    public CommonResult<List<TaskVO>> listTaskByCondition(CustomStatement customStatement, String keywords,
                                                          Long pageNum, Long pageSize) {
        Page<Task> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Task> queryWrapper = new LambdaQueryWrapper<Task>()
                .select(Task::getId, Task::getTaskTitle, Task::getTaskDesc,
                        Task::getIsImportant, Task::getIsStarred, Task::getIsTrashed)
                .eq(keywords.equals(TaskAttributes.IMPORTANT.getAttributes()), Task::getIsImportant, 1)
                .eq(keywords.equals(TaskAttributes.STARRED.getAttributes()), Task::getIsStarred, 1)
                .eq(keywords.equals(TaskAttributes.DONE.getAttributes()), Task::getIsDone, 1)
                .eq(keywords.equals(TaskAttributes.TRASHED.getAttributes()), Task::getIsTrashed, 1)
                .orderByDesc(Task::getCreateTime);
        List<TaskVO> taskVoList = BeanCopyUtils.copyList(taskDao.selectPage(page, queryWrapper).getRecords(), TaskVO.class);
        taskVoList.forEach(item -> {
            item.setTagNameList(tagDao.listTagNameByTaskId(item.getId()));
        });
        return CommonResult.success(taskVoList);
    }


}
