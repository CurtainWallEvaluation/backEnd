package com.mqa.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Data
@Schema(description = "原图的数据概览计算结果接口参数")
public class OriginalStaticDto {
    @Schema(description = "code")
    private int code;

    @Schema(description = "data")
    private List<StaticDataDto> data;

    @Schema(description = "msg")
    private String msg;
}
