package com.iprismech.alertnikkiresidence.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.KidsListAdapter;
import com.iprismech.alertnikkiresidence.adapters.StaffListAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.KidsListPojo;
import com.iprismech.alertnikkiresidence.pojo.MyStaff_Maids_List_Pojo;
import com.iprismech.alertnikkiresidence.request.DeleteKidRequest;
import com.iprismech.alertnikkiresidence.request.DeleteStaffRequest;
import com.iprismech.alertnikkiresidence.request.KidGatePassRequest;
import com.iprismech.alertnikkiresidence.request.KidsListRequest;
import com.iprismech.alertnikkiresidence.request.StaffRequest;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.AppPermissions;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

public class KidsGateAlertActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {

    private RelativeLayout RlKidssLists;
    private LinearLayout layoutNoKid;
    private RecyclerView rvKidLists;
    private LinearLayoutManager manager;
    private RetrofitResponseListener retrofitResponseListener;
    private TextView txtAddKid;
    private KidsListAdapter kidsListAdapter;
    private Object obj;
    ImageView add_kid;
    private KidsListPojo kidsListPojo;
    private int removed_postion;
    private AlertDialog alertDialog;
    private ImageView scott_image;
    private int mImageid, CAMERA_DOC = 100, GALLERY_DOC = 101;
    private String mTitle, base64profile = "";

