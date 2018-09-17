package com.bwie.MoNiJingDong.entity;

public class SuccessEntity {

    public HomeBean homeBean;

    public SuccessEntity(HomeBean homeBean) {
        this.homeBean = homeBean;
    }

    public HomeBean getHomeBean() {
        return homeBean;
    }

    public void setHomeBean(HomeBean homeBean) {
        this.homeBean = homeBean;
    }
}
