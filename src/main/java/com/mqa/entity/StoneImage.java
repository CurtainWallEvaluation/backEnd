package com.mqa.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mqa.dto.InputStoneDto;
import lombok.Data;

import java.util.Arrays;
import java.util.Collections;

@Data
@TableName(value="stone_image")
public class StoneImage {

    @TableId(value = "id",type = IdType.AUTO)
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

    public void setStoneImage(InputStoneDto inputStoneDto, int originalImageId) {
        this.originalImageId = originalImageId;
        this.crackArea = inputStoneDto.getCrackArea();
        this.crackLength = inputStoneDto.getLength();
        this.crackMaxWidth = 0.0;
        if(inputStoneDto.getMaxWidth()!=null){
            for (double width : inputStoneDto.getMaxWidth()) {
                if (width > this.crackMaxWidth) {
                    this.crackMaxWidth = width;
                }
            }
            this.crackNum = inputStoneDto.getMaxWidth().length;
        }
        else{
            this.crackNum = 0;
        }
        this.stainArea = inputStoneDto.getStainsArea();
        this.stainProportion = inputStoneDto.getProportion();
        //通过污渍面积和污渍面积占比即可计算总面积
        if(stainProportion==0.0){
            this.crackAreaPercent = -1.0;
            this.lengthCaculated = -1.0;
            this.widthCaculated = -1.0;
        }
        else{
            int imageArea = (int) (stainArea / stainProportion);
            this.crackAreaPercent = (double) (crackArea) / imageArea;
            this.lengthCaculated = (double) (crackLength) * crackLength / imageArea;
            this.widthCaculated = (double) (crackMaxWidth) * crackMaxWidth / imageArea;
        }
        this.stoneImageUrl = inputStoneDto.getUrl();
        this.stainColorDiffer = inputStoneDto.getColorDiffer();
    }
}
