package com.iprismech.alertnikkiresidence.pojo;

import java.util.List;

public class VisitorsHistoryPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * allow_records : [{"id":"1","user_type":"1","user_id":"1","admin_id":"2","service_type":"2","name":"subbu","mobile":"8500553103","from_date_time":"2018-11-28 16:30:46","to_date_time":"2018-11-28 19:30:46","alert_status":"1","created_on":"2018-11-28 16:30:46","modified_on":"0000-00-00 00:00:00","in_time":"15:00:00","out_time":"07:00:00","allow_deny_status":"1","reson_for_deny":"","service":"Delivery"},{"id":"1","otp_sent_type":"1","user_type":"1","user_id":"1","admin_id":"2","otp":"1115","name":"raju","mobile":"9490245547","vehicle_no":"1","from_date_time":"2018-11-27","created_on":"2018-11-23 16:36:12","modified_on":"0000-00-00 00:00:00","in_time":"15:14:00","out_time":"06:00:00","allow_deny_status":"1","reson_for_deny":""},{"id":"2","otp_sent_type":"1","user_type":"1","user_id":"1","admin_id":"2","otp":"1115","name":"srinivas","mobile":"9490245547","vehicle_no":"APSLCD001","from_date_time":"2018-11-27","created_on":"2018-11-23 16:36:12","modified_on":"0000-00-00 00:00:00","in_time":"14:00:00","out_time":"07:17:00","allow_deny_status":"1","reson_for_deny":""}]
     * deny_records : [{"id":"5","user_type":"1","user_id":"1","admin_id":"2","service_type":"2","name":"subbu","mobile":"8500553103","from_date_time":"2018-11-28 17:07:40","to_date_time":"2018-11-28 20:07:40","alert_status":"1","created_on":"2018-11-28 17:07:40","modified_on":"0000-00-00 00:00:00","in_time":"00:00:00","out_time":"00:00:00","allow_deny_status":"2","reson_for_deny":"not for me","service":"Delivery"}]
     */

    private boolean status;
    private String message;
    private List<AllowRecordsBean> allow_records;
    private List<DenyRecordsBean> deny_records;

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

    public List<AllowRecordsBean> getAllow_records() {
        return allow_records;
    }

    public void setAllow_records(List<AllowRecordsBean> allow_records) {
        this.allow_records = allow_records;
    }

    public List<DenyRecordsBean> getDeny_records() {
        return deny_records;
    }

    public void setDeny_records(List<DenyRecordsBean> deny_records) {
        this.deny_records = deny_records;
    }

    public static class AllowRecordsBean {
        /**
         * id : 1
         * user_type : 1
         * user_id : 1
         * admin_id : 2
         * service_type : 2
         * name : subbu
         * mobile : 8500553103
         * from_date_time : 2018-11-28 16:30:46
         * to_date_time : 2018-11-28 19:30:46
         * alert_status : 1
         * created_on : 2018-11-28 16:30:46
         * modified_on : 0000-00-00 00:00:00
         * in_time : 15:00:00
         * out_time : 07:00:00
         * allow_deny_status : 1
         * reson_for_deny :
         * service : Delivery
         * otp_sent_type : 1
         * otp : 1115
         * vehicle_no : 1
         */

        private String id;
        private String user_type;
        private String user_id;
        private String admin_id;
        private String service_type;
        private String name;
        private String mobile;
        private String from_date_time;
        private String to_date_time;
        private String alert_status;
        private String created_on;
        private String modified_on;
        private String in_time;
        private String out_time;
        private String allow_deny_status;
        private String reson_for_deny;
        private String service;
        private String otp_sent_type;
        private String otp;
        private String vehicle_no;

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

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getOtp_sent_type() {
            return otp_sent_type;
        }

        public void setOtp_sent_type(String otp_sent_type) {
            this.otp_sent_type = otp_sent_type;
        }

        public String getOtp() {
            return otp;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }

        public String getVehicle_no() {
            return vehicle_no;
        }

        public void setVehicle_no(String vehicle_no) {
            this.vehicle_no = vehicle_no;
        }
    }

    public static class DenyRecordsBean {
        /**
         * id : 5
         * user_type : 1
         * user_id : 1
         * admin_id : 2
         * service_type : 2
         * name : subbu
         * mobile : 8500553103
         * from_date_time : 2018-11-28 17:07:40
         * to_date_time : 2018-11-28 20:07:40
         * alert_status : 1
         * created_on : 2018-11-28 17:07:40
         * modified_on : 0000-00-00 00:00:00
         * in_time : 00:00:00
         * out_time : 00:00:00
         * allow_deny_status : 2
         * reson_for_deny : not for me
         * service : Delivery
         */

        private String id;
        private String user_type;
        private String user_id;
        private String admin_id;
        private String service_type;
        private String name;
        private String mobile;
        private String from_date_time;
        private String to_date_time;
        private String alert_status;
        private String created_on;
        private String modified_on;
        private String in_time;
        private String out_time;
        private String allow_deny_status;
        private String reson_for_deny;
        private String service;

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

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }
    }
}
