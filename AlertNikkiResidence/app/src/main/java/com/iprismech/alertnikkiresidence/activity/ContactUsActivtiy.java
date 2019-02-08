package com.iprismech.alertnikkiresidence.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;

public class ContactUsActivtiy extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private LinearLayout ll_make_call_contact_us;
    private TextView tv_number_contact_us;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ll_make_call_contact_us.findViewById(R.id.ll_make_call_contact_us);
        tv_number_contact_us.findViewById(R.id.tv_number_contact_us);


        ll_make_call_contact_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", tv_number_contact_us.getText().toString(), null));
                startActivity(intent);
            }
        });
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_contact_us, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {

    }
}
