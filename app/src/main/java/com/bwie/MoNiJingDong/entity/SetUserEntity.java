package com.bwie.MoNiJingDong.entity;

import java.util.ArrayList;
import java.util.List;

public class SetUserEntity {

    public List<String> list;

    /*public static class DataBean{

        public String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public DataBean(String name) {
            this.name = name;
        }
    }*/

    public SetUserEntity(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
