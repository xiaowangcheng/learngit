package com.manage.rain.model.commons;

import com.manage.rain.model.Base;

import java.util.List;



public class Comments extends Base {

    /**
     * status : 1
     * msg : null
     * data : {"commitList":[{"commitId":"2b80a3e2321748289e917b2d865fb68f","commitContent":"ff","userId":"cc755fb6856243db807bf04053779061","commitTime":1511538583000,"indentId":null,"user":{"userid":"cc755fb6856243db807bf04053779061","phonenumber":"15958273487","password":null,"openid":null,"username":null,"loginname":null,"integral":0,"balance":0,"addressid":null,"imgurl":null}},{"commitId":"5b0307ad12354862b1c20b2d4361f122","commitContent":"adsf","userId":"cc755fb6856243db807bf04053779061","commitTime":1511538530000,"indentId":null,"user":{"userid":"cc755fb6856243db807bf04053779061","phonenumber":"15958273487","password":null,"openid":null,"username":null,"loginname":null,"integral":0,"balance":0,"addressid":null,"imgurl":null}},{"commitId":"7f2ccb03a2d1423aa28a290ac0625384","commitContent":"a大师傅","userId":"cc755fb6856243db807bf04053779061","commitTime":1511452800000,"indentId":null,"user":{"userid":"cc755fb6856243db807bf04053779061","phonenumber":"15958273487","password":null,"openid":null,"username":null,"loginname":null,"integral":0,"balance":0,"addressid":null,"imgurl":null}},{"commitId":"c6016ae1a097421dae32fb49774f1147","commitContent":"adsf","userId":"cc755fb6856243db807bf04053779061","commitTime":1511538540000,"indentId":null,"user":{"userid":"cc755fb6856243db807bf04053779061","phonenumber":"15958273487","password":null,"openid":null,"username":null,"loginname":null,"integral":0,"balance":0,"addressid":null,"imgurl":null}}],"pageInfo":{"total":4,"currentPage":1,"totalPage":1}}
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
         * commitList : [{"commitId":"2b80a3e2321748289e917b2d865fb68f","commitContent":"ff","userId":"cc755fb6856243db807bf04053779061","commitTime":1511538583000,"indentId":null,"user":{"userid":"cc755fb6856243db807bf04053779061","phonenumber":"15958273487","password":null,"openid":null,"username":null,"loginname":null,"integral":0,"balance":0,"addressid":null,"imgurl":null}},{"commitId":"5b0307ad12354862b1c20b2d4361f122","commitContent":"adsf","userId":"cc755fb6856243db807bf04053779061","commitTime":1511538530000,"indentId":null,"user":{"userid":"cc755fb6856243db807bf04053779061","phonenumber":"15958273487","password":null,"openid":null,"username":null,"loginname":null,"integral":0,"balance":0,"addressid":null,"imgurl":null}},{"commitId":"7f2ccb03a2d1423aa28a290ac0625384","commitContent":"a大师傅","userId":"cc755fb6856243db807bf04053779061","commitTime":1511452800000,"indentId":null,"user":{"userid":"cc755fb6856243db807bf04053779061","phonenumber":"15958273487","password":null,"openid":null,"username":null,"loginname":null,"integral":0,"balance":0,"addressid":null,"imgurl":null}},{"commitId":"c6016ae1a097421dae32fb49774f1147","commitContent":"adsf","userId":"cc755fb6856243db807bf04053779061","commitTime":1511538540000,"indentId":null,"user":{"userid":"cc755fb6856243db807bf04053779061","phonenumber":"15958273487","password":null,"openid":null,"username":null,"loginname":null,"integral":0,"balance":0,"addressid":null,"imgurl":null}}]
         * pageInfo : {"total":4,"currentPage":1,"totalPage":1}
         */

        private PageInfoEntity pageInfo;
        private List<CommitListEntity> commitList;

        public void setPageInfo(PageInfoEntity pageInfo) {
            this.pageInfo = pageInfo;
        }

        public void setCommitList(List<CommitListEntity> commitList) {
            this.commitList = commitList;
        }

        public PageInfoEntity getPageInfo() {
            return pageInfo;
        }

        public List<CommitListEntity> getCommitList() {
            return commitList;
        }

        public static class PageInfoEntity {
            /**
             * total : 4
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

        public static class CommitListEntity {
            /**
             * commitId : 2b80a3e2321748289e917b2d865fb68f
             * commitContent : ff
             * userId : cc755fb6856243db807bf04053779061
             * commitTime : 1511538583000
             * indentId : null
             * user : {"userid":"cc755fb6856243db807bf04053779061","phonenumber":"15958273487","password":null,"openid":null,"username":null,"loginname":null,"integral":0,"balance":0,"addressid":null,"imgurl":null}
             */

            private String commitId;
            private String commitContent;
            private String userId;
            private long commitTime;
            private String indentId;
            private UserEntity user;

            public void setCommitId(String commitId) {
                this.commitId = commitId;
            }

            public void setCommitContent(String commitContent) {
                this.commitContent = commitContent;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public void setCommitTime(long commitTime) {
                this.commitTime = commitTime;
            }

            public void setIndentId(String indentId) {
                this.indentId = indentId;
            }

            public void setUser(UserEntity user) {
                this.user = user;
            }

            public String getCommitId() {
                return commitId;
            }

            public String getCommitContent() {
                return commitContent;
            }

            public String getUserId() {
                return userId;
            }

            public long getCommitTime() {
                return commitTime;
            }

            public String getIndentId() {
                return indentId;
            }

            public UserEntity getUser() {
                return user;
            }

            public static class UserEntity {
                /**
                 * userid : cc755fb6856243db807bf04053779061
                 * phonenumber : 15958273487
                 * password : null
                 * openid : null
                 * username : null
                 * loginname : null
                 * integral : 0
                 * balance : 0.0
                 * addressid : null
                 * imgurl : null
                 */

                private String userid;
                private String phonenumber;
                private Object password;
                private Object openid;
                private Object username;
                private Object loginname;
                private int integral;
                private double balance;
                private Object addressid;
                private Object imgurl;

                public void setUserid(String userid) {
                    this.userid = userid;
                }

                public void setPhonenumber(String phonenumber) {
                    this.phonenumber = phonenumber;
                }

                public void setPassword(Object password) {
                    this.password = password;
                }

                public void setOpenid(Object openid) {
                    this.openid = openid;
                }

                public void setUsername(Object username) {
                    this.username = username;
                }

                public void setLoginname(Object loginname) {
                    this.loginname = loginname;
                }

                public void setIntegral(int integral) {
                    this.integral = integral;
                }

                public void setBalance(double balance) {
                    this.balance = balance;
                }

                public void setAddressid(Object addressid) {
                    this.addressid = addressid;
                }

                public void setImgurl(Object imgurl) {
                    this.imgurl = imgurl;
                }

                public String getUserid() {
                    return userid;
                }

                public String getPhonenumber() {
                    return phonenumber;
                }

                public Object getPassword() {
                    return password;
                }

                public Object getOpenid() {
                    return openid;
                }

                public Object getUsername() {
                    return username;
                }

                public Object getLoginname() {
                    return loginname;
                }

                public int getIntegral() {
                    return integral;
                }

                public double getBalance() {
                    return balance;
                }

                public Object getAddressid() {
                    return addressid;
                }

                public Object getImgurl() {
                    return imgurl;
                }
            }
        }
    }
}
