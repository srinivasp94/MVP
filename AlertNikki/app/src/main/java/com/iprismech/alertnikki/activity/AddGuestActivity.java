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
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismech.alertnikki.Pojo.BuildingsPojo;
import com.iprismech.alertnikki.Pojo.FlatPojo;
import com.iprismech.alertnikki.Pojo.MoveinPojo;
import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Request.BuildingListRequest;
import com.iprismech.alertnikki.Request.DeliveryBoy_Req;
import com.iprismech.alertnikki.app.factories.constants.AppConstants;
import com.iprismech.alertnikki.app.factories.controllers.ApplicationController;
import com.iprismech.alertnikki.base.BaseAbstractActivity;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.Common;
import com.iprismech.alertnikki.utilities.SharedPrefsUtils;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class AddGuestActivity extends BaseAbstractActivity<Class> implements View.OnClickListener, RetrofitResponseListener

{

    private ImageView close, boy_pic, companyPic;
    private RetrofitResponseListener retrofitResponseListener;
    private TextView title;
    private EditText mName, mMobile, mCompany, mVehicle1, mVehicle2, mVehicle3, mVehicle4, mResidentMobile;
    private Button sendnotify;
    private Spinner spin_Building, spin_Flat;
    private String mTitle, base64profile;
    private int mImageid, CAMERA_DOC = 100, GALLERY_DOC = 101;
    private Object obj;
    private Spinner sp_building, sp_flat;
    private ArrayAdapter<String> customadapter;
    private BuildingsPojo buildingsPojo;
    private FlatPojo flatPojo;
    private MoveinPojo moveinPojo;
    private ArrayList<String> building_array = new ArrayList<>();
    private ArrayList<String> flat_array = new ArrayList<>();
    private String building_id, flat_id;
    private LinearLayout ll_buiding, ll_flatno;
    private TextView tv_flat_number, tv_building_name;
    private Bitmap profilebit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_delivery);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_add_guest, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        sendnotify.setOnClickListener(this);
        close.setOnClickListener(this);

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
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);
        retrofitResponseListener = this;
        // sp_building = findViewById(R.id.spinner_building);
        //  sp_flat = findViewById(R.id.spinner_flat);

        // sp_building.setOnItemSelectedListener(this);
        // sp_flat.setOnItemSelectedListener(this);

        ll_buiding = findViewById(R.id.ll_building_delivery);
        ll_flatno = findViewById(R.id.ll_flatno_delivery);
        tv_building_name = findViewById(R.id.tv_building_name_delivery);
        tv_flat_number = findViewById(R.id.tv_flat_number_delivery);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mTitle = bundle.getString("Key_Title");
            mImageid = bundle.getInt("Key_Image");
        }

        close = findViewById(R.id.img_Close);
        boy_pic = findViewById(R.id.img_deliveryBoy_pic);
        //  companyPic = findViewById(R.id.img_deliverycompany);

        title = findViewById(R.id.txt_title);

        mName = findViewById(R.id.edt_Name);
        mMobile = findViewById(R.id.edt_Mobile);
        mCompany = findViewById(R.id.edt_DeliveryFrom);
        mVehicle1 = findViewById(R.id.edt_vehicle_first);
        mVehicle2 = findViewById(R.id.edt_vehicle_one);
        mVehicle3 = findViewById(R.id.edt_vehicle_two);
        mVehicle4 = findViewById(R.id.edt_vehicle_three);
        // mResidentMobile = findViewById(R.id.edt_resident_Mobile);

        // spin_Building = findViewById(R.id.spinner_building);
        //spin_Flat = findViewById(R.id.spinner_flat);
        sendnotify = findViewById(R.id.btn_sendnotify);

        title.setText(mTitle);
        //  mCompany.setText(mTitle);
