package com.mqa.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "原图的数据概览计算结果参数")
public class StaticDataDto {
    @Schema(description = "goodNum")
    private int goodNum;

    @Schema(description = "qualNum")
    private int qualNum;

    @Schema(description = "uqualNum")
    private int uqualNum;

    @Schema(description = "crackAreaPercent")
    private double crackAreaPercent;

    @Schema(description = "stainPercent")
    private double stainPercent;
}
