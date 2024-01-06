package com.mqa.service;

import com.mqa.dto.GlassDto;
import com.mqa.dto.InputDataDto;
import com.mqa.dto.InputGlassDto;
import com.mqa.dto.InputStoneDto;
import com.mqa.entity.*;
import com.mqa.mapper.*;
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

    @Autowired
    public InputDataService(OriginalImageMapper originalImageMapper, StoneImageMapper stoneImageMapper, GlassImageMapper glassImageMapper, TaskMapper taskMapper, CrackWidthMapper crackWidthMapper) {
        this.originalImageMapper = originalImageMapper;
        this.stoneImageMapper = stoneImageMapper;
        this.glassImageMapper = glassImageMapper;
        this.taskMapper = taskMapper;
        this.crackWidthMapper = crackWidthMapper;
    }

    public boolean getInputData(List<InputDataDto> inputDataDtos){
        Task task=new Task();
        task.setTask(LocalDateTime.now(),0,0,inputDataDtos.size());
        taskMapper.insert(task);
        int taskID=task.getTaskID();
        for(InputDataDto inputDataDto : inputDataDtos){
            OriginalImage originalImage = new OriginalImage();
            originalImage.setOriginalImage(inputDataDto, taskID);
            originalImageMapper.insert(originalImage);
            int oriID=originalImage.getOriginalImgID();
            List<InputGlassDto> inputGlassDtos= inputDataDto.getGlassList();
            for(InputGlassDto inputGlassDto : inputGlassDtos){
                GlassImage glassImage=new GlassImage();
                glassImage.setGlassImage(inputGlassDto,oriID);
                glassImageMapper.insert(glassImage);
            }
            List<InputStoneDto> inputStoneDtos= inputDataDto.getStoneList();
            for(InputStoneDto inputStoneDto : inputStoneDtos){
                StoneImage stoneImage=new StoneImage();
                stoneImage.setStoneImage(inputStoneDto,oriID);
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
        }
        return true;
    }
}
