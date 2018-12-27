package com.iprismech.alertnikki.Pojo;

import java.util.List;

public class MoreAllServicesPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"1","title":"Cab","created_on":"2018-12-15 00:00:00","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"2","title":"Delivery Boy","created_on":"2018-12-15 00:00:00","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"3","title":"Add Guest","created_on":"2018-12-15 00:00:00","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"4","title":"Gas Delivery ","created_on":"2018-12-20 00:00:00","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"5","title":"Courier Boy","created_on":"2018-12-22 00:00:00","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"}]
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
         * title : Cab
         * created_on : 2018-12-15 00:00:00
         * modified_on : 0000-00-00 00:00:00
         * status : Active
         * delete_status : 1
         */

        private String id;
        private String title;
        private String created_on;
        private String modified_on;
        private String status;
        private String delete_status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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
    }
}
