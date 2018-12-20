package com.iprismech.alertnikki.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Request.Visitor;
import com.iprismech.alertnikki.Response.WaitingVisitors;
import com.iprismech.alertnikki.adapters.InsideAdapter;
import com.iprismech.alertnikki.app.factories.controllers.ApplicationController;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.Common;
import com.iprismech.alertnikki.utilities.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class Inside_Visitors_fragment extends BaseAbstractFragment<Class> implements RetrofitResponseListener {
    private RecyclerView rv_visit_inside;
    private LinearLayoutManager manager;
    private InsideAdapter insideAdapter;
    private ArrayList arrayList;
    private Object obj;

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_inside_waititng, null);
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

        rv_visit_inside = view.findViewById(R.id.rv_visitors);
        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        rv_visit_inside.setLayoutManager(manager);


        Visitor visitor = new Visitor();

        //for inside status as 1
        visitor.adminId = SharedPrefsUtils.getInstance(getActivity()).getAdmin();
        visitor.status = "1";
        visitor.search = "";
        try {
            obj = Class.forName(Visitor.class.getName()).cast(visitor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "visitors", true);

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectRequest.equals("")) {
            Common.showToast(getActivity(), "PLease Try again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject object = new JSONObject(jsonString);
                if (object.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            WaitingVisitors waitingVisitors = Common.getSpecificDataObject(objectResponse, WaitingVisitors.class);

                            arrayList = (ArrayList) waitingVisitors.response;
                            if (arrayList != null && arrayList.size() > 0) {
                                insideAdapter = new InsideAdapter(getActivity(), arrayList);
                                rv_visit_inside.setAdapter(insideAdapter);
                            } else {
                                Common.showToast(getActivity(), "Items Found");
                            }
                            break;
                    }
                } else {
                    Common.showToast(getActivity(), object.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }
}
