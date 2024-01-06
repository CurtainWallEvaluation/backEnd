package com.mqa.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("task")
public class Task {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("date_time")
    private Date dateTime;

    @TableField("qual_num")
    private Integer qualNum;

    @TableField("unqual_num")
    private Integer unqualNum;
}
