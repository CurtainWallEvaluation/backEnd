package com.mqa.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mqa.dto.GlassStaticDto;
import com.mqa.mapper.OriginalImageMapper;
import com.mqa.mapper.StoneImageMapper;
import com.mqa.mapper.GlassImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OriginalImageService {
    private final OriginalImageMapper originalImageMapper;
    private final StoneImageMapper stoneImageMapper;
    private final GlassImageMapper glassImageMapper;

    @Autowired
    public OriginalImageService(OriginalImageMapper originalImageMapper, StoneImageMapper stoneImageMapper, GlassImageMapper glassImageMapper) {
        this.originalImageMapper = originalImageMapper;
        this.stoneImageMapper = stoneImageMapper;
        this.glassImageMapper = glassImageMapper;
    }

    public GlassStaticDto findGlassStatic(int oriID) {
        int[] returnList=new int[2];
        QueryWrapper wrapper1 = new QueryWrapper();
        wrapper1.eq("original_image_id",oriID);
        wrapper1.eq("is_implosion",1);
        returnList[0]= Math.toIntExact(glassImageMapper.selectCount(wrapper1));
        QueryWrapper wrapper2 = new QueryWrapper();
        wrapper2.eq("original_image_id",oriID);
        wrapper2.eq("is_implosion",0);
        returnList[1]= Math.toIntExact(glassImageMapper.selectCount(wrapper2));
        GlassStaticDto returnData=new GlassStaticDto();
        returnData.setData(returnList);
        return returnData;
    }
}
