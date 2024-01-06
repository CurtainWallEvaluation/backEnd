package com.mqa.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Data
@Schema(description = "获取原图分片数据接口参数")
public class PictureInfoDto {
    @Schema(description = "code")
    private int code;

    @Schema(description = "data")
    private List<OriginalBlocksDto> originalBlocksList;

    @Schema(description = "msg")
    private String msg;
}
