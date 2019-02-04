package com.iprismech.alertnikkiresidence.pojo;

import java.util.List;

public class LocalServiceContactsPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"9","admin_id":"2","service_id":"1","name":"Sai","mobile":"8887776667","experience":"2","image":"storage/service_persons/Water_man3.png","created_on":"2019-01-31 18:38:48","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"0","service":"Water man"},{"id":"8","admin_id":"2","service_id":"1","name":"Sai","mobile":"8887776667","experience":"2","image":"storage/service_persons/Water_man2.png","created_on":"2019-01-31 18:38:44","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"0","service":"Water man"},{"id":"7","admin_id":"2","service_id":"1","name":"Sai","mobile":"8887776667","experience":"2","image":"storage/service_persons/Water_man1.png","created_on":"2019-01-31 18:38:24","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"0","service":"Water man"},{"id":"6","admin_id":"2","service_id":"1","name":"manish","mobile":"9030438354","experience":"5","image":"storage/service_persons/Water_man.png","created_on":"2019-01-18 15:03:00","modified_on":"2019-01-31 18:33:21","status":"Active","delete_status":"1","service":"Water man"},{"id":"2","admin_id":"2","service_id":"1","name":"raghu","mobile":"9494608101","experience":"8","image":"storage/service_persons/Lighthouse.jpg","created_on":"2018-12-03 14:58:18","modified_on":"2018-12-03 16:35:22","status":"Active","delete_status":"0","service":"Water man"},{"id":"1","admin_id":"2","service_id":"1","name":"srinivas","mobile":"9490245547","experience":"8","image":"","created_on":"2018-12-03 14:46:15","modified_on":"2018-12-03 14:55:39","status":"Active","delete_status":"0","service":"Water man"}]
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
         * id : 9
         * admin_id : 2
         * service_id : 1
         * name : Sai
         * mobile : 8887776667
         * experience : 2
         * image : storage/service_persons/Water_man3.png
         * created_on : 2019-01-31 18:38:48
         * modified_on : 0000-00-00 00:00:00
         * status : Active
         * delete_status : 0
         * service : Water man
         */

        private String id;
        private String admin_id;
        private String service_id;
        private String name;
        private String mobile;
        private String experience;
        private String image;
        private String created_on;
        private String modified_on;
        private String status;
        private String delete_status;
        private String service;

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

        public String getService_id() {
            return service_id;
        }

        public void setService_id(String service_id) {
            this.service_id = service_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getExperience() {
            return experience;
        }

        public void setExperience(String experience) {
            this.experience = experience;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDelete_status() {
            return delete_status;
        }

        public void setDelete_status(String delete_status) {
            this.delete_status = delete_status;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }
    }
}
