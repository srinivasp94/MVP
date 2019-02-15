package com.iprismech.alertnikkiresidence.pojo;

public class ForgotPasswordPojo {


    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : {"id":"24","admin_id":"2","passcode":"2865097","name":"Surekha","mobile":"9030438354","email_id":"Satya","blood_group":"b+ve","password":"3dbac701c3c3f89072447f63a615087f","otp":"212963","image":"storage/users/5c542debc2e9c.jpeg","qrcode":"storage/qrcode/2865097.png","city_id":"4","society_id":"2","building_id":"4","flat_id":"4","flats":"","residence_type_id":"1","invite_guest":"yes","my_staff":"yes","society_gate":"yes","school_bus":"yes","kids_gatepass":"yes","token":"","ios_token":"fiw24H5-_vc:APA91bGMUM_XE_2ieUMz7g1xWdKtnzlWvB1O-cV9Ht4wRD6T-15FPuhWjFuQEstcWX82UO_gkwB7-mPr5JrSdbsXfdwvBdORZ75BljzFvjuyz39HfVrm93E64uLYB7RDTSZWO1zH7PIv","status":"Active","delete_status":"1","created_on":"2019-01-22 14:45:51","modified_on":"2019-02-12 10:18:38","otp_submit_status":"1"}
     * OTP : {"otp":524736}
     */

    private boolean status;
    private String message;
    private ResponseBean response;
    private OTPBean OTP;

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

    public OTPBean getOTP() {
        return OTP;
    }

    public void setOTP(OTPBean OTP) {
        this.OTP = OTP;
    }

    public static class ResponseBean {
        /**
         * id : 24
         * admin_id : 2
         * passcode : 2865097
         * name : Surekha
         * mobile : 9030438354
         * email_id : Satya
         * blood_group : b+ve
         * password : 3dbac701c3c3f89072447f63a615087f
         * otp : 212963
         * image : storage/users/5c542debc2e9c.jpeg
         * qrcode : storage/qrcode/2865097.png
         * city_id : 4
         * society_id : 2
         * building_id : 4
         * flat_id : 4
         * flats :
         * residence_type_id : 1
         * invite_guest : yes
         * my_staff : yes
         * society_gate : yes
         * school_bus : yes
         * kids_gatepass : yes
         * token :
         * ios_token : fiw24H5-_vc:APA91bGMUM_XE_2ieUMz7g1xWdKtnzlWvB1O-cV9Ht4wRD6T-15FPuhWjFuQEstcWX82UO_gkwB7-mPr5JrSdbsXfdwvBdORZ75BljzFvjuyz39HfVrm93E64uLYB7RDTSZWO1zH7PIv
         * status : Active
         * delete_status : 1
         * created_on : 2019-01-22 14:45:51
         * modified_on : 2019-02-12 10:18:38
         * otp_submit_status : 1
         */

        private String id;
        private String admin_id;
        private String passcode;
        private String name;
        private String mobile;
        private String email_id;
        private String blood_group;
        private String password;
        private String otp;
        private String image;
        private String qrcode;
        private String city_id;
        private String society_id;
        private String building_id;
        private String flat_id;
        private String flats;
        private String residence_type_id;
        private String invite_guest;
        private String my_staff;
        private String society_gate;
        private String school_bus;
        private String kids_gatepass;
        private String token;
        private String ios_token;
        private String status;
        private String delete_status;
        private String created_on;
        private String modified_on;
        private String otp_submit_status;

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

        public String getPasscode() {
            return passcode;
        }

        public void setPasscode(String passcode) {
            this.passcode = passcode;
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

        public String getEmail_id() {
            return email_id;
        }

        public void setEmail_id(String email_id) {
            this.email_id = email_id;
        }

        public String getBlood_group() {
            return blood_group;
        }

        public void setBlood_group(String blood_group) {
            this.blood_group = blood_group;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getOtp() {
            return otp;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getSociety_id() {
            return society_id;
        }

        public void setSociety_id(String society_id) {
            this.society_id = society_id;
        }

        public String getBuilding_id() {
            return building_id;
        }

        public void setBuilding_id(String building_id) {
            this.building_id = building_id;
        }

        public String getFlat_id() {
            return flat_id;
        }

        public void setFlat_id(String flat_id) {
            this.flat_id = flat_id;
        }

        public String getFlats() {
            return flats;
        }

        public void setFlats(String flats) {
            this.flats = flats;
        }

        public String getResidence_type_id() {
            return residence_type_id;
        }

        public void setResidence_type_id(String residence_type_id) {
            this.residence_type_id = residence_type_id;
        }

        public String getInvite_guest() {
            return invite_guest;
        }

        public void setInvite_guest(String invite_guest) {
            this.invite_guest = invite_guest;
        }

        public String getMy_staff() {
            return my_staff;
        }

        public void setMy_staff(String my_staff) {
            this.my_staff = my_staff;
        }

        public String getSociety_gate() {
            return society_gate;
        }

        public void setSociety_gate(String society_gate) {
            this.society_gate = society_gate;
        }

        public String getSchool_bus() {
            return school_bus;
        }

        public void setSchool_bus(String school_bus) {
            this.school_bus = school_bus;
        }

        public String getKids_gatepass() {
            return kids_gatepass;
        }

        public void setKids_gatepass(String kids_gatepass) {
            this.kids_gatepass = kids_gatepass;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getIos_token() {
            return ios_token;
        }

        public void setIos_token(String ios_token) {
            this.ios_token = ios_token;
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

        public String getOtp_submit_status() {
            return otp_submit_status;
        }

        public void setOtp_submit_status(String otp_submit_status) {
            this.otp_submit_status = otp_submit_status;
        }
    }

    public static class OTPBean {
        /**
         * otp : 524736
         */

        private int otp;

        public int getOtp() {
            return otp;
        }

        public void setOtp(int otp) {
            this.otp = otp;
        }
    }
}
