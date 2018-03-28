package com.rainbow.laundry.modle.message;

/**
 * Created by wyc on 2018/1/3.
 */

public class MessageModel {

    /**
     * id : 1231231231
     * messageTitle : 测试测试
     * messageContent : 测试测试
     * userId : dddsadasd
     * status : 1
     */

    private String id;
    private String messageTitle;
    private String messageContent;
    private String userId;
    private int status;
    private long addTime;
    private String indentId;

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    public String getIndentId() {
        return indentId;
    }

    public void setIndentId(String indentId) {
        this.indentId = indentId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public String getUserId() {
        return userId;
    }

    public int getStatus() {
        return status;
    }
}
