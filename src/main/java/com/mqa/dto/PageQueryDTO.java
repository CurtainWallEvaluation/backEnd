package com.mqa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageQueryDTO {
    private Integer taskID;
    private Integer currentPage;
    private Integer pageSize;
}
