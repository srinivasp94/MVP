package com.iprismech.alertnikkiresidence.pojo;

import java.util.List;

public class MaidAttendanceHistoryPojo {


    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : {"weekly_history":[{"smenu":[{"id":"58","admin_id":"2","maid_id":"68","date":"2019-02-05","in_time":"12:21:18 PM","out_time":"12:30:25 PM"}]},{"smenu":[{"id":"57","admin_id":"2","maid_id":"68","date":"2019-02-04","in_time":"05:10:15 PM","out_time":"03:54:20 PM"}]}],"monthly_history":[{"smenu":[{"id":"58","admin_id":"2","maid_id":"68","date":"2019-02-05","in_time":"12:21:18 PM","out_time":"12:30:25 PM"}]},{"smenu":[{"id":"57","admin_id":"2","maid_id":"68","date":"2019-02-04","in_time":"05:10:15 PM","out_time":"03:54:20 PM"}]},{"smenu":[{"id":"56","admin_id":"2","maid_id":"68","date":"2019-01-30","in_time":"02:48:23 PM","out_time":"04:14:59 PM"}]},{"smenu":[{"id":"53","admin_id":"2","maid_id":"68","date":"2019-01-29","in_time":"07:35:24 PM","out_time":"12:00:00 AM"}]},{"smenu":[{"id":"52","admin_id":"2","maid_id":"68","date":"2019-01-28","in_time":"07:26:15 PM","out_time":"06:47:54 PM"}]},{"smenu":[{"id":"44","admin_id":"2","maid_id":"68","date":"2019-01-25","in_time":"06:33:29 PM","out_time":"06:35:50 PM"}]}]}
     */

    private boolean status;
    private String message;
    private ResponseBean response;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public static class ResponseBean {
        private List<WeeklyHistoryBean> weekly_history;
        private List<MonthlyHistoryBean> monthly_history;

        public List<WeeklyHistoryBean> getWeekly_history() {
            return weekly_history;
        }

        public void setWeekly_history(List<WeeklyHistoryBean> weekly_history) {
            this.weekly_history = weekly_history;
        }

        public List<MonthlyHistoryBean> getMonthly_history() {
            return monthly_history;
        }

        public void setMonthly_history(List<MonthlyHistoryBean> monthly_history) {
            this.monthly_history = monthly_history;
        }

        public static class WeeklyHistoryBean {
            private List<SmenuBean> smenu;

            public List<SmenuBean> getSmenu() {
                return smenu;
            }

            public void setSmenu(List<SmenuBean> smenu) {
                this.smenu = smenu;
            }

            public static class SmenuBean {
                /**
                 * id : 58
                 * admin_id : 2
                 * maid_id : 68
                 * date : 2019-02-05
                 * in_time : 12:21:18 PM
                 * out_time : 12:30:25 PM
                 */

                private String id;
                private String admin_id;
                private String maid_id;
                private String date;
                private String in_time;
                private String out_time;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getAdmin_id() {
                    return admin_id;
                }

                public void setAdmin_id(String admin_id) {
                    this.admin_id = admin_id;
                }

                public String getMaid_id() {
                    return maid_id;
                }

                public void setMaid_id(String maid_id) {
                    this.maid_id = maid_id;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getIn_time() {
                    return in_time;
                }

                public void setIn_time(String in_time) {
                    this.in_time = in_time;
                }

                public String getOut_time() {
                    return out_time;
                }

                public void setOut_time(String out_time) {
                    this.out_time = out_time;
                }
            }
        }

        public static class MonthlyHistoryBean {
            private List<SmenuBeanX> smenu;

            public List<SmenuBeanX> getSmenu() {
                return smenu;
            }

            public void setSmenu(List<SmenuBeanX> smenu) {
                this.smenu = smenu;
            }

            public static class SmenuBeanX {
                /**
                 * id : 58
                 * admin_id : 2
                 * maid_id : 68
                 * date : 2019-02-05
                 * in_time : 12:21:18 PM
                 * out_time : 12:30:25 PM
                 */

                private String id;
                private String admin_id;
                private String maid_id;
                private String date;
                private String in_time;
                private String out_time;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getAdmin_id() {
                    return admin_id;
                }

                public void setAdmin_id(String admin_id) {
                    this.admin_id = admin_id;
                }

                public String getMaid_id() {
                    return maid_id;
                }

                public void setMaid_id(String maid_id) {
                    this.maid_id = maid_id;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getIn_time() {
                    return in_time;
                }

                public void setIn_time(String in_time) {
                    this.in_time = in_time;
                }

                public String getOut_time() {
                    return out_time;
                }

                public void setOut_time(String out_time) {
                    this.out_time = out_time;
                }
            }
        }
    }
}
