package com.iprismech.alertnikki.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismech.alertnikki.Pojo.AddServiceTypePojo;
import com.iprismech.alertnikki.Pojo.DailyHelpsPojo;
import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Request.DailyHelpsReq;
import com.iprismech.alertnikki.adapters.AddServiceAdapter;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.Common;
import com.iprismech.alertnikki.utilities.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.List;

public class AddServiceFragment extends BaseAbstractFragment<Class> implements RetrofitResponseListener, View.OnClickListener {
    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private List<String> childdata;
    private AddServiceTypePojo addServiceTypePojo;
    private DailyHelpsPojo dailyHelpsPojo;
    private Object obj;
    private RetrofitResponseListener retrofitResponseListener;
    private GridView gv_addservice;
    private AddServiceAdapter addServiceAdapter;
    private TextView tv_add_service_dailyhelps;
    private ImageView addservicetype_img;
    private TextView service_category_name;
    private String img_status = "false";
    private TextView btn_next_add_service_type;
    private FragmentManager fragmentManager;
    private String service_id="";
    private EditText autocomplete;

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.add_service_layout, null);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter();
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
        retrofitResponseListener = this;
        // expListView = view.findViewById(R.id.lvExp);
        tv_add_service_dailyhelps = view.findViewById(R.id.add_service_dailyhelps);
        gv_addservice = view.findViewById(R.id.gv_add_service);
        addservicetype_img = view.findViewById(R.id.service_cat_img);
        service_category_name = view.findViewById(R.id.service_category_name);
        btn_next_add_service_type = view.findViewById(R.id.next_add_service_type);
        autocomplete=view.findViewById(R.id.autocomplete);
        tv_add_service_dailyhelps.setOnClickListener(this);
        btn_next_add_service_type.setOnClickListener(this);
        fragmentManager = getFragmentManager();
        autocomplete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                if (autocomplete.getText().toString().trim().length() > 0) {
//                    applySearch(autocomplete.getText().toString().trim());
//                } else {
//                    adapter1.setC_pname(name);
//                    adapter1.setC_pdescription(description);
//                    adapter1.setC_pimage(image);
//                    adapter1.setC_pprice(price);
//                    adapter1.notifyDataSetChanged();
//                }
            }
        });
        // preparing list data
        // prepareListData();

//        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, childdata);
//
//        // setting list adapter
//        expListView.setAdapter(listAdapter);
//
//
//        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v,
//                                        int groupPosition, long id) {
//                // Toast.makeText(getApplicationContext(),
//                // "Group Clicked " + listDataHeader.get(groupPosition),
//                // Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
//
//        // Listview Group expanded listener
//        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getActivity(),
//                        listDataHeader.get(groupPosition) + " Expanded",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//
//        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
//
//            @Override
//            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getActivity(),
//                        listDataHeader.get(groupPosition) + " Collapsed",
//                        Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//        // Listview on child click listener
//        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v,
//                                        int groupPosition, int childPosition, long id) {
//                // TODO Auto-generated method stub
//                Toast.makeText(
//                        getActivity(),
//                        listDataHeader.get(groupPosition)
//                                + " : "
//                                + listDataChild.get(
//                                listDataHeader.get(groupPosition)).get(
//                                childPosition), Toast.LENGTH_SHORT)
//                        .show();
//                return false;
//            }
//        });
//
//
//    }
//
//    private void prepareListData() {
//        listDataHeader = new ArrayList<String>();
//        childdata=new ArrayList<>();
//        listDataHeader.add("Daily Helps");
//        listDataHeader.add("Vendors");
//        listDataHeader.add("Tutors");
//        listDataHeader.add("Full Time Helps");
//        listDataHeader.add("Society Administration Staff");
//
//        for(int i=0;i<addServiceTypePojo.getResponse().size();i++){
//            childdata.add(addServiceTypePojo.getResponse().get(i).getTitle());
//        }
//
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

                if (object.optBoolean("status") == true) {
                    switch (requestId) {
                        case 1:
                            dailyHelpsPojo = new Gson().fromJson(jsonString, DailyHelpsPojo.class);
                            addServiceAdapter = new AddServiceAdapter(getActivity(), dailyHelpsPojo);
                            gv_addservice.setAdapter(addServiceAdapter);
                            gv_addservice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    service_category_name.setText(dailyHelpsPojo.getResponse().get(position).getTitle());
                                    addservicetype_img.setImageDrawable(getResources().getDrawable(R.drawable.add_services_checked));
                                    service_id = dailyHelpsPojo.getResponse().get(position).getId();

                                }
                            });

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
        switch (v.getId()) {
            case R.id.add_service_dailyhelps:
                gv_addservice.setVisibility(View.VISIBLE);
                DailyHelpsReq dailyHelpsReq = new DailyHelpsReq();
                dailyHelpsReq.admin_id = SharedPrefsUtils.getInstance(getActivity()).getAdmin();
                //flatListRequest.building_id="4";
                try {
                    obj = Class.forName(DailyHelpsReq.class.getName()).cast(dailyHelpsReq);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new RetrofitRequester(retrofitResponseListener).callPostServices(obj, 1, "daily_helps_list", true);
//
                break;
            case R.id.next_add_service_type:
                if (service_id == "" || service_id.isEmpty()) {
                    Toast.makeText(getActivity(), "Select service type", Toast.LENGTH_SHORT).show();
                } else {
                    AddMobileNumber fragment = new AddMobileNumber();
                    Bundle bundle = new Bundle();
                    bundle.putString("service_id", service_id);
                    fragment.setArguments(bundle);
                    fragmentManager.beginTransaction().replace(R.id.fm_container, fragment, "").addToBackStack("").commit();
                }
                break;

        }
    }

}
