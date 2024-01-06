package com.mqa.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mqa.dto.InputGlassDto;
import lombok.Data;

@Data
@TableName(value = "glass_image")
public class GlassImage {

    @TableId(value = "id")
    private Integer id;

    @TableField(value = "glass_image_url")
    private String glassImageUrl;

    @TableField(value = "original_image_id")
    private Integer originalImageId;

    @TableField("is_implosion")
    private Integer isImplosion;

    @TableField("status")
    private Integer status;

    public GlassImage(InputGlassDto inputGlassDto, int originalImageId){
        this.originalImageId=originalImageId;
        this.glassImageUrl=inputGlassDto.getUrl();
        this.isImplosion=inputGlassDto.getIsImplosion();
        if(this.isImplosion==0){
            this.status=1;
        }
        else{
            this.status=0;
        }
    }
}
