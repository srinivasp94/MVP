package com.iprismech.alertnikki.Pojo;

import java.util.List;

public class AlertsPojo {

    /**
     * status : true
     * message : Data fetched Successfully!
     * digital_gatepass_alerts : [{"user_type":"2","user_id":"2","list_id":"2","date":"2018-12-18","entry_in":"17:20:20","entry_out":"18:20:20","image":"storage/maids/5bfd15e9cec75.png","description":"test123","member":{"id":"2","user_id":"1","passcode":"4788852","name":"raju","mobile":"9490245540","relation":"Brother","blood_group":"O positive","vehicle_type":"","image":"","qrcode":"storage/qrcode/4788852.png","token":"","ios_token":"","status":"Active","delete_status":"1","created_on":"2018-12-04 18:03:22","modified_on":"0000-00-00 00:00:00","admin_id":"2","city":"hyderabad","society":"Malaysian Township","building":"Block D","flat":"100","member_type":"family_member"}}]
     * notify_gate_alerts : [{"user_type":"1","user_id":"1","notify_gate_id":"12","person_name":"sudhakar","person_mobile":"8500553107","from_date_time":"2018-12-17 20:00:48","to_date_time":"0000-00-00 00:00:00","service":"Delivery","member":{"id":"1","admin_id":"2","passcode":"5011352","name":"srinivas","mobile":"9490245547","email_id":"asrinivas433@gmail.com","blood_group":"O positive","password":"e10adc3949ba59abbe56e057f20f883e","otp":"0","image":"","qrcode":"","city_id":"4","society_id":"1","building_id":"1","flat_id":"1","residence_type_id":"1","token":"dcKygb0doOc:APA91bF3QxaydwVqb449iHR-GrFbZrmMG0fhhGZM4R9SWXOswMe9EA-qq8rwwaOIMc1tPh1uDhOvO2tvDXwHzD3BxbwhmMkxysG9dmysdcMiLolFp0H-URBwc8C2vT6m_qhDtk99ZB","ios_token":"","status":"Active","delete_status":"1","created_on":"2018-11-21 17:49:58","modified_on":"2018-11-23 13:19:29","otp_submit_status":"1","city":"hyderabad","society":"Malaysian Township","building":"Block D","flat":"100","residence_type":"Owner","member_type":"user"}},{"user_type":"1","user_id":"1","notify_gate_id":"13","person_name":"sudhakar","person_mobile":"8500553107","from_date_time":"2018-12-17 20:00:59","to_date_time":"0000-00-00 00:00:00","service":"Delivery","member":{"id":"1","admin_id":"2","passcode":"5011352","name":"srinivas","mobile":"9490245547","email_id":"asrinivas433@gmail.com","blood_group":"O positive","password":"e10adc3949ba59abbe56e057f20f883e","otp":"0","image":"","qrcode":"","city_id":"4","society_id":"1","building_id":"1","flat_id":"1","residence_type_id":"1","token":"dcKygb0doOc:APA91bF3QxaydwVqb449iHR-GrFbZrmMG0fhhGZM4R9SWXOswMe9EA-qq8rwwaOIMc1tPh1uDhOvO2tvDXwHzD3BxbwhmMkxysG9dmysdcMiLolFp0H-URBwc8C2vT6m_qhDtk99ZB","ios_token":"","status":"Active","delete_status":"1","created_on":"2018-11-21 17:49:58","modified_on":"2018-11-23 13:19:29","otp_submit_status":"1","city":"hyderabad","society":"Malaysian Township","building":"Block D","flat":"100","residence_type":"Owner","member_type":"user"}}]
     * kids_gate_alerts : [{"user_type":"1","user_id":"1","kid_name":"raghu","purpose":"dance","kid_going_with_whom":"sudhakar","in_time":"13:00:00","out_time":"14:40:00","member":{"id":"1","admin_id":"2","passcode":"5011352","name":"srinivas","mobile":"9490245547","email_id":"asrinivas433@gmail.com","blood_group":"O positive","password":"e10adc3949ba59abbe56e057f20f883e","otp":"0","image":"","qrcode":"","city_id":"4","society_id":"1","building_id":"1","flat_id":"1","residence_type_id":"1","token":"dcKygb0doOc:APA91bF3QxaydwVqb449iHR-GrFbZrmMG0fhhGZM4R9SWXOswMe9EA-qq8rwwaOIMc1tPh1uDhOvO2tvDXwHzD3BxbwhmMkxysG9dmysdcMiLolFp0H-URBwc8C2vT6m_qhDtk99ZB","ios_token":"","status":"Active","delete_status":"1","created_on":"2018-11-21 17:49:58","modified_on":"2018-11-23 13:19:29","otp_submit_status":"1","city":"hyderabad","society":"Malaysian Township","building":"Block D","flat":"100","residence_type":"Owner","member_type":"user"}}]
     */

