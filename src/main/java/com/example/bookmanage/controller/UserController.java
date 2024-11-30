package com.example.bookmanage.controller;

import com.example.bookmanage.common.Result;
import com.example.bookmanage.pojo.dto.UserDTO;
import com.example.bookmanage.pojo.entity.User;
import com.example.bookmanage.service.UserService;
import com.example.bookmanage.utils.JwtUtils;
import com.example.bookmanage.utils.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;

    /**
     * 新用户注册
     * @param userDTO
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody UserDTO userDTO){
        User userByAccount = userService.findUserByAccount(userDTO.getAccount());
        if(userByAccount != null){
            return Result.error("用户已存在");
        }else{
            User user = User.builder()
                    .account(userDTO.getAccount())
                    .password(userDTO.getPassword())
                    .build();
            userService.insertUser(user);
            return Result.success();
        }
    }


    /**
     * 用户登录
     * @param userDTO
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO){
        log.info("账号:{}",userDTO.getAccount());
        log.info("密码:{}",userDTO.getPassword());

        User userByAccount = userService.findUserByAccount(userDTO.getAccount());

        Map<String,String> payload = new HashMap<>();
        payload.put("account",userDTO.getAccount());

        if (userByAccount == null){
            return Result.error("用户不存在");
        }
        if (!userByAccount.getPassword().equals(userDTO.getPassword())){
            return Result.error("密码错误");
        }else{
            userByAccount.setPassword("***");
            return Result.success(userByAccount).addData("token", JwtUtils.getToken(payload));
        }

    }


    /**
     * 更新密码
     * @param userDTO
     * @return
     */
    @PutMapping("/updatePwd")
    public Result updateUser(@RequestBody UserDTO userDTO){
        User user = userService.findUserByAccount(userDTO.getAccount());
        userService.updateUserByAccount(userDTO.getAccount(), user);
        return Result.success(user);
    }


    /**
     * 更新用户信息(账号)
     * @param userDTO
     * @return
     */
    @PutMapping("/updateSelfUserInfo")
    public Result updateSelfUserInfo(@RequestBody UserDTO userDTO){
        //获取当前进程中的userId
        String account = RequestContext.getAccount();

        User newUser = User.builder()
                .account(userDTO.getAccount())
                .password(userDTO.getPassword())
                .build();
        userService.updateUserByAccount(account,newUser);
        Map<String,String> payload = new HashMap<>();
        payload.put("account",newUser.getAccount());
        newUser.setPassword("***");
        return Result.success(newUser).addData("token", JwtUtils.getToken(payload));
    }





}
