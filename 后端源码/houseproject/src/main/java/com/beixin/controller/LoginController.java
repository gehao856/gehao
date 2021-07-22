package com.beixin.controller;

import com.beixin.model.AdminInfo;
import com.beixin.model.Customer;
import com.beixin.model.User;
import com.beixin.service.ICustomerService;
import com.beixin.service.IUserService;
import com.beixin.util.JwtUtils2;
import com.beixin.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private IUserService userService;
    @Autowired
    private ICustomerService customerService;


    @RequestMapping("/login")
    public Object login(String username, String password, String type){
        AdminInfo info=new AdminInfo();
        Map maps=new HashMap<>();
        String password1 = MD5Utils.md5(password);
        if(type.equals("1")){
            //管理员
            User user=userService.queryUserByNameAndPwd(username,password1);
            if(user!=null){//存在
                info.setId(user.getId());
                info.setUsername(user.getUsername());
                info.setType("1");
            }
        }else{
            //租客
            Customer customer=customerService.queryUserByNameAndPwd(username,password1);
            if(customer!=null){
                info.setId(customer.getId());
                info.setUsername(customer.getCustname());
                info.setType("0");
            }
        }
        //生成token
        String token= JwtUtils2.geneJsonWebToken(info);
        Map map=new HashMap<>();
        map.put("userId",info.getId());
        map.put("username",info.getUsername());
        map.put("type",info.getType());
        map.put("token",token);
        return map;
    }
}
