package com.bwie.MoNiJingDong.entity;

public class MessageEvent {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public MessageEvent(String msg) {

        this.msg = msg;
    }
}
