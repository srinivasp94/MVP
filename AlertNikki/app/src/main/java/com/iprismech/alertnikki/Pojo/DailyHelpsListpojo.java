package com.iprismech.alertnikki.Pojo;

import java.util.List;

public class DailyHelpsListpojo {


    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"1","title":"Daily Helps","created_on":"2019-01-01 00:00:00","modified_on":"0000-00-00 00:00:00","delete_status":"1","slist":[{"id":"7","admin_id":"2","category_id":"1","title":"Driver","slug":"Driver","app_icon":"","created_on":"2018-11-27 16:05:59","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"6","admin_id":"2","category_id":"1","title":"Cook","slug":"Cook","app_icon":"","created_on":"2018-11-27 16:05:59","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"5","admin_id":"2","category_id":"1","title":"Car cleaner","slug":"Car-cleaner","app_icon":"","created_on":"2018-11-27 16:05:59","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"4","admin_id":"2","category_id":"1","title":"Paper boy","slug":"Paper-boy","app_icon":"","created_on":"2018-11-27 16:04:52","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"3","admin_id":"2","category_id":"1","title":"Milk man","slug":"Milk-man","app_icon":"","created_on":"2018-11-27 16:04:52","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"2","admin_id":"2","category_id":"1","title":"Maids","slug":"Maids","app_icon":"","created_on":"2018-11-23 19:09:48","modified_on":"2018-11-27 12:46:11","status":"Active","delete_status":"1"},{"id":"1","admin_id":"2","category_id":"1","title":"Pulmber","slug":"Pulmber","app_icon":"","created_on":"2018-11-23 19:09:48","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"}]},{"id":"2","title":"Vendors","created_on":"2019-01-09 00:00:00","modified_on":"0000-00-00 00:00:00","delete_status":"1","slist":[{"id":"8","admin_id":"2","category_id":"2","title":"test123","slug":"test123","app_icon":"storage/services/menu-vendor-color6.png","created_on":"2019-01-09 14:52:05","modified_on":"2019-01-09 15:17:04","status":"Active","delete_status":"1"}]},{"id":"3","title":"Tutors","created_on":"2019-01-10 00:00:00","modified_on":"0000-00-00 00:00:00","delete_status":"1","slist":[]},{"id":"4","title":"Full Time Helps","created_on":"2019-01-09 00:00:00","modified_on":"0000-00-00 00:00:00","delete_status":"1","slist":[{"id":"9","admin_id":"2","category_id":"4","title":"maid","slug":"maid","app_icon":"storage/services/movie.jpg","created_on":"2019-01-09 17:44:43","modified_on":"2019-01-09 17:45:16","status":"Active","delete_status":"1"}]},{"id":"5","title":"Society Adminstration Staff","created_on":"2019-01-09 00:00:00","modified_on":"0000-00-00 00:00:00","delete_status":"1","slist":[]}]
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
         * title : Daily Helps
         * created_on : 2019-01-01 00:00:00
         * modified_on : 0000-00-00 00:00:00
         * delete_status : 1
         * slist : [{"id":"7","admin_id":"2","category_id":"1","title":"Driver","slug":"Driver","app_icon":"","created_on":"2018-11-27 16:05:59","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"6","admin_id":"2","category_id":"1","title":"Cook","slug":"Cook","app_icon":"","created_on":"2018-11-27 16:05:59","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"5","admin_id":"2","category_id":"1","title":"Car cleaner","slug":"Car-cleaner","app_icon":"","created_on":"2018-11-27 16:05:59","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"4","admin_id":"2","category_id":"1","title":"Paper boy","slug":"Paper-boy","app_icon":"","created_on":"2018-11-27 16:04:52","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"3","admin_id":"2","category_id":"1","title":"Milk man","slug":"Milk-man","app_icon":"","created_on":"2018-11-27 16:04:52","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"2","admin_id":"2","category_id":"1","title":"Maids","slug":"Maids","app_icon":"","created_on":"2018-11-23 19:09:48","modified_on":"2018-11-27 12:46:11","status":"Active","delete_status":"1"},{"id":"1","admin_id":"2","category_id":"1","title":"Pulmber","slug":"Pulmber","app_icon":"","created_on":"2018-11-23 19:09:48","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"}]
         */

        private String id;
        private String title;
        private String created_on;
        private String modified_on;
        private String delete_status;
        private List<SlistBean> slist;

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

        public String getDelete_status() {
            return delete_status;
        }

        public void setDelete_status(String delete_status) {
            this.delete_status = delete_status;
        }

        public List<SlistBean> getSlist() {
            return slist;
        }

        public void setSlist(List<SlistBean> slist) {
            this.slist = slist;
        }

        public static class SlistBean {
            /**
             * id : 7
             * admin_id : 2
             * category_id : 1
             * title : Driver
             * slug : Driver
             * app_icon :
             * created_on : 2018-11-27 16:05:59
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
}
