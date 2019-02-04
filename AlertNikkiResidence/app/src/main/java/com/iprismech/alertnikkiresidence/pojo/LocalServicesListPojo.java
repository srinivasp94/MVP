package com.iprismech.alertnikkiresidence.pojo;

import java.util.List;

public class LocalServicesListPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"1","admin_id":"2","title":"Water man","slug":"Water-man","app_icon":"storage/services/Water_man.png","created_on":"2018-11-30 19:55:14","modified_on":"2019-01-31 19:51:12","status":"Active","delete_status":"1"},{"id":"2","admin_id":"2","title":"Electrician","slug":"Electrician","app_icon":"storage/services/Eelectrican.png","created_on":"2018-11-30 19:55:14","modified_on":"2019-01-31 19:45:45","status":"Active","delete_status":"1"},{"id":"3","admin_id":"2","title":"Plumber","slug":"Plumber","app_icon":"storage/services/plumber@.png","created_on":"2018-11-30 19:55:27","modified_on":"2019-01-31 19:45:08","status":"Active","delete_status":"1"},{"id":"4","admin_id":"2","title":"Carpenter","slug":"Carpenter","app_icon":"storage/services/carpenter1.png","created_on":"2018-12-03 12:52:08","modified_on":"2019-01-31 19:44:51","status":"Active","delete_status":"1"},{"id":"5","admin_id":"2","title":"Mechanic","slug":"Mechanic","app_icon":"storage/services/Mechanic_man.png","created_on":"2018-12-03 12:52:21","modified_on":"2019-01-31 19:44:34","status":"Active","delete_status":"1"},{"id":"28","admin_id":"2","title":"Driver","slug":"Driver","app_icon":"storage/services/Driver_(2).png","created_on":"2019-01-09 17:42:31","modified_on":"2019-01-31 19:42:40","status":"Active","delete_status":"1"},{"id":"29","admin_id":"2","title":"Maid","slug":"Maid","app_icon":"storage/services/Maid@1.png","created_on":"2019-01-09 17:46:16","modified_on":"2019-01-31 19:42:22","status":"Active","delete_status":"1"},{"id":"30","admin_id":"2","title":"fan repairer","slug":"fan-repairer","app_icon":"storage/services/Fan_repair@.png","created_on":"2019-01-18 14:49:27","modified_on":"2019-01-31 19:41:07","status":"Active","delete_status":"1"},{"id":"31","admin_id":"2","title":"helper","slug":"helper","app_icon":"storage/services/Helper1.png","created_on":"2019-01-18 14:53:35","modified_on":"2019-01-31 19:40:43","status":"Active","delete_status":"1"},{"id":"32","admin_id":"2","title":"services","slug":"services","app_icon":"storage/services/Services.png","created_on":"2019-01-18 14:54:21","modified_on":"2019-01-31 18:54:14","status":"Active","delete_status":"1"}]
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
         * title : Water man
         * slug : Water-man
         * app_icon : storage/services/Water_man.png
         * created_on : 2018-11-30 19:55:14
         * modified_on : 2019-01-31 19:51:12
         * status : Active
         * delete_status : 1
         */

        private String id;
        private String admin_id;
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
