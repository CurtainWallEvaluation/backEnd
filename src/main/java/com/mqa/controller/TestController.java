package com.mqa.controller;

import com.mqa.dto.TestLoginDto;
import com.mqa.entity.Result;
import com.mqa.entity.TestLogin;
import com.mqa.properties.JwtProperties;
import com.mqa.service.TestService;
import com.mqa.utils.JwtUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/test")
@Api(tags = "测试接口")
public class TestController {

    private final TestService testService;
    private final JwtProperties jwtProperties;

    public TestController(TestService testService, JwtProperties jwtProperties) {
        this.testService = testService;
        this.jwtProperties = jwtProperties;
    }

    @GetMapping("/hello")
    public Result<String> hello() {
        log.info("test hello function");
        return Result.success("hello world");
    }

    /**
     * 测试登陆功能
     */
    @PostMapping("/login")
    public Result<String> login(@RequestBody TestLoginDto testLoginDto) {
        log.info("test login function , params:{}",testLoginDto);

        //执行登陆，获取数据库中的用户信息
        TestLogin testLogin = testService.login(testLoginDto);
        //登陆成功后，生成token
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", testLogin.getId());

        String token = JwtUtil.createJWT(
                jwtProperties.getSecretKey(),
                jwtProperties.getDuration(),
                claims);

        log.info("test login over");
        return Result.success(token);
    }
}
