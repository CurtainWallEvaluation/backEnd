package com.mqa.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mqa.dto.InputStoneDto;
import lombok.Data;

import java.util.Arrays;
import java.util.Collections;

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
    private int stainArea;

    @TableField("stain_color_differ")
    private Double stainColorDiffer;

    @TableField("crack_num")
    private Integer crackNum;

    @TableField("crack_length")
    private int crackLength;

    @TableField("crack_max_width")
    private Double crackMaxWidth;

    @TableField("crack_area")
    private int crackArea;

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

    public StoneImage(InputStoneDto inputStoneDto, int originalImageId) {
        this.originalImageId = originalImageId;
        this.stainArea = inputStoneDto.getStainsArea();
        this.stainProportion = inputStoneDto.getProportion();
        //通过污渍面积和污渍面积占比即可计算总面积
        int imageArea = (int) (stainArea / stainProportion);
        this.crackArea = inputStoneDto.getCrackArea();
        this.crackAreaPercent = (double) (crackArea) / imageArea;
        this.crackLength = inputStoneDto.getLength();
        this.crackNum = inputStoneDto.getMaxWidth().length;
        this.crackMaxWidth = 0.0;
        for (double width : inputStoneDto.getMaxWidth()) {
            if (width > this.crackMaxWidth) {
                this.crackMaxWidth = width;
            }
        }
        this.stoneImageUrl = inputStoneDto.getUrl();
        this.stainColorDiffer = inputStoneDto.getColorDiffer();
        this.lengthCaculated = (double) (crackLength) * crackLength / imageArea;
        this.widthCaculated = (double) (crackMaxWidth) * crackMaxWidth / imageArea;
    }
}
