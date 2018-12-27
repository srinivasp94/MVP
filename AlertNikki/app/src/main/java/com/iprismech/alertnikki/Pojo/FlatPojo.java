package com.iprismech.alertnikki.Pojo;

import java.util.List;

public class FlatPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"1","admin_id":"2","building_id":"4","title":"100","slug":"100","created_on":"2018-11-20 15:25:19","modified_on":"0000-00-00 00:00:00","delete_status":"1"},{"id":"2","admin_id":"2","building_id":"4","title":"1006","slug":"1006","created_on":"2018-11-20 15:25:40","modified_on":"2018-11-20 15:37:23","delete_status":"1"},{"id":"4","admin_id":"2","building_id":"4","title":"1001","slug":"1001","created_on":"2018-11-20 15:44:46","modified_on":"0000-00-00 00:00:00","delete_status":"1"},{"id":"5","admin_id":"2","building_id":"4","title":"123","slug":"123","created_on":"2018-11-20 15:44:46","modified_on":"2018-11-23 12:37:15","delete_status":"1"}]
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
         * building_id : 4
         * title : 100
         * slug : 100
         * created_on : 2018-11-20 15:25:19
         * modified_on : 0000-00-00 00:00:00
         * delete_status : 1
         */

        private String id;
        private String admin_id;
        private String building_id;
        private String title;
        private String slug;
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

        public String getBuilding_id() {
            return building_id;
        }

        public void setBuilding_id(String building_id) {
            this.building_id = building_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
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
