package com.iprismech.alertnikkiresidence.pojo;

import java.util.List;

public class SelectCityPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"4","title":"hyderabad","slug":"hyderabad","status":"Active","delete_status":"1","created_on":"2018-06-28 17:31:47","modified_on":"2018-11-01 11:33:09"}]
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
         * id : 4
         * title : hyderabad
         * slug : hyderabad
         * status : Active
         * delete_status : 1
         * created_on : 2018-06-28 17:31:47
         * modified_on : 2018-11-01 11:33:09
         */

        private String id;
        private String title;
        private String slug;
        private String status;
        private String delete_status;
        private String created_on;
        private String modified_on;

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

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
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
    }
}
