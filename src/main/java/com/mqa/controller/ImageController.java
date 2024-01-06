package com.mqa.controller;

import com.mqa.dto.GlassStaticDto;
import com.mqa.dto.OriginalBlocksDto;
import com.mqa.dto.StaticDataDto;
import com.mqa.entity.Result;
import com.mqa.service.OriginalImageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/image")
@Tag(name = "原图相关接口")
@RestController
@Slf4j
public class ImageController {
    private final OriginalImageService originalImageService;

    @Autowired
    public ImageController(OriginalImageService originalImageService) {
        this.originalImageService = originalImageService;
    }

    @GetMapping("/getImgCurtainInfo/{oriImgID}")
    public Result<OriginalBlocksDto> getImgCurtainInfo(@PathVariable("oriImgID") int oriImgID) {
        log.info("get image curtain info, oriImgID:{}", oriImgID);
        OriginalBlocksDto resultDto=originalImageService.findImgCurtainInfo(oriImgID);
        return Result.success(resultDto);
    }

    @GetMapping("/getOriImgStatic/{oriImgID}")
    public Result<StaticDataDto> getOriImgStatic(@PathVariable("oriImgID") Integer oriImgID) {
        log.info("get image static info, oriImgID:{}", oriImgID);
        StaticDataDto resultDto=originalImageService.findOriImgStatic(oriImgID);
        return Result.success(resultDto);
    }

    @GetMapping("/getOriImgGlassStatic/{oriImgID}")
    public Result<GlassStaticDto> getOriImgGlassStatic(@PathVariable("oriImgID") Integer oriImgID) {
        log.info("get image glass static info, oriImgID:{}", oriImgID);
        GlassStaticDto resultData=originalImageService.findGlassStatic(oriImgID);
        return Result.success(resultData);
    }
}
