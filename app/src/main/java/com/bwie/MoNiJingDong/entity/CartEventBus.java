package com.bwie.MoNiJingDong.entity;

public class CartEventBus {

    public boolean isLogin;

    public CartEventBus(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
