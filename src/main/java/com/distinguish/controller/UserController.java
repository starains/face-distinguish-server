package com.distinguish.controller;

import com.distinguish.bean.DataResp;
import com.distinguish.bean.UserBean;
import com.distinguish.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassNamme UserController
 * @Description TODO
 * @Author
 * @Date 2019/3/19 21:30
 * @Version 1.0
 **/
@RestController
@RequestMapping(value="/users")
@Api(tags = "用户信息相关操作")
public class UserController {
    @Autowired
    UserService userService;
    /*
     *查询所有用户
     */
    @RequestMapping(value = "/qryUserInfos", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取所有用户信息",notes = "不需要输入参数")
    @ApiResponses(value = {@ApiResponse(code = 401, message = "服务器认证失败"),
            @ApiResponse(code = 403, message = "资源不存在"),
            @ApiResponse(code = 404, message = "传入的参数无效"),
            @ApiResponse(code = 500, message = "服务器出现异常错误")})
    public List<UserBean> qryUserInfos(){
        return userService.qryUserInfos();
    }

    /*
     *登陆信息校验
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "登陆验证",notes = "输入用户名以及密码")
    @ApiResponses(value = {@ApiResponse(code = 401, message = "服务器认证失败"),
            @ApiResponse(code = 403, message = "资源不存在"),
            @ApiResponse(code = 404, message = "传入的参数无效"),
            @ApiResponse(code = 500, message = "服务器出现异常错误")})
    public @ResponseBody
    Map<String, Object> login(@RequestBody UserBean user){
        System.out.println("登录用户信息：" + user);
        //将查询结果封装在map中，返回json串
        Map<String, Object> map = new HashMap<>();
        DataResp dataResp = userService.qryUserInfosByMsg(user);
        if(dataResp.getCode() == DataResp.Code.SUCCESS) {
            map.put("code", "0000");
            map.put("data", dataResp.getData());
            map.put("desc", "success");
        }else{
            map.put("code", "8888");
            map.put("data", dataResp.getData());
            map.put("desc", dataResp.getMessage());
        }
        return map;
    }

    /*
     *注册
     */
    @RequestMapping(value = "/registerUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "注册",notes = "输入用户名以及密码")
    @ApiResponses(value = {@ApiResponse(code = 401, message = "服务器认证失败"),
            @ApiResponse(code = 403, message = "资源不存在"),
            @ApiResponse(code = 404, message = "传入的参数无效"),
            @ApiResponse(code = 500, message = "服务器出现异常错误")})
    public @ResponseBody
    Map<String, Object> registerUser(@RequestBody UserBean user){
        System.out.println("注册用户信息：" + user.toString());
        //先判断用户名是否存在
        UserBean newUser = new UserBean();
        newUser.setUsername(user.getUsername());
        newUser.setPasswordLogin(true);
        DataResp qryDataResp = userService.qryUserInfosByMsg(newUser);
        Map<String, Object> map = new HashMap<>();
        if(qryDataResp.getCode() == DataResp.Code.SUCCESS){
            map.put("code","1111");
            map.put("data",qryDataResp.getData());
            map.put("desc","exist");
        }else{
            DataResp dataResp = userService.registerUser(user);
            if (dataResp.getCode() == DataResp.Code.SUCCESS) {
                map.put("code", "0000");
                map.put("data", dataResp.getData());
                map.put("desc", "success");
            }else{
                map.put("code", "8888");
                map.put("data", dataResp.getData());
                map.put("desc", dataResp.getMessage());
            }
        }
        return map;
    }

    @RequestMapping(value = "/aliveCheck", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DataResp aliveCheck(@RequestBody UserBean user) {
        return userService.aliveCheck(user.getImgbase64(), user.getCheckType(), user.getUsername());
    }
}
