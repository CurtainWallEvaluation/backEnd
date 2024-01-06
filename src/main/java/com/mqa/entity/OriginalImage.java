package com.mqa.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value="original_image")
public class OriginalImage {

    @TableId(value = "id")
    private Integer id;

    @TableField("image_url")
    private String imageUrl;

    @TableField("task_id")
    private Integer taskId;

    @TableField("glass_num")
    private Integer glassNum;

    @TableField("stone_num")
    private Integer stoneNum;

    @TableField("status")
    private Integer status;
}
