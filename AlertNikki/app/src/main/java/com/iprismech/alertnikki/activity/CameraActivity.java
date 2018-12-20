package com.iprismech.alertnikki.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Base64;
import android.view.View;

import com.google.gson.Gson;
import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Request.Login_model;
import com.iprismech.alertnikki.Response.Login;
import com.iprismech.alertnikki.app.factories.constants.AppConstants;
import com.iprismech.alertnikki.app.factories.controllers.ApplicationController;
import com.iprismech.alertnikki.base.BaseAbstractActivity;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.Common;
import com.iprismech.alertnikki.utilities.SharedPrefsUtils;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.Date;

public class CameraActivity extends BaseAbstractActivity<Class> implements RetrofitResponseListener {

    private String img_path;
    private String mSecurity_Id,
            mAdmin_Id,
            mPasscode,
            mName;
    private Object obj;
    private String timeStamp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_camera);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_camera, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mSecurity_Id = bundle.getString("Key_Security_Id", "");
            mAdmin_Id = bundle.getString("Key_Admin_Id", "");
            mPasscode = bundle.getString("Key_Passcode", "");
            mName = bundle.getString("Key_Name", "");
        }
        Common.commonLogs(CameraActivity.this, bundle.toString());

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, 2);
    }

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        Thread timer = new Thread() {
            {
                try {
                    sleep(300);
                    CameraActivity.this.runOnUiThread(new Runnable() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void run() {
                            try {
                                if (requestCode == 2) {
                                    timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                                    Bitmap picture = (Bitmap) data.getExtras().get("data");
//                                    profile.setImageBitmap(picture);
                                    //ImageFile = new File(CameraImageUri.getPath());
//                                    UiAppConstant.venueimage = Util.getInstance().convertInBase64(picture);
                                    img_path = convertInBase64(picture);

                                    Login_model login_model = new Login_model();
                                    login_model.passcode = mPasscode;
                                    login_model.selected_image = img_path;
                                    try {
                                        obj = Class.forName(Login_model.class.getName()).cast(login_model);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    new RetrofitRequester(CameraActivity.this).callPostServices(obj, 1, "login", true);

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    Common.showToast(context, "ERROR : " + e.getMessage());
                }
            }
        };

        timer.start();
    }

    public String convertInBase64(Bitmap imageBitmap) {
        String encodedString = "";
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            encodedString = Base64.encodeToString(byteArray, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodedString;
    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectRequest.equals("")) {
            Common.showToast(CameraActivity.this, "PLease Try again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject object = new JSONObject(jsonString);
                if (object.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            Login login = Common.getSpecificDataObject(objectResponse, Login.class);
                            SharedPrefsUtils.getInstance(this).createUserSession(login.response.securityId,
                                    login.response.adminId, login.response.loginDate, login.response.loginTime
                                    , login.response.society, login.response.city);


                            Bundle bundle = new Bundle();
                            bundle.putString("Key_Base", img_path);
                            Common.commonLogs(CameraActivity.this, img_path);
                            bundle.putString("Key_Security_Id", mSecurity_Id);
                            bundle.putString("Key_Admin_Id", mAdmin_Id);
                            bundle.putString("Key_Passcode", mPasscode);
                            bundle.putString("Key_Name", mName);
                            bundle.putString("Key_timeStamp", timeStamp);
                            bundle.putString("Key_Society", login.response.society);


                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_WELCOME_SCREEN, bundle);
                            finish();
                            break;
                    }
                } else {
                    Common.showToast(CameraActivity.this, object.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }


}