    private boolean status;
    private String message;
    private List<DigitalGatepassAlertsBean> digital_gatepass_alerts;
    private List<NotifyGateAlertsBean> notify_gate_alerts;
    private List<KidsGateAlertsBean> kids_gate_alerts;
    private List<CommonNotify> commonNotifies;

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

    public List<DigitalGatepassAlertsBean> getDigital_gatepass_alerts() {
        return digital_gatepass_alerts;
    }

    public void setDigital_gatepass_alerts(List<DigitalGatepassAlertsBean> digital_gatepass_alerts) {
        this.digital_gatepass_alerts = digital_gatepass_alerts;
    }

    public List<NotifyGateAlertsBean> getNotify_gate_alerts() {
        return notify_gate_alerts;
    }

    public void setNotify_gate_alerts(List<NotifyGateAlertsBean> notify_gate_alerts) {
        this.notify_gate_alerts = notify_gate_alerts;
    }

    public List<KidsGateAlertsBean> getKids_gate_alerts() {
        return kids_gate_alerts;
    }

    public void setKids_gate_alerts(List<KidsGateAlertsBean> kids_gate_alerts) {
        this.kids_gate_alerts = kids_gate_alerts;
    }

    public static class DigitalGatepassAlertsBean {
        /**
         * user_type : 2
         * user_id : 2
         * list_id : 2
         * date : 2018-12-18
         * entry_in : 17:20:20
         * entry_out : 18:20:20
         * image : storage/maids/5bfd15e9cec75.png
         * description : test123
         * member : {"id":"2","user_id":"1","passcode":"4788852","name":"raju","mobile":"9490245540","relation":"Brother","blood_group":"O positive","vehicle_type":"","image":"","qrcode":"storage/qrcode/4788852.png","token":"","ios_token":"","status":"Active","delete_status":"1","created_on":"2018-12-04 18:03:22","modified_on":"0000-00-00 00:00:00","admin_id":"2","city":"hyderabad","society":"Malaysian Township","building":"Block D","flat":"100","member_type":"family_member"}
         */

        private String user_type;
        private String user_id;
        private String list_id;
        private String date;
        private String entry_in;
        private String entry_out;
        private String image;
        private String description;
        private MemberBean member;

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

        public String getList_id() {
            return list_id;
        }

