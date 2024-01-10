package com.mqa.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class InputStoneDto {
    @Schema(description = "url")
    private String url;

    @Schema(description = "imgArea")
    private int imgArea;

    @Schema(description = "crackArea")
    private int crackArea;

    @Schema(description = "length")
    private int length;

    @Schema(description = "width")
    private double width;

    @Schema(description = "maxWidth")
    private double[] maxWidth;

    @Schema(description = "stainsArea")
    private int stainsArea;

    @Schema(description = "proportion")
    private double proportion;

    @Schema(description = "lengthCaculate")
    private double lengthCaculate;

    @Schema(description = "colorDiffer")
    private double colorDiffer;
}
