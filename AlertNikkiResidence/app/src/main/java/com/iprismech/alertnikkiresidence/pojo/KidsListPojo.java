package com.iprismech.alertnikkiresidence.pojo;

import java.util.List;

public class KidsListPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"1","user_type":"1","user_id":"1","admin_id":"2","days":"1,2,3","kid_name":"srinivas1","purpose":"swimming","in_time":"13:00:00","out_time":"14:40:00","notification_status":"0","created_on":"2018-12-31 13:27:34","modified_on":"2019-01-22 13:15:21","full_days":"Sunday,Monday,Tuesday"},{"id":"2","user_type":"1","user_id":"1","admin_id":"2","days":"1,2,3","kid_name":"srinivas","purpose":"swimmin,dance","in_time":"13:00:00","out_time":"14:40:00","notification_status":"1","created_on":"2019-01-07 13:06:53","modified_on":"0000-00-00 00:00:00","full_days":"Sunday,Monday,Tuesday"},{"id":"12","user_type":"1","user_id":"1","admin_id":"2","days":"1,2","kid_name":"srinivas","purpose":"Swimming","in_time":"10:00:00","out_time":"11:40:00","notification_status":"1","created_on":"2019-01-07 19:07:31","modified_on":"0000-00-00 00:00:00","full_days":"Sunday,Monday"},{"id":"13","user_type":"1","user_id":"1","admin_id":"2","days":"1,2","kid_name":"srinivas","purpose":"dance","in_time":"12:00:00","out_time":"13:40:00","notification_status":"1","created_on":"2019-01-07 19:07:31","modified_on":"0000-00-00 00:00:00","full_days":"Sunday,Monday"},{"id":"14","user_type":"1","user_id":"1","admin_id":"2","days":"1,2","kid_name":"srinivas","purpose":"Tutorials","in_time":"15:00:00","out_time":"17:40:00","notification_status":"1","created_on":"2019-01-07 19:07:31","modified_on":"0000-00-00 00:00:00","full_days":"Sunday,Monday"},{"id":"15","user_type":"1","user_id":"1","admin_id":"2","days":"1,2","kid_name":"srinivas","purpose":"Swimming","in_time":"10:00:00","out_time":"11:40:00","notification_status":"1","created_on":"2019-01-07 19:39:06","modified_on":"0000-00-00 00:00:00","full_days":"Sunday,Monday"},{"id":"16","user_type":"1","user_id":"1","admin_id":"2","days":"1,2","kid_name":"srinivas","purpose":"dance","in_time":"12:00:00","out_time":"13:40:00","notification_status":"1","created_on":"2019-01-07 19:39:06","modified_on":"0000-00-00 00:00:00","full_days":"Sunday,Monday"},{"id":"17","user_type":"1","user_id":"1","admin_id":"2","days":"1,2","kid_name":"srinivas","purpose":"Tutorials","in_time":"15:00:00","out_time":"17:40:00","notification_status":"1","created_on":"2019-01-07 19:39:06","modified_on":"0000-00-00 00:00:00","full_days":"Sunday,Monday"}]
     */

    private boolean status;
    private String message;
    private List<ResponseBean> response;

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

    public List<ResponseBean> getResponse() {
        return response;
    }

    public void setResponse(List<ResponseBean> response) {
        this.response = response;
    }

    public static class ResponseBean {
        /**
         * id : 1
         * user_type : 1
         * user_id : 1
         * admin_id : 2
         * days : 1,2,3
         * kid_name : srinivas1
         * purpose : swimming
         * in_time : 13:00:00
         * out_time : 14:40:00
         * notification_status : 0
         * created_on : 2018-12-31 13:27:34
         * modified_on : 2019-01-22 13:15:21
         * full_days : Sunday,Monday,Tuesday
         */

        private String id;
        private String user_type;
        private String user_id;
        private String admin_id;
        private String days;
        private String kid_name;
        private String purpose;
        private String in_time;
        private String out_time;
        private String notification_status;
        private String created_on;
        private String modified_on;
        private String full_days;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getAdmin_id() {
            return admin_id;
        }

        public void setAdmin_id(String admin_id) {
            this.admin_id = admin_id;
        }

        public String getDays() {
            return days;
        }

        public void setDays(String days) {
            this.days = days;
        }

        public String getKid_name() {
            return kid_name;
        }

        public void setKid_name(String kid_name) {
            this.kid_name = kid_name;
        }

        public String getPurpose() {
            return purpose;
        }

        public void setPurpose(String purpose) {
            this.purpose = purpose;
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

        public String getNotification_status() {
            return notification_status;
        }

        public void setNotification_status(String notification_status) {
            this.notification_status = notification_status;
        }

        public String getCreated_on() {
            return created_on;
        }

        public void setCreated_on(String created_on) {
            this.created_on = created_on;
        }

        public String getModified_on() {
            return modified_on;
        }

        public void setModified_on(String modified_on) {
            this.modified_on = modified_on;
        }

        public String getFull_days() {
            return full_days;
        }

        public void setFull_days(String full_days) {
            this.full_days = full_days;
        }
    }
}
