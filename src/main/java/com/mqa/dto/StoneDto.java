package com.mqa.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "石材幕墙接口参数")
public class StoneDto {
    @Schema(description = "imageUrl")
    private String imageUrl;

    @Schema(description = "point")
    private double point;

    @Schema(description = "crackAreaProportion")
    private double crackAreaProportion;

    @Schema(description = "stainAreaProportion")
    private double stainAreaProportion;

    @Schema(description = "stainColorDiffer")
    private double stainColorDiffer;

    @Schema(description = "crackNum")
    private int crackNum;

    @Schema(description = "status")
    private int status;

    @Schema(description = "lengthCaculate")
    private double lengthCaculate;

    @Schema(description = "widthCaculate")
    private int widthCaculate;
}