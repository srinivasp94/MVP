package com.iprismech.alertnikkiresidence.pojo;

import java.util.List;

public class SchoolBusHistoryPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : {"weekly_history":[{"date":"2019-02-12","smenu":[{"id":"240","admin_id":"2","schoolbus_id":"1","schoolbus_name":"Vidya Education school bus","schoolbus_address":"TEST","date":"2019-02-12","in_time":"11:28:00 AM","out_time":"02:48:05 PM"},{"id":"252","admin_id":"2","schoolbus_id":"1","schoolbus_name":"Vidya Education school bus","schoolbus_address":"TEST","date":"2019-02-12","in_time":"03:20:03 PM","out_time":"07:27:14 PM"},{"id":"257","admin_id":"2","schoolbus_id":"1","schoolbus_name":"Vidya Education school bus","schoolbus_address":"TEST","date":"2019-02-12","in_time":"07:30:01 PM","out_time":"12:00:00 AM"}]},{"date":"2019-02-11","smenu":[{"id":"220","admin_id":"2","schoolbus_id":"1","schoolbus_name":"Vidya Education school bus","schoolbus_address":"TEST","date":"2019-02-11","in_time":"04:25:46 PM","out_time":"12:00:00 AM"}]},{"date":"2019-02-08","smenu":[{"id":"212","admin_id":"2","schoolbus_id":"1","schoolbus_name":"Vidya Education school bus","schoolbus_address":"TEST","date":"2019-02-08","in_time":"04:58:44 PM","out_time":"12:00:00 AM"}]}],"monthly_history":[{"date":"2019-02-12","smenu":[{"id":"240","admin_id":"2","schoolbus_id":"1","schoolbus_name":"Vidya Education school bus","schoolbus_address":"TEST","date":"2019-02-12","in_time":"11:28:00 AM","out_time":"02:48:05 PM"},{"id":"252","admin_id":"2","schoolbus_id":"1","schoolbus_name":"Vidya Education school bus","schoolbus_address":"TEST","date":"2019-02-12","in_time":"03:20:03 PM","out_time":"07:27:14 PM"},{"id":"257","admin_id":"2","schoolbus_id":"1","schoolbus_name":"Vidya Education school bus","schoolbus_address":"TEST","date":"2019-02-12","in_time":"07:30:01 PM","out_time":"12:00:00 AM"}]},{"date":"2019-02-11","smenu":[{"id":"220","admin_id":"2","schoolbus_id":"1","schoolbus_name":"Vidya Education school bus","schoolbus_address":"TEST","date":"2019-02-11","in_time":"04:25:46 PM","out_time":"12:00:00 AM"}]},{"date":"2019-02-08","smenu":[{"id":"212","admin_id":"2","schoolbus_id":"1","schoolbus_name":"Vidya Education school bus","schoolbus_address":"TEST","date":"2019-02-08","in_time":"04:58:44 PM","out_time":"12:00:00 AM"}]}]}
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
            /**
             * date : 2019-02-12
             * smenu : [{"id":"240","admin_id":"2","schoolbus_id":"1","schoolbus_name":"Vidya Education school bus","schoolbus_address":"TEST","date":"2019-02-12","in_time":"11:28:00 AM","out_time":"02:48:05 PM"},{"id":"252","admin_id":"2","schoolbus_id":"1","schoolbus_name":"Vidya Education school bus","schoolbus_address":"TEST","date":"2019-02-12","in_time":"03:20:03 PM","out_time":"07:27:14 PM"},{"id":"257","admin_id":"2","schoolbus_id":"1","schoolbus_name":"Vidya Education school bus","schoolbus_address":"TEST","date":"2019-02-12","in_time":"07:30:01 PM","out_time":"12:00:00 AM"}]
             */

            private String date;
            private List<SmenuBean> smenu;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public List<SmenuBean> getSmenu() {
                return smenu;
            }

            public void setSmenu(List<SmenuBean> smenu) {
                this.smenu = smenu;
            }

            public static class SmenuBean {
                /**
                 * id : 240
                 * admin_id : 2
                 * schoolbus_id : 1
                 * schoolbus_name : Vidya Education school bus
                 * schoolbus_address : TEST
                 * date : 2019-02-12
                 * in_time : 11:28:00 AM
                 * out_time : 02:48:05 PM
                 */

                private String id;
                private String admin_id;
                private String schoolbus_id;
                private String schoolbus_name;
                private String schoolbus_address;
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

                public String getSchoolbus_id() {
                    return schoolbus_id;
                }

                public void setSchoolbus_id(String schoolbus_id) {
                    this.schoolbus_id = schoolbus_id;
                }

                public String getSchoolbus_name() {
                    return schoolbus_name;
                }

                public void setSchoolbus_name(String schoolbus_name) {
                    this.schoolbus_name = schoolbus_name;
                }

                public String getSchoolbus_address() {
                    return schoolbus_address;
                }

                public void setSchoolbus_address(String schoolbus_address) {
                    this.schoolbus_address = schoolbus_address;
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
            /**
             * date : 2019-02-12
             * smenu : [{"id":"240","admin_id":"2","schoolbus_id":"1","schoolbus_name":"Vidya Education school bus","schoolbus_address":"TEST","date":"2019-02-12","in_time":"11:28:00 AM","out_time":"02:48:05 PM"},{"id":"252","admin_id":"2","schoolbus_id":"1","schoolbus_name":"Vidya Education school bus","schoolbus_address":"TEST","date":"2019-02-12","in_time":"03:20:03 PM","out_time":"07:27:14 PM"},{"id":"257","admin_id":"2","schoolbus_id":"1","schoolbus_name":"Vidya Education school bus","schoolbus_address":"TEST","date":"2019-02-12","in_time":"07:30:01 PM","out_time":"12:00:00 AM"}]
             */

            private String date;
            private List<SmenuBeanX> smenu;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public List<SmenuBeanX> getSmenu() {
                return smenu;
            }

            public void setSmenu(List<SmenuBeanX> smenu) {
                this.smenu = smenu;
            }

            public static class SmenuBeanX {
                /**
                 * id : 240
                 * admin_id : 2
                 * schoolbus_id : 1
                 * schoolbus_name : Vidya Education school bus
                 * schoolbus_address : TEST
                 * date : 2019-02-12
                 * in_time : 11:28:00 AM
                 * out_time : 02:48:05 PM
                 */

                private String id;
                private String admin_id;
                private String schoolbus_id;
                private String schoolbus_name;
                private String schoolbus_address;
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

                public String getSchoolbus_id() {
                    return schoolbus_id;
                }

                public void setSchoolbus_id(String schoolbus_id) {
                    this.schoolbus_id = schoolbus_id;
                }

                public String getSchoolbus_name() {
                    return schoolbus_name;
                }

                public void setSchoolbus_name(String schoolbus_name) {
                    this.schoolbus_name = schoolbus_name;
                }

                public String getSchoolbus_address() {
                    return schoolbus_address;
                }

                public void setSchoolbus_address(String schoolbus_address) {
                    this.schoolbus_address = schoolbus_address;
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
