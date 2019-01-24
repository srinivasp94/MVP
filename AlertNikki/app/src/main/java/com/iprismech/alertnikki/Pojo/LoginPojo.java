package com.iprismech.alertnikki.Pojo;

public class LoginPojo {

    /**
     * status : true
     * message : Login Successfully!
     * response : {"admin_id":"2","security_id":"4","image":"storage/security_login/5c36e98108c00.","login_date":"2019-01-10","login_time":"12:13:13","city":"hyderabad","society":"Prime Towers","blood_group":"O-Negative","shift_name":"A","from_time":"02:30:00","to_time":"22:30:00"}
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
         * admin_id : 2
         * security_id : 4
         * image : storage/security_login/5c36e98108c00.
         * login_date : 2019-01-10
         * login_time : 12:13:13
         * city : hyderabad
         * society : Prime Towers
         * blood_group : O-Negative
         * shift_name : A
         * from_time : 02:30:00
         * to_time : 22:30:00
         */

        private String admin_id;
        private String security_id;
        private String image;
        private String login_date;
        private String login_time;
        private String city;
        private String society;
        private String blood_group;
        private String shift_name;
        private String from_time;
        private String to_time;

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

        public String getLogin_date() {
            return login_date;
        }

        public void setLogin_date(String login_date) {
            this.login_date = login_date;
        }

        public String getLogin_time() {
            return login_time;
        }

        public void setLogin_time(String login_time) {
            this.login_time = login_time;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getSociety() {
            return society;
        }

        public void setSociety(String society) {
            this.society = society;
        }

        public String getBlood_group() {
            return blood_group;
        }

        public void setBlood_group(String blood_group) {
            this.blood_group = blood_group;
        }

        public String getShift_name() {
            return shift_name;
        }

        public void setShift_name(String shift_name) {
            this.shift_name = shift_name;
        }

        public String getFrom_time() {
            return from_time;
        }

        public void setFrom_time(String from_time) {
            this.from_time = from_time;
        }

        public String getTo_time() {
            return to_time;
        }

        public void setTo_time(String to_time) {
            this.to_time = to_time;
        }
    }
}
