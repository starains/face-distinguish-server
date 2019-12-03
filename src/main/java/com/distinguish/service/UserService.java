package com.distinguish.service;

import com.distinguish.bean.DataResp;
import com.distinguish.bean.UserBean;
import java.util.List;

public interface UserService {
    public List<UserBean> qryUserInfos();
    //登录校验
    public DataResp qryUserInfosByMsg(UserBean user);
    //注册新用户
    public DataResp registerUser(UserBean user);
    public DataResp aliveCheck(String imgBase64, String checkType, String userId);
}
