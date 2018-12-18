package com.iprismech.alertnikki.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.app.factories.constants.AppConstants;
import com.iprismech.alertnikki.app.factories.controllers.ApplicationController;
import com.iprismech.alertnikki.base.BaseAbstractActivity;
import com.iprismech.alertnikki.utilities.Common;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class CameraActivity extends BaseAbstractActivity<Class> {

    private String img_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_camera);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_camera, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, 2);
    }

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        Thread timer = new Thread() {
            {
                try {
                    sleep(300);
                    CameraActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (requestCode == 2) {

                                    Bitmap picture = (Bitmap) data.getExtras().get("data");
//                                    profile.setImageBitmap(picture);
                                    //ImageFile = new File(CameraImageUri.getPath());
//                                    UiAppConstant.venueimage = Util.getInstance().convertInBase64(picture);
                                    img_path = convertInBase64(picture);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("Key_Base", img_path);
                                    Log.d("Base64",img_path);
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_WELCOME_SCREEN, bundle);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    Common.showToast(context, "ERROR : " + e.getMessage());
                }
            }
        };

        timer.start();
    }

    public String convertInBase64(Bitmap imageBitmap) {
        String encodedString = "";
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            encodedString = Base64.encodeToString(byteArray, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodedString;
    }

}
