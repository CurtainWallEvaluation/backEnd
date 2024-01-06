package com.mqa.service;

import com.mqa.dto.InputDataDto;
import com.mqa.mapper.GlassImageMapper;
import com.mqa.mapper.OriginalImageMapper;
import com.mqa.mapper.StoneImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InputDataService {
    private final OriginalImageMapper originalImageMapper;
    private final StoneImageMapper stoneImageMapper;
    private final GlassImageMapper glassImageMapper;

    @Autowired
    public InputDataService(OriginalImageMapper originalImageMapper, StoneImageMapper stoneImageMapper, GlassImageMapper glassImageMapper) {
        this.originalImageMapper = originalImageMapper;
        this.stoneImageMapper = stoneImageMapper;
        this.glassImageMapper = glassImageMapper;
    }

    public boolean getInputData(List<InputDataDto> inputDataDtos){
        return true;
    }
}
