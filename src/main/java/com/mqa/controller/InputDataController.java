package com.mqa.controller;

import com.mqa.dto.InputDataDto;
import com.mqa.dto.TestLoginDto;
import com.mqa.entity.Result;
import com.mqa.service.InputDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/input")
@Tag(name = "上传外部数据接口")
@RestController
@Slf4j
public class InputDataController {
    private final InputDataService inputDataService;

    @Autowired
    public InputDataController(InputDataService inputDataService) {
        this.inputDataService = inputDataService;
    }

    @PostMapping
    public Result<String> login(@RequestBody List<InputDataDto> inputDataDtos) {
        log.info("Receive an input");
        if(inputDataService.getInputData(inputDataDtos)) {
            return Result.success("Data uploaded successfully!");
        }
        else{
            return Result.error("Data input failed!");
        }
    }

}
