package com.iprismech.alertnikki.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Request.DeliveryBoy_Req;
import com.iprismech.alertnikki.app.factories.constants.AppConstants;
import com.iprismech.alertnikki.app.factories.controllers.ApplicationController;
import com.iprismech.alertnikki.base.BaseAbstractActivity;
import com.iprismech.alertnikki.fragments.BaseAbstractFragment;
import com.iprismech.alertnikki.fragments.Inside_Visitors_fragment;
import com.iprismech.alertnikki.fragments.WaitingVisitorFragment;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.Common;
import com.iprismech.alertnikki.utilities.SharedPrefsUtils;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DummyActivity extends BaseAbstractActivity implements RetrofitResponseListener, View.OnClickListener {


    private Object obj;
    private EditText mName, mMobile, mCompany, mVehicle1, mVehicle2, mVehicle3, mVehicle4, mResidentMobile;
    private ImageView close, boy_pic, companyPic;
    private Button sendnotify;
    private Spinner spin_Building, spin_Flat;
    private String mTitle, base64profile;
    private int mImageid, CAMERA_DOC = 100, GALLERY_DOC = 101;

    private String building_id, flat_id;
    private LinearLayout ll_buiding, ll_flatno;
    private TextView tv_flat_number, tv_building_name;
    private View view;
    private String mpersonName, mPhone, mService;

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        sendnotify.setOnClickListener(this);
        close.setOnClickListener(this);
        boy_pic.setOnClickListener(this);

        ll_buiding.setOnClickListener(this);
        ll_flatno.setOnClickListener(this);


        mVehicle1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mVehicle1.getText().toString().length() == 2)     //size as per your requirement
                {
                    mVehicle2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mVehicle2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mVehicle2.getText().toString().length() == 2)     //size as per your requirement
                {
                    mVehicle3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mVehicle3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mVehicle3.getText().toString().length() == 2)     //size as per your requirement
                {
                    mVehicle4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_dummy, null);
        return view;
    }


    @Override
    public void setPresenter() {

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        Bundle bundle = getIntent().getExtras();
        mpersonName = bundle.getString("Key_Person");
        mPhone = bundle.getString("Key_PersonMobile");
        mTitle = bundle.getString("Key_PersonService");

        //Toast.makeText(context, "" + mpersonName + mPhone + mService, Toast.LENGTH_SHORT).show();

        ll_buiding = findViewById(R.id.ll_building_delivery);
        ll_flatno = findViewById(R.id.ll_flatno_delivery);
        tv_building_name = findViewById(R.id.tv_building_name_delivery);
        tv_flat_number = findViewById(R.id.tv_flat_number_delivery);

        close = findViewById(R.id.img_Close);
        boy_pic = findViewById(R.id.img_deliveryBoy_pic);
        companyPic = findViewById(R.id.img_deliverycompany);

//        title = findViewById(R.id.txt_title);

        mName = findViewById(R.id.edt_Name);
        mMobile = findViewById(R.id.edt_Mobile);
        mCompany = findViewById(R.id.edt_DeliveryFrom);
        mVehicle1 = findViewById(R.id.edt_vehicle_first);
        mVehicle2 = findViewById(R.id.edt_vehicle_one);
        mVehicle3 = findViewById(R.id.edt_vehicle_two);
        mVehicle4 = findViewById(R.id.edt_vehicle_three);

        sendnotify = findViewById(R.id.btn_sendnotify);

        mName.setText("" + mpersonName);
        mMobile.setText("" + mPhone);
        mCompany.setText("" + mTitle);
        try {
//            obj = Class.forName(Visitor.class.getName()).cast(visitor);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        new RetrofitRequester(this).callPostServices(obj, 1, "visitors", true);
    }


    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
//        WaitingVisitors

        if (objectResponse == null || objectRequest.equals("")) {
            Common.showToast(DummyActivity.this, "Please Try again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject object = new JSONObject(jsonString);
                if (object.optBoolean("status")) {
                    switch (requestId) {
                        case 3:
                            Toast.makeText(this, "Notification sent to User Successfully", Toast.LENGTH_SHORT).show();
                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
                            finish();
                            break;

                    }
                } else {
                    Common.showToast(DummyActivity.this, object.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_sendnotify:
                String sName = mName.getText().toString();
                String smobile = mMobile.getText().toString();
                String scompany = mCompany.getText().toString();
                String sv1 = mVehicle1.getText().toString();
                String sv2 = mVehicle2.getText().toString();
                String sv3 = mVehicle3.getText().toString();
                String sv4 = mVehicle4.getText().toString();
                //  String sResident = mResidentMobile.getText().toString();

                if (sName.length() == 0 && smobile.length() == 0 && scompany.length() == 0 && sv1.length() == 0 &&
                        sv2.length() == 0 && sv3.length() == 0 && sv4.length() == 0
                        ) {
                    Common.showToast(DummyActivity.this, "Please Enter all fields");
                } else if (sName.length() == 0) {
                    Common.showToast(DummyActivity.this, "Please enter name");
                } else if (smobile.length() == 0 || smobile.length() < 10) {
                    Common.showToast(DummyActivity.this, "Please Enter Valid 10 Digit Mobile");
                } else if (scompany.length() == 0) {
                    Common.showToast(DummyActivity.this, "Please Enter Delivery From");
                } else if (tv_building_name.getText().toString().equalsIgnoreCase("")
                        || tv_building_name.getText().toString().isEmpty()) {
                    Toast.makeText(DummyActivity.this, "Please Select Building", Toast.LENGTH_SHORT).show();
                } else if (tv_flat_number.getText().toString().equalsIgnoreCase("")
                        || tv_flat_number.getText().toString().isEmpty()) {
                    Toast.makeText(DummyActivity.this, "Please Select Flat Number", Toast.LENGTH_SHORT).show();
                } else {
                    LayoutInflater dialoginflater = LayoutInflater.from(getApplicationContext());
                    View dialogview = dialoginflater.inflate(R.layout.alert_delivery_warning, null);
                    final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                    alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    alertDialog.setView(dialogview);
                    alertDialog.setCancelable(false);
                    TextView ok_submit, security_name, login_time;
//                    ImageView security_img;
                    ok_submit = dialogview.findViewById(R.id.delivery_alert_ok);

                    ok_submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            callWsforSendNotify();
                            alertDialog.dismiss();
                        }
                    });
                    alertDialog.getWindow().getAttributes().windowAnimations = R.style.dialog_animation;
                    alertDialog.show();

                }

                break;
            case R.id.img_Close:
                onBackPressed();
                break;
            case R.id.img_deliveryBoy_pic:
                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());
                //     permissionsRequest();
                showPictureDialog("");
                break;
            case R.id.ll_building_delivery:
                Intent intent = new Intent(DummyActivity.this, SelectBuildingActvity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.ll_flatno_delivery:
                Intent intent_flat = new Intent(DummyActivity.this, SelectFlatActivity.class);
                intent_flat.putExtra("building_id", building_id);
                startActivityForResult(intent_flat, 2);
                break;
        }
    }

    private void callWsforSendNotify() {

/*
        {
            "admin_id":"2",
                "name":"gururaj",
                "mobile":"8500553107",
                "building_id":"1",
                "flat_id":"1",
                "delivery_from":"flip_cart",
                "vehicle_no":"AP5656L90",
                "photo":""
        }
*/


        DeliveryBoy_Req req = new DeliveryBoy_Req();
        String service_from = mCompany.getText().toString();
        req.adminId = SharedPrefsUtils.getInstance(DummyActivity.this).getAdmin();
        req.name = mName.getText().toString();
        req.mobile = mMobile.getText().toString();
        req.buildingId = building_id;
        //req.buildingId = "1";

        req.flatId = flat_id;
        //req.flatId = "1";
        req.deliveryFrom = service_from;
        //service
        req.service_from = service_from;
        req.vehicleNo = mVehicle1.getText().toString() + mVehicle2.getText().toString() +
                mVehicle3.getText().toString() + mVehicle4.getText().toString();
        req.photo = base64profile;
        req.service_from = mTitle;

        //   new RetrofitRequester(this).callPostServices(obj, 3, "delivery_send_notification", true);

        try {
            if (mTitle.equalsIgnoreCase("Delivery")) {

                req.guest_type = "delivery";

                try {
                    obj = Class.forName(DeliveryBoy_Req.class.getName()).cast(req);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                new RetrofitRequester(this).callPostServices(obj, 3, "delivery_send_notification", true);
            } else if (mTitle.equalsIgnoreCase("Cab") || mTitle.equalsIgnoreCase("OLA") || mTitle.equalsIgnoreCase("Uber") || mTitle.equalsIgnoreCase("Bla Bla")) {
                req.guest_type = "cab";

                try {
                    obj = Class.forName(DeliveryBoy_Req.class.getName()).cast(req);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                new RetrofitRequester(this).callPostServices(obj, 3, "cab_notify", true);
                // new RetrofitRequester(this).callPostServices(obj, 3, "delivery_send_notification", true);

            }
//        else if (mTitle.equalsIgnoreCase("Add Guest")) {
//            new RetrofitRequester(this).callPostServices(obj, 3, "delivery_send_notification", true);
//        }
            else if (mTitle.equalsIgnoreCase("Gas Delivery ")) {
                new RetrofitRequester(this).callPostServices(obj, 3, "delivery_send_notification", true);
            } else if (mTitle.equalsIgnoreCase("Courier Boy")) {
                new RetrofitRequester(this).callPostServices(obj, 3, "delivery_send_notification", true);
            } else if (mTitle.equalsIgnoreCase("Swiggy") || mTitle.equalsIgnoreCase("Food Panda") || mTitle.equalsIgnoreCase("Zomato") || mTitle.equalsIgnoreCase("Uber Eats")
                    || mTitle.equalsIgnoreCase("Other") || mTitle.equalsIgnoreCase("Paytm") || mTitle.equalsIgnoreCase("Amazon")
                    || mTitle.equalsIgnoreCase("FlipKart") || mTitle.equalsIgnoreCase("Myntra")) {
                new RetrofitRequester(this).callPostServices(obj, 3, "delivery_send_notification", true);
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showPictureDialog(final String base64) {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(context);
        pictureDialog.setTitle("Select Action");
//        String[] pictureDialogItems = {
//                "Select photo from gallery",
//                "Capture photo from camera"};
        String[] pictureDialogItems = {
                "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                // choosePhotoFromGallary(base64);
                                takePhotoFromCamera(base64);
                                break;
                            case 1:
                                // takePhotoFromCamera(base64);
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    private void takePhotoFromCamera(String base64) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                Log.d("hhhh", "Permissions not granted");
                // ask for permission
                ActivityCompat.requestPermissions(getParent(), new String[]{Manifest.permission.CAMERA}, 1);
            }
        }
        try {
            //   FileName = System.currentTimeMillis() + ".jpg";
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            startActivityForResult(intent, CAMERA_DOC);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        } else if (requestCode == GALLERY_DOC) {
            if (data != null) {
                Uri choosenImage = data.getData();
                if (choosenImage != null) {

                    Bitmap bp = decodeUri(choosenImage, 200);
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    bp.compress(Bitmap.CompressFormat.JPEG, 70, bytes);
                    boy_pic.setImageBitmap(bp);

                    byte[] byte_arr = bytes.toByteArray();
                    base64profile = Base64.encodeToString(byte_arr, Base64.DEFAULT);

                }
            }
        } else if (requestCode == CAMERA_DOC) {

            // Variable.img_banner = profile;
            Bitmap profilebit = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            profilebit.compress(Bitmap.CompressFormat.JPEG, 70, stream);
            boy_pic.setImageBitmap(profilebit);
            byte[] byte_arr = stream.toByteArray();
            base64profile = Base64.encodeToString(byte_arr, Base64.DEFAULT);

        }


        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                building_id = data.getStringExtra("id");
                String name = data.getStringExtra("name");
                tv_building_name.setText(name);
                // Toast.makeText(getActivity(), building_id + "and" + name, Toast.LENGTH_SHORT).show();
            }
            if (resultCode == Activity.RESULT_CANCELED) {

            }

        } else if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                flat_id = data.getStringExtra("id");
                String name = data.getStringExtra("name");
                tv_flat_number.setText(name);
                //Toast.makeText(getActivity(), building_id + "and" + name, Toast.LENGTH_SHORT).show();
            }
            if (resultCode == Activity.RESULT_CANCELED) {

            }

        }

    }

    protected Bitmap decodeUri(Uri selectedImage, int REQUIRED_SIZE) {
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(getApplicationContext().getContentResolver().openInputStream(selectedImage), null, o);
            // The new size we want to scale to
            // final int REQUIRED_SIZE =  size;
            // Find the correct scale value. It should be the power of 2.
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE
                        || height_tmp / 2 < REQUIRED_SIZE) {
                    break;
                }
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }
            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(getApplicationContext().getContentResolver().openInputStream(selectedImage), null, o2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}

