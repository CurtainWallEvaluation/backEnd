package com.mqa.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value="stone_image")
public class StoneImage {

    @TableField("id")
    private Integer id;

    @TableField("stone_image_url")
    private String stoneImageUrl;

    @TableField("original_image_id")
    private Integer originalImageId;

    @TableField("stain_area")
    private Double stainArea;

    @TableField("stain_color_differ")
    private Double stainColorDiffer;

    @TableField("crack_num")
    private Integer crackNum;

    @TableField("crack_length")
    private Double crackLength;

    @TableField("crack_max_width")
    private Double crackMaxWidth;

    @TableField("crack_area")
    private Double crackArea;

    @TableField("point")
    private Double point;

    @TableField("stain_proportion")
    private Double stainProportion;

    @TableField("crack_area_percent")
    private Double crackAreaPercent;

    @TableField("length_caculated")
    private Double lengthCaculated;

    @TableField("width_caculated")
    private Double widthCaculated;

    @TableField("status")
    private Integer status;
}
