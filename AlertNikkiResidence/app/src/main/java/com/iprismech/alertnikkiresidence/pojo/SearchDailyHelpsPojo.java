package com.iprismech.alertnikkiresidence.pojo;

import java.util.List;

public class SearchDailyHelpsPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"93","admin_id":"2","category_id":"3","title":"Society Plumber","slug":"Society-Plumber","app_icon":"storage/services/Plumber.png","created_on":"2019-02-07 17:36:45","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"104","admin_id":"2","category_id":"9","title":"Plumber","slug":"Plumber","app_icon":"storage/services/Plumber5.png","created_on":"2019-02-07 18:40:10","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"}]
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
         * id : 93
         * admin_id : 2
         * category_id : 3
         * title : Society Plumber
         * slug : Society-Plumber
         * app_icon : storage/services/Plumber.png
         * created_on : 2019-02-07 17:36:45
         * modified_on : 0000-00-00 00:00:00
         * status : Active
         * delete_status : 1
         */

        private String id;
        private String admin_id;
        private String category_id;
        private String title;
        private String slug;
        private String app_icon;
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

        public String getAdmin_id() {
            return admin_id;
        }

        public void setAdmin_id(String admin_id) {
            this.admin_id = admin_id;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
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

        public String getApp_icon() {
            return app_icon;
        }

        public void setApp_icon(String app_icon) {
            this.app_icon = app_icon;
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
