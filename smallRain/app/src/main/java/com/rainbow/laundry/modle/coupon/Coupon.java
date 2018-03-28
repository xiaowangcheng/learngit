package com.rainbow.laundry.modle.coupon;


import com.rainbow.laundry.modle.Base;

import java.util.List;

/**
 * Created by wyc on 2018/1/3.
 */

public class Coupon extends Base {


    /**
     * status : 1
     * msg : null
     * data : {"pageInfo":{"total":2,"totalPage":1,"currentPage":1},"kuponList":[{"id":1,"userId":1,"kuponId":1,"kupon":{"id":1,"fordrn":1111,"agio":1,"startTime":1543593600000,"endTime":1543680000000,"status":0,"addTime":null},"status":0,"overdueTime":null,"addTime":1516723200000,"useTime":null},{"id":2,"userId":1,"kuponId":2,"kupon":{"id":2,"fordrn":1111,"agio":2,"startTime":1515772800000,"endTime":1516982400000,"status":0,"addTime":null},"status":0,"overdueTime":null,"addTime":1516723200000,"useTime":null}]}
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
         * pageInfo : {"total":2,"totalPage":1,"currentPage":1}
         * kuponList : [{"id":1,"userId":1,"kuponId":1,"kupon":{"id":1,"fordrn":1111,"agio":1,"startTime":1543593600000,"endTime":1543680000000,"status":0,"addTime":null},"status":0,"overdueTime":null,"addTime":1516723200000,"useTime":null},{"id":2,"userId":1,"kuponId":2,"kupon":{"id":2,"fordrn":1111,"agio":2,"startTime":1515772800000,"endTime":1516982400000,"status":0,"addTime":null},"status":0,"overdueTime":null,"addTime":1516723200000,"useTime":null}]
         */

        private PageInfoEntity pageInfo;
        private List<KuponListEntity> kuponList;

        public void setPageInfo(PageInfoEntity pageInfo) {
            this.pageInfo = pageInfo;
        }

        public void setKuponList(List<KuponListEntity> kuponList) {
            this.kuponList = kuponList;
        }

        public PageInfoEntity getPageInfo() {
            return pageInfo;
        }

        public List<KuponListEntity> getKuponList() {
            return kuponList;
        }

        public static class PageInfoEntity {
            /**
             * total : 2
             * totalPage : 1
             * currentPage : 1
             */

            private int total;
            private int totalPage;
            private int currentPage;

            public void setTotal(int total) {
                this.total = total;
            }

            public void setTotalPage(int totalPage) {
                this.totalPage = totalPage;
            }

            public void setCurrentPage(int currentPage) {
                this.currentPage = currentPage;
            }

            public int getTotal() {
                return total;
            }

            public int getTotalPage() {
                return totalPage;
            }

            public int getCurrentPage() {
                return currentPage;
            }
        }

        public static class KuponListEntity {
            /**
             * id : 1
             * userId : 1
             * kuponId : 1
             * kupon : {"id":1,"fordrn":1111,"agio":1,"startTime":1543593600000,"endTime":1543680000000,"status":0,"addTime":null}
             * status : 0
             * overdueTime : null
             * addTime : 1516723200000
             * useTime : null
             */

            private int id;
            private int userId;
            private int kuponId;
            private KuponEntity kupon;
            private int status;
            private Object overdueTime;
            private long addTime;
            private Object useTime;

            public void setId(int id) {
                this.id = id;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public void setKuponId(int kuponId) {
                this.kuponId = kuponId;
            }

            public void setKupon(KuponEntity kupon) {
                this.kupon = kupon;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public void setOverdueTime(Object overdueTime) {
                this.overdueTime = overdueTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public void setUseTime(Object useTime) {
                this.useTime = useTime;
            }

            public int getId() {
                return id;
            }

            public int getUserId() {
                return userId;
            }

            public int getKuponId() {
                return kuponId;
            }

            public KuponEntity getKupon() {
                return kupon;
            }

            public int getStatus() {
                return status;
            }

            public Object getOverdueTime() {
                return overdueTime;
            }

            public long getAddTime() {
                return addTime;
            }

            public Object getUseTime() {
                return useTime;
            }

            public static class KuponEntity {
                /**
                 * id : 1
                 * fordrn : 1111.0
                 * agio : 1.0
                 * startTime : 1543593600000
                 * endTime : 1543680000000
                 * status : 0
                 * addTime : null
                 */

                private int id;
                private double fordrn;
                private double agio;
                private long startTime;
                private long endTime;
                private int status;
                private Object addTime;

                public void setId(int id) {
                    this.id = id;
                }

                public void setFordrn(double fordrn) {
                    this.fordrn = fordrn;
                }

                public void setAgio(double agio) {
                    this.agio = agio;
                }

                public void setStartTime(long startTime) {
                    this.startTime = startTime;
                }

                public void setEndTime(long endTime) {
                    this.endTime = endTime;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public void setAddTime(Object addTime) {
                    this.addTime = addTime;
                }

                public int getId() {
                    return id;
                }

                public double getFordrn() {
                    return fordrn;
                }

                public double getAgio() {
                    return agio;
                }

                public long getStartTime() {
                    return startTime;
                }

                public long getEndTime() {
                    return endTime;
                }

                public int getStatus() {
                    return status;
                }

                public Object getAddTime() {
                    return addTime;
                }
            }
        }
    }
}
