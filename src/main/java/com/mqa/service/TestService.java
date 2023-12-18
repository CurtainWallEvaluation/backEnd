package com.mqa.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mqa.dto.TestLoginDto;
import com.mqa.entity.TestLogin;
import com.mqa.exception.MyException;
import com.mqa.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class TestService {
    private final TestMapper testMapper;

    @Autowired
    public TestService(TestMapper testMapper) {
        this.testMapper = testMapper;
    }


    public TestLogin login(TestLoginDto testLoginDto) {
        String username = testLoginDto.getUsername();
        String password = testLoginDto.getPassword();

        //查询数据库信息
        QueryWrapper<TestLogin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        TestLogin testLogin = testMapper.selectOne(queryWrapper);

        if (testLogin == null) {
            throw new MyException("用户不存在");
        }

        //比对密码
        String inputPwd = DigestUtils.md5DigestAsHex(password.getBytes());

        if (!inputPwd.equals(testLogin.getPassword())) {
            //密码错误
            throw new MyException("密码错误");
        }

        return testLogin;
    }
}
