package com.iprismech.alertnikki.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikki.MainActivity;
import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Request.AdminStaff;
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
    private EditText msearchView;
    private WaitingVisitorAdapter adapter;
    public ArrayList<ResponseVisitMember> arrayList = new ArrayList<>();
    private Object obj;
    private int itemposition;
    private SharedPrefsUtils sharedPrefsUtils;

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_waiting, null);
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //getChildFragmentManager().beginTransaction().detach(getParentFragment()).attach(getParentFragment()).commit();
           // getChildFragmentManager().beginTransaction().attach(getParentFragment()).commit();
        }
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

        rv_visit_inside = view.findViewById(R.id.rv_visitors_waiting);
        sharedPrefsUtils=new SharedPrefsUtils(getActivity());
        msearchView = view.findViewById(R.id.searchview_admin);
        Visitor visitor = new Visitor();
        visitor.adminId = SharedPrefsUtils.getInstance(getActivity()).getAdmin();
       // visitor.adminId = "2";
        //for waiting status as 0
        visitor.status = "0";
        visitor.search = "";
        try {
            obj = Class.forName(Visitor.class.getName()).cast(visitor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "visitors", true);

//        msearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String s) {
//                if (s.length()>=2)
//                    adapter.getFilter().filter(s);
//                return false;
//            }
//        });
        msearchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //if (s.length()>=2)
                   adapter.getFilter().filter(s);
                adapter.notifyDataSetChanged();
              // return false;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
                                manager = new LinearLayoutManager(getActivity());
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                rv_visit_inside.setLayoutManager(manager);
                                adapter = new WaitingVisitorAdapter(getActivity(), arrayList);
                                rv_visit_inside.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                adapter.setOnItemClickListener(new WaitingVisitorAdapter.OnitemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position, ArrayList<ResponseVisitMember> arrayList) {

                                        switch (view.getId()) {
                                            case R.id.btn_in:
                                                callWaiting_in_WS(position,arrayList);
                                                break;
                                            case R.id.btn_check:
                                                call_Waiting_check_WS(position,arrayList);
                                                break;
                                            case R.id.btn_call:
                                                call_Waiting_call_WS(position,arrayList);
                                                break;
                                            case R.id.btn_msg:
                                                call_Waiting_msg_WS(position,arrayList);
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

                            AdminStaff req = new AdminStaff();
                            req.adminId = SharedPrefsUtils.getInstance(getActivity()).getAdmin();
                            try {
                                obj = Class.forName(AdminStaff.class.getName()).cast(req);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            new RetrofitRequester(this).callPostServices(obj, 4, "visitors_count", false);
                         //   new RetrofitRequester(this).callPostServices(obj, 5, "alerts_count", true);




                            break;
                        case 3:
                            Common.showToast(getActivity(), object.optString("message"));
                            break;

                        case 4:

//                        {"status":true,"message":"Data fetched Successfully!","visitors_count":8}

                          
                            sharedPrefsUtils.visitor_count(object.optInt("visitors_count_inside"), object.optInt("visitors_count"));

                            getActivity().getSupportFragmentManager().beginTransaction().
                                    replace(R.id.fm_container,new Visitors_Fragment(),"").commit();

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

    private void call_Waiting_msg_WS(int position,ArrayList<ResponseVisitMember> arrayList2) {
//        msg_notification

    }

    private void callWaiting_in_WS(int position,ArrayList<ResponseVisitMember> arrayList2) {

        itemposition = position;
        Visitor_In req = new Visitor_In();
        req.securityId = SharedPrefsUtils.getInstance(getActivity()).getsecurityId();
        req.visitorId = arrayList2.get(position).visitorId;
        if (arrayList2.get(position).type.equalsIgnoreCase("Guest")) {
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

    private void call_Waiting_check_WS(int position,ArrayList<ResponseVisitMember> arrayList2) {

    }

    private void call_Waiting_call_WS(int position,ArrayList<ResponseVisitMember> arrayList2) {
//        user_call_service

        CallVisitor req = new CallVisitor();
        req.adminId = SharedPrefsUtils.getInstance(getActivity()).getAdmin();
        req.userId = arrayList2.get(position).userId;
        req.userType = arrayList2.get(position).userType;

        try {
            obj = Class.forName(CallVisitor.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 3, "user_call_service", true);
    }


}

