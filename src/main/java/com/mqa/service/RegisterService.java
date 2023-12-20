package com.mqa.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mqa.dto.RegisterDto;
import com.mqa.entity.Register;
import com.mqa.entity.TestLogin;
import com.mqa.exception.MyException;
import com.mqa.mapper.RegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class RegisterService {
    private final RegisterMapper registerMapper;

    @Autowired
    public RegisterService(RegisterMapper registerMapper) {
        this.registerMapper = registerMapper;
    }

    public boolean register(RegisterDto registerDto) {
        String username = registerDto.getUsername();
        String password = registerDto.getPassword();

        //查询数据库信息
        QueryWrapper<Register> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        Register register = registerMapper.selectOne(queryWrapper);

        if (register != null) {
            return false;
        }
        Register newRegister=new Register(username,password);
        registerMapper.insert(newRegister);
        return true;
    }
}
