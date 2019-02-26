package com.iprismech.alertnikki.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.iprismech.alertnikki.Request.Visitor;
import com.iprismech.alertnikki.Request.Visitors_Common_Req;
import com.iprismech.alertnikki.Response.ResponseVisitMember;
import com.iprismech.alertnikki.Response.WaitingVisitors;
import com.iprismech.alertnikki.adapters.InsideAdapter;
import com.iprismech.alertnikki.app.factories.constants.AppConstants;
import com.iprismech.alertnikki.app.factories.controllers.ApplicationController;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.AlertUtils;
import com.iprismech.alertnikki.utilities.Common;
import com.iprismech.alertnikki.utilities.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class Inside_Visitors_fragment extends BaseAbstractFragment<Class> implements RetrofitResponseListener {
    private RecyclerView rv_visit_inside;
    private LinearLayoutManager manager;
    private InsideAdapter insideAdapter;
    private ArrayList<ResponseVisitMember> arrayList = new ArrayList<>();
    private Object obj;
    private EditText msearchView;
    int itemposition;
    private SharedPrefsUtils sharedPrefsUtils;

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

        msearchView = view.findViewById(R.id.search_inside);
        Visitor visitor = new Visitor();

        //for inside status as 1
        visitor.adminId = SharedPrefsUtils.getInstance(getActivity()).getAdmin();
  //      visitor.adminId = "2";
        visitor.status = "1";
        visitor.search = "";
        try {
            obj = Class.forName(Visitor.class.getName()).cast(visitor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "visitors", true);


        msearchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //if (s.length()>=2)
                insideAdapter.getFilter().filter(s);
                insideAdapter.notifyDataSetChanged();
                // return false;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //getChildFragmentManager().beginTransaction().detach(getParentFragment()).attach(getParentFragment()).commit();
        }
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
                                manager = new LinearLayoutManager(getActivity());
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                rv_visit_inside.setLayoutManager(manager);

                                insideAdapter = new InsideAdapter(getActivity(), arrayList);
                                rv_visit_inside.setAdapter(insideAdapter);
                                insideAdapter.setOnItemClickListener(new InsideAdapter.OnitemClickListener() {
                                    @Override
                                    public void onItemClick(View view, final int position, final ArrayList<ResponseVisitMember> arrayList) {
                                        switch (view.getId()) {
                                            case R.id.btn_out_inside:
                                                AlertUtils.showSimpleAlert(getActivity(), "Do you want to send him/her out", "Confirm...?", "Yes", "No", new AlertUtils.onClicklistners() {
                                                    @Override
                                                    public void onpositiveclick() {
                                                        callVisitor_Out_WS(position,arrayList);
                                                    }

                                                    @Override
                                                    public void onNegativeClick() {

                                                    }
                                                });

                                                break;
                                            case R.id.ll_rootVisitor:
                                                Bundle bundle = new Bundle();
                                                bundle.putString("Key_Visitor_id", arrayList.get(position).visitorId);
                                                bundle.putString("Key_Type_id", arrayList.get(position).type);
                                                bundle.putString("Name",arrayList.get(position).name);
                                                bundle.putString("UserType",arrayList.get(position).userType);
                                                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_GUEST_DETAILS_SCREEN, bundle);
                                                break;
                                        }

                                    }
                                });
                            } else {
                                Common.showToast(getActivity(), "Items Found");
                            }
                            break;
                        case 2:
                            if (object.optString("result").equalsIgnoreCase("true")) {
                                Common.showToast(getActivity(), object.optString("message"));

//                                AdminStaff req = new AdminStaff();
//                                req.adminId = SharedPrefsUtils.getInstance(getActivity()).getAdmin();
//                                try {
//                                    obj = Class.forName(AdminStaff.class.getName()).cast(req);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                                new RetrofitRequester(this).callPostServices(obj, 3, "visitors_count", false);
                                

                               


                                arrayList.remove(itemposition);
                                insideAdapter.notifyDataSetChanged();



                            } else {
                                Common.showToast(getActivity(), object.optString("message"));
                            }
                            break;
//                        case 3:
//
//                            sharedPrefsUtils.visitor_count(object.optInt("visitors_count_inside"), object.optInt("visitors_count"));
//                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fm_container,new Visitors_Fragment(),"").commit();
//                            break;
                    }
                } else {
                    Common.showToast(getActivity(), object.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void callVisitor_Out_WS(int position,ArrayList<ResponseVisitMember> arrayList1) {
        itemposition = position;

        Visitors_Common_Req req = new Visitors_Common_Req();
        req.visitorId = arrayList1.get(position).visitorId;
        arrayList=arrayList1;
//        req.type = arrayList.get(position).userType;
        if (arrayList1.get(position).type.equalsIgnoreCase("Guest")) {
            req.type = "1";
        } else {
            req.type = "2";
        }
        try {
            obj = Class.forName(Visitors_Common_Req.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 2, "update_out_visitors", true);
    }
}
