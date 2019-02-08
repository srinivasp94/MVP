package com.iprismech.alertnikkiresidence.pojo;

import java.util.List;

public class EmergencyConatctPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"1","admin_id":"2","name":"subbaraju","email":"asdasf12@gmail.com","contact_number":"9490245547","created_on":"2018-11-30 18:54:29","modified_on":"2018-11-30 19:01:24","delete_status":"1"},{"id":"2","admin_id":"2","name":"father","email":"fath@gmail.com","contact_number":"9123435465","created_on":"2019-01-09 17:29:23","modified_on":"2019-01-09 17:29:51","delete_status":"1"},{"id":"3","admin_id":"2","name":"sanvi","email":"sanvi@gmail.com","contact_number":"9632587413","created_on":"2019-01-18 14:45:40","modified_on":"2019-01-18 14:46:14","delete_status":"1"}]
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
         * admin_id : 2
         * name : subbaraju
         * email : asdasf12@gmail.com
         * contact_number : 9490245547
         * created_on : 2018-11-30 18:54:29
         * modified_on : 2018-11-30 19:01:24
         * delete_status : 1
         */

        private String id;
        private String admin_id;
        private String name;
        private String email;
        private String contact_number;
        private String created_on;
        private String modified_on;
        private String delete_status;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getContact_number() {
            return contact_number;
        }

        public void setContact_number(String contact_number) {
            this.contact_number = contact_number;
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

        public String getDelete_status() {
            return delete_status;
        }

        public void setDelete_status(String delete_status) {
            this.delete_status = delete_status;
        }
    }
}