    private ImageView imgClose;
    private 	TextView txtitle;




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgClose:
                onBackPressed();
                break;
            case R.id.txtAddKid:
                Bundle bundle = new Bundle();
                bundle.putString("screen", "Add kid");
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_ADD_KID_SCREEN, bundle);
                break;
            case R.id.add_kid:
                Bundle bundle1 = new Bundle();
                bundle1.putString("screen", "Add kid");
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_ADD_KID_SCREEN, bundle1);
                break;
        }
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        txtAddKid.setOnClickListener(this);
        add_kid.setOnClickListener(this);
        imgClose.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);

        txtitle = findViewById(R.id.txtitle);
        imgClose= findViewById(R.id.imgClose);
        txtitle.setText("Kids Gate");


        retrofitResponseListener = this;
        txtAddKid = findViewById(R.id.txtAddKid);
        layoutNoKid = findViewById(R.id.layoutNoKid);
        RlKidssLists = findViewById(R.id.LayoutKid);
        add_kid = findViewById(R.id.add_kid);
        rvKidLists = findViewById(R.id.rvKidsLists);


        KidsListRequest req = new KidsListRequest();
        req.adminId = "2";
        req.user_id = SharedPrefsUtils.getInstance(KidsGateAlertActivity.this).getId();
        req.user_type = SharedPrefsUtils.getInstance(KidsGateAlertActivity.this).getuserType();
        // req.user_id = "1";
        // req.user_type = "1";
        try {
            obj = Class.forName(KidsListRequest.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "kids_list", true);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_kid_alert, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {

        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(KidsGateAlertActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    layoutNoKid.setVisibility(View.GONE);
                    RlKidssLists.setVisibility(View.VISIBLE);
                    switch (requestId) {
                        case 1:
                            manager = new LinearLayoutManager(KidsGateAlertActivity.this);
                            manager.setOrientation(LinearLayoutManager.VERTICAL);
                            rvKidLists.setLayoutManager(manager);
                            kidsListPojo = gson.fromJson(jsonString, KidsListPojo.class);
                            kidsListAdapter = new KidsListAdapter(KidsGateAlertActivity.this, kidsListPojo);
                            rvKidLists.setAdapter(kidsListAdapter);
                            kidsListAdapter.notifyDataSetChanged();


                            kidsListAdapter.setOnItemClickListener(new StaffListAdapter.OnitemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    removed_postion = position;
                                    switch (view.getId()) {
                                        case R.id.ll_delete_kid:
                                            DeleteKidRequest deleteKidRequest = new DeleteKidRequest();
                                            deleteKidRequest.user_kid_id = kidsListPojo.getResponse().get(position).getId();
                                            try {
                                                obj = Class.forName(DeleteKidRequest.class.getName()).cast(deleteKidRequest);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            new RetrofitRequester(retrofitResponseListener).callPostServices(obj, 2, "delete_kid", true);

                                            break;
                                        case R.id.ll_send_gate_pass_kid:
                                            LayoutInflater inflater = LayoutInflater.from(KidsGateAlertActivity.this);
//        getLayoutInflater().inflate(R.layout.alert_alerts,null);
                                            View view1 = inflater.inflate(R.layout.dailog_kids_alert, null);
                                            alertDialog = new AlertDialog.Builder(KidsGateAlertActivity.this).create();
                                            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                            alertDialog.setView(view1);
                                            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                            alertDialog.setCancelable(true);
                                            final EditText kid_name, scott_name;
                                            TextView btn_gate_pass_send_notify;
                                            ImageView close;
                                            LinearLayout ll_upload_scott_img;

                                            kid_name = view1.findViewById(R.id.gate_pass_kid_name);
                                            scott_name = view1.findViewById(R.id.gate_pass_scott_name);
                                            scott_image = view1.findViewById(R.id.iv_scott_image);
                                            close = view1.findViewById(R.id.gate_pass_iv_close);
                                            ll_upload_scott_img = view1.findViewById(R.id.ll_upload_image);

                                            kid_name.setText(kidsListPojo.getResponse().get(position).getKid_name());
                                            btn_gate_pass_send_notify = view1.findViewById(R.id.btn_gate_pass_send_notify);


                                            ll_upload_scott_img.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                                                    StrictMode.setVmPolicy(builder.build());
                                                    //     permissionsRequest();
                                                    showPictureDialog("");
                                                }
                                            });
                                            close.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    alertDialog.dismiss();
                                                }
                                            });
                                            btn_gate_pass_send_notify.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    if (scott_name.getText().toString().isEmpty() || scott_name.getText().toString().equalsIgnoreCase("")) {
                                                        Toast.makeText(KidsGateAlertActivity.this, "Please Enter Scott Name", Toast.LENGTH_SHORT).show();
                                                    } else if (base64profile.equalsIgnoreCase("") || base64profile.isEmpty()) {
                                                        Toast.makeText(KidsGateAlertActivity.this, "Please upload Scott Image", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        KidGatePassRequest req = new KidGatePassRequest();
                                                        req.user_kid_id = kidsListPojo.getResponse().get(removed_postion).getId();
                                                        req.kid_going_with_whom = scott_name.getText().toString();
                                                        req.kid_image = base64profile;
                                                        try {
                                                            obj = Class.forName(KidGatePassRequest.class.getName()).cast(req);
                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                        }
                                                        new RetrofitRequester(retrofitResponseListener).callPostServices(obj, 3, "kid_gatepass_notify", true);
                                                    }
                                                }
                                            });

                                            alertDialog.show();

                                            break;
                                    }

                                }
                            });
                            break;
                        case 2:
                            Toast.makeText(KidsGateAlertActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                            kidsListPojo.getResponse().remove(removed_postion);
//                        notifyDataSetChanged();
                            kidsListAdapter.notifyDataSetChanged();
                            kidsListAdapter.notifyItemRemoved(removed_postion);
                            break;
                        case 3:
                            Toast.makeText(KidsGateAlertActivity.this, "Gate Pass Sent Successfully", Toast.LENGTH_SHORT).show();
                            break;
                    }
                } else {
                    Common.showToast(KidsGateAlertActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private void showPictureDialog(final String base64) {
        android.app.AlertDialog.Builder pictureDialog = new android.app.AlertDialog.Builder(context);
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
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                Log.d("hhhh", "Permissions not granted");
                // ask for permission
                AppPermissions.callPermissionForContacts(KidsGateAlertActivity.this);
                // ActivityCompat.requestPermissions(getParent(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
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
        } else if (requestCode == CAMERA_DOC) {

            // Variable.img_banner = profile;
            Bitmap profilebit = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            profilebit.compress(Bitmap.CompressFormat.JPEG, 70, stream);

            scott_image.setImageBitmap(profilebit);
            byte[] byte_arr = stream.toByteArray();
            base64profile = Base64.encodeToString(byte_arr, Base64.DEFAULT);

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


}
