package com.iprismech.alertnikki.retrofitnetwork;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import android.widget.Toast;

import com.iprismech.alertnikki.MyApplication;
import com.iprismech.alertnikki.utilities.Common;
import com.iprismech.alertnikki.utilities.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RetrofitRequester {
    private final RetrofitResponseListener retrofitResponseListener;
    private Activity activity;
    private Context context;
    private Dialog progressDialog;

    public RetrofitRequester(RetrofitResponseListener retrofitResponseListener) {

        this.retrofitResponseListener = retrofitResponseListener;

        if (retrofitResponseListener instanceof Activity) {
            this.context = (Context) retrofitResponseListener;
            this.activity = (Activity) retrofitResponseListener;
        } else if (retrofitResponseListener instanceof Fragment) {
            this.context = ((Fragment) retrofitResponseListener).getActivity();
            this.activity = ((Fragment) retrofitResponseListener).getActivity();

        } else if (retrofitResponseListener instanceof android.support.v4.app.Fragment) {
            this.context = ((android.support.v4.app.Fragment) retrofitResponseListener).getActivity();
            this.activity = ((android.support.v4.app.Fragment) retrofitResponseListener).getActivity();

        } else if (retrofitResponseListener instanceof WakefulBroadcastReceiver) {
            this.context = (Context) retrofitResponseListener;
            this.activity = (Activity) retrofitResponseListener;

        } else if (retrofitResponseListener instanceof DialogInterface.OnClickListener) {
            this.context = (Context) retrofitResponseListener;
            this.activity = (Activity) retrofitResponseListener;

        }


    }

    public RetrofitRequester(Context context, RetrofitResponseListener retrofitResponseListener1, Dialog progressDialog) {

        this.retrofitResponseListener = retrofitResponseListener1;
        this.context = context;
        this.progressDialog = progressDialog;
    }


    public void callPostServices(final Object obj, final int requesterId, String url, final boolean showProgressDialog) {

        if (requesterId == Constants.RequestCodes.ONCREATE_REQUEST_CODE) {
            Common.showContentView((Activity) context, false);
        }

        if (!Common.haveInternet(context)) {

//            Common.customToast(context, Constants.INTERNET_UNABLEABLE);
            Toast.makeText(context, Constants.INTERNET_UNABLEABLE, Toast.LENGTH_LONG).show();

            return;

        }
// Run now

        if (showProgressDialog) {

            progressDialog = Common.showProgressDialog(context);
//            progressDialog.dismiss();

        }


        Call<Object> dfdf = MyApplication.getInstance().getAPIInterface().callPost(url, obj);
        dfdf.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Common.dismissProgressDialog(progressDialog);
//            progressDialog.dismiss();
                Log.d("retrofit", response.toString());
                retrofitResponseListener.onResponseSuccess(response.body(), obj, requesterId);
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Common.dismissProgressDialog(progressDialog);
                Log.d("retrofiterror", t.toString());
//            progressDialog.dismiss();
            }
        });



    }
    public void callGetServices(final Object obj, final int requesterId, String url, final boolean showProgressDialog) {

        if (requesterId == Constants.RequestCodes.ONCREATE_REQUEST_CODE) {
            Common.showContentView((Activity) context, false);
        }

        if (!Common.haveInternet(context)) {

//            Common.customToast(context, Constants.INTERNET_UNABLEABLE);
            Toast.makeText(context, Constants.INTERNET_UNABLEABLE, Toast.LENGTH_LONG).show();

            return;

        }
// Run now

        if (showProgressDialog) {

            progressDialog = Common.showProgressDialog(context);
//            progressDialog.dismiss();

        }


        Call<Object> dfdf = MyApplication.getInstance().getAPIInterface().callGet(url);
        dfdf.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Common.dismissProgressDialog(progressDialog);
//            progressDialog.dismiss();
                Log.d("retrofit", response.toString());
                retrofitResponseListener.onResponseSuccess(response.body(), obj, requesterId);
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Common.dismissProgressDialog(progressDialog);
                Log.d("retrofiterror", t.toString());
//            progressDialog.dismiss();
            }
        });



    }
}
