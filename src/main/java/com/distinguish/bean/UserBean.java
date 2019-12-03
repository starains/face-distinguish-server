package com.distinguish.bean;

/**
 * @ClassNamme UserBean
 * @Description TODO
 * @Author
 * @Date 2019/3/19 20:50
 * @Version 1.0
 **/
public class UserBean {
    private long id;
    private String username;
    private String password;
    private String imgbase64;
    private boolean passwordLogin;
    private String checkType;

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImgbase64() {
        return imgbase64;
    }

    public void setImgbase64(String imgbase64) {
        this.imgbase64 = imgbase64;
    }

    public boolean isPasswordLogin() {
        return passwordLogin;
    }

    public void setPasswordLogin(boolean passwordLogin) {
        this.passwordLogin = passwordLogin;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", imgbase64='" + imgbase64 + '\'' +
                ", checkType='" + checkType + '\'' +
                ", passwordLogin=" + passwordLogin +
                '}';
    }
}
