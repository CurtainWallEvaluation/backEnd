package com.mqa.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value="crack_width")
public class CrackWidth {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("stone_image_id")
    private Integer stoneImageId;

    @TableField("width")
    private Double width;

    public void setCrackWidth(Double width,Integer stoneImageId) {
        this.stoneImageId = stoneImageId;
        this.width = width;
    }
}
