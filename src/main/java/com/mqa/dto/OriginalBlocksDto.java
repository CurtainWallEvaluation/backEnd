package com.mqa.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Data
@Schema(description = "原图分片数据")
public class OriginalBlocksDto {
    @Schema(description = "stoneList")
    private List<StoneDto> stoneList;

    @Schema(description = "glassList")
    private List<GlassDto> glassList;
}
