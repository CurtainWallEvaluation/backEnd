package com.mqa.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import org.springframework.util.DigestUtils;

@Data
@TableName(value="user")
public class Register implements Serializable{
    private String username;
    private String password;
    public Register(String username,String password){
        this.username=username;
        this.password=DigestUtils.md5DigestAsHex(password.getBytes());
    }
}
