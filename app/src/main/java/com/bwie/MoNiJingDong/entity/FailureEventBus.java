package com.bwie.MoNiJingDong.entity;

public class FailureEventBus {

    public int failureNum;

    public FailureEventBus(int failureNum) {
        this.failureNum = failureNum;
    }

    public int getFailureNum() {
        return failureNum;
    }

    public void setFailureNum(int failureNum) {
        this.failureNum = failureNum;
    }
}
