package com.iprismech.alertnikki.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.iprismech.alertnikki.Pojo.MoreAllServicesPojo;
import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.adapters.MoreAllServicesAdapter;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.Common;

import org.json.JSONObject;

public class MoreFragment extends BaseAbstractFragment<Class> implements RetrofitResponseListener {
    private RecyclerView rview_more_all_services;
    private RetrofitResponseListener retrofitResponseListener;
    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.more_all_services, null);
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
        retrofitResponseListener=this;
        rview_more_all_services=view.findViewById(R.id.rview_more_all_services);
        new RetrofitRequester(retrofitResponseListener).callPostServices("", 1, "more_all_services", true);
    }
    @Override
    protected void initialiseViews() {
        super.initialiseViews();
    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(getActivity(), "PLease Try again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject object = new JSONObject(jsonString);

                if (object.optBoolean("status") == true) {
                    switch (requestId) {
                        case 1:
                            MoreAllServicesPojo moreAllServicesPojo = new Gson().fromJson(jsonString, MoreAllServicesPojo.class);
                            rview_more_all_services.setHasFixedSize(true);
                            LinearLayoutManager lManager=new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
                            rview_more_all_services.setAdapter(new MoreAllServicesAdapter(getActivity(),moreAllServicesPojo));
                            rview_more_all_services.setLayoutManager(lManager);
                            break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