        public void setList_id(String list_id) {
            this.list_id = list_id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getEntry_in() {
            return entry_in;
        }

        public void setEntry_in(String entry_in) {
            this.entry_in = entry_in;
        }

        public String getEntry_out() {
            return entry_out;
        }

        public void setEntry_out(String entry_out) {
            this.entry_out = entry_out;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public MemberBean getMember() {
            return member;
        }

        public void setMember(MemberBean member) {
            this.member = member;
        }

        public static class MemberBean {
            /**
             * id : 2
             * user_id : 1
             * passcode : 4788852
             * name : raju
             * mobile : 9490245540
             * relation : Brother
             * blood_group : O positive
             * vehicle_type :
             * image :
             * qrcode : storage/qrcode/4788852.png
             * token :
             * ios_token :
             * status : Active
             * delete_status : 1
             * created_on : 2018-12-04 18:03:22
             * modified_on : 0000-00-00 00:00:00
             * admin_id : 2
             * city : hyderabad
             * society : Malaysian Township
             * building : Block D
             * flat : 100
             * member_type : family_member
             */

            private String id;
            private String user_id;
            private String passcode;
            private String name;
            private String mobile;
            private String relation;
            private String blood_group;
            private String vehicle_type;
            private String image;
            private String qrcode;
            private String token;
            private String ios_token;
            private String status;
            private String delete_status;
            private String created_on;
            private String modified_on;
            private String admin_id;
            private String city;
            private String society;
            private String building;
            private String flat;
            private String member_type;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
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

            public String getRelation() {
                return relation;
            }

            public void setRelation(String relation) {
                this.relation = relation;
            }

            public String getBlood_group() {
                return blood_group;
            }

            public void setBlood_group(String blood_group) {
                this.blood_group = blood_group;
            }

            public String getVehicle_type() {
                return vehicle_type;
            }

            public void setVehicle_type(String vehicle_type) {
                this.vehicle_type = vehicle_type;
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

            public String getAdmin_id() {
                return admin_id;
            }

            public void setAdmin_id(String admin_id) {
                this.admin_id = admin_id;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getSociety() {
                return society;
            }

            public void setSociety(String society) {
                this.society = society;
            }

            public String getBuilding() {
                return building;
            }

            public void setBuilding(String building) {
                this.building = building;
            }

            public String getFlat() {
                return flat;
            }

            public void setFlat(String flat) {
                this.flat = flat;
            }

            public String getMember_type() {
                return member_type;
            }

            public void setMember_type(String member_type) {
                this.member_type = member_type;
            }
        }
    }

    public static class NotifyGateAlertsBean {
        /**
         * user_type : 1
         * user_id : 1
         * notify_gate_id : 12
         * person_name : sudhakar
         * person_mobile : 8500553107
         * from_date_time : 2018-12-17 20:00:48
         * to_date_time : 0000-00-00 00:00:00
         * service : Delivery
         * member : {"id":"1","admin_id":"2","passcode":"5011352","name":"srinivas","mobile":"9490245547","email_id":"asrinivas433@gmail.com","blood_group":"O positive","password":"e10adc3949ba59abbe56e057f20f883e","otp":"0","image":"","qrcode":"","city_id":"4","society_id":"1","building_id":"1","flat_id":"1","residence_type_id":"1","token":"dcKygb0doOc:APA91bF3QxaydwVqb449iHR-GrFbZrmMG0fhhGZM4R9SWXOswMe9EA-qq8rwwaOIMc1tPh1uDhOvO2tvDXwHzD3BxbwhmMkxysG9dmysdcMiLolFp0H-URBwc8C2vT6m_qhDtk99ZB","ios_token":"","status":"Active","delete_status":"1","created_on":"2018-11-21 17:49:58","modified_on":"2018-11-23 13:19:29","otp_submit_status":"1","city":"hyderabad","society":"Malaysian Township","building":"Block D","flat":"100","residence_type":"Owner","member_type":"user"}
         */

        private String user_type;
        private String user_id;
        private String notify_gate_id;
        private String person_name;
        private String person_mobile;
        private String from_date_time;
        private String to_date_time;
        private String service;
        private MemberBeanX member;

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

        public String getNotify_gate_id() {
            return notify_gate_id;
        }

        public void setNotify_gate_id(String notify_gate_id) {
            this.notify_gate_id = notify_gate_id;
        }

        public String getPerson_name() {
            return person_name;
        }

        public void setPerson_name(String person_name) {
            this.person_name = person_name;
        }

        public String getPerson_mobile() {
            return person_mobile;
        }

        public void setPerson_mobile(String person_mobile) {
            this.person_mobile = person_mobile;
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

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public MemberBeanX getMember() {
            return member;
        }

        public void setMember(MemberBeanX member) {
            this.member = member;
        }

        public static class MemberBeanX {
            /**
             * id : 1
             * admin_id : 2
             * passcode : 5011352
             * name : srinivas
             * mobile : 9490245547
             * email_id : asrinivas433@gmail.com
             * blood_group : O positive
             * password : e10adc3949ba59abbe56e057f20f883e
             * otp : 0
             * image :
             * qrcode :
             * city_id : 4
             * society_id : 1
             * building_id : 1
             * flat_id : 1
             * residence_type_id : 1
             * token : dcKygb0doOc:APA91bF3QxaydwVqb449iHR-GrFbZrmMG0fhhGZM4R9SWXOswMe9EA-qq8rwwaOIMc1tPh1uDhOvO2tvDXwHzD3BxbwhmMkxysG9dmysdcMiLolFp0H-URBwc8C2vT6m_qhDtk99ZB
             * ios_token :
             * status : Active
             * delete_status : 1
             * created_on : 2018-11-21 17:49:58
             * modified_on : 2018-11-23 13:19:29
             * otp_submit_status : 1
             * city : hyderabad
             * society : Malaysian Township
             * building : Block D
             * flat : 100
             * residence_type : Owner
             * member_type : user
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
            private String residence_type_id;
            private String token;
            private String ios_token;
            private String status;
            private String delete_status;
            private String created_on;
            private String modified_on;
            private String otp_submit_status;
            private String city;
            private String society;
            private String building;
            private String flat;
            private String residence_type;
            private String member_type;

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

            public String getResidence_type_id() {
                return residence_type_id;
            }

            public void setResidence_type_id(String residence_type_id) {
                this.residence_type_id = residence_type_id;
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

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getSociety() {
                return society;
            }

            public void setSociety(String society) {
                this.society = society;
            }

            public String getBuilding() {
                return building;
            }

            public void setBuilding(String building) {
                this.building = building;
            }

            public String getFlat() {
                return flat;
            }

            public void setFlat(String flat) {
                this.flat = flat;
            }

            public String getResidence_type() {
                return residence_type;
            }

            public void setResidence_type(String residence_type) {
                this.residence_type = residence_type;
            }

            public String getMember_type() {
                return member_type;
            }

            public void setMember_type(String member_type) {
                this.member_type = member_type;
            }
        }
    }

    public static class KidsGateAlertsBean {
        /**
         * user_type : 1
         * user_id : 1
         * kid_name : raghu
         * purpose : dance
         * kid_going_with_whom : sudhakar
         * in_time : 13:00:00
         * out_time : 14:40:00
         * member : {"id":"1","admin_id":"2","passcode":"5011352","name":"srinivas","mobile":"9490245547","email_id":"asrinivas433@gmail.com","blood_group":"O positive","password":"e10adc3949ba59abbe56e057f20f883e","otp":"0","image":"","qrcode":"","city_id":"4","society_id":"1","building_id":"1","flat_id":"1","residence_type_id":"1","token":"dcKygb0doOc:APA91bF3QxaydwVqb449iHR-GrFbZrmMG0fhhGZM4R9SWXOswMe9EA-qq8rwwaOIMc1tPh1uDhOvO2tvDXwHzD3BxbwhmMkxysG9dmysdcMiLolFp0H-URBwc8C2vT6m_qhDtk99ZB","ios_token":"","status":"Active","delete_status":"1","created_on":"2018-11-21 17:49:58","modified_on":"2018-11-23 13:19:29","otp_submit_status":"1","city":"hyderabad","society":"Malaysian Township","building":"Block D","flat":"100","residence_type":"Owner","member_type":"user"}
         */

        private String user_type;
        private String user_id;
        private String kid_name;
        private String purpose;
        private String kid_going_with_whom;
        private String in_time;
        private String out_time;
        private MemberBeanXX member;

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

        public String getKid_name() {
            return kid_name;
        }

        public void setKid_name(String kid_name) {
            this.kid_name = kid_name;
        }

        public String getPurpose() {
            return purpose;
        }

        public void setPurpose(String purpose) {
            this.purpose = purpose;
        }

        public String getKid_going_with_whom() {
            return kid_going_with_whom;
        }

        public void setKid_going_with_whom(String kid_going_with_whom) {
            this.kid_going_with_whom = kid_going_with_whom;
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

        public MemberBeanXX getMember() {
            return member;
        }

        public void setMember(MemberBeanXX member) {
            this.member = member;
        }

        public static class MemberBeanXX {
            /**
             * id : 1
             * admin_id : 2
             * passcode : 5011352
             * name : srinivas
             * mobile : 9490245547
             * email_id : asrinivas433@gmail.com
             * blood_group : O positive
             * password : e10adc3949ba59abbe56e057f20f883e
             * otp : 0
             * image :
             * qrcode :
             * city_id : 4
             * society_id : 1
             * building_id : 1
             * flat_id : 1
             * residence_type_id : 1
             * token : dcKygb0doOc:APA91bF3QxaydwVqb449iHR-GrFbZrmMG0fhhGZM4R9SWXOswMe9EA-qq8rwwaOIMc1tPh1uDhOvO2tvDXwHzD3BxbwhmMkxysG9dmysdcMiLolFp0H-URBwc8C2vT6m_qhDtk99ZB
             * ios_token :
             * status : Active
             * delete_status : 1
             * created_on : 2018-11-21 17:49:58
             * modified_on : 2018-11-23 13:19:29
             * otp_submit_status : 1
             * city : hyderabad
             * society : Malaysian Township
             * building : Block D
             * flat : 100
             * residence_type : Owner
             * member_type : user
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
            private String residence_type_id;
            private String token;
            private String ios_token;
            private String status;
            private String delete_status;
            private String created_on;
            private String modified_on;
            private String otp_submit_status;
            private String city;
            private String society;
            private String building;
            private String flat;
            private String residence_type;
            private String member_type;

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

            public String getResidence_type_id() {
                return residence_type_id;
            }

            public void setResidence_type_id(String residence_type_id) {
                this.residence_type_id = residence_type_id;
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

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getSociety() {
                return society;
            }

            public void setSociety(String society) {
                this.society = society;
            }

            public String getBuilding() {
                return building;
            }

            public void setBuilding(String building) {
                this.building = building;
            }

            public String getFlat() {
                return flat;
            }

            public void setFlat(String flat) {
                this.flat = flat;
            }

            public String getResidence_type() {
                return residence_type;
            }

            public void setResidence_type(String residence_type) {
                this.residence_type = residence_type;
            }

            public String getMember_type() {
                return member_type;
            }

            public void setMember_type(String member_type) {
                this.member_type = member_type;
            }
        }
}
    public static class CommonNotify {
        public String date_common, service, description_common, type_notif;
        private MemberBeanXCommon member;
        public String getDate_common() {
            return date_common;
        }

        public void setDate_common(String date_common) {
            this.date_common = date_common;
        }
        public String getService() {
            return service;
        }
        public void setService(String service) {
            this.service = service;
        }
        public String getDescription_common() {
            return description_common;
        }
        public void setDescription_common(String description_common) {
            this.description_common = description_common;
        }

        public String getType_notif() {
            return type_notif;
        }

        public void setType_notif(String type_notif) {
            this.type_notif = type_notif;
        }

        public MemberBeanXCommon getMember() {
            return member;
        }

        public void setMember(MemberBeanXCommon member) {
            this.member = member;
        }




        public static class MemberBeanXCommon {
            String passcode_common;
            String name_common;
            String mobile_common;
            String society_com;
            String flat_com;
            String building_com;

            public String getPasscode_common() {
                return passcode_common;
            }

            public void setPasscode_common(String passcode_common) {
                this.passcode_common = passcode_common;
            }

            public String getName_common() {
                return name_common;
            }

            public void setName_common(String name_common) {
                this.name_common = name_common;
            }

            public String getMobile_common() {
                return mobile_common;
            }

            public void setMobile_common(String mobile_common) {
                this.mobile_common = mobile_common;
            }

            public String getSociety_com() {
                return society_com;
            }

            public void setSociety_com(String society_com) {
                this.society_com = society_com;
            }

            public String getFlat_com() {
                return flat_com;
            }

            public void setFlat_com(String flat_com) {
                this.flat_com = flat_com;
            }

            public String getBuilding_com() {
                return building_com;
            }

            public void setBuilding_com(String building_com) {
                this.building_com = building_com;
            }

            public String getResident_type_com() {
                return resident_type_com;
            }

            public void setResident_type_com(String resident_type_com) {
                this.resident_type_com = resident_type_com;
            }

            public String getMembere_type_commom() {
                return membere_type_commom;
            }

            public void setMembere_type_commom(String membere_type_commom) {
                this.membere_type_commom = membere_type_commom;
            }

            public String getId_common() {
                return id_common;
            }

            public void setId_common(String id_common) {
                this.id_common = id_common;
            }

            String resident_type_com;
            String membere_type_commom;
            String id_common;
        }
    }
}
