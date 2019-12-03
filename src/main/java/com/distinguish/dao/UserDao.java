package com.distinguish.dao;

import com.distinguish.bean.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassNamme UserDao
 * @Description TODO
 * @Author
 * @Date 2019/3/19 21:02
 * @Version 1.0
 **/
@Mapper
public interface UserDao {
    /*
        查询所有用户信息
     */
    @Select("select * from TF_F_USER")
    public List<UserBean> qryUserInfos();
    /*
        通过用户名或者密码查询用户信息--动态SQL
     */
    @Select({"<script>" +
            "select * from TF_F_USER " +
            "where 1=1 " +
            "<if test='username != null'>" +
            "and binary username = #{username} " +
            "</if>" +
            "<if test='password != null and password != \"\" '>" +
            "and password = #{password} " +
            "</if>" +
            "</script>"})
    public UserBean qryUserInfosByMsg(UserBean user);
    /*
        注册新用户
     */
    @Select("insert into TF_F_USER (username,password,imgbase64,registtime)values(#{username}, #{password}, #{imgbase64}, now())")
    public void registerUser(UserBean user);
}
