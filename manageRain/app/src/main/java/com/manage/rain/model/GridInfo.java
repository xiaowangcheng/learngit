package com.manage.rain.model;

/**
 * 主页的gridview 对象
 * Created by wyf on 2018/1/6.
 */

public class GridInfo {
    private int id;
    private String name;
    private boolean isSelect=false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
