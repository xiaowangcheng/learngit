package com.rainbow.laundry.modle;

/**
 * Created by wangcheng on 2018/2/28.
 */

public class IntegralDetail {


    /**
     * status : 1
     * msg : null
     * data : {"integral":0,"project":{"id":1,"productImg":"/upload/mr9sic5s734cheadimg.png","productAddTime":1518084063000,"productTitle":"积分产品","productPrice":1,"productIntegral":11,"productIntroduce":"好东西想 好东西 啊啊","productStart":1516723200000,"productEnd":1516896000000,"productType":0,"productPosted":1}}
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
         * integral : 0
         * project : {"id":1,"productImg":"/upload/mr9sic5s734cheadimg.png","productAddTime":1518084063000,"productTitle":"积分产品","productPrice":1,"productIntegral":11,"productIntroduce":"好东西想 好东西 啊啊","productStart":1516723200000,"productEnd":1516896000000,"productType":0,"productPosted":1}
         */

        private int integral;
        private ProjectEntity project;

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public void setProject(ProjectEntity project) {
            this.project = project;
        }

        public int getIntegral() {
            return integral;
        }

        public ProjectEntity getProject() {
            return project;
        }

        public static class ProjectEntity {
            /**
             * id : 1
             * productImg : /upload/mr9sic5s734cheadimg.png
             * productAddTime : 1518084063000
             * productTitle : 积分产品
             * productPrice : 1.0
             * productIntegral : 11
             * productIntroduce : 好东西想 好东西 啊啊
             * productStart : 1516723200000
             * productEnd : 1516896000000
             * productType : 0
             * productPosted : 1
             */

            private int id;
            private String productImg;
            private long productAddTime;
            private String productTitle;
            private double productPrice;
            private int productIntegral;
            private String productIntroduce;
            private long productStart;
            private long productEnd;
            private int productType;
            private int productPosted;

            public void setId(int id) {
                this.id = id;
            }

            public void setProductImg(String productImg) {
                this.productImg = productImg;
            }

            public void setProductAddTime(long productAddTime) {
                this.productAddTime = productAddTime;
            }

            public void setProductTitle(String productTitle) {
                this.productTitle = productTitle;
            }

            public void setProductPrice(double productPrice) {
                this.productPrice = productPrice;
            }

            public void setProductIntegral(int productIntegral) {
                this.productIntegral = productIntegral;
            }

            public void setProductIntroduce(String productIntroduce) {
                this.productIntroduce = productIntroduce;
            }

            public void setProductStart(long productStart) {
                this.productStart = productStart;
            }

            public void setProductEnd(long productEnd) {
                this.productEnd = productEnd;
            }

            public void setProductType(int productType) {
                this.productType = productType;
            }

            public void setProductPosted(int productPosted) {
                this.productPosted = productPosted;
            }

            public int getId() {
                return id;
            }

            public String getProductImg() {
                return productImg;
            }

            public long getProductAddTime() {
                return productAddTime;
            }

            public String getProductTitle() {
                return productTitle;
            }

            public double getProductPrice() {
                return productPrice;
            }

            public int getProductIntegral() {
                return productIntegral;
            }

            public String getProductIntroduce() {
                return productIntroduce;
            }

            public long getProductStart() {
                return productStart;
            }

            public long getProductEnd() {
                return productEnd;
            }

            public int getProductType() {
                return productType;
            }

            public int getProductPosted() {
                return productPosted;
            }
        }
    }
}
