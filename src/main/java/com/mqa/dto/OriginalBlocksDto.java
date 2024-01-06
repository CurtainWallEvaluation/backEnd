package com.mqa.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Data
@Schema(description = "原图分片数据")
public class OriginalBlocksDto {
    private List<StoneDto> stontList;
    private List<GlassDto> glassList;
}
