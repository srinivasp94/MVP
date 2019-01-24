package com.iprismech.alertnikki.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.iprismech.alertnikki.Pojo.MoreAllServicesPojo;
import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.adapters.MoreAllServicesAdapter;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.Common;

import org.json.JSONObject;

public class MoreFragment extends BaseAbstractFragment<Class> implements RetrofitResponseListener,View.OnClickListener {
    private RecyclerView rview_more_all_services;
    private RetrofitResponseListener retrofitResponseListener;
    private LinearLayout ll_movein,ll_moveout;
    private FragmentManager fragmentManager;
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
        }
    @Override
    protected void initialiseViews() {
        super.initialiseViews();

        fragmentManager = getFragmentManager();
        retrofitResponseListener=this;
        rview_more_all_services=view.findViewById(R.id.rview_more_all_services);
        ll_movein=view.findViewById(R.id.ll_movein);
        ll_moveout=view.findViewById(R.id.ll_moveout);
        ll_moveout.setOnClickListener(this);
        ll_movein.setOnClickListener(this);

        new RetrofitRequester(retrofitResponseListener).callPostServices("", 1, "more_all_services", true);
    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(getActivity(), "Please Try again");
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

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.ll_movein:
                fragmentManager.beginTransaction().addToBackStack("").replace(R.id.fm_container, new MoveInFragment(), "").commit();
                break;
            case R.id.ll_moveout:
                fragmentManager.beginTransaction().addToBackStack("").replace(R.id.fm_container, new MoveOutFragment(), "").commit();
                break;
        }

    }
}
