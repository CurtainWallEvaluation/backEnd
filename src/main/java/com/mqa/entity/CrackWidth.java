package com.mqa.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value="crack_width")
public class CrackWidth {

    @TableId("id")
    private Integer id;

    @TableField("stone_image_id")
    private Integer stoneImageId;

    @TableField("width")
    private Double width;
}
