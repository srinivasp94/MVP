package com.iprismech.alertnikkiresidence.pojo;

import java.util.List;

public class SearchSocietyPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"1","city_id":"4","title":"Malaysian Township","slug":"Malaysian-Township","delete_status":"1","created_on":"2018-06-28 18:56:33","modified_on":"2018-11-19 18:19:30"},{"id":"3","city_id":"4","title":"Rachi Society","slug":"Rachi-Society","delete_status":"1","created_on":"2018-08-22 18:49:28","modified_on":"2018-11-19 18:17:53"},{"id":"4","city_id":"4","title":"Hanuman Society","slug":"Hanuman-Society","delete_status":"1","created_on":"2018-08-28 19:44:13","modified_on":"2018-11-19 18:17:29"},{"id":"5","city_id":"4","title":"Ayyapa Society","slug":"Ayyapa-Society","delete_status":"1","created_on":"2018-11-01 11:25:06","modified_on":"2018-11-19 18:17:12"}]
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
         * city_id : 4
         * title : Malaysian Township
         * slug : Malaysian-Township
         * delete_status : 1
         * created_on : 2018-06-28 18:56:33
         * modified_on : 2018-11-19 18:19:30
         */

        private String id;
        private String city_id;
        private String title;
        private String slug;
        private String delete_status;
        private String created_on;
        private String modified_on;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
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
