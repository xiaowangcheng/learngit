package com.rainbow.laundry.modle;

import java.io.Serializable;

/**
 * Created by wyc on 2018/1/2.
 */

public class PageInfo implements Serializable{
    /**
     * total : 9
     * currentPage : 1
     * totalPage : 1
     */

    private int total;
    private int currentPage;
    private int totalPage;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotal() {
        return total;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }
}
