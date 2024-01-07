package com.mqa.dto;

import com.mqa.entity.GlassImage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "玻璃幕墙接口参数")
public class GlassDto {
    @Schema(description = "imageUrl")
    private String imageUrl;

    @Schema(description = "status")
    private int status;

    @Schema(description = "imageUrl")
    private int isImplosion;

    public GlassDto(GlassImage glassImage){
        this.imageUrl=glassImage.getGlassImageUrl();
        this.isImplosion=glassImage.getIsImplosion();
        this.status=glassImage.getStatus();
    }
}
