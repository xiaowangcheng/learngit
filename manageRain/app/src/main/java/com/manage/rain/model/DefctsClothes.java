package com.manage.rain.model;

import java.util.List;

/**
 * Created by wangcheng on 2018/2/9.
 */

public class DefctsClothes {


    /**
     * data : [{"id":7,"abnormalImg":"/upload/4p7viv3j65g1headimg.png","indentId":"15","addTime":1518186715000,"abnormalIntro":"毛线衣服啊"},{"id":6,"abnormalImg":"/upload/okvc9dgvelydheadimg.png","indentId":"15","addTime":1518186697000,"abnormalIntro":"破衣服洗毛线"},{"id":4,"abnormalImg":"/upload/rygoq9t8fotpheadimg.png","indentId":"15","addTime":1517928489000,"abnormalIntro":"破烂衣服"},{"id":5,"abnormalImg":"/upload/an7j52vcocubheadimg.png","indentId":"15","addTime":1517927624000,"abnormalIntro":"破破烂烂"}]
     * message : suss
     * success : true
     */

    private String message;
    private boolean success;
    private List<DataEntity> data;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public boolean getSuccess() {
        return success;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        /**
         * id : 7
         * abnormalImg : /upload/4p7viv3j65g1headimg.png
         * indentId : 15
         * addTime : 1518186715000
         * abnormalIntro : 毛线衣服啊
         */

        private int id;
        private String abnormalImg;
        private String indentId;
        private long addTime;
        private String abnormalIntro;
        private int height=0;

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setAbnormalImg(String abnormalImg) {
            this.abnormalImg = abnormalImg;
        }

        public void setIndentId(String indentId) {
            this.indentId = indentId;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

        public void setAbnormalIntro(String abnormalIntro) {
            this.abnormalIntro = abnormalIntro;
        }

        public int getId() {
            return id;
        }

        public String getAbnormalImg() {
            return abnormalImg;
        }

        public String getIndentId() {
            return indentId;
        }

        public long getAddTime() {
            return addTime;
        }

        public String getAbnormalIntro() {
            return abnormalIntro;
        }
    }
}
