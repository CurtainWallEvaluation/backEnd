package com.mqa.dto;

import lombok.Data;

@Data
public class PageQueryDTO {
    private Integer taskID;
    private Integer currentPage;
    private Integer pageSize;
}
