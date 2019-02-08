package com.iprismech.alertnikkiresidence.pojo;

import java.util.List;

public class GetDigitalInterComPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"2","user_type":"1","user_id":"2","admin_id":"2","primary_number":"8500553103","secondary_number":"9490245547","created_on":"2018-12-04 20:15:08","modified_on":"0000-00-00 00:00:00","delete_status":"1"}]
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
         * id : 2
         * user_type : 1
         * user_id : 2
         * admin_id : 2
         * primary_number : 8500553103
         * secondary_number : 9490245547
         * created_on : 2018-12-04 20:15:08
         * modified_on : 0000-00-00 00:00:00
         * delete_status : 1
         */

        private String id;
        private String user_type;
        private String user_id;
        private String admin_id;
        private String primary_number;
        private String secondary_number;
        private String created_on;
        private String modified_on;
        private String delete_status;

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

        public String getPrimary_number() {
            return primary_number;
        }

        public void setPrimary_number(String primary_number) {
            this.primary_number = primary_number;
        }

        public String getSecondary_number() {
            return secondary_number;
        }

        public void setSecondary_number(String secondary_number) {
            this.secondary_number = secondary_number;
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
