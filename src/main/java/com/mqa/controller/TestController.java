package com.mqa.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mqa.dto.InputDataDto;
import com.mqa.dto.TestLoginDto;
import com.mqa.dto.RegisterDto;
import com.mqa.entity.Result;
import com.mqa.entity.TestLogin;
import com.mqa.properties.JwtProperties;
import com.mqa.service.TestService;
import com.mqa.service.RegisterService;
import com.mqa.utils.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/test")
@Tag(name = "测试接口")
public class TestController {

    private final TestService testService;
    private final RegisterService registerService;
    private final JwtProperties jwtProperties;

    private final InputDataController inputDataController;

    public TestController(TestService testService, RegisterService registerService, JwtProperties jwtProperties, InputDataController inputDataController) {
        this.testService = testService;
        this.registerService=registerService;
        this.jwtProperties = jwtProperties;
        this.inputDataController = inputDataController;
    }

    @GetMapping
    @Operation(description = "测试接口")
    public Result<String> hello() {
        log.info("test function");
        Result<String> result;
        try {
            // 指定本地文件路径，这里假设文件位于src/main/resources目录下
            String filePath = "./data.json";

            // 使用Spring的Resource加载文件
            Resource resource = new ClassPathResource(filePath);

            // 使用Jackson库进行JSON到Java对象的转换
            ObjectMapper objectMapper = new ObjectMapper();
            List<InputDataDto> inputDataList = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<InputDataDto>>() {});
            result=inputDataController.inputData(inputDataList);
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            return null;
        }
        return result;
    }

    /**
     * 测试登陆功能
     */
    @PostMapping("/login")
    @Operation(description = "测试登陆功能")
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

    /**
     * 注册功能
     */
    @PostMapping("/register")
    public Result<String> register(@RequestBody RegisterDto registerDto) {
        log.info("register function , params:{}",registerDto);

        //执行登陆，获取数据库中的用户信息
        boolean success = registerService.register(registerDto);
        if(success) {
            return Result.success();
        }
        else{
            return Result.error("用户名已存在");
        }
    }
}