package com.iprismech.alertnikki.Pojo;

public class LoginTimePojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : {"id":"51","admin_id":"2","security_id":"4","image":"storage/security_login/5c20834c320a4.png","date":"2018-12-24","in_time":"12:27:16","out_time":"00:00:00","attendance":"present","created_on":"2018-12-24 12:27:16","modified_on":"0000-00-00 00:00:00","security_name":"athar"}
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
        /**
         * id : 51
         * admin_id : 2
         * security_id : 4
         * image : storage/security_login/5c20834c320a4.png
         * date : 2018-12-24
         * in_time : 12:27:16
         * out_time : 00:00:00
         * attendance : present
         * created_on : 2018-12-24 12:27:16
         * modified_on : 0000-00-00 00:00:00
         * security_name : athar
         */

        private String id;
        private String admin_id;
        private String security_id;
        private String image;
        private String date;
        private String in_time;
        private String out_time;
        private String attendance;
        private String created_on;
        private String modified_on;
        private String security_name;

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

        public String getSecurity_id() {
            return security_id;
        }

        public void setSecurity_id(String security_id) {
            this.security_id = security_id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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

        public String getAttendance() {
            return attendance;
        }

        public void setAttendance(String attendance) {
            this.attendance = attendance;
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

        public String getSecurity_name() {
            return security_name;
        }

        public void setSecurity_name(String security_name) {
            this.security_name = security_name;
        }
    }
}
