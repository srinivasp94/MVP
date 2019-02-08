package com.iprismech.alertnikkiresidence.pojo;

import java.util.List;

public class NoticeBoardPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"1","admin_id":"2","description":"Some test 123","status":"Active","created_on":"2018-12-04 19:07:46","modified_on":"2018-12-04 19:15:46","delete_status":"1"},{"id":"2","admin_id":"2","description":"birthday","status":"Active","created_on":"2019-01-09 18:47:47","modified_on":"0000-00-00 00:00:00","delete_status":"1"},{"id":"3","admin_id":"2","description":"About all functions of india","status":"Active","created_on":"2019-01-18 15:15:36","modified_on":"2019-01-18 15:16:00","delete_status":"1"}]
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
         * description : Some test 123
         * status : Active
         * created_on : 2018-12-04 19:07:46
         * modified_on : 2018-12-04 19:15:46
         * delete_status : 1
         */

        private String id;
        private String admin_id;
        private String description;
        private String status;
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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
