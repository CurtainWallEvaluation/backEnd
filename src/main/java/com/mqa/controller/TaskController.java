package com.mqa.controller;

import com.mqa.dto.PageQueryDTO;
import com.mqa.entity.OriginalImage;
import com.mqa.entity.Result;
import com.mqa.entity.Task;
import com.mqa.service.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/task")
@Tag(name = "任务相关接口")
@RestController
@Slf4j
@CrossOrigin
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping("/getTaskInfo/{taskID}")
    public Result<Task> getTaskInfo(@PathVariable("taskID") Integer taskID) {
        log.info("get task info, taskID:{}", taskID);

        Task task = taskService.getTaskInfo(taskID);

        if(task == null) {
            log.error("get task info failed, taskID:{}\n", taskID);
            return Result.error("get task info failed");
        }
        log.info("get task info success\n");

        return Result.success(task);
    }

    @GetMapping("/getImgByPage")
    public Result<List<OriginalImage>> getImgByPage(@RequestBody PageQueryDTO pageQueryDTO) {
        log.info("get img by page, params:{}", pageQueryDTO);

        List<OriginalImage> originalImageList = taskService.getImgByPage(pageQueryDTO);
        if(originalImageList == null) {
            log.error("get img by page failed\n");
            return Result.error("get img by page failed");
        }
        log.info("get img by page success\n");

        return Result.success(originalImageList);
    }

    @GetMapping("/getErrorImg/{taskID}")
    public Result<List<OriginalImage>> getErrorImg(@PathVariable("taskID") Integer taskID) {
        log.info("get error img, taskID:{}", taskID);

        List<OriginalImage> originalImageList = taskService.getErrorImg(taskID);
        if(originalImageList == null) {
            log.error("get error img failed, taskID:{}\n", taskID);
            return Result.error("get error img failed");
        }

        log.info("get error img success\n");

        return Result.success(originalImageList);
    }

    @GetMapping("/getAllTask")
    public Result<List<Task>> getAllTask() {
        log.info("get all task");

        List<Task> taskList = taskService.getAllTask();
        if(taskList == null) {
            log.error("get all task failed\n");
            return Result.error("get all task failed");
        }

        log.info("get all task success\n");
        return Result.success(taskList);
    }

}
