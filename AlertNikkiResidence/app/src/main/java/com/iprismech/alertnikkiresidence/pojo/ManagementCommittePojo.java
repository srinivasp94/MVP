package com.iprismech.alertnikkiresidence.pojo;

import java.util.List;

public class ManagementCommittePojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"1","admin_id":"2","name":"gururaju","designation":"co worker1","email":"asds@gmail.com","mobile":"7093668623","created_on":"2018-11-30 19:21:57","modified_on":"2019-01-02 19:28:59","delete_status":"1"},{"id":"2","admin_id":"2","name":"varna12","designation":"main staff","email":"asrinivas433@gmail.com","mobile":"8530055310","created_on":"2018-11-30 19:22:46","modified_on":"2019-01-02 19:28:39","delete_status":"1"},{"id":"3","admin_id":"2","name":"Employees Commity ","designation":"Software Engineers","email":"employee@gmail.com","mobile":"8889912345","created_on":"2019-01-09 17:40:21","modified_on":"2019-01-09 17:41:22","delete_status":"1"},{"id":"4","admin_id":"2","name":"employees unity","designation":"engineer","email":"emp@employe.com","mobile":"9888745658","created_on":"2019-01-18 14:47:11","modified_on":"2019-01-18 14:47:24","delete_status":"1"}]
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
         * name : gururaju
         * designation : co worker1
         * email : asds@gmail.com
         * mobile : 7093668623
         * created_on : 2018-11-30 19:21:57
         * modified_on : 2019-01-02 19:28:59
         * delete_status : 1
         */

        private String id;
        private String admin_id;
        private String name;
        private String designation;
        private String email;
        private String mobile;
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

        public String getDesignation() {
            return designation;
        }

        public void setDesignation(String designation) {
            this.designation = designation;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
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
