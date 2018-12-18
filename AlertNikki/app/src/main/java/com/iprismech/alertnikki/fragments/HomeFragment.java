package com.iprismech.alertnikki.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.activity.ScannerActivity;

public class HomeFragment extends BaseAbstractFragment<Class> implements View.OnClickListener {
    private LinearLayout qrcode;

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.home_fragment, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        qrcode.setOnClickListener(this);
    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();
        qrcode = view.findViewById(R.id.qrcode_Scan_Layout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qrcode_Scan_Layout:
                Intent intent = new Intent(getActivity(), ScannerActivity.class);
                startActivity(intent);
                break;
        }
    }
}
