package com.iprismech.alertnikkiresidence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.ContactModel;
import com.iprismech.alertnikkiresidence.utilities.Common;

import java.util.ArrayList;

public class GuestEditActivity extends BaseAbstractActivity implements View.OnClickListener {
    private EditText edtName, edtMobile, edtDate, edtVehicle1, edtVehicle2, edtVehicle3, edtVehicle4;
    private TextView txtSave;
    private String Name = "", Contact = "", tag = "";

    private ArrayList<ContactModel> contactsList = new ArrayList<>();
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_edit);
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
            Name = contactsList.get(position).getContactName();
            Contact = contactsList.get(position).getContactNumber();
        }
        edtName = findViewById(R.id.edtname);
        edtMobile = findViewById(R.id.edtMobileNumber);
        edtDate = findViewById(R.id.edtDate);
        edtVehicle1 = findViewById(R.id.edtVehicle1);
        edtVehicle2 = findViewById(R.id.edtVehicle2);
        edtVehicle3 = findViewById(R.id.edtVehicle3);
        edtVehicle4 = findViewById(R.id.edtVehicle4);
        txtSave = findViewById(R.id.txtSave);

        edtName.setText(Name);
        edtMobile.setText(Contact);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtSave:
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
                    contactsList.add(position,model);
                    Intent intent = new Intent(GuestEditActivity.this, ViewInviteGuestActivity.class);
                    intent.putParcelableArrayListExtra("Key_Contacts", contactsList);
//                    intent.putExtra("Key_Position", position);
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }
}
