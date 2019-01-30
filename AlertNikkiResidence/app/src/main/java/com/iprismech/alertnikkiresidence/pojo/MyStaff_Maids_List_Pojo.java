package com.iprismech.alertnikkiresidence.pojo;

import java.util.List;

public class MyStaff_Maids_List_Pojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"83","user_type":"1","user_id":"22","flat_id":"2","admin_id":"2","maid_id":"63","available_timings":"5,4","working_type":"1","from_date":"0000-00-00","to_date":"0000-00-00","notification_status":"0","created_on":"2019-01-23 23:08:35","modified_on":"0000-00-00 00:00:00","delete_status":"1","maid_designation":"paper boy","passcode":"6483773","mobile":"9874563212","maid_name":"nikki","working_flats":"2","rating":"3.00","in_time":null,"out_time":null},{"id":"111","user_type":"1","user_id":"22","flat_id":"2","admin_id":"2","maid_id":"13","available_timings":"20","working_type":"1","from_date":"0000-00-00","to_date":"0000-00-00","notification_status":"1","created_on":"2019-01-24 20:22:06","modified_on":"0000-00-00 00:00:00","delete_status":"1","maid_designation":"helper","passcode":"5430945","mobile":"9490245511","maid_name":"TEST","working_flats":"1","rating":"","in_time":null,"out_time":null},{"id":"115","user_type":"1","user_id":"22","flat_id":"2","admin_id":"2","maid_id":"1","available_timings":"8","working_type":"1","from_date":"0000-00-00","to_date":"0000-00-00","notification_status":"1","created_on":"2019-01-24 20:44:55","modified_on":"0000-00-00 00:00:00","delete_status":"1","maid_designation":"teacer","passcode":"6160428","mobile":"9490245547","maid_name":"srinivas","working_flats":"3","rating":"5.00","in_time":"03:18:44 PM","out_time":"06:12:33 PM"},{"id":"125","user_type":"1","user_id":"22","flat_id":"2","admin_id":"2","maid_id":"4","available_timings":"17","working_type":"1","from_date":"0000-00-00","to_date":"0000-00-00","notification_status":"1","created_on":"2019-01-26 16:18:40","modified_on":"0000-00-00 00:00:00","delete_status":"1","maid_designation":"milk man","passcode":"8842966","mobile":"7895855952","maid_name":"guru","working_flats":"2","rating":"3.00","in_time":"04:11:34 PM","out_time":"04:15:14 PM"}]
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
         * id : 83
         * user_type : 1
         * user_id : 22
         * flat_id : 2
         * admin_id : 2
         * maid_id : 63
         * available_timings : 5,4
         * working_type : 1
         * from_date : 0000-00-00
         * to_date : 0000-00-00
         * notification_status : 0
         * created_on : 2019-01-23 23:08:35
         * modified_on : 0000-00-00 00:00:00
         * delete_status : 1
         * maid_designation : paper boy
         * passcode : 6483773
         * mobile : 9874563212
         * maid_name : nikki
         * working_flats : 2
         * rating : 3.00
         * in_time : null
         * out_time : null
         */

        private String id;
        private String user_type;
        private String user_id;
        private String flat_id;
        private String admin_id;
        private String maid_id;
        private String available_timings;
        private String working_type;
        private String from_date;
        private String to_date;
        private String notification_status;
        private String created_on;
        private String modified_on;
        private String delete_status;
        private String maid_designation;
        private String passcode;
        private String mobile;
        private String maid_name;
        private String working_flats;
        private String rating;
        private Object in_time;
        private Object out_time;

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

        public String getFlat_id() {
            return flat_id;
        }

        public void setFlat_id(String flat_id) {
            this.flat_id = flat_id;
        }

        public String getAdmin_id() {
            return admin_id;
        }

        public void setAdmin_id(String admin_id) {
            this.admin_id = admin_id;
        }

        public String getMaid_id() {
            return maid_id;
        }

        public void setMaid_id(String maid_id) {
            this.maid_id = maid_id;
        }

        public String getAvailable_timings() {
            return available_timings;
        }

        public void setAvailable_timings(String available_timings) {
            this.available_timings = available_timings;
        }

        public String getWorking_type() {
            return working_type;
        }

        public void setWorking_type(String working_type) {
            this.working_type = working_type;
        }

        public String getFrom_date() {
            return from_date;
        }

        public void setFrom_date(String from_date) {
            this.from_date = from_date;
        }

        public String getTo_date() {
            return to_date;
        }

        public void setTo_date(String to_date) {
            this.to_date = to_date;
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

        public String getMaid_designation() {
            return maid_designation;
        }

        public void setMaid_designation(String maid_designation) {
            this.maid_designation = maid_designation;
        }

        public String getPasscode() {
            return passcode;
        }

        public void setPasscode(String passcode) {
            this.passcode = passcode;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getMaid_name() {
            return maid_name;
        }

        public void setMaid_name(String maid_name) {
            this.maid_name = maid_name;
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

        public Object getIn_time() {
            return in_time;
        }

        public void setIn_time(Object in_time) {
            this.in_time = in_time;
        }

        public Object getOut_time() {
            return out_time;
        }

        public void setOut_time(Object out_time) {
            this.out_time = out_time;
        }
    }
}
