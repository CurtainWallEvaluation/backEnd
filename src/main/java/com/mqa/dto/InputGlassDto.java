package com.mqa.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class InputGlassDto {
    @Schema(description = "url")
    private String url;

    @Schema(description = "isImplosion")
    private int isImplosion;
}
