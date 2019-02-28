package com.iprismech.alertnikkiresidence.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.ContactModel;
import com.iprismech.alertnikkiresidence.request.GuestEditReq;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.TimeUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class GuestEditActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private EditText edtName, edtMobile, edtDate, edtVehicle1, edtVehicle2, edtVehicle3, edtVehicle4;
    private TextView txtSave;
    private String Name = "", Contact = "", tag = "";
    private String screenid, guestName, guestPhn, guestId;

    private ArrayList<ContactModel> contactsList = new ArrayList<>();
    private int position = 0;
    private Object obj;

    private ImageView imgClose;
    private TextView txtitle;
    private String mMobile = "";
    private String sub_Id;
    private String key_name;


    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (screenid.equalsIgnoreCase("1")) {
            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_INVITE_GUEST_SCREEN);
            finish();
        } else if (screenid.equalsIgnoreCase("3")) {
            Intent intent = new Intent(GuestEditActivity.this, ViewInviteGuestActivity.class);
            intent.putParcelableArrayListExtra("Key_Contacts", contactsList);
//                    intent.putExtra("Key_Position", position);
            startActivity(intent);
            finish();

        } else {
            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_INVITE_GUEST_SCREEN);
            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_guest_edit);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_guest_edit, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        txtSave.setOnClickListener(this);
        imgClose.setOnClickListener(this);
        edtDate.setOnClickListener(this);
        edtVehicle1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edtVehicle1.getText().toString().length() == 2) {
                    edtVehicle2.requestFocus();
                }
            }
        });
        edtVehicle2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edtVehicle2.getText().toString().length() == 2) {
                    edtVehicle3.requestFocus();
                }
            }
        });
        edtVehicle3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edtVehicle3.getText().toString().length() == 2) {
                    edtVehicle4.requestFocus();
                }
            }
        });

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        ApplicationController.getInstance().setContext(context);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
           /* Name = bundle.getString("Key_Name", "");
            Contact = bundle.getString("Key_Contact", "");
            tag = bundle.getString("Key_Tag", "");*/
