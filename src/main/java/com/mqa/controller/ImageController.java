package com.mqa.controller;

import com.mqa.dto.GlassStaticDto;
import com.mqa.entity.Result;
import com.mqa.service.OriginalImageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/image")
@Tag(name = "原图相关接口")
@RestController
@Slf4j
public class ImageController {
    private final OriginalImageService originalImageService;

    public ImageController(OriginalImageService originalImageService) {
        this.originalImageService = originalImageService;
    }

    @GetMapping("/getImgCurtainInfo/{oriImgID}")
    public Result<String> getImgCurtainInfo(@PathVariable("oriImgID") String oriImgID) {
        log.info("get image curtain info, oriImgID:{}", oriImgID);

        return Result.success("get image curtain info success");
    }

    @GetMapping("/getOriImgStatic/{oriImgID}")
    public Result<String> getOriImgStatic(@PathVariable("oriImgID") String oriImgID) {
        log.info("get image static info, oriImgID:{}", oriImgID);

        return Result.success("get image curtain info success");
    }

    @GetMapping("/getOriImgGlassStatic/{oriImgID}")
    public Result<String> getOriImgGlassStatic(@PathVariable("oriImgID") String oriImgID) {
        log.info("get image glass static info, oriImgID:{}", oriImgID);
        GlassStaticDto resultData=originalImageService.findGlassStatic(Integer.parseInt(oriImgID));
        return Result.success(resultData.toString());
    }
}
