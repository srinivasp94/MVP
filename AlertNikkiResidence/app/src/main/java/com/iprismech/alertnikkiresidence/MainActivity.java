package com.iprismech.alertnikkiresidence;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.adapters.Slidemenu_adapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.fragments.HomeFragment;
import com.iprismech.alertnikkiresidence.request.FamilydeviceToken;
import com.iprismech.alertnikkiresidence.request.UpdateDeviceToken;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

public class MainActivity extends BaseAbstractActivity<Class> implements RetrofitResponseListener {
    FragmentManager fragmentManager;
    private Object obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home_screen);
        FirebaseApp.initializeApp(MainActivity.this);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_main, null);
        return view;
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        // ApplicationController.getInstance().setContext(context);
        try {

            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.fm_container, new HomeFragment(), "").commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
//user
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        if (SharedPrefsUtils.getString(SharedPrefsUtils.KEY_USER_TYPE).equalsIgnoreCase("1.0")) {

            UpdateDeviceToken updateDeviceToken = new UpdateDeviceToken();
            updateDeviceToken.user_id = SharedPrefsUtils.getInstance(MainActivity.this).getId();
            //updateDeviceToken.security_id = "1";
            updateDeviceToken.token = refreshedToken;
            try {
                obj = Class.forName(UpdateDeviceToken.class.getName()).cast(updateDeviceToken);
            } catch (Exception e) {
                e.printStackTrace();
            }
            new RetrofitRequester(this).callPostServices(obj, 1, "update_user_device_token", true);




        } else if (SharedPrefsUtils.getString(SharedPrefsUtils.KEY_USER_TYPE).equalsIgnoreCase("2.0")) {

            FamilydeviceToken req = new FamilydeviceToken();
            req.familyMemberId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_ID);
            req.token = refreshedToken;

            try {
                obj = Class.forName(FamilydeviceToken.class.getName()).cast(req);
            } catch (Exception e) {

            }
            new RetrofitRequester(this).callPostServices(obj, 2, "update_family_member_device_token", true);
        }


    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectRequest.equals("")) {
            Common.showToast(getApplicationContext(), "PLease Try again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject object = new JSONObject(jsonString);

                if (object.optBoolean("status") == true) {
                    switch (requestId) {
                        case 1:
                            JSONObject jsonObject = object.optJSONObject("response");
                            break;
                        case 2:
//                            JSONObject jsonObject = object.optJSONObject("response");
                            break;
                    }


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