//        if (mImageid == 0) {
//            companyPic.setImageResource(R.drawable.dummy);
//        } else {
//            companyPic.setImageResource(mImageid);
//        }
        boy_pic.setOnClickListener(this);

        ll_buiding.setOnClickListener(this);
        ll_flatno.setOnClickListener(this);

        BuildingListRequest buildingListRequest = new BuildingListRequest();
        buildingListRequest.admin_id = SharedPrefsUtils.getInstance(getApplicationContext()).getAdmin();
        try {
            obj = Class.forName(BuildingListRequest.class.getName()).cast(buildingListRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(retrofitResponseListener).callPostServices(obj, 1, "buildings", true);

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
                    Common.showToast(AddGuestActivity.this, "Please Enter all fields");
                }
//                else if (sName.length() == 0 || sName.length() < 6) {
//                    Common.showToast(AddGuestActivity.this, "Name must be 6 characters ");
//                }
                else if (smobile.length() == 0 || smobile.length() < 10) {
                    Common.showToast(AddGuestActivity.this, "Please Enter Valid 10 Digit Mobile");
                } else if (scompany.length() == 0) {
                    Common.showToast(AddGuestActivity.this, "Please Enter Number of persons");
                } else if (tv_building_name.getText().toString().equalsIgnoreCase("")
                        || tv_building_name.getText().toString().isEmpty()) {
                    Toast.makeText(AddGuestActivity.this, "Please Select Building", Toast.LENGTH_SHORT).show();
                } else if (tv_flat_number.getText().toString().equalsIgnoreCase("")
                        || tv_flat_number.getText().toString().isEmpty()) {
                    Toast.makeText(AddGuestActivity.this, "Please Select Flat Number", Toast.LENGTH_SHORT).show();
                }

//                else if (sv1.length() == 0 || sv1.length() < 2) {
//                    Common.showToast(AddGuestActivity.this, "Please Enter vehicle Number");
//                } else if (sv2.length() == 0 || sv2.length() < 2) {
//                    Common.showToast(AddGuestActivity.this, "Please Enter vehicle Number");
//                } else if (sv3.length() == 0 || sv3.length() < 2) {
//                    Common.showToast(AddGuestActivity.this, "Please Enter vehicle Number");
//                } else if (sv4.length() == 0 || sv4.length() < 4) {
//                    Common.showToast(AddGuestActivity.this, "Please Enter vehicle Number");
//                }
//                else if (mResidentMobile.length() == 0 || mResidentMobile.length() < 10) {
//                    Common.showToast(DeliveryActivity.this, "Please Enter Resident Mobile ");
//                }
                else {
                    LayoutInflater dialoginflater = LayoutInflater.from(getApplicationContext());
                    View dialogview = dialoginflater.inflate(R.layout.alert_delivery_warning, null);
                    final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                    alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    alertDialog.setView(dialogview);
                    alertDialog.setCancelable(true);
                    TextView ok_submit, security_name, login_time;
                    ImageView guest_img;
                    ok_submit = dialogview.findViewById(R.id.delivery_alert_ok);
                    guest_img = dialogview.findViewById(R.id.guest_img);

                    guest_img.setImageBitmap(profilebit);
//                    security_name=dialogview.findViewById(R.id.security_name_login);
//                    login_time=dialogview.findViewById(R.id.login_time);
//                    security_img=dialogview.findViewById(R.id.sec_img_login_time);


                    // login_time.setText(loginTimePojo.getResponse().getIn_time());


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
                Intent intent = new Intent(AddGuestActivity.this, SelectBuildingActvity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.ll_flatno_delivery:
                Intent intent_flat = new Intent(AddGuestActivity.this, SelectFlatActivity.class);
                intent_flat.putExtra("building_id", building_id);
                startActivityForResult(intent_flat, 2);
                break;
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
                                //   choosePhotoFromGallary(base64);
                                takePhotoFromCamera(base64);
                                break;
                            case 1:
                                //   takePhotoFromCamera(base64);
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary(String base64) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                Log.d("hhhh", "Permissions not granted");
                // ask for permission
                ActivityCompat.requestPermissions(getParent(), new String[]{Manifest.permission.CAMERA}, 1);
            }
        }
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(Intent.createChooser(galleryIntent, "Select Picture"), GALLERY_DOC);


    }

    private void takePhotoFromCamera(String base64) {
        if (Build.VERSION.SDK_INT >= 23) {
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
            profilebit = (Bitmap) data.getExtras().get("data");
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
        req.adminId = SharedPrefsUtils.getInstance(AddGuestActivity.this).getAdmin();
        req.name = mName.getText().toString();
        req.mobile = mMobile.getText().toString();
        req.buildingId = building_id;
        //req.buildingId = "1";

        req.flatId = flat_id;
        //req.flatId = "1";
        req.deliveryFrom = mTitle;

        req.vehicleNo = mVehicle1.getText().toString() + mVehicle2.getText().toString() +
                mVehicle3.getText().toString() + mVehicle4.getText().toString();
        req.photo = base64profile;
        req.service_from = mTitle;

        try {
            obj = Class.forName(DeliveryBoy_Req.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mTitle.equalsIgnoreCase("Delivery Boy")) {
            new RetrofitRequester(this).callPostServices(obj, 3, "delivery_send_notification", true);
        } else if (mTitle.equalsIgnoreCase("Cab")) {
            new RetrofitRequester(this).callPostServices(obj, 3, "cab_notify", true);
        } else if (mTitle.equalsIgnoreCase("Add Guest")) {
            DeliveryBoy_Req req1 = new DeliveryBoy_Req();
            req1.adminId = SharedPrefsUtils.getInstance(AddGuestActivity.this).getAdmin();
            req1.name = mName.getText().toString();
            req1.mobile = mMobile.getText().toString();
            req1.buildingId = building_id;
            //req1.buildingId = "4";

            req1.flatId = flat_id;
            //req1.flatId = "4";
            req1.deliveryFrom = mTitle;

            req1.vehicleNo = mVehicle1.getText().toString() + mVehicle2.getText().toString() +
                    mVehicle3.getText().toString() + mVehicle4.getText().toString();
            req1.photo = base64profile;
            req1.service_from = mTitle;
            req1.added_from = "invite_guest";
            req1.guest_type = "guest";

            try {
                obj = Class.forName(DeliveryBoy_Req.class.getName()).cast(req1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            new RetrofitRequester(this).callPostServices(obj, 3, "delivery_send_notification", true);
        } else if (mTitle.equalsIgnoreCase("Gas Delivery ")) {
            new RetrofitRequester(this).callPostServices(obj, 3, "delivery_send_notification", true);
        } else if (mTitle.equalsIgnoreCase("Courier Boy")) {
            new RetrofitRequester(this).callPostServices(obj, 3, "delivery_send_notification", true);
        } else if (mTitle.equalsIgnoreCase("Swiggy") || mTitle.equalsIgnoreCase("Food Panda") || mTitle.equalsIgnoreCase("Zomoto") || mTitle.equalsIgnoreCase("Uber Eats")
                || mTitle.equalsIgnoreCase("Other") || mTitle.equalsIgnoreCase("Paytm") || mTitle.equalsIgnoreCase("Amazon")
                || mTitle.equalsIgnoreCase("FlipKart") || mTitle.equalsIgnoreCase("Myntra")) {
            new RetrofitRequester(this).callPostServices(obj, 3, "delivery_send_notification", true);
        }
    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectRequest.equals("")) {
            Common.showToast(AddGuestActivity.this, "PLease Try again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject object = new JSONObject(jsonString);
                if (object.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            buildingsPojo = new Gson().fromJson(jsonString, BuildingsPojo.class);
                            //building_array = object.optJSONArray("response");
                            building_array.add("Select Building");
                            for (int i = 0; i < buildingsPojo.getResponse().size(); i++) {
                                building_array.add(buildingsPojo.getResponse().get(i).getTitle());
//                                if (user_country.equalsIgnoreCase(countryPojo.getResponse().get(i).getTitle())) {
//                                    // countryspinner.setSelection(i);
//                                    country_position = i + 1;
//                                }
                            }
                            customadapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item, building_array);
                            customadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            sp_building.setAdapter(customadapter);
                            break;
                        case 2:
                            flatPojo = new Gson().fromJson(jsonString, FlatPojo.class);
                            //building_array = object.optJSONArray("response");
                            flat_array.add("Select Flat");
                            for (int i = 0; i < flatPojo.getResponse().size(); i++) {
                                flat_array.add(flatPojo.getResponse().get(i).getTitle());
//                                if (user_country.equalsIgnoreCase(countryPojo.getResponse().get(i).getTitle())) {
//                                    // countryspinner.setSelection(i);
//                                    country_position = i + 1;
//                                }
                            }
                            customadapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item, flat_array);
                            customadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            sp_flat.setAdapter(customadapter);
                            break;
                        case 3:
                            // Common.showToast(DeliveryActivity.this, object.optString("message"));

                            Toast.makeText(getApplicationContext(), "Notification Sent to user Successfully", Toast.LENGTH_SHORT).show();
                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
                            //onBackPressed();

                            break;
                    }
                } else {
                    if (object.optString("message").equalsIgnoreCase("User is not there your posted flat!")) {
                        Toast.makeText(context, "No Tenant/User in Selected Flat, Please Check", Toast.LENGTH_SHORT).show();
                    } else {
                        Common.showToast(AddGuestActivity.this, object.optString("message"));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

