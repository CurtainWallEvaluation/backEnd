package com.mqa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mqa.entity.TestLogin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper extends BaseMapper<TestLogin> {
}