//            Name = contactsList.get(position).getContactName();
//            Contact = contactsList.get(position).getContactNumber();
            contactsList = bundle.getParcelableArrayList("Key_Contacts");
            position = bundle.getInt("Key_Position", 0);
            screenid = bundle.getString("Key_id", "");
            sub_Id = bundle.getString("Key_Sub_Id", "");
            guestId = bundle.getString("Key_GuestId", "");
            guestName = bundle.getString("Key_Name", "");
            guestPhn = bundle.getString("Key_Mobile", "");
            key_name = bundle.getString("Key_Name", "");
        }

        txtitle = findViewById(R.id.txtitle);
        imgClose = findViewById(R.id.imgClose);
        if (key_name.equalsIgnoreCase("OTHERS"))
            txtitle.setText("Add Guest Details");
        else
            txtitle.setText("Edit Guest Details");

        edtName = findViewById(R.id.edtname);
        edtMobile = findViewById(R.id.edtMobileNumber);
        edtDate = findViewById(R.id.edtDate);
        edtVehicle1 = findViewById(R.id.edtVehicle1);
        edtVehicle2 = findViewById(R.id.edtVehicle2);
        edtVehicle3 = findViewById(R.id.edtVehicle3);
        edtVehicle4 = findViewById(R.id.edtVehicle4);
        txtSave = findViewById(R.id.txtSave);

        /*edtName.setText(Name);
        edtMobile.setText(Contact);*/
        //!screenid.equalsIgnoreCase("3") &&
        if (!screenid.equalsIgnoreCase("1") && contactsList != null && contactsList.size() > 0 && !sub_Id.equalsIgnoreCase("10")) {
            edtName.setText(contactsList.get(position).getContactName().trim());
            int len = contactsList.get(position).getContactNumber().replace(" ", "").trim().length();
            int length = 0;
            if (len > 10) {
                mMobile = contactsList.get(position).getContactNumber().replace(" ", "").trim().substring(len - 10);
                edtMobile.setText(mMobile.trim());
            } else
                edtMobile.setText(contactsList.get(position).getContactNumber().replace(" ", "").trim());
//            edtMobile.setText(contactsList.get(position).getContactNumber());

        } else {
            edtName.setText(guestName);
            int len = guestPhn.trim().length();
            int length = 0;
            if (len > 10) {
                mMobile = guestPhn.trim().substring(len - 10);
                edtMobile.setText(guestPhn.trim());
            }
            if (!TextUtils.isEmpty(guestPhn))
                edtMobile.setText(guestPhn.trim());
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgClose:
                onBackPressed();
                break;
            case R.id.edtDate:
                TimeUtils.showDatePickerDialog(GuestEditActivity.this, "", edtDate);
                break;
            case R.id.txtSave:

                if (screenid.equalsIgnoreCase("1")) {
                    GuestEditReq req = new GuestEditReq();
                    req.guestId = guestId;
                    req.name = edtName.getText().toString();
                    req.date = edtDate.getText().toString();
                    req.vehicleNo = edtVehicle1.getText().toString() + " " + edtVehicle2.getText().toString() +
                            " " + edtVehicle3.getText().toString() + " " + edtVehicle4.getText().toString();
                    try {
                        obj = Class.forName(GuestEditReq.class.getName()).cast(req);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new RetrofitRequester(this).callPostServices(obj, 2, " edit_guest", true);
                } else if (screenid.equalsIgnoreCase("3")) {
                    ContactModel model = new ContactModel();
                    model.setContactName(edtName.getText().toString());
                    model.setContactNumber(edtMobile.getText().toString());
                    model.setVehiclenumber(edtVehicle1.getText().toString() + "" + edtVehicle2.getText().toString() +
                            " " + edtVehicle3.getText().toString() + " " + edtVehicle4.getText().toString());
                    contactsList.add(model);
                    Intent intent = new Intent(GuestEditActivity.this, ViewInviteGuestActivity.class);
                    intent.putParcelableArrayListExtra("Key_Contacts", contactsList);
//                    intent.putExtra("Key_Position", position);
                    startActivity(intent);
                    finish();
                } else {
                    if (edtName.getText().toString().length() == 0 && edtMobile.getText().toString().length() == 0) {
                        Common.showToast(GuestEditActivity.this, "Please Enter Details");
                    } else if (edtName.getText().toString().length() == 0) {
                        Common.showToast(GuestEditActivity.this, "Please Enter Name");
                    } else if (edtMobile.getText().toString().length() == 0 || edtMobile.getText().toString().length() < 10) {
                        Common.showToast(GuestEditActivity.this, "Please Enter phone");
                    } else {
                        ContactModel model = new ContactModel();
                        model.setContactName(edtName.getText().toString());
                        model.setContactNumber(edtMobile.getText().toString());
                        model.setVehiclenumber(edtVehicle1.getText().toString() + "" + edtVehicle2.getText().toString() +
                                " " + edtVehicle3.getText().toString() + " " + edtVehicle4.getText().toString());
                        if (contactsList != null && contactsList.size() > 0) {
                            contactsList.remove(position);
                            contactsList.add(model);
                        } else {
//                    contactsList.remove(position);
                            try {
                                contactsList = new ArrayList<>();
                                contactsList.add(model);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        Intent intent = new Intent(GuestEditActivity.this, ViewInviteGuestActivity.class);
                        intent.putParcelableArrayListExtra("Key_Contacts", contactsList);
//                    intent.putExtra("Key_Position", position);
                        startActivity(intent);
                        finish();
                    }
                }
                break;
        }
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(GuestEditActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 2:
                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_INVITE_GUEST_SCREEN);
                            finish();
                            break;
                    }
                } else {
                    Common.showToast(GuestEditActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
