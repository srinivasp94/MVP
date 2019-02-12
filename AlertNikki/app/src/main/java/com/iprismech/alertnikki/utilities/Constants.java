package com.iprismech.alertnikki.utilities;

public class Constants {
    public static final String BASE_URL = "http://iprismconstructions.com/alertnikki/app/security/";
    //http://iprismconstructions.com/alertnikki/storage/security/testimonial-1.jpg
    public static final String BASE_IMAGE_URL = "http://iprismconstructions.com/alertnikki/";
    public static final String INTERNET_UNABLEABLE = "Not connected to the internet. Please check your connection and try again.";
    public static final long CONNECTION_TIME_OUT = 60;
    public static final long READ_TIME_OUT = 60;
    public static final long WRITE_TIME_OUT = 60;
    public interface RequestCodes {
        int ONCREATE_REQUEST_CODE = 5000;
    }
}
