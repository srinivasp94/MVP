package com.iprismech.alertnikki.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.Result;
import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.base.BaseAbstractActivity;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QrCode_Fragment extends BaseAbstractActivity<Class> implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;

    @Override
    public void setPresenter() {

    }
    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
    }



    @Override
    protected View getView() {
        mScannerView = new ZXingScannerView(QrCode_Fragment.this);
        setContentView(mScannerView);
        View view = mScannerView;
        return view;
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        mScannerView.setResultHandler(QrCode_Fragment.this);
        // Start camera on resume
        mScannerView.startCamera();
    }



    @Override
    public void handleResult(Result result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setMessage(result.getText());
        AlertDialog alert1 = builder.create();
        alert1.show();

        // If you would like to resume scanning, call this method below:
        mScannerView.stopCamera();
    }
    @Override
    public void onResume() {
        super.onResume();
        // Register ourselves as a handler for scan results.

    }

    @Override
    public void onPause() {
        super.onPause();
        // Stop camera on pause
        mScannerView.stopCamera();
    }

}
