package com.iprismech.alertnikki.Pojo;

import java.util.List;

public class AddServiceTypePojo {


    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"1","admin_id":"2","title":"Pulmber","slug":"Pulmber","created_on":"2018-11-23 19:09:48","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"2","admin_id":"2","title":"Maids","slug":"Maids","created_on":"2018-11-23 19:09:48","modified_on":"2018-11-27 12:46:11","status":"Active","delete_status":"1"},{"id":"3","admin_id":"2","title":"Milk man","slug":"Milk-man","created_on":"2018-11-27 16:04:52","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"4","admin_id":"2","title":"Paper boy","slug":"Paper-boy","created_on":"2018-11-27 16:04:52","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"5","admin_id":"2","title":"Car cleaner","slug":"Car-cleaner","created_on":"2018-11-27 16:05:59","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"6","admin_id":"2","title":"Cook","slug":"Cook","created_on":"2018-11-27 16:05:59","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"7","admin_id":"2","title":"Driver","slug":"Driver","created_on":"2018-11-27 16:05:59","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"}]
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
         * title : Pulmber
         * slug : Pulmber
         * created_on : 2018-11-23 19:09:48
         * modified_on : 0000-00-00 00:00:00
         * status : Active
         * delete_status : 1
         */

        private String id;
        private String admin_id;
        private String title;
        private String slug;
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
