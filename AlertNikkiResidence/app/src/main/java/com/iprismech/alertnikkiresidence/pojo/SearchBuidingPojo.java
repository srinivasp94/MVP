package com.iprismech.alertnikkiresidence.pojo;

import java.util.List;

public class SearchBuidingPojo {


    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"4","admin_id":"2","title":"Block A","slug":"Block-A","no_of_flats":"0","status":"Active","created_on":"2018-11-20 13:10:15","modified_on":"2018-11-20 13:16:31","delete_status":"1"}]
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
         * admin_id : 2
         * title : Block A
         * slug : Block-A
         * no_of_flats : 0
         * status : Active
         * created_on : 2018-11-20 13:10:15
         * modified_on : 2018-11-20 13:16:31
         * delete_status : 1
         */

        private String id;
        private String admin_id;
        private String title;
        private String slug;
        private String no_of_flats;
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

        public String getNo_of_flats() {
            return no_of_flats;
        }

        public void setNo_of_flats(String no_of_flats) {
            this.no_of_flats = no_of_flats;
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
