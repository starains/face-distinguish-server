package com.distinguish.service.impl;

import com.distinguish.bean.DataResp;
import com.distinguish.bean.UserBean;
import com.distinguish.dao.UserDao;
import com.distinguish.service.UserService;
import com.distinguish.util.FaceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassNamme UserServiceImpl
 * @Description TODO
 * @Author
 * @Date 2019/3/19 21:27
 * @Version 1.0
 **/
@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public List<UserBean> qryUserInfos(){
        return userDao.qryUserInfos();
    }
    @Override
    public DataResp qryUserInfosByMsg(UserBean user){
        DataResp dataResp = new DataResp();
        // 判断是否是刷脸登陆
        if (!user.isPasswordLogin()) {
            // 首先要对提交的人脸做一下识别，看是否是符合用来作人脸对比的图像
            dataResp = FaceHelper.faceDetect(user.getImgbase64());
            // 如果成功的话，就用这张人脸和注册时录入的人脸进行比对
            if (dataResp.getCode() == DataResp.Code.SUCCESS) {
                // 查询登陆用户
                UserBean compareUser = userDao.qryUserInfosByMsg(user);
                if (compareUser != null) {
                    // 进行对比
                    dataResp = FaceHelper.faceCompare(user.getImgbase64(), compareUser.getImgbase64());
                    if (dataResp.getCode() == DataResp.Code.SUCCESS) {
                        // 把用户返回前端，用于后续操作
                        dataResp.setData(compareUser);
                    }
                } else {
                    dataResp.setCode(DataResp.Code.ERROR);
                    dataResp.setMessage("该用户不存在");
                }
            }
        }else{
            user = userDao.qryUserInfosByMsg(user);
            if (user != null) {
                dataResp.setCode(DataResp.Code.SUCCESS);
                dataResp.setData(user);
            } else {
                dataResp.setCode(DataResp.Code.ERROR);
                dataResp.setMessage("用户名或密码错误");
            }
        }
        return dataResp;
    }

    @Override
    public DataResp registerUser(UserBean user){
        // 识别人脸
        System.out.println("注册用户信息：" + user);
        DataResp dataResp = FaceHelper.faceDetect(user.getImgbase64());
        if (dataResp.getCode() == DataResp.Code.SUCCESS) {
            userDao.registerUser(user);
        }
        return dataResp;
    }

    @Override
    public DataResp aliveCheck(String imgBase64, String checkType, String userId) {
        System.out.println("后台收到的 checkType: "+checkType+" base64：" + imgBase64);
        return FaceHelper.aliveCheck(imgBase64, checkType, userId);
    }
}
