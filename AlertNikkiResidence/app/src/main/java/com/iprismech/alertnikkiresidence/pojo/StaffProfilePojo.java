package com.iprismech.alertnikkiresidence.pojo;

import java.util.List;

public class StaffProfilePojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : {"id":"2","admin_id":"2","local_service_id":"2","security_id":"0","request_type":"1","image":"","title":"sdasd","slug":"sdasd","passcode":"4086995","date_of_joining":"2018-11-21","designation":"paper boy","mobile":"7995929444","timings":"2,3,4","id_image":"","id_type":"1","idproof_number":"12557455WWE","address":"Flat,1523","notification_status":"1","created_on":"2018-11-26 13:19:18","modified_on":"0000-00-00 00:00:00","delete_status":"0","status":"Active","rating":"4.2","flats":"1006","availble_timings":[{"id":"4","title":"3:00-4:00"},{"id":"9","title":"8:00-9:00"},{"id":"10","title":"9:00-10:00"},{"id":"11","title":"10:00-11:00"},{"id":"12","title":"11:00-12:00"},{"id":"13","title":"13:00-14:00"},{"id":"14","title":"14:00-15:00"},{"id":"15","title":"15:00-16:00"},{"id":"16","title":"16:00-17:00"},{"id":"17","title":"17:00-18:00"},{"id":"18","title":"18:00-19:00"},{"id":"19","title":"19:00-20:00"},{"id":"20","title":"20:00-21:00"},{"id":"21","title":"21:00-22:00"},{"id":"22","title":"22:00-23:00"},{"id":"23","title":"23:00-24:00"}]}
     */

    private boolean status;
    private String message;
    private ResponseBean response;

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

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public static class ResponseBean {
        /**
         * id : 2
         * admin_id : 2
         * local_service_id : 2
         * security_id : 0
         * request_type : 1
         * image :
         * title : sdasd
         * slug : sdasd
         * passcode : 4086995
         * date_of_joining : 2018-11-21
         * designation : paper boy
         * mobile : 7995929444
         * timings : 2,3,4
         * id_image :
         * id_type : 1
         * idproof_number : 12557455WWE
         * address : Flat,1523
         * notification_status : 1
         * created_on : 2018-11-26 13:19:18
         * modified_on : 0000-00-00 00:00:00
         * delete_status : 0
         * status : Active
         * rating : 4.2
         * flats : 1006
         * availble_timings : [{"id":"4","title":"3:00-4:00"},{"id":"9","title":"8:00-9:00"},{"id":"10","title":"9:00-10:00"},{"id":"11","title":"10:00-11:00"},{"id":"12","title":"11:00-12:00"},{"id":"13","title":"13:00-14:00"},{"id":"14","title":"14:00-15:00"},{"id":"15","title":"15:00-16:00"},{"id":"16","title":"16:00-17:00"},{"id":"17","title":"17:00-18:00"},{"id":"18","title":"18:00-19:00"},{"id":"19","title":"19:00-20:00"},{"id":"20","title":"20:00-21:00"},{"id":"21","title":"21:00-22:00"},{"id":"22","title":"22:00-23:00"},{"id":"23","title":"23:00-24:00"}]
         */

        private String id;
        private String admin_id;
        private String local_service_id;
        private String security_id;
        private String request_type;
        private String image;
        private String title;
        private String slug;
        private String passcode;
        private String date_of_joining;
        private String designation;
        private String mobile;
        private String timings;
        private String id_image;
        private String id_type;
        private String idproof_number;
        private String address;
        private String notification_status;
        private String created_on;
        private String modified_on;
        private String delete_status;
        private String status;
        private String rating;
        private String flats;
        private List<AvailbleTimingsBean> availble_timings;

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

        public String getLocal_service_id() {
            return local_service_id;
        }

        public void setLocal_service_id(String local_service_id) {
            this.local_service_id = local_service_id;
        }

        public String getSecurity_id() {
            return security_id;
        }

        public void setSecurity_id(String security_id) {
            this.security_id = security_id;
        }

        public String getRequest_type() {
            return request_type;
        }

        public void setRequest_type(String request_type) {
            this.request_type = request_type;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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

        public String getPasscode() {
            return passcode;
        }

        public void setPasscode(String passcode) {
            this.passcode = passcode;
        }

        public String getDate_of_joining() {
            return date_of_joining;
        }

        public void setDate_of_joining(String date_of_joining) {
            this.date_of_joining = date_of_joining;
        }

        public String getDesignation() {
            return designation;
        }

        public void setDesignation(String designation) {
            this.designation = designation;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getTimings() {
            return timings;
        }

        public void setTimings(String timings) {
            this.timings = timings;
        }

        public String getId_image() {
            return id_image;
        }

        public void setId_image(String id_image) {
            this.id_image = id_image;
        }

        public String getId_type() {
            return id_type;
        }

        public void setId_type(String id_type) {
            this.id_type = id_type;
        }

        public String getIdproof_number() {
            return idproof_number;
        }

        public void setIdproof_number(String idproof_number) {
            this.idproof_number = idproof_number;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getNotification_status() {
            return notification_status;
        }

        public void setNotification_status(String notification_status) {
            this.notification_status = notification_status;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getFlats() {
            return flats;
        }

        public void setFlats(String flats) {
            this.flats = flats;
        }

        public List<AvailbleTimingsBean> getAvailble_timings() {
            return availble_timings;
        }

        public void setAvailble_timings(List<AvailbleTimingsBean> availble_timings) {
            this.availble_timings = availble_timings;
        }

        public static class AvailbleTimingsBean {
            /**
             * id : 4
             * title : 3:00-4:00
             */

            private String id;
            private String title;

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
        }
    }
}
