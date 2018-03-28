package com.rainbow.laundry.modle.message;


import com.rainbow.laundry.modle.PageInfo;

import java.util.List;

/**
 * Created by wyc on 2018/1/3.
 */

public class MessageList {


    /**
     * status : 1
     * msg : null
     * data : {"pageInfo":{"total":2,"currentPage":1,"totalPage":1},"msgList":[{"messageId":"1231231231","messageTitle":"测试测试","messageContent":"测试测试","userId":"dddsadasd","status":1},{"messageId":"sadadadasd","messageTitle":"测试测试","messageContent":"测试测试","userId":"dddsadasd","status":0}]}
     */

    private int status;
    private Object msg;
    private DataEntity data;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public Object getMsg() {
        return msg;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        /**
         * pageInfo : {"total":2,"currentPage":1,"totalPage":1}
         * msgList : [{"messageId":"1231231231","messageTitle":"测试测试","messageContent":"测试测试","userId":"dddsadasd","status":1},{"messageId":"sadadadasd","messageTitle":"测试测试","messageContent":"测试测试","userId":"dddsadasd","status":0}]
         */

        private PageInfo pageInfo;
        private List<MessageModel> msgList;

        public void setPageInfo(PageInfo pageInfo) {
            this.pageInfo = pageInfo;
        }

        public void setMsgList(List<MessageModel> msgList) {
            this.msgList = msgList;
        }

        public PageInfo getPageInfo() {
            return pageInfo;
        }

        public List<MessageModel> getMsgList() {
            return msgList;
        }

    }
}
