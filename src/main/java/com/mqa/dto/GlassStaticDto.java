package com.mqa.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "原图的玻璃内爆和无内爆的块数接口参数")
public class GlassStaticDto {
    @Schema(description = "data")
    private int[] data;
}
