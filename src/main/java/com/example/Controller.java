package com.example;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenh on 2017/3/24.
 */
@RestController
public class Controller {

    private HashMap<Integer, User> users = new HashMap<Integer, User>();

    @ApiOperation(value = "测试post请求", notes = "注意事项")
    @ApiImplicitParam(dataType = "User", name = "user", value = "用户信息", paramType = "body", required = true)
    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String createUser(@RequestBody User user){
        users.put(user.getId(), user);
        System.out.println(users);
        return "success";
    }


    @ApiOperation(value = "测试get请求", notes="注意事项")
    @ApiImplicitParam(name = "id", value = "用户id", dataType = "String", paramType = "path")
    @RequestMapping(value = "/selectUser/{id}", method = RequestMethod.GET)
    public User selectUser(@PathVariable String id){
        System.out.println(id);
        User user =  users.get(Integer.parseInt(id));
        System.out.println("user: " + user);

        return user;
    }

    @ApiOperation(value = "测试组合注解", notes = "注意事项")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "User", name = "user", value = "用户信息", required = true, paramType = "body"),
            @ApiImplicitParam(dataType = "string", name = "id", value = "用户id", required = true, paramType = "path")
    })
    @RequestMapping(value = "/updateUser/{id}", method = RequestMethod.POST)
    public User updateUser(@PathVariable String id, @RequestBody User user){
        users.put(user.getId(), user);
        return user;
    }

    @ApiIgnore
    public String testIgnore(){
        return "success";
    }
}
