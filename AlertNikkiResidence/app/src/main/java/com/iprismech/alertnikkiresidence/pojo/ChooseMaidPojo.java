package com.iprismech.alertnikkiresidence.pojo;

import java.util.List;

public class ChooseMaidPojo {
    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"4","admin_id":"2","local_service_id":"2","security_id":"0","request_type":"1","image":"","title":"guru","slug":"guru","passcode":"8842966","date_of_joining":"2018-11-28","designation":"milk man","mobile":"7895855952","timings":"2,3,4","id_image":"","id_type":"1","idproof_number":"12557455WWE","address":"dasdadas","notification_status":"1","created_on":"2018-11-28 18:47:10","modified_on":"2018-11-28 18:52:04","delete_status":"1","status":"Active","local_service":"Maids","working_flats":"5","rating":"3"},{"id":"13","admin_id":"2","local_service_id":"2","security_id":"0","request_type":"1","image":"storage/maids/22.jpg","title":"TEST","slug":"TEST","passcode":"5430945","date_of_joining":"2019-01-09","designation":"helper","mobile":"9490245511","timings":"3,4","id_image":"","id_type":"1","idproof_number":"asf","address":"asfasf","notification_status":"1","created_on":"2018-12-27 20:29:50","modified_on":"2019-01-09 19:02:56","delete_status":"1","status":"Active","local_service":"Maids","working_flats":"1","rating":null},{"id":"16","admin_id":"2","local_service_id":"2","security_id":"4","request_type":"0","image":"storage/maids/5c408442ec817.jpeg","title":"srikanth","slug":"srikanth","passcode":"7189773","date_of_joining":"0000-00-00","designation":"","mobile":"1234567890","timings":"","id_image":"","id_type":"1","idproof_number":"gdysudidokdkdkdkdkd","address":"Hyderabad","notification_status":"1","created_on":"2019-01-17 19:03:54","modified_on":"0000-00-00 00:00:00","delete_status":"1","status":"Active","local_service":"Maids","working_flats":"1","rating":null},{"id":"19","admin_id":"2","local_service_id":"2","security_id":"4","request_type":"1","image":"storage/maids/5c4086144e36b.jpeg","title":"tutoriak","slug":"tutoriak","passcode":"6695475","date_of_joining":"0000-00-00","designation":"","mobile":"9865321245","timings":"","id_image":"","id_type":"1","idproof_number":"sdfgjhklpoi","address":"Hyderabad","notification_status":"1","created_on":"2019-01-17 19:11:40","modified_on":"0000-00-00 00:00:00","delete_status":"1","status":"Active","local_service":"Maids","working_flats":"1","rating":null},{"id":"20","admin_id":"2","local_service_id":"2","security_id":"0","request_type":"1","image":"","title":"sanuki","slug":"sanuki","passcode":"5267154","date_of_joining":"2019-01-18","designation":"maid","mobile":"9632587412","timings":"3","id_image":"","id_type":"1","idproof_number":"123456789456","address":"hyderabad","notification_status":"1","created_on":"2019-01-18 11:53:08","modified_on":"0000-00-00 00:00:00","delete_status":"1","status":"Active","local_service":"Maids","working_flats":"2","rating":null},{"id":"24","admin_id":"2","local_service_id":"2","security_id":"4","request_type":"1","image":"storage/maids/5c417f799be84.jpeg","title":"maddy","slug":"maddy","passcode":"9645737","date_of_joining":"0000-00-00","designation":"","mobile":"9632580712","timings":"","id_image":"","id_type":"1","idproof_number":"51123456789","address":"Hyderabad","notification_status":"1","created_on":"2019-01-18 12:55:45","modified_on":"0000-00-00 00:00:00","delete_status":"1","status":"Active","local_service":"Maids","working_flats":"1","rating":"2.8"},{"id":"60","admin_id":"2","local_service_id":"2","security_id":"0","request_type":"0","image":"","title":"ramani","slug":"ramani","passcode":"3298788","date_of_joining":"2019-01-23","designation":"maid","mobile":"8895656357","timings":"3","id_image":"","id_type":"1","idproof_number":"78965412369","address":"Hyderabad","notification_status":"1","created_on":"2019-01-23 16:33:58","modified_on":"0000-00-00 00:00:00","delete_status":"1","status":"Active","local_service":"Maids","working_flats":"0","rating":null},{"id":"61","admin_id":"2","local_service_id":"2","security_id":"0","request_type":"0","image":"","title":"malika","slug":"malika","passcode":"6880876","date_of_joining":"2019-01-23","designation":"maid","mobile":"9898653225","timings":"5","id_image":"","id_type":"1","idproof_number":"5698321478965","address":"Hyderabad","notification_status":"1","created_on":"2019-01-23 16:45:32","modified_on":"0000-00-00 00:00:00","delete_status":"1","status":"Active","local_service":"Maids","working_flats":"0","rating":null},{"id":"67","admin_id":"2","local_service_id":"2","security_id":"0","request_type":"1","image":"","title":"mahi","slug":"mahi","passcode":"4799970","date_of_joining":"2019-01-23","designation":"maid","mobile":"8989653265","timings":"1","id_image":"","id_type":"1","idproof_number":"7896541236","address":"Hyderabad","notification_status":"1","created_on":"2019-01-23 17:01:44","modified_on":"0000-00-00 00:00:00","delete_status":"1","status":"Active","local_service":"Maids","working_flats":"6","rating":null},{"id":"68","admin_id":"2","local_service_id":"2","security_id":"0","request_type":"1","image":"","title":"sailaja","slug":"sailaja","passcode":"3771355","date_of_joining":"2019-01-24","designation":"maid","mobile":"9632587412","timings":"2","id_image":"","id_type":"0","idproof_number":"9874563215","address":"Hyderabad","notification_status":"1","created_on":"2019-01-24 15:01:12","modified_on":"0000-00-00 00:00:00","delete_status":"1","status":"Active","local_service":"Maids","working_flats":"2","rating":null}]
     */

    private boolean status;
    private String message;

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    private boolean isClicked;

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
         * local_service_id : 2
         * security_id : 0
         * request_type : 1
         * image :
         * title : guru
         * slug : guru
         * passcode : 8842966
         * date_of_joining : 2018-11-28
         * designation : milk man
         * mobile : 7895855952
         * timings : 2,3,4
         * id_image :
         * id_type : 1
         * idproof_number : 12557455WWE
         * address : dasdadas
         * notification_status : 1
         * created_on : 2018-11-28 18:47:10
         * modified_on : 2018-11-28 18:52:04
         * delete_status : 1
         * status : Active
         * local_service : Maids
         * working_flats : 5
         * rating : 3
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
        private String local_service;
        private String working_flats;
        private String rating;

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

        public String getLocal_service() {
            return local_service;
        }

        public void setLocal_service(String local_service) {
            this.local_service = local_service;
        }

        public String getWorking_flats() {
            return working_flats;
        }

        public void setWorking_flats(String working_flats) {
            this.working_flats = working_flats;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }
    }
}
