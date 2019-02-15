package com.iprismech.alertnikkiresidence.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.UploadImagesAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.StaffProfilePojo;
import com.iprismech.alertnikkiresidence.request.DeleteStaffRequest;
import com.iprismech.alertnikkiresidence.request.DigitalPassRequest;
import com.iprismech.alertnikkiresidence.request.GiveStaffRatingRequest;
import com.iprismech.alertnikkiresidence.request.StaffProfileRequest;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.AlertUtils;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.Constants;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class StaffProfileActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private LinearLayout ll_make_call, ll_gate_pass, ll_staff_delete, ll_attendance_history, ll_give_rating, ll_available_slots;
    private TextView tv_satff_name, tv_staff_type, tv_working_flats, tv_staff_rating;
    private EditText et_staff_descrption;
    private ImageView iv_staff;
    private RatingBar rating_staff_profile;

    String rating = "0.0", user_id, user_type;

    private RetrofitResponseListener retrofitResponseListener;
    String maid_id;
    private Object obj;
    private StaffProfilePojo staffprofilePojo;
    private AlertDialog alertDialog;
    private String user_maid_id;
    private String base64profile;
    private UploadImagesAdapter uploadImagesAdapter;
    private Bitmap profile;
    private ArrayList<Bitmap> uploadimages = new ArrayList<>();
    private JSONArray jArray = new JSONArray();
    private ArrayList<String> arrayList = new ArrayList<>();
    private int i;
    private RecyclerView rcvuploadimages;
    List<DigitalPassRequest.ImageItems> imageIt = new ArrayList<>();
    private ImageView imgClose;
    private TextView txtitle;


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
            case R.id.ll_make_call:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + staffprofilePojo.getResponse().getMobile()));
                startActivity(intent);
                break;
            case R.id.ll_gate_pass:
                LayoutInflater inflater1 = LayoutInflater.from(StaffProfileActivity.this);
//        getLayoutInflater().inflate(R.layout.alert_alerts,null);
                View view1 = inflater1.inflate(R.layout.dialog_alert_staff_gate_pass, null);
                alertDialog = new AlertDialog.Builder(StaffProfileActivity.this).create();
                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alertDialog.setView(view1);
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.setCancelable(true);

                TextView tv_btn_upload_images, tv_cancel, tv_submit;
                final EditText et_comment;
                tv_btn_upload_images = view1.findViewById(R.id.tv_btn_upload_images);
                tv_cancel = view1.findViewById(R.id.tv_btn_pass_cancel);
                tv_submit = view1.findViewById(R.id.tv_btn_pass_submit);
                et_comment = view1.findViewById(R.id.et_comment);
                rcvuploadimages = view1.findViewById(R.id.rview_add_images);
                tv_btn_upload_images.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                        StrictMode.setVmPolicy(builder.build());
                        //     permissionsRequest();
                        showPictureDialog("");
                    }
                });

                tv_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
                tv_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (et_comment.getText().toString().isEmpty() || et_comment.getText().toString().equalsIgnoreCase("")) {
                            Toast.makeText(StaffProfileActivity.this, "Please write description", Toast.LENGTH_SHORT).show();
                        } else {
                            DigitalPassRequest digitalPassRequest = new DigitalPassRequest();
                            digitalPassRequest.adminId = SharedPrefsUtils.getInstance(StaffProfileActivity.this).getAdminID();
                            digitalPassRequest.userId = SharedPrefsUtils.getInstance(StaffProfileActivity.this).getId();
                            digitalPassRequest.userType = SharedPrefsUtils.getInstance(StaffProfileActivity.this).getuserType();
                            digitalPassRequest.maidId = maid_id;
                            digitalPassRequest.description = et_comment.getText().toString();
                            digitalPassRequest.entryIn = "17:20:20";
                            digitalPassRequest.entryOut = "18:20:20";
                            // digitalPassRequest.images = (List<DigitalPassRequest.ImageItems>) jArray;
                            digitalPassRequest.images = imageIt;
                            try {
                                obj = Class.forName(DigitalPassRequest.class.getName()).cast(digitalPassRequest);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            new RetrofitRequester(retrofitResponseListener).callPostServices(obj, 4, "maid_digital_pass", true);

                        }
                    }
                });

                break;
            case R.id.ll_staff_delete:

                AlertUtils.showSimpleAlert(StaffProfileActivity.this, "Do you want to delete", "Confirm...?", "Yes", "No", new AlertUtils.onClicklistners() {
                    @Override
                    public void onpositiveclick() {
                        DeleteStaffRequest deleteStaffRequest = new DeleteStaffRequest();
                        deleteStaffRequest.user_maid_id = user_maid_id;
                        try {
                            obj = Class.forName(DeleteStaffRequest.class.getName()).cast(deleteStaffRequest);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        new RetrofitRequester(retrofitResponseListener).callPostServices(obj, 3, "delete_user_maid", true);
                    }

                    @Override
                    public void onNegativeClick() {

                    }
                });

                break;
            case R.id.ll_attendance_history:
                Bundle bundle = new Bundle();
                bundle.putString("maid_id", maid_id);
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAID_ATTENDANCE_HISTORY_SCREEN, bundle);
                break;
            case R.id.ll_give_rating:
                LayoutInflater inflater = LayoutInflater.from(context);
