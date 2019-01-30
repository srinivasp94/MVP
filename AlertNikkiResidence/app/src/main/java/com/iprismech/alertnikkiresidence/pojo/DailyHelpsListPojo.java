package com.iprismech.alertnikkiresidence.pojo;

import java.util.List;

public class DailyHelpsListPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"1","admin_id":"2","category_id":"1","title":"Pulmber","slug":"Pulmber","app_icon":"storage/services/Plumber2.png","created_on":"2018-11-23 19:09:48","modified_on":"2019-01-25 19:45:26","status":"Active","delete_status":"1"},{"id":"2","admin_id":"2","category_id":"1","title":"Maids","slug":"Maids","app_icon":"storage/services/Maid2.png","created_on":"2018-11-23 19:09:48","modified_on":"2019-01-25 19:41:35","status":"Active","delete_status":"1"},{"id":"3","admin_id":"2","category_id":"1","title":"Milk man","slug":"Milk-man","app_icon":"storage/services/milk_man.png","created_on":"2018-11-27 16:04:52","modified_on":"2019-01-25 20:35:13","status":"Active","delete_status":"1"},{"id":"4","admin_id":"2","category_id":"1","title":"Paper boy","slug":"Paper-boy","app_icon":"storage/services/paper_Man.png","created_on":"2018-11-27 16:04:52","modified_on":"2019-01-25 20:34:50","status":"Active","delete_status":"1"},{"id":"5","admin_id":"2","category_id":"1","title":"Car cleaner","slug":"Car-cleaner","app_icon":"","created_on":"2018-11-27 16:05:59","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"6","admin_id":"2","category_id":"1","title":"Cook","slug":"Cook","app_icon":"storage/services/cook1.png","created_on":"2018-11-27 16:05:59","modified_on":"2019-01-25 19:46:29","status":"Active","delete_status":"1"},{"id":"7","admin_id":"2","category_id":"1","title":"Driver","slug":"Driver","app_icon":"storage/services/Van_driver2.png","created_on":"2018-11-27 16:05:59","modified_on":"2019-01-25 19:47:40","status":"Active","delete_status":"1"},{"id":"8","admin_id":"2","category_id":"2","title":"test123","slug":"test123","app_icon":"storage/services/menu-vendor-color6.png","created_on":"2019-01-09 14:52:05","modified_on":"2019-01-09 15:17:04","status":"Active","delete_status":"1"},{"id":"9","admin_id":"2","category_id":"4","title":"maid","slug":"maid","app_icon":"storage/services/Maid3.png","created_on":"2019-01-09 17:44:43","modified_on":"2019-01-25 19:47:06","status":"Active","delete_status":"1"},{"id":"10","admin_id":"2","category_id":"3","title":"test1","slug":"test1","app_icon":"storage/services/180883-200.png","created_on":"2019-01-10 15:47:35","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"11","admin_id":"2","category_id":"3","title":"test 2","slug":"test-2","app_icon":"storage/services/bulk_sms.png","created_on":"2019-01-10 15:48:00","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"12","admin_id":"2","category_id":"5","title":"admin 1","slug":"admin-1","app_icon":"storage/services/favicon.png","created_on":"2019-01-10 15:48:19","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"13","admin_id":"2","category_id":"5","title":"driver","slug":"driver","app_icon":"","created_on":"2019-01-18 14:51:11","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"14","admin_id":"2","category_id":"5","title":"helper","slug":"helper","app_icon":"","created_on":"2019-01-18 14:53:02","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"15","admin_id":"2","category_id":"5","title":"civil engineering","slug":"civil-engineering","app_icon":"","created_on":"2019-01-18 14:59:48","modified_on":"2019-01-25 19:56:15","status":"Active","delete_status":"1"},{"id":"16","admin_id":"2","category_id":"1","title":"Parking","slug":"Parking","app_icon":"storage/services/23.jpg","created_on":"2019-01-25 18:46:52","modified_on":"2019-01-25 18:48:45","status":"Active","delete_status":"1"},{"id":"17","admin_id":"2","category_id":"1","title":"Maids","slug":"Maids","app_icon":"","created_on":"2019-01-25 19:41:28","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"18","admin_id":"2","category_id":"1","title":"Van Driver","slug":"Van-Driver","app_icon":"storage/services/Van_driver3.png","created_on":"2019-01-25 19:43:19","modified_on":"2019-01-25 19:50:25","status":"Active","delete_status":"1"},{"id":"19","admin_id":"2","category_id":"1","title":"Plumber","slug":"Plumber","app_icon":"storage/services/Plumber3.png","created_on":"2019-01-25 19:43:58","modified_on":"2019-01-25 19:49:43","status":"Active","delete_status":"1"},{"id":"20","admin_id":"2","category_id":"3","title":"Tutor","slug":"Tutor","app_icon":"storage/services/tutors.png","created_on":"2019-01-25 19:59:58","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"21","admin_id":"2","category_id":"2","title":"Vegetable Vendor","slug":"Vegetable-Vendor","app_icon":"storage/services/vegatable_vendor.png","created_on":"2019-01-25 20:01:06","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"22","admin_id":"2","category_id":"1","title":"Women Sweeping","slug":"Women-Sweeping","app_icon":"storage/services/women_sweping.png","created_on":"2019-01-25 20:01:49","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"23","admin_id":"2","category_id":"1","title":"Beautician","slug":"Beautician","app_icon":"storage/services/beautician.png","created_on":"2019-01-25 20:02:28","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"24","admin_id":"2","category_id":"1","title":"House Cleaner","slug":"House-Cleaner","app_icon":"storage/services/house_cleaner.png","created_on":"2019-01-25 20:03:08","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"25","admin_id":"2","category_id":"4","title":"Teacher","slug":"Teacher","app_icon":"storage/services/teacher.png","created_on":"2019-01-25 20:03:36","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"26","admin_id":"2","category_id":"1","title":"Tailor","slug":"Tailor","app_icon":"storage/services/tailor.png","created_on":"2019-01-25 20:04:06","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"27","admin_id":"2","category_id":"1","title":"Music trainer","slug":"Music-trainer","app_icon":"storage/services/music_trainer.png","created_on":"2019-01-25 20:06:05","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"28","admin_id":"2","category_id":"1","title":"Music trainer","slug":"Music-trainer","app_icon":"storage/services/music_trainer1.png","created_on":"2019-01-25 20:06:13","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"29","admin_id":"2","category_id":"1","title":"Nurse","slug":"Nurse","app_icon":"storage/services/Nurse.png","created_on":"2019-01-25 20:06:43","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"30","admin_id":"2","category_id":"1","title":"Gardener","slug":"Gardener","app_icon":"storage/services/gardener.png","created_on":"2019-01-25 20:07:41","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"31","admin_id":"2","category_id":"4","title":"Full Time Helpers","slug":"Full-Time-Helpers","app_icon":"storage/services/full_time_helpers.png","created_on":"2019-01-25 20:08:15","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"32","admin_id":"2","category_id":"1","title":"Gas Delivery","slug":"Gas-Delivery","app_icon":"storage/services/gas_deliver.png","created_on":"2019-01-25 20:08:52","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"33","admin_id":"2","category_id":"1","title":"AC Repairer","slug":"AC-Repairer","app_icon":"storage/services/AC_repair.png","created_on":"2019-01-25 20:09:17","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"34","admin_id":"2","category_id":"1","title":"Elder Taker","slug":"Elder-Taker","app_icon":"storage/services/Elder_caretaker1.png","created_on":"2019-01-25 20:09:59","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"35","admin_id":"2","category_id":"1","title":"Lift","slug":"Lift","app_icon":"storage/services/Lift.png","created_on":"2019-01-25 20:10:21","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"36","admin_id":"2","category_id":"1","title":"Hair Dresser","slug":"Hair-Dresser","app_icon":"storage/services/Hair_dresser.png","created_on":"2019-01-25 20:11:07","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"37","admin_id":"2","category_id":"1","title":"Painter","slug":"Painter","app_icon":"storage/services/painter.png","created_on":"2019-01-25 20:15:03","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"38","admin_id":"2","category_id":"5","title":"Group Administrator","slug":"Group-Administrator","app_icon":"storage/services/Group_adminstrator.png","created_on":"2019-01-25 20:15:33","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"39","admin_id":"2","category_id":"5","title":"Group Administrator","slug":"Group-Administrator","app_icon":"storage/services/Group_adminstrator1.png","created_on":"2019-01-25 20:15:40","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"40","admin_id":"2","category_id":"1","title":"Gym Trainer","slug":"Gym-Trainer","app_icon":"storage/services/gym_trainer.png","created_on":"2019-01-25 20:16:04","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"41","admin_id":"2","category_id":"1","title":"Clerk","slug":"Clerk","app_icon":"storage/services/clerk.png","created_on":"2019-01-25 20:17:11","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"42","admin_id":"2","category_id":"1","title":"Bus Driver","slug":"Bus-Driver","app_icon":"storage/services/bus_driver.png","created_on":"2019-01-25 20:17:59","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"43","admin_id":"2","category_id":"1","title":"Doctor","slug":"Doctor","app_icon":"storage/services/Doctor.png","created_on":"2019-01-25 20:18:26","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"44","admin_id":"2","category_id":"1","title":"Garbage Clean","slug":"Garbage-Clean","app_icon":"storage/services/garbage_can.png","created_on":"2019-01-25 20:19:29","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"45","admin_id":"2","category_id":"1","title":"Garden Cleaner","slug":"Garden-Cleaner","app_icon":"storage/services/garden_cleaner.png","created_on":"2019-01-25 20:20:07","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"46","admin_id":"2","category_id":"1","title":"Dance Trainer","slug":"Dance-Trainer","app_icon":"storage/services/dance_trainer.png","created_on":"2019-01-25 20:20:30","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"47","admin_id":"2","category_id":"1","title":"Medicine Shop","slug":"Medicine-Shop","app_icon":"storage/services/Medicine_shop.png","created_on":"2019-01-25 20:21:20","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"48","admin_id":"2","category_id":"1","title":"Take Care Of Children","slug":"Take-Care-Of-Children","app_icon":"storage/services/take_care_of_childern.png","created_on":"2019-01-25 20:22:19","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"49","admin_id":"2","category_id":"1","title":"Swimming Trainer","slug":"Swimming-Trainer","app_icon":"storage/services/swimming_trainer.png","created_on":"2019-01-25 20:23:17","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"50","admin_id":"2","category_id":"1","title":"Skating Training","slug":"Skating-Training","app_icon":"storage/services/Skating.png","created_on":"2019-01-25 20:24:04","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"},{"id":"51","admin_id":"2","category_id":"1","title":"Society Boy","slug":"Society-Boy","app_icon":"storage/services/society_boy.png","created_on":"2019-01-25 20:24:30","modified_on":"0000-00-00 00:00:00","status":"Active","delete_status":"1"}]
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
         * category_id : 1
         * title : Pulmber
         * slug : Pulmber
         * app_icon : storage/services/Plumber2.png
         * created_on : 2018-11-23 19:09:48
         * modified_on : 2019-01-25 19:45:26
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
