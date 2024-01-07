package com.mqa.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mqa.dto.InputDataDto;
import lombok.Data;

@Data
@TableName(value="original_image")
public class OriginalImage {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer originalImgID;

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

    public void setOriginalImage(InputDataDto inputDataDto, int taskId){

        this.imageUrl=inputDataDto.getOriginUrl();
        this.taskId=taskId;
        this.glassNum=inputDataDto.getGlassList().size();
        this.stoneNum=inputDataDto.getStoneList().size();
    }

}
