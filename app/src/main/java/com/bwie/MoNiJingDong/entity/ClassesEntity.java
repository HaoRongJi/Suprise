package com.bwie.MoNiJingDong.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ClassesEntity implements MultiItemEntity {

    public static final int ClASSES_TYPE=1;
    public static final int SHOWLIST_TYPT=2;
    private int itemType;



    public ClassesEntity(int itemType) {
        this.itemType=itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
