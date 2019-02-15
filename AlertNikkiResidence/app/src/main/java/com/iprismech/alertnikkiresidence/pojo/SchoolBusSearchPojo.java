package com.iprismech.alertnikkiresidence.pojo;

import java.util.List;

public class SchoolBusSearchPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"1","admin_id":"2","flat_id":"0","request_type":"1","school_bus_name":"Vidya Education school bus","driver_name":"subbaraju","mobile":"7093668623","vehicle_number":"APS145566","address":"TEST","status":"Active","created_on":"2018-11-29 13:16:22","modified_on":"2018-11-29 13:22:43","delete_status":"1"}]
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
         * flat_id : 0
         * request_type : 1
         * school_bus_name : Vidya Education school bus
         * driver_name : subbaraju
         * mobile : 7093668623
         * vehicle_number : APS145566
         * address : TEST
         * status : Active
         * created_on : 2018-11-29 13:16:22
         * modified_on : 2018-11-29 13:22:43
         * delete_status : 1
         */

        private String id;
        private String admin_id;
        private String flat_id;
        private String request_type;
        private String school_bus_name;
        private String driver_name;
        private String mobile;
        private String vehicle_number;
        private String address;
        private String status;
        private String created_on;
        private String modified_on;
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

        public String getFlat_id() {
            return flat_id;
        }

        public void setFlat_id(String flat_id) {
            this.flat_id = flat_id;
        }

        public String getRequest_type() {
            return request_type;
        }

        public void setRequest_type(String request_type) {
            this.request_type = request_type;
        }

        public String getSchool_bus_name() {
            return school_bus_name;
        }

        public void setSchool_bus_name(String school_bus_name) {
            this.school_bus_name = school_bus_name;
        }

        public String getDriver_name() {
            return driver_name;
        }

        public void setDriver_name(String driver_name) {
            this.driver_name = driver_name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getVehicle_number() {
            return vehicle_number;
        }

        public void setVehicle_number(String vehicle_number) {
            this.vehicle_number = vehicle_number;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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
    }
}
