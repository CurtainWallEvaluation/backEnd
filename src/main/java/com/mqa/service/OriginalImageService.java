package com.mqa.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mqa.dto.*;
import com.mqa.entity.GlassImage;
import com.mqa.entity.StoneImage;
import com.mqa.mapper.OriginalImageMapper;
import com.mqa.mapper.StoneImageMapper;
import com.mqa.mapper.GlassImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public StaticDataDto findOriImgStatic(int oriID){
        StaticDataDto resultDto=new StaticDataDto();
        int qualNum=0, uqualNum=0, stoneNum=0;
        double crackAreaPercent=0.0, stainPercent=0.0;
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("original_image_id",oriID);
        List<StoneImage> stoneList=stoneImageMapper.selectList(wrapper);
        List<GlassImage> glassList=glassImageMapper.selectList(wrapper);
        for(StoneImage stoneImage : stoneList){
            stoneNum++;
            if(stoneImage.getStatus()==0){
                qualNum++;
            }
            else{
                uqualNum++;
            }
            crackAreaPercent += stoneImage.getCrackAreaPercent();
            stainPercent += stoneImage.getStainProportion();
        }
        for(GlassImage glassImage : glassList){
            if(glassImage.getStatus()==0){
                qualNum++;
            }
            else{
                uqualNum++;
            }
        }
        if(stoneNum!=0) {
            stainPercent /= stoneNum;
            crackAreaPercent /= stoneNum;
        }
        resultDto.setQualNum(qualNum);
        resultDto.setUqualNum(uqualNum);
        resultDto.setStainPercent(stainPercent);
        resultDto.setCrackAreaPercent(crackAreaPercent);
        return resultDto;
    }

    public OriginalBlocksDto findImgCurtainInfo(int oriID){
        OriginalBlocksDto resultDto = new OriginalBlocksDto();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("original_image_id",oriID);
        List<StoneImage> stoneList=stoneImageMapper.selectList(wrapper);
        List<GlassImage> glassList=glassImageMapper.selectList(wrapper);
        List<StoneDto> stoneDtos = new ArrayList<StoneDto>();
        List<GlassDto> glassDtos = new ArrayList<GlassDto>();
        for(StoneImage stoneImage : stoneList){
            StoneDto stoneDto = new StoneDto(stoneImage);
            stoneDtos.add(stoneDto);
        }
        for(GlassImage glassImage : glassList){
            GlassDto glassDto=new GlassDto(glassImage);
            glassDtos.add(glassDto);
        }
        resultDto.setStontList(stoneDtos);
        resultDto.setGlassList(glassDtos);
        return resultDto;
    }
}
