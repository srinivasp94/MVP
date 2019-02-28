package com.iprismech.alertnikkiresidence.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.ContactViewAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.Contact;
import com.iprismech.alertnikkiresidence.pojo.ContactModel;
import com.iprismech.alertnikkiresidence.request.Guestinvite;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;
import com.iprismech.alertnikkiresidence.utilities.TimeUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class ViewInviteGuestActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private ContactViewAdapter adapter;
    private RecyclerView rvSelectContact;
    private LinearLayoutManager manager;
    ArrayList<ContactModel> contactsList;

    private RelativeLayout rlDateselect;
    private EditText edtdate;
    private LinearLayout InviteGuests;
    private Object obj;

    int itemPosition = 0;
    private ImageView imgClose;
    private TextView txtitle;
    private ImageView fab;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_inview_guest);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_view_inview_guest, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        edtdate.setOnClickListener(this);
        InviteGuests.setOnClickListener(this);
        fab.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);
        contactsList = getIntent().getParcelableArrayListExtra("Key_Contacts");
        manager = new LinearLayoutManager(ViewInviteGuestActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        txtitle = findViewById(R.id.txtitle);
        imgClose = findViewById(R.id.imgClose);
        txtitle.setText("Guest");
        imgClose.setOnClickListener(this);

        rvSelectContact = findViewById(R.id.RvSelectContact);
        rlDateselect = findViewById(R.id.rlDateselect);
        InviteGuests = findViewById(R.id.llInviteGuests);
        edtdate = findViewById(R.id.edtGuestDate);
        fab = findViewById(R.id.fab);

        rvSelectContact.setLayoutManager(manager);
        if (contactsList != null && contactsList.size() > 0) {
            adapter = new ContactViewAdapter(ViewInviteGuestActivity.this, contactsList);
            rvSelectContact.setAdapter(adapter);
            adapter.setOnItemClickListener(new ContactViewAdapter.OnitemClickListener() {
                @SuppressLint("WrongConstant")
                @Override
                public void onItemClick(View view, int position) {
                    switch (view.getId()) {
                        case R.id.imgDelete:
                            contactsList.remove(contactsList.get(position));
                            adapter.notifyDataSetChanged();
                            break;
                        case R.id.imgEdit:
                            /*Bundle bundle1 = new Bundle();
                            bundle1.putString("Key_Name", contactsList.get(position).getContactName());
                            bundle1.putString("Key_Contact", contactsList.get(position).getContactNumber());
                            bundle1.putString("Key_Tag", "ViewGuest");
                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_EDIT_GUEST_SCREEN, bundle1);*/
                            Intent intent = new Intent(ViewInviteGuestActivity.this, GuestEditActivity.class);
                            intent.putParcelableArrayListExtra("Key_Contacts", contactsList);
                            intent.putExtra("Key_id", "3");
                            intent.putExtra("Key_Position", position);
                            startActivity(intent);
                            finish();
                            break;

                    }
                }
            });
        } else {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgClose:
                onBackPressed();
                break;
            case R.id.edtGuestDate:
                TimeUtils.showDatePickerDialog(ViewInviteGuestActivity.this, "", edtdate);
                break;
            case R.id.llInviteGuests:
                if (edtdate.getText().toString().length() == 0) {
                    Common.showToast(ViewInviteGuestActivity.this, "Please Select Date");
                } else {
                    ArrayList<Contact> list = new ArrayList<>();
                    Contact contact;
                    for (int i = 0; i < contactsList.size(); i++) {
                        contact = new Contact();
                        contact.name = contactsList.get(i).getContactName();
                        contact.mobile = contactsList.get(i).getContactNumber();
                        contact.vehicleNo = contactsList.get(i).getVehiclenumber();
                        list.add(contact);
                    }
                    Guestinvite req = new Guestinvite();
                    req.adminId = SharedPrefsUtils.getInstance(ViewInviteGuestActivity.this).getAdminID();
                    req.otpSentType = "1";
                    req.userId = SharedPrefsUtils.getInstance(ViewInviteGuestActivity.this).getId();
                    req.userType = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_USER_TYPE);

                    ArrayList<Guestinvite.WeekDate> week = new ArrayList<>();
                    Guestinvite.WeekDate weekdate = new Guestinvite.WeekDate();
                    weekdate.date = edtdate.getText().toString();
                    week.add(weekdate);

                    req.weekDates = week;
                    req.contacts = list;


                    try {
                        obj = Class.forName(Guestinvite.class.getName()).cast(req);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new RetrofitRequester(this).callPostServices(obj, 1, "send_invite_guest", true);
                }
                break;
            case R.id.fab:
                Intent intent1 = new Intent(ViewInviteGuestActivity.this, GuestEditActivity.class);
                intent1.putParcelableArrayListExtra("Key_Contacts", contactsList);
                intent1.putExtra("Key_id", "3");
                intent1.putExtra("Key_Sub_Id", "10");
                intent1.putExtra("Key_Position", "0");
                intent1.putExtra("Key_TitleName", "OTHERS");
                startActivity(intent1);
                finish();
                break;
        }
    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(ViewInviteGuestActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            finish();
                            break;
                    }
                } else {
                    Common.showToast(ViewInviteGuestActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
