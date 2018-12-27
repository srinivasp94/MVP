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
import com.iprismech.alertnikki.Request.CallVisitor;
import com.iprismech.alertnikki.Request.Visitor;
import com.iprismech.alertnikki.Request.Visitor_In;
import com.iprismech.alertnikki.Response.ResponseVisitMember;
import com.iprismech.alertnikki.Response.WaitingVisitors;
import com.iprismech.alertnikki.adapters.WaitingVisitorAdapter;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.Common;
import com.iprismech.alertnikki.utilities.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class WaitingVisitorFragment extends BaseAbstractFragment<Class> implements RetrofitResponseListener {
    private RecyclerView rv_visit_inside;
    private LinearLayoutManager manager;
    private WaitingVisitorAdapter adapter;
    private ArrayList<ResponseVisitMember> arrayList = new ArrayList<>();
    private Object obj;
    private int itemposition;

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
        visitor.adminId = SharedPrefsUtils.getInstance(getActivity()).getAdmin();
        //for waiting status as 0
        visitor.status = "0";
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
//        WaitingVisitors

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
                            arrayList = (ArrayList<ResponseVisitMember>) waitingVisitors.response;

                            if (arrayList != null && arrayList.size() > 0) {
                                adapter = new WaitingVisitorAdapter(getActivity(), arrayList);
                                rv_visit_inside.setAdapter(adapter);
                                adapter.setOnItemClickListener(new WaitingVisitorAdapter.OnitemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        switch (view.getId()) {

                                            case R.id.btn_in:
                                                callWaiting_in_WS(position);
                                                break;
                                            case R.id.btn_check:
                                                call_Waiting_check_WS(position);
                                                break;
                                            case R.id.btn_call:
                                                call_Waiting_call_WS(position);
                                                break;
                                            case R.id.btn_msg:
                                                call_Waiting_msg_WS(position);
                                                break;
                                        }
                                    }
                                });
                            }
                            break;
                        case 2:
                            arrayList.remove(itemposition);
                            adapter.notifyDataSetChanged();
                            Common.showToast(getActivity(), object.optString("message"));
                            break;
                        case 3:
                            Common.showToast(getActivity(), object.optString("message"));
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

    private void call_Waiting_msg_WS(int position) {
//        msg_notification

    }

    private void callWaiting_in_WS(int position) {

        itemposition = position;
        Visitor_In req = new Visitor_In();
        req.securityId = SharedPrefsUtils.getInstance(getActivity()).getsecurityId();
        req.visitorId = arrayList.get(position).visitorId;
        if (arrayList.get(position).type.equalsIgnoreCase("Guest")) {
            req.type = "1";
        } else {
            req.type = "2";
        }

        try {
            obj = Class.forName(Visitor_In.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 2, "update_in_visitors", true);

    }

    private void call_Waiting_check_WS(int position) {

    }

    private void call_Waiting_call_WS(int position) {
//        user_call_service

        CallVisitor req = new CallVisitor();
        req.adminId = SharedPrefsUtils.getInstance(getActivity()).getAdmin();
        req.userId = arrayList.get(position).userId;
        req.userType = arrayList.get(position).userType;

        try {
            obj = Class.forName(CallVisitor.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 3, "user_call_service", true);
    }


}