//        getLayoutInflater().inflate(R.layout.alert_alerts,null);
                View view2 = inflater.inflate(R.layout.dialog_alert_rating, null);

                alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alertDialog.setView(view2);
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.setCancelable(true);
                final RatingBar give_rating;
                TextView tv_not_now, tv_rating_submit;


                give_rating = view2.findViewById(R.id.give_staff_rating);
                tv_not_now = view2.findViewById(R.id.tv_not_now_rating);
                tv_rating_submit = view2.findViewById(R.id.tv_submit_rating);
                et_staff_descrption = view2.findViewById(R.id.et_staff_description);

                tv_rating_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rating = String.valueOf(give_rating.getRating());
                        if (rating.isEmpty() || rating.equalsIgnoreCase("") || rating.equalsIgnoreCase("0.0")) {
                            Toast.makeText(StaffProfileActivity.this, "Please give rating to Staff", Toast.LENGTH_SHORT).show();
                        } else {
                            // Toast.makeText(StaffProfileActivity.this, "Given", Toast.LENGTH_SHORT).show();
                            GiveStaffRatingRequest giveStaffRatingRequest = new GiveStaffRatingRequest();
                            giveStaffRatingRequest.user_id = user_id;
                            //   staffProfileRequest.maidId = maid_id;
                            giveStaffRatingRequest.user_type = user_type;
                            giveStaffRatingRequest.maid_id = maid_id;
                            giveStaffRatingRequest.description = et_staff_descrption.getText().toString() + "";
                            giveStaffRatingRequest.rating = rating;
                            try {
                                obj = Class.forName(GiveStaffRatingRequest.class.getName()).cast(giveStaffRatingRequest);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            new RetrofitRequester(retrofitResponseListener).callPostServices(obj, 2, "give_rating_to_maid", true);


                        }
                    }
                });

                tv_not_now.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
                break;
            case R.id.ll_available_slots:

                Bundle bundle1 = new Bundle();
                bundle1.putString("Key_screen", "Avalable");
                bundle1.putString("Key_MaidId", maid_id);
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_STANDARD_TIMINGS, bundle1);

                break;
        }
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_my_staff_profile, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        retrofitResponseListener = this;
        maid_id = getIntent().getExtras().getString("maid_id");
        user_maid_id = getIntent().getExtras().getString("user_maid_id");

        txtitle = findViewById(R.id.txtitle);
        imgClose = findViewById(R.id.imgClose);
        txtitle.setText("Staff Profile");
        imgClose.setOnClickListener(this);

        tv_satff_name = findViewById(R.id.tv_staff_name);
        tv_staff_type = findViewById(R.id.tv_staff_type);
        tv_staff_rating = findViewById(R.id.tv_staff_rating);
        tv_working_flats = findViewById(R.id.tv_staff_working_flats);
        iv_staff = findViewById(R.id.iv_staff);
        rating_staff_profile = findViewById(R.id.rating_staff_profile);


        ll_make_call = findViewById(R.id.ll_make_call);
        ll_gate_pass = findViewById(R.id.ll_gate_pass);
        ll_staff_delete = findViewById(R.id.ll_staff_delete);
        ll_attendance_history = findViewById(R.id.ll_attendance_history);
        ll_give_rating = findViewById(R.id.ll_give_rating);
        ll_available_slots = findViewById(R.id.ll_available_slots);


        user_id = SharedPrefsUtils.getInstance(StaffProfileActivity.this).getId();
        user_type = SharedPrefsUtils.getInstance(StaffProfileActivity.this).getuserType();


        StaffProfileRequest staffProfileRequest = new StaffProfileRequest();
        staffProfileRequest.adminId = SharedPrefsUtils.getInstance(StaffProfileActivity.this).getAdminID();
        staffProfileRequest.maidId = maid_id;
        //staffProfileRequest.maidId = "2";
        try {
            obj = Class.forName(StaffProfileRequest.class.getName()).cast(staffProfileRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "maid_view", true);

        ll_make_call.setOnClickListener(this);
        ll_gate_pass.setOnClickListener(this);
        ll_staff_delete.setOnClickListener(this);
        ll_attendance_history.setOnClickListener(this);
        ll_give_rating.setOnClickListener(this);
        ll_available_slots.setOnClickListener(this);
    }


    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();

    }


    private void showPictureDialog(final String base64) {
        android.app.AlertDialog.Builder pictureDialog = new android.app.AlertDialog.Builder(StaffProfileActivity.this);
        pictureDialog.setTitle("Select Action");
//        String[] pictureDialogItems = {
//                "Select photo from gallery",
//                "Capture photo from camera"
//        };
        String[] pictureDialogItems = {
                "Capture photo from camera"
        };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                takePhotoFromCamera(base64);
                                break;
//                            case 1:
//                                takePhotoFromCamera(base64) ;
//                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    private void takePhotoFromCamera(String base64) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(StaffProfileActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(StaffProfileActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(StaffProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                Log.d("hhhh", "Permissions not granted");
                // ask for permission
                ActivityCompat.requestPermissions(getParent(), new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }
        try {
            //   FileName = System.currentTimeMillis() + ".jpg";
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        } else if (requestCode == 1) {

            profile = (Bitmap) data.getExtras().get("data");
            uploadimages.add(profile);
            new Async_BitmapWorkerTaskForProfile().execute();

            //saveImage(thumbnail);
        }
    }

    class Async_BitmapWorkerTaskForProfile extends AsyncTask<Integer, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        // Compress and Decode image in background.
        @Override
        protected String doInBackground(Integer... params) {

            Bitmap profilebit = profile;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            profilebit.compress(Bitmap.CompressFormat.JPEG, 70, stream);
            byte[] byte_arr = stream.toByteArray();
            base64profile = Base64.encodeToString(byte_arr, Base64.DEFAULT);
            return base64profile.trim();
        }

        // This method is run on the UI thread
        @Override
        protected void onPostExecute(String string) {
            try {
                //   JsonPrimitive firstHost = new JsonPrimitive("data:image/png;base64," + string);
                JSONObject jsonObject = new JSONObject();


                DigitalPassRequest.ImageItems imageItems = new DigitalPassRequest.ImageItems();
                imageItems.image = base64profile;
                imageIt.add(imageItems);


                jsonObject.put("image", base64profile);
                jArray.put(jsonObject);
                //  arrayList.add("data:image/png;base64," + string);
                arrayList.add(string);
                i = i + 1;
                rcvuploadimages.setVisibility(View.VISIBLE);
//                LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
//                mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                LinearLayoutManager layoutManager = new LinearLayoutManager(StaffProfileActivity.this, LinearLayoutManager.HORIZONTAL, false);
                rcvuploadimages.setLayoutManager(layoutManager);
                rcvuploadimages.setHasFixedSize(true);
                rcvuploadimages.setItemAnimator(new DefaultItemAnimator());
                uploadImagesAdapter = new UploadImagesAdapter(uploadimages, StaffProfileActivity.this);
                rcvuploadimages.setAdapter(uploadImagesAdapter);
                uploadImagesAdapter.notifyDataSetChanged();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(StaffProfileActivity.this, "Please Try Again");
        } else {
            try {

                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);

                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            staffprofilePojo = gson.fromJson(jsonString, StaffProfilePojo.class);
                            Picasso.with(StaffProfileActivity.this).load(Constants.BASE_IMAGE_URL + staffprofilePojo.getResponse().getImage())
                                    .error(R.drawable.dummy)
                                    .into(iv_staff);
                            tv_satff_name.setText(staffprofilePojo.getResponse().getTitle());
                            tv_staff_type.setText(staffprofilePojo.getResponse().getDesignation());
                            tv_staff_rating.setText(staffprofilePojo.getResponse().getRating());
                            tv_working_flats.setText(staffprofilePojo.getResponse().getFlats());
                            if (staffprofilePojo.getResponse().getRating().equalsIgnoreCase("")
                                    || staffprofilePojo.getResponse().getRating().isEmpty()
                                    || staffprofilePojo.getResponse().getRating() == null) {
                                rating_staff_profile.setRating(Float.parseFloat("0.0"));

                            } else {
                                rating_staff_profile.setRating(Float.parseFloat(staffprofilePojo.getResponse().getRating()));
                            }

                            //user_maid_id=staffprofilePojo.getResponse().getId();
                            break;
                        case 2:
                            Toast.makeText(StaffProfileActivity.this, "Thanks for Rating", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                            break;
                        case 3:

                            Toast.makeText(StaffProfileActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MYSTAFF_ALERTS);
                            finish();
                            break;
                        case 4:
                            Toast.makeText(StaffProfileActivity.this, "Gate pass have been Sent to security Sucessfully", Toast.LENGTH_SHORT).show();
                            break;
                    }

                }
            } catch (Exception e) {
            }
        }
    }
}
