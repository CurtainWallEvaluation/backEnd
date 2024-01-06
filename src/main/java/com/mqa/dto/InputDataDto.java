package com.mqa.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "上传外部数据的接口参数")
public class InputDataDto {
    @Schema(description = "originUrl")
    private String originUrl;

    @Schema(description = "glassList")
    private List<InputGlassDto> glassList;

    @Schema(description = "stoneList")
    private List<InputStoneDto> stoneList;
}
