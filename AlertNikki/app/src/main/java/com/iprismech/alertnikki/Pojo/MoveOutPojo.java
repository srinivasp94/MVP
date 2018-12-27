package com.iprismech.alertnikki.Pojo;

public class MoveOutPojo {

    /**
     * status : true
     * message : Data Posted Successfully!
     * response : true
     */

    private boolean status;
    private String message;
    private boolean response;

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

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }
}
