package com.iprismech.alertnikkiresidence.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
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
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.StaffListAdapter;
import com.iprismech.alertnikkiresidence.adapters.UploadImagesAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.MyStaff_Maids_List_Pojo;
import com.iprismech.alertnikkiresidence.request.DeleteStaffRequest;
import com.iprismech.alertnikkiresidence.request.DigitalPassRequest;
import com.iprismech.alertnikkiresidence.request.StaffNotify;
import com.iprismech.alertnikkiresidence.request.StaffRequest;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.AlertUtils;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MyStaffAlerts extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {

    private RelativeLayout RlStaffsLists;
    private LinearLayout layoutNoStaff;
    private RecyclerView rvSatffLists;
    private LinearLayoutManager manager;
    private RetrofitResponseListener retrofitResponseListener;
    private TextView txtAddStaff;
    private StaffListAdapter staffListAdapter;
    private Object obj;
    private ImageView add_staff;
    private String base64profile;
    private MyStaff_Maids_List_Pojo myStaff_maids_list_pojo;
    private int removed_postion;
    private AlertDialog alertDialog;
    private UploadImagesAdapter uploadImagesAdapter;
    private Bitmap profile;
    private ArrayList<Bitmap> uploadimages = new ArrayList<>();
    private JSONArray jArray = new JSONArray();
    private int GALLERY_DOC = 0;
    private List<DigitalPassRequest.ImageItems> imageIt = new ArrayList<>();

    private List<String> arrayList = new ArrayList<>();
    private int i;
    private RecyclerView rcvuploadimages;

    private ImageView imgClose;
    private TextView txtitle;
    private boolean switchonOff = false;


    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
        finish();
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.imgClose:
                onBackPressed();
                break;
            case R.id.txtAddStaff:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_ADD_STAFF_SCREEN);
                finish();
                break;
            case R.id.fab_add_staff:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_ADD_STAFF_SCREEN);
                finish();
                break;
        }
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        txtitle = findViewById(R.id.txtitle);
        imgClose = findViewById(R.id.imgClose);
        txtitle.setText("My Staff alerts");
        imgClose.setOnClickListener(this);

        retrofitResponseListener = this;
        ApplicationController.getInstance().setContext(context);
        manager = new LinearLayoutManager(MyStaffAlerts.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        txtAddStaff = findViewById(R.id.txtAddStaff);
        layoutNoStaff = findViewById(R.id.layoutNoStaff);
        RlStaffsLists = findViewById(R.id.LayoutStaff);
        add_staff = findViewById(R.id.fab_add_staff);
        rvSatffLists = findViewById(R.id.rvStaffLists);


        String user_id = SharedPrefsUtils.getInstance(MyStaffAlerts.this).getId();

        rvSatffLists.setLayoutManager(manager);

        StaffRequest req = new StaffRequest();
        req.adminId = SharedPrefsUtils.getInstance(MyStaffAlerts.this).getAdminID();
        req.userId = SharedPrefsUtils.getInstance(MyStaffAlerts.this).getId();
        //  req.userId = 22;
        try {
            obj = Class.forName(StaffRequest.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "user_maids", true);


    }


    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_my_staff, null);
        return view;
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        txtAddStaff.setOnClickListener(this);
        add_staff.setOnClickListener(this);

    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, final int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(MyStaffAlerts.this, "Please Try Again");
        } else {
            try {

                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);


                JSONObject jsonObject = new JSONObject(jsonString);


                if (jsonObject.optBoolean("status")) {
                    layoutNoStaff.setVisibility(View.GONE);
                    RlStaffsLists.setVisibility(View.VISIBLE);


                    switch (requestId) {
                        case 1:
                            myStaff_maids_list_pojo = gson.fromJson(jsonString, MyStaff_Maids_List_Pojo.class);
                            staffListAdapter = new StaffListAdapter(MyStaffAlerts.this, myStaff_maids_list_pojo);
                            rvSatffLists.setAdapter(staffListAdapter);
//                            staffListAdapter.notifyDataSetChanged();
                            staffListAdapter.setOnItemClickListener(new StaffListAdapter.OnitemClickListener() {
                                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                                @Override
                                public void onItemClick(View view, int position) {
                                    switch (view.getId()) {
                                        case R.id.switch_noti:
                                            Switch aSwitch = view.findViewById(R.id.switch_noti);
                                            switchonOff = aSwitch.isChecked();
                                            //update_maid_notification_status
                                            staffNotifyWS(position, switchonOff);
//
                                            break;
                                        case R.id.ll_delete_maid:
                                            removed_postion = position;

                                            AlertUtils.showSimpleAlert(MyStaffAlerts.this, "Do you want to delete", "Confirm...?", "Yes", "No", new AlertUtils.onClicklistners() {
                                                @Override
                                                public void onpositiveclick() {
                                                    DeleteStaffRequest deleteStaffRequest = new DeleteStaffRequest();
                                                    deleteStaffRequest.user_maid_id = myStaff_maids_list_pojo.getResponse().get(removed_postion).getId();
                                                    try {
                                                        obj = Class.forName(DeleteStaffRequest.class.getName()).cast(deleteStaffRequest);
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                    new RetrofitRequester(retrofitResponseListener).callPostServices(obj, 2, "delete_user_maid", true);

                                                }

                                                @Override
                                                public void onNegativeClick() {

                                                }
                                            });

                                            break;
                                        case R.id.ll_make_call:
                                            Intent intent = new Intent(Intent.ACTION_DIAL);
                                            intent.setData(Uri.parse("tel:" + myStaff_maids_list_pojo.getResponse().get(position).getMobile()));
                                            startActivity(intent);
                                            break;

                                        case R.id.ll_send_gate_pass:
                                            LayoutInflater inflater = LayoutInflater.from(MyStaffAlerts.this);
//        getLayoutInflater().inflate(R.layout.alert_alerts,null);
                                            View view1 = inflater.inflate(R.layout.dialog_alert_staff_gate_pass, null);
                                            alertDialog = new AlertDialog.Builder(MyStaffAlerts.this).create();
                                            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                            alertDialog.setView(view1);
                                            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                            alertDialog.setCancelable(true);

                                            TextView tv_btn_upload_images, tv_cancel, tv_submit, tv_staff_maid_name, tv_maid_type;
                                            final EditText et_comment;
                                            tv_btn_upload_images = view1.findViewById(R.id.tv_btn_upload_images);
                                            tv_cancel = view1.findViewById(R.id.tv_btn_pass_cancel);
                                            tv_submit = view1.findViewById(R.id.tv_btn_pass_submit);
                                            et_comment = view1.findViewById(R.id.et_comment);
                                            tv_staff_maid_name = view1.findViewById(R.id.tv_staff_maid_name);
                                            tv_maid_type = view1.findViewById(R.id.tv_maid_type);
                                            rcvuploadimages = view1.findViewById(R.id.rview_add_images);
                                            tv_staff_maid_name.setText(myStaff_maids_list_pojo.getResponse().get(position).getMaid_name());
                                            tv_maid_type.setText(myStaff_maids_list_pojo.getResponse().get(position).getMaid_designation());

                                            tv_btn_upload_images.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                                                    StrictMode.setVmPolicy(builder.build());
                                                    //     permissionsRequest();
                                                    showPictureDialog("");
//                                                    v.setOnClickListener(new View.OnClickListener() {
//                                                        @Override
//                                                        public void onClick(View v) {
//                                                            switch (v.getId()) {
//                                                                case R.id.imgdelete:
//                                                                    jArray.remove(removed_postion);
//                                                                    uploadimages.remove(removed_postion);
//                                                                    uploadImagesAdapter.notifyDataSetChanged();
//                                                                    uploadImagesAdapter.notifyItemChanged(removed_postion);
//                                                                    if (jArray.length() == 0) {
//                                                                        //rcvuploadimages.setVisibility(View.GONE);
//                                                                    }
//                                                                    break;
//                                                            }
//                                                        }
//                                                    });
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
                                                        Toast.makeText(MyStaffAlerts.this, "Please write description", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        DigitalPassRequest digitalPassRequest = new DigitalPassRequest();
                                                        digitalPassRequest.adminId = SharedPrefsUtils.getInstance(MyStaffAlerts.this).getAdminID();
                                                        digitalPassRequest.userId = SharedPrefsUtils.getInstance(MyStaffAlerts.this).getId();
                                                        digitalPassRequest.userType = SharedPrefsUtils.getInstance(MyStaffAlerts.this).getuserType();
                                                        digitalPassRequest.maidId = myStaff_maids_list_pojo.getResponse().get(removed_postion).getId();
                                                        digitalPassRequest.description = et_comment.getText().toString();
                                                        digitalPassRequest.entryIn = "17:20:20";
                                                        digitalPassRequest.entryOut = "18:20:20";
                                                        //digitalPassRequest.images = jArray;
                                                        digitalPassRequest.images = imageIt;

                                                        try {
                                                            obj = Class.forName(DigitalPassRequest.class.getName()).cast(digitalPassRequest);
                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                        }
                                                        new RetrofitRequester(retrofitResponseListener).callPostServices(obj, 3, "maid_digital_pass", true);

                                                    }
                                                }
                                            });

                                            break;
                                        case R.id.imgdelete:
                                            jArray.remove(removed_postion);
                                            uploadimages.remove(removed_postion);
                                            uploadImagesAdapter.notifyDataSetChanged();
                                            uploadImagesAdapter.notifyItemChanged(removed_postion);
                                            if (jArray.length() == 0) {
                                                //rcvuploadimages.setVisibility(View.GONE);
                                            }
                                            break;

                                    }
                                }
                            });

                            break;
                        case 2:
                            Toast.makeText(MyStaffAlerts.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                            myStaff_maids_list_pojo.getResponse().remove(removed_postion);
//                        notifyDataSetChanged();
                            staffListAdapter.notifyDataSetChanged();
                            staffListAdapter.notifyItemRemoved(removed_postion);
                            break;

                        case 3:
                            Toast.makeText(MyStaffAlerts.this, "Successfully Sent to Security", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                            break;
                        case 4:
                            staffListAdapter.notifyDataSetChanged();
                            break;

                    }
                } else {
                    Common.showToast(MyStaffAlerts.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void staffNotifyWS(int position, boolean switchonOff) {
        StaffNotify req = new StaffNotify();
        req.adminId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_ADMIN_ID);
        req.maidId = myStaff_maids_list_pojo.getResponse().get(position).getMaid_id();
        if (switchonOff) {

            req.status = "1";
            myStaff_maids_list_pojo.getResponse().get(position).setNotification_status("1");
        } else if (switchonOff == false) {
            req.status = "0";
            myStaff_maids_list_pojo.getResponse().get(position).setNotification_status("0");

        }
        try {
            obj = Class.forName(StaffNotify.class.getName()).cast(req);
        } catch (Exception e) {

        }

        new RetrofitRequester(this).callPostServices(obj, 4, "update_maid_notification_status", false);
    }

    private void showPictureDialog(final String base64) {
        android.app.AlertDialog.Builder pictureDialog = new android.app.AlertDialog.Builder(MyStaffAlerts.this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera"
        };
//        String[] pictureDialogItems = {
//                "Select photo from gallery",
//                "Capture photo from camera"
////        };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary(base64);
                                break;
                            case 1:
                                takePhotoFromCamera(base64);
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary(String base64) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(MyStaffAlerts.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(MyStaffAlerts.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(MyStaffAlerts.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                Log.d("hhhh", "Permissions not granted");
                // ask for permission
                ActivityCompat.requestPermissions(MyStaffAlerts.this, new String[]{Manifest.permission.CAMERA}, 1);
            }
        }
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(Intent.createChooser(galleryIntent, "Select Picture"), GALLERY_DOC);


    }

    protected Bitmap decodeUri(Uri selectedImage, int REQUIRED_SIZE) {
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);
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
            return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void takePhotoFromCamera(String base64) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(MyStaffAlerts.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(MyStaffAlerts.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(MyStaffAlerts.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
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
        } else if (requestCode == GALLERY_DOC) {
            if (data != null) {

                Uri contentURI = data.getData();
                profile = decodeUri(contentURI, 200);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                profile.compress(Bitmap.CompressFormat.JPEG, 70, bytes);
                uploadimages.add(profile);
                new Async_BitmapWorkerTaskForProfile().execute();
                // respond to users whose devices do not support the crop action

                //     new Async_BitmapWorkerTask().execute();
                // String path = saveImage(bitmap);
            }
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
                LinearLayoutManager layoutManager = new LinearLayoutManager(MyStaffAlerts.this, LinearLayoutManager.HORIZONTAL, false);
                rcvuploadimages.setLayoutManager(layoutManager);
                rcvuploadimages.setHasFixedSize(true);
                rcvuploadimages.setItemAnimator(new DefaultItemAnimator());
                uploadImagesAdapter = new UploadImagesAdapter(uploadimages, MyStaffAlerts.this);
                rcvuploadimages.setAdapter(uploadImagesAdapter);
                uploadImagesAdapter.notifyDataSetChanged();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

