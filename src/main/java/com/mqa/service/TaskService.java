package com.mqa.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mqa.dto.PageQueryDTO;
import com.mqa.entity.OriginalImage;
import com.mqa.entity.Task;
import com.mqa.mapper.OriginalImageMapper;
import com.mqa.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskMapper taskMapper;
    private final OriginalImageMapper originalImageMapper;

    @Autowired
    public TaskService(TaskMapper taskMapper, OriginalImageMapper originalImageMapper) {
        this.taskMapper = taskMapper;
        this.originalImageMapper = originalImageMapper;
    }


    public List<Task> getAllTask() {
        return taskMapper.selectList(null);
    }

    public Task getTaskInfo(Integer taskID) {
        return taskMapper.selectById(taskID);
    }

    public List<OriginalImage> getErrorImg(Integer taskID) {
        LambdaQueryWrapper<OriginalImage> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OriginalImage::getTaskID, taskID);
        queryWrapper.eq(OriginalImage::getStatus, 0);
        return originalImageMapper.selectList(queryWrapper);
    }

    public List<OriginalImage> getImgByPage(PageQueryDTO pageQueryDTO) {
        Page<OriginalImage> page = new Page<>(pageQueryDTO.getCurrentPage(), pageQueryDTO.getPageSize());
        LambdaQueryWrapper<OriginalImage> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(OriginalImage::getTaskID, pageQueryDTO.getTaskID());
        page = originalImageMapper.selectPage(page, lambdaQueryWrapper);
        return page.getRecords();
    }
}
