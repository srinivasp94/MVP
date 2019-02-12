package com.iprismech.alertnikki.fragments;

import android.app.Activity;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Request.AdminStaff;
import com.iprismech.alertnikki.Request.OutAdminStaff;
import com.iprismech.alertnikki.Response.DailyHelps;
import com.iprismech.alertnikki.Response.DailyHelpsList;
import com.iprismech.alertnikki.adapters.AdminStaffAdapter;
import com.iprismech.alertnikki.adapters.DailyHelpsAdapter;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.AlertUtils;
import com.iprismech.alertnikki.utilities.Common;
import com.iprismech.alertnikki.utilities.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class DailyHelps_Fragement extends BaseAbstractFragment<Class> implements RetrofitResponseListener {

    private EditText msearchView;
    private RecyclerView rv_DailyHelps;
    private DailyHelpsAdapter helpsAdapter;
    private LinearLayoutManager manager;
    private ArrayList<DailyHelpsList> arrayList = new ArrayList<>();
    private ArrayList<DailyHelpsList> dailyHelps_List = new ArrayList<>();
    private Object obj;
    private int item_position;
    private LinearLayout ll_noResponse, ll_search;


    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragments_admin_staff, null);
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

        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        msearchView = view.findViewById(R.id.search_admin_staff);
        rv_DailyHelps = view.findViewById(R.id.rv_adminstaff);

        ll_search = view.findViewById(R.id.ll_search);
        ll_noResponse = view.findViewById(R.id.ll_noResponse);

        AdminStaff staff = new AdminStaff();
        staff.adminId = SharedPrefsUtils.getInstance(getActivity()).getAdmin();
        staff.search = "";

        try {
            obj = Class.forName(AdminStaff.class.getName()).cast(staff);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "daily_helps", true);

//        msearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                if (s.length()>2)
//                    helpsAdapter.getFilter().filter(s);
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
                helpsAdapter.getFilter().filter(s);
                helpsAdapter.notifyDataSetChanged();
                // return false;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // ImageView closeButton = (ImageView) msearchView.findViewById(R.id.search_close_btn);
//        closeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                msearchView.setQuery("",true);
//                msearchView.setFocusable(false);
//
//                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
//                //Find the currently focused view, so we can grab the correct window token from it.
//                View view = getActivity().getCurrentFocus();
//                //If no view currently has focus, create a new one, just so we can grab a window token from it
//                if (view == null) {
//                    view = new View(getActivity());
//                }
//                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//
//                helpsAdapter = new DailyHelpsAdapter(getActivity(), dailyHelps_List);
//                rv_DailyHelps.setLayoutManager(manager);
//                helpsAdapter.notifyDataSetChanged();
//            }
//        });
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
                            DailyHelps dailyHelps = Common.getSpecificDataObject(objectResponse, DailyHelps.class);
                            arrayList = (ArrayList<DailyHelpsList>) dailyHelps.response;
                            if (arrayList != null && arrayList.size() > 0) {

                                for (int i = 0; i < arrayList.size(); i++) {
                                    if (arrayList.get(i).attendence.adminId != null) {
                                        //Common.commonLogs(getActivity(), "123daily  " + arrayList.get(i).name);
                                        dailyHelps_List.add(arrayList.get(i));
                                    }
                                }

                                if (dailyHelps_List != null && dailyHelps_List.size() > 0) {
                                    ll_noResponse.setVisibility(View.GONE);
                                    ll_search.setVisibility(View.VISIBLE);
//                                    ll_search.setVisibility(View.VISIBLE);
                                    helpsAdapter = new DailyHelpsAdapter(getActivity(), dailyHelps_List);
                                    rv_DailyHelps.setLayoutManager(manager);
                                    rv_DailyHelps.setAdapter(helpsAdapter);
                                    helpsAdapter.notifyDataSetChanged();
                                    helpsAdapter.setOnItemClickListener(new DailyHelpsAdapter.OnitemClickListener() {
                                        @Override
                                        public void onItemClick(View view, final int position, final ArrayList<DailyHelpsList> arrayList) {

                                            AlertUtils.showSimpleAlert(getActivity(), "Do you want to send him/her out", "Confirm...?", "Yes", "No", new AlertUtils.onClicklistners() {
                                                @Override
                                                public void onpositiveclick() {
                                                    call_out_DailyHelps_WS(position, arrayList);
                                                }

                                                @Override
                                                public void onNegativeClick() {

                                                }
                                            });

                                        }
                                    });
                                } else {

                                    //  Common.showToast(getActivity(), "No Data Found");
                                    ll_noResponse.setVisibility(View.VISIBLE);
                                    ll_search.setVisibility(View.GONE);
                                }
                            } else {
                                // Common.showToast(getActivity(), "No Data Found");
                                ll_search.setVisibility(View.GONE);


                            }
//                                }

                   /* } else{
                        // Common.showToast(getActivity(), "No Data found");
                    }*/


                    break;
                    case 2:
                        Common.showToast(getActivity(), object.optString("message"));
                        dailyHelps_List.remove(item_position);
                        msearchView.setText("");
//                            helpsAdapter = new DailyHelpsAdapter(getActivity(), dailyHelps_List);
//                            rv_DailyHelps.setLayoutManager(manager);
//                            rv_DailyHelps.setAdapter(helpsAdapter);


                        helpsAdapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), "Removed Successfully", Toast.LENGTH_SHORT).show();

                        break;
                }
            } else{
                Common.showToast(getActivity(), object.optString("message"));
            }
        } catch(Exception e){
            e.printStackTrace();
        }


    }

}

    private void call_out_DailyHelps_WS(int position, ArrayList<DailyHelpsList> arrayList1) {
//        out_daily_helps
        item_position = position;
        OutAdminStaff req = new OutAdminStaff();
        req.attendenceId = arrayList1.get(position).attendence.id;
        dailyHelps_List = arrayList1;
        try {
            obj = Class.forName(OutAdminStaff.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 2, "out_daily_helps", true);
        helpsAdapter.notifyDataSetChanged();
    }
}
