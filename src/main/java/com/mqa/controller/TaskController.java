package com.mqa.controller;

import com.mqa.entity.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/task")
@Tag(name = "任务相关接口")
@RestController
@Slf4j
public class TaskController {

    @GetMapping("/getTaskInfo/{taskID}")
    public Result<String> getTaskInfo(@PathVariable("taskID") String taskID) {
        log.info("get task info, taskID:{}", taskID);

        return Result.success("get task info success");
    }


}
