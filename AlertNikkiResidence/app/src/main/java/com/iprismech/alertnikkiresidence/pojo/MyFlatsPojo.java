package com.iprismech.alertnikkiresidence.pojo;

import java.util.List;

public class MyFlatsPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"user_id":"24","city":"hyderabad","society":"Prime Towers","building":"Block A","flat":"1001","residence_type":"Owner","flats":"","flat_id":"4"}]
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
         * user_id : 24
         * city : hyderabad
         * society : Prime Towers
         * building : Block A
         * flat : 1001
         * residence_type : Owner
         * flats :
         * flat_id : 4
         */

        private String user_id;
        private String city;
        private String society;
        private String building;
        private String flat;
        private String residence_type;
        private String flats;
        private String flat_id;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
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

        public String getFlats() {
            return flats;
        }

        public void setFlats(String flats) {
            this.flats = flats;
        }

        public String getFlat_id() {
            return flat_id;
        }

        public void setFlat_id(String flat_id) {
            this.flat_id = flat_id;
        }
    }
}
