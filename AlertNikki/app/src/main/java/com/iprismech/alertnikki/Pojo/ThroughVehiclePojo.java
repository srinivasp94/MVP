package com.iprismech.alertnikki.Pojo;

import java.util.List;

public class ThroughVehiclePojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"22","user_type":"1","user_id":"1","admin_id":"2","service_type":"1","image":"","delivery_from":"","service_from":"Cab","vehicle_no":"TS30CD1234","name":"Srinivas","mobile":"1234646998","from_date":"2018-12-25","from_date_time":"2018-12-24 19:49:42","to_date_time":"0000-00-00 00:00:00","alert_status":"0","no_of_persons":"0","created_on":"2018-12-24 19:49:42","modified_on":"2018-12-25 18:24:00","in_date":"0000-00-00","in_time":"18:15:54","out_time":"0000-00-00 00:00:00","allow_deny_status":"1","reson_for_deny":"","in_out_status":"2","cron_alert":"0","allow_security_id":"4","security_accept_status":"2","primary_call_status":"0","secondary_call_status":"0"},{"id":"21","user_type":"1","user_id":"1","admin_id":"2","service_type":"2","image":"","delivery_from":"Delivery Boy","service_from":"","vehicle_no":"TS60CD1234","name":"Srinivas","mobile":"8888888888","from_date":"2018-12-25","from_date_time":"2018-12-24 19:37:50","to_date_time":"0000-00-00 00:00:00","alert_status":"0","no_of_persons":"0","created_on":"2018-12-24 19:37:50","modified_on":"2018-12-25 18:12:06","in_date":"0000-00-00","in_time":"18:12:06","out_time":"0000-00-00 00:00:00","allow_deny_status":"1","reson_for_deny":"","in_out_status":"1","cron_alert":"0","allow_security_id":"4","security_accept_status":"2","primary_call_status":"1","secondary_call_status":"0"},{"id":"20","user_type":"1","user_id":"1","admin_id":"2","service_type":"2","image":"","delivery_from":"Delivery Boy","service_from":"","vehicle_no":"TS30CD1234","name":"Srinivas","mobile":"8888888800","from_date":"2018-12-25","from_date_time":"2018-12-24 19:34:28","to_date_time":"0000-00-00 00:00:00","alert_status":"0","no_of_persons":"0","created_on":"2018-12-24 19:34:28","modified_on":"2018-12-25 18:24:31","in_date":"0000-00-00","in_time":"18:24:31","out_time":"0000-00-00 00:00:00","allow_deny_status":"1","reson_for_deny":"","in_out_status":"1","cron_alert":"0","allow_security_id":"4","security_accept_status":"2","primary_call_status":"0","secondary_call_status":"0"},{"id":"19","user_type":"1","user_id":"1","admin_id":"2","service_type":"1","image":"","delivery_from":"","service_from":"Cab","vehicle_no":"TS30CD1234","name":"Srinivas","mobile":"8888888880","from_date":"2018-12-24","from_date_time":"2018-12-24 19:33:52","to_date_time":"0000-00-00 00:00:00","alert_status":"0","no_of_persons":"0","created_on":"2018-12-24 19:33:52","modified_on":"0000-00-00 00:00:00","in_date":"0000-00-00","in_time":"00:00:00","out_time":"0000-00-00 00:00:00","allow_deny_status":"0","reson_for_deny":"","in_out_status":"0","cron_alert":"0","allow_security_id":"0","security_accept_status":"1","primary_call_status":"0","secondary_call_status":"0"},{"id":"18","user_type":"1","user_id":"1","admin_id":"2","service_type":"2","image":"","delivery_from":"flip_cart","service_from":"","vehicle_no":"TS30CD1234","name":"Srinivas","mobile":"8888888888","from_date":"2018-12-24","from_date_time":"2018-12-24 19:21:24","to_date_time":"0000-00-00 00:00:00","alert_status":"0","no_of_persons":"0","created_on":"2018-12-24 19:21:24","modified_on":"0000-00-00 00:00:00","in_date":"0000-00-00","in_time":"00:00:00","out_time":"0000-00-00 00:00:00","allow_deny_status":"0","reson_for_deny":"","in_out_status":"0","cron_alert":"0","allow_security_id":"0","security_accept_status":"1","primary_call_status":"0","secondary_call_status":"0"},{"id":"17","user_type":"1","user_id":"1","admin_id":"2","service_type":"2","image":"","delivery_from":"flip_cart","service_from":"","vehicle_no":"TS30CD1234","name":"Srinivas","mobile":"8888888888","from_date":"2018-12-24","from_date_time":"2018-12-24 19:20:18","to_date_time":"0000-00-00 00:00:00","alert_status":"0","no_of_persons":"0","created_on":"2018-12-24 19:20:18","modified_on":"0000-00-00 00:00:00","in_date":"0000-00-00","in_time":"00:00:00","out_time":"0000-00-00 00:00:00","allow_deny_status":"0","reson_for_deny":"","in_out_status":"0","cron_alert":"0","allow_security_id":"0","security_accept_status":"1","primary_call_status":"0","secondary_call_status":"0"},{"id":"16","user_type":"1","user_id":"1","admin_id":"2","service_type":"2","image":"","delivery_from":"flip_cart","service_from":"","vehicle_no":"TS30CD1234","name":"Srinivas","mobile":"8888888888","from_date":"2018-12-24","from_date_time":"2018-12-24 19:04:45","to_date_time":"0000-00-00 00:00:00","alert_status":"0","no_of_persons":"0","created_on":"2018-12-24 19:04:45","modified_on":"0000-00-00 00:00:00","in_date":"0000-00-00","in_time":"00:00:00","out_time":"0000-00-00 00:00:00","allow_deny_status":"0","reson_for_deny":"","in_out_status":"0","cron_alert":"0","allow_security_id":"0","security_accept_status":"1","primary_call_status":"0","secondary_call_status":"0"}]
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
         * id : 22
         * user_type : 1
         * user_id : 1
         * admin_id : 2
         * service_type : 1
         * image :
         * delivery_from :
         * service_from : Cab
         * vehicle_no : TS30CD1234
         * name : Srinivas
         * mobile : 1234646998
         * from_date : 2018-12-25
         * from_date_time : 2018-12-24 19:49:42
         * to_date_time : 0000-00-00 00:00:00
         * alert_status : 0
         * no_of_persons : 0
         * created_on : 2018-12-24 19:49:42
         * modified_on : 2018-12-25 18:24:00
         * in_date : 0000-00-00
         * in_time : 18:15:54
         * out_time : 0000-00-00 00:00:00
         * allow_deny_status : 1
         * reson_for_deny :
         * in_out_status : 2
         * cron_alert : 0
         * allow_security_id : 4
         * security_accept_status : 2
         * primary_call_status : 0
         * secondary_call_status : 0
         */

        private String id;
        private String user_type;
        private String user_id;
        private String admin_id;
        private String service_type;
        private String image;
        private String delivery_from;
        private String service_from;
        private String vehicle_no;
        private String name;
        private String mobile;
        private String from_date;
        private String from_date_time;
        private String to_date_time;
        private String alert_status;
        private String no_of_persons;
        private String created_on;
        private String modified_on;
        private String in_date;
        private String in_time;
        private String out_time;
        private String allow_deny_status;
        private String reson_for_deny;
        private String in_out_status;
        private String cron_alert;
        private String allow_security_id;
        private String security_accept_status;
        private String primary_call_status;
        private String secondary_call_status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getAdmin_id() {
            return admin_id;
        }

        public void setAdmin_id(String admin_id) {
            this.admin_id = admin_id;
        }

        public String getService_type() {
            return service_type;
        }

        public void setService_type(String service_type) {
            this.service_type = service_type;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDelivery_from() {
            return delivery_from;
        }

        public void setDelivery_from(String delivery_from) {
            this.delivery_from = delivery_from;
        }

        public String getService_from() {
            return service_from;
        }

        public void setService_from(String service_from) {
            this.service_from = service_from;
        }

        public String getVehicle_no() {
            return vehicle_no;
        }

        public void setVehicle_no(String vehicle_no) {
            this.vehicle_no = vehicle_no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getFrom_date() {
            return from_date;
        }

        public void setFrom_date(String from_date) {
            this.from_date = from_date;
        }

        public String getFrom_date_time() {
            return from_date_time;
        }

        public void setFrom_date_time(String from_date_time) {
            this.from_date_time = from_date_time;
        }

        public String getTo_date_time() {
            return to_date_time;
        }

        public void setTo_date_time(String to_date_time) {
            this.to_date_time = to_date_time;
        }

        public String getAlert_status() {
            return alert_status;
        }

        public void setAlert_status(String alert_status) {
            this.alert_status = alert_status;
        }

        public String getNo_of_persons() {
            return no_of_persons;
        }

        public void setNo_of_persons(String no_of_persons) {
            this.no_of_persons = no_of_persons;
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

        public String getIn_date() {
            return in_date;
        }

        public void setIn_date(String in_date) {
            this.in_date = in_date;
        }

        public String getIn_time() {
            return in_time;
        }

        public void setIn_time(String in_time) {
            this.in_time = in_time;
        }

        public String getOut_time() {
            return out_time;
        }

        public void setOut_time(String out_time) {
            this.out_time = out_time;
        }

        public String getAllow_deny_status() {
            return allow_deny_status;
        }

        public void setAllow_deny_status(String allow_deny_status) {
            this.allow_deny_status = allow_deny_status;
        }

        public String getReson_for_deny() {
            return reson_for_deny;
        }

        public void setReson_for_deny(String reson_for_deny) {
            this.reson_for_deny = reson_for_deny;
        }

        public String getIn_out_status() {
            return in_out_status;
        }

        public void setIn_out_status(String in_out_status) {
            this.in_out_status = in_out_status;
        }

        public String getCron_alert() {
            return cron_alert;
        }

        public void setCron_alert(String cron_alert) {
            this.cron_alert = cron_alert;
        }

        public String getAllow_security_id() {
            return allow_security_id;
        }

        public void setAllow_security_id(String allow_security_id) {
            this.allow_security_id = allow_security_id;
        }

        public String getSecurity_accept_status() {
            return security_accept_status;
        }

        public void setSecurity_accept_status(String security_accept_status) {
            this.security_accept_status = security_accept_status;
        }

        public String getPrimary_call_status() {
            return primary_call_status;
        }

        public void setPrimary_call_status(String primary_call_status) {
            this.primary_call_status = primary_call_status;
        }

        public String getSecondary_call_status() {
            return secondary_call_status;
        }

        public void setSecondary_call_status(String secondary_call_status) {
            this.secondary_call_status = secondary_call_status;
        }
    }
}
