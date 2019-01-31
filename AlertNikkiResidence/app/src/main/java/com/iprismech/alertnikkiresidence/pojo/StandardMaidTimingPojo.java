package com.iprismech.alertnikkiresidence.pojo;

import java.util.List;

public class StandardMaidTimingPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"1","title":"24:00-1:00","booked_status":1},{"id":"2","title":"1:00-2:00","booked_status":1},{"id":"3","title":"2:00-3:00","booked_status":1},{"id":"4","title":"3:00-4:00","booked_status":0},{"id":"5","title":"4:00-5:00","booked_status":1},{"id":"6","title":"5:00-6:00","booked_status":1},{"id":"7","title":"6:00-7:00","booked_status":1},{"id":"8","title":"7:00-8:00","booked_status":1},{"id":"9","title":"8:00-9:00","booked_status":0},{"id":"10","title":"9:00-10:00","booked_status":0},{"id":"11","title":"10:00-11:00","booked_status":0},{"id":"12","title":"11:00-12:00","booked_status":0},{"id":"13","title":"13:00-14:00","booked_status":0},{"id":"14","title":"14:00-15:00","booked_status":0},{"id":"15","title":"15:00-16:00","booked_status":0},{"id":"16","title":"16:00-17:00","booked_status":0},{"id":"17","title":"17:00-18:00","booked_status":0},{"id":"18","title":"18:00-19:00","booked_status":0},{"id":"19","title":"19:00-20:00","booked_status":0},{"id":"20","title":"20:00-21:00","booked_status":0},{"id":"21","title":"21:00-22:00","booked_status":0},{"id":"22","title":"22:00-23:00","booked_status":0},{"id":"23","title":"23:00-24:00","booked_status":0}]
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
         * title : 24:00-1:00
         * booked_status : 1
         */

        private String id;
        private String title;
        private int booked_status;

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

        public int getBooked_status() {
            return booked_status;
        }

        public void setBooked_status(int booked_status) {
            this.booked_status = booked_status;
        }
    }
}
