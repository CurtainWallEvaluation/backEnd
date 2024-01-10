package com.mqa.service;

import com.mqa.dto.InputDataDto;
import com.mqa.dto.InputGlassDto;
import com.mqa.dto.InputStoneDto;
import com.mqa.entity.*;
import com.mqa.mapper.*;
import com.mqa.utils.CaculateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InputDataService {
    private final OriginalImageMapper originalImageMapper;
    private final StoneImageMapper stoneImageMapper;
    private final GlassImageMapper glassImageMapper;
    private final TaskMapper taskMapper;
    private final CrackWidthMapper crackWidthMapper;

    private Integer qualNum = 0;
    private Integer unqualNum = 0;
    private Boolean GoodOrBad;
    private Double point;

    @Autowired
    public InputDataService(OriginalImageMapper originalImageMapper, StoneImageMapper stoneImageMapper, GlassImageMapper glassImageMapper, TaskMapper taskMapper, CrackWidthMapper crackWidthMapper) {
        this.originalImageMapper = originalImageMapper;
        this.stoneImageMapper = stoneImageMapper;
        this.glassImageMapper = glassImageMapper;
        this.taskMapper = taskMapper;
        this.crackWidthMapper = crackWidthMapper;
    }

    public int getInputData(List<InputDataDto> inputDataDtos){
        //每次任务开始前清空CaculateUtil中的数据
        if(CaculateUtil.stainProportion!=null) {
            CaculateUtil.stainProportion.clear();
            CaculateUtil.stainColorDiffer.clear();
        }
        if(CaculateUtil.crackNum!=null) {
            CaculateUtil.crackNum.clear();
            CaculateUtil.lengthCaculated.clear();
            CaculateUtil.widthCaculated.clear();
            CaculateUtil.crackAreaPercent.clear();
        }
        //插入石材图片的数据
        StoneImage tmpStoneImage=new StoneImage();
        for(InputDataDto inputDataDto : inputDataDtos){
            List<InputStoneDto> inputStoneDtos= inputDataDto.getStoneList();
            for(InputStoneDto inputStoneDto : inputStoneDtos){
                tmpStoneImage.setStoneImage(inputStoneDto,-1);
                //将每张图片的相关数据传递给CaculateUtil类进行计算
                CaculateUtil.stainProportion.add(tmpStoneImage.getStainProportion());
                CaculateUtil.stainColorDiffer.add(tmpStoneImage.getStainColorDiffer());
                CaculateUtil.crackNum.add(tmpStoneImage.getCrackNum());
                CaculateUtil.lengthCaculated.add(tmpStoneImage.getLengthCaculated());
                CaculateUtil.widthCaculated.add(tmpStoneImage.getWidthCaculated());
                CaculateUtil.crackAreaPercent.add(tmpStoneImage.getCrackAreaPercent());
            }
        }
        //所有数据插入完成后，计算权重
        CaculateUtil.caculateWeight();

        Task task=new Task();
        //插入本次任务，合格，不合格图片数量要在所有图片上传完后更新
        task.setTask(LocalDateTime.now(),0,0,inputDataDtos.size());
        taskMapper.insert(task);
        int taskID=task.getTaskID();

        for(InputDataDto inputDataDto : inputDataDtos){
            //处理每张原图对应数据前重置GoodOrBad标记
            this.GoodOrBad = true;

            OriginalImage originalImage = new OriginalImage();
            originalImage.setOriginalImage(inputDataDto, taskID);
            originalImageMapper.insert(originalImage);

            int oriID=originalImage.getOriginalImgID();
            //插入玻璃图片
            List<InputGlassDto> inputGlassDtos= inputDataDto.getGlassList();
            for(InputGlassDto inputGlassDto : inputGlassDtos){
                GlassImage glassImage=new GlassImage();
                glassImage.setGlassImage(inputGlassDto,oriID);
                glassImageMapper.insert(glassImage);

                if(glassImage.getIsImplosion() == 1){
                    this.GoodOrBad = false;
                }
            }
            //插入石头图片
            List<InputStoneDto> inputStoneDtos= inputDataDto.getStoneList();
            for(InputStoneDto inputStoneDto : inputStoneDtos){
                StoneImage stoneImage=new StoneImage();
                stoneImage.setStoneImage(inputStoneDto,oriID);
                //计算韧性得分
                this.point = CaculateUtil.caculatePoint(stoneImage);
                stoneImage.setPoint(this.point);
                //更新石材图片状态
                if(this.point < 33){
                    this.GoodOrBad = false;
                    stoneImage.setStatus(2);
                }
                else if(this.point < 66){
                    stoneImage.setStatus(1);
                }
                else{
                    stoneImage.setStatus(0);
                }
                stoneImageMapper.insert(stoneImage);
                int stontID=stoneImage.getId();
                if(inputStoneDto.getMaxWidth()!=null) {
                    for (double width : inputStoneDto.getMaxWidth()) {
                        CrackWidth crackWidth = new CrackWidth();
                        crackWidth.setCrackWidth(width, stontID);
                        crackWidthMapper.insert(crackWidth);
                    }
                }
            }
            //更新任务中的合格，不合格图片数量
            if(this.GoodOrBad){
                this.qualNum++;
                originalImage.setStatus(0);
                originalImageMapper.updateById(originalImage);
            }
            else{
                originalImage.setStatus(1);
                originalImageMapper.updateById(originalImage);
                this.unqualNum++;
            }
        }
        //更新任务中的合格，不合格图片数量
        task.setQualNum(this.qualNum);
        task.setUnqualNum(this.unqualNum);
        taskMapper.updateById(task);

        return taskID;
    }
}
