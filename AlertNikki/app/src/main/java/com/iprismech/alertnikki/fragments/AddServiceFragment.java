package com.iprismech.alertnikki.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismech.alertnikki.Pojo.AddServiceTypePojo;
import com.iprismech.alertnikki.Pojo.DailyHelpsListpojo;
import com.iprismech.alertnikki.Pojo.DailyHelpsPojo;
import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Request.DailyHelpsReq;
import com.iprismech.alertnikki.adapters.AddServiceAdapter;
import com.iprismech.alertnikki.customviews.ExpandableHeightGridView;
import com.iprismech.alertnikki.expandviews.ItemClickListener;
import com.iprismech.alertnikki.expandviews.Section;
import com.iprismech.alertnikki.expandviews.SectionedExpandableLayoutHelper;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.Common;
import com.iprismech.alertnikki.utilities.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddServiceFragment extends BaseAbstractFragment<Class> implements RetrofitResponseListener, View.OnClickListener, ItemClickListener {

    /*    private List<String> listDataHeader;
        private List<String> childdata;*/
    private AddServiceTypePojo addServiceTypePojo;
    private DailyHelpsListpojo dailyHelpsListpojo;
    private Object obj;
    private RetrofitResponseListener retrofitResponseListener;
    // private GridView gv_tutors, gv_vendors, gv_societadmin, gv_fulltimehelps;
    private AddServiceAdapter addServiceAdapter, vendorsAdapter, tutorsAdapter, fulltimeHelpsAdapter,
            socity_admin_Adapter, transport_adapter, medical_help_adapter, handymen_adapter, mainttance_adpater, security_adpater;
    private TextView tv_add_service_dailyhelps;
    private ImageView addservicetype_img;
    private TextView service_category_name;
    private String img_status = "false";
    private TextView btn_next_add_service_type;
    private FragmentManager fragmentManager;
    private String service_id = "";
    private EditText autocomplete;
    private LinearLayout ll_daily_helps, ll_vendors, ll_tuttors, ll_fulltimehelps, ll_societyAdmin, ll_transport, ll_medical_help, ll_handymen, ll_soc_maintanance, ll_security;


    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    RecyclerView mRecyclerView;
    SectionedExpandableLayoutHelper sectionedExpandableLayoutHelper;
    ExpandableHeightGridView gv_daily_helps, gv_vendors, gv_tutors, gv_fulltimehelps, gv_societadmin, gv_tranport, gv_medical_help, gv_handymen, gv_maintance, gv_security;

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
        ll_daily_helps.setOnClickListener(this);
        ll_fulltimehelps.setOnClickListener(this);
        ll_societyAdmin.setOnClickListener(this);
        ll_tuttors.setOnClickListener(this);
        ll_vendors.setOnClickListener(this);
        ll_transport.setOnClickListener(this);
        ll_medical_help.setOnClickListener(this);
        ll_handymen.setOnClickListener(this);
        ll_soc_maintanance.setOnClickListener(this);
        ll_security.setOnClickListener(this);

    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();
        retrofitResponseListener = this;
        // expListView = view.findViewById(R.id.lvExp);
        //  tv_add_service_dailyhelps = view.findViewById(R.id.add_service_dailyhelps);
        gv_daily_helps = (ExpandableHeightGridView) view.findViewById(R.id.gv_add_service);
        gv_daily_helps.setExpanded(true);
        try {
            gv_daily_helps.setPadding(0, 0, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }


        gv_vendors = (ExpandableHeightGridView) view.findViewById(R.id.gv_vendor_service);
        gv_vendors.setExpanded(true);
        try {
            gv_daily_helps.setPadding(0, 0, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }


        gv_tutors = (ExpandableHeightGridView) view.findViewById(R.id.gv_tutor);
        gv_tutors.setExpanded(true);
        try {
            gv_tutors.setPadding(0, 0, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        gv_fulltimehelps = (ExpandableHeightGridView) view.findViewById(R.id.gv_fulltimehelps_service);
        gv_fulltimehelps.setExpanded(true);
        try {
            gv_fulltimehelps.setPadding(0, 0, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        gv_societadmin = (ExpandableHeightGridView) view.findViewById(R.id.gv_societyadmin_service);
        gv_societadmin.setExpanded(true);
        try {
            gv_societadmin.setPadding(0, 0, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        gv_tranport = (ExpandableHeightGridView) view.findViewById(R.id.gv_transport_service);
        gv_tranport.setExpanded(true);
        try {
            gv_tranport.setPadding(0, 0, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        gv_medical_help = (ExpandableHeightGridView) view.findViewById(R.id.gv_medical_help_service);
        gv_medical_help.setExpanded(true);
        try {
            gv_medical_help.setPadding(0, 0, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }


        gv_handymen = (ExpandableHeightGridView) view.findViewById(R.id.gv_handymen_service);
        gv_handymen.setExpanded(true);
        try {
            gv_handymen.setPadding(0, 0, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }


        gv_maintance = (ExpandableHeightGridView) view.findViewById(R.id.gv_societymaintance_service);
        gv_maintance.setExpanded(true);
        try {
            gv_maintance.setPadding(0, 0, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }


        gv_security = (ExpandableHeightGridView) view.findViewById(R.id.gv_security_service);
        gv_security.setExpanded(true);
        try {
            gv_security.setPadding(0, 0, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        addservicetype_img = view.findViewById(R.id.service_cat_img);
        service_category_name = view.findViewById(R.id.service_category_name);
        btn_next_add_service_type = view.findViewById(R.id.next_add_service_type);
        autocomplete = view.findViewById(R.id.autocomplete);
        ll_daily_helps = view.findViewById(R.id.add_service_dailyhelps);
        ll_fulltimehelps = view.findViewById(R.id.add_service_fulltimehelps);
        ll_societyAdmin = view.findViewById(R.id.add_service_socityAdmin);
        ll_tuttors = view.findViewById(R.id.add_service_tutors);
        ll_vendors = view.findViewById(R.id.add_service_vendors);

        ll_transport = view.findViewById(R.id.add_service_transport);
        ll_medical_help = view.findViewById(R.id.add_service_medical_help);
        ll_handymen = view.findViewById(R.id.add_service_handymen);
        ll_soc_maintanance = view.findViewById(R.id.add_service_socityMaintanance);
        ll_security = view.findViewById(R.id.add_service_security);


        // get the listview
//        expListView = (ExpandableListView) view.findViewById(R.id.lvExp);

        // preparing list data

//        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
//        expListView.setAdapter(listAdapter);

//        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
//        sectionedExpandableLayoutHelper = new SectionedExpandableLayoutHelper(getActivity(),
//                mRecyclerView, this, 4);


        //tv_add_service_dailyhelps.setOnClickListener(this);
        btn_next_add_service_type.setOnClickListener(this);

        DailyHelpsReq dailyHelpsReq = new DailyHelpsReq();
        dailyHelpsReq.admin_id = SharedPrefsUtils.getInstance(getActivity()).getAdmin();
        //flatListRequest.building_id="4";
        try {
            obj = Class.forName(DailyHelpsReq.class.getName()).cast(dailyHelpsReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(retrofitResponseListener).callPostServices(obj, 1, "daily_helps_list", true);

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

            }
        });

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
                            dailyHelpsListpojo = new Gson().fromJson(jsonString, DailyHelpsListpojo.class);
                            List<DailyHelpsListpojo.ResponseBean> responseBeans = dailyHelpsListpojo.getResponse();

                            ArrayList<DailyHelpsListpojo.ResponseBean.SlistBean> items1 = (ArrayList<DailyHelpsListpojo.ResponseBean.SlistBean>) responseBeans.get(0).getSlist();
                            sectionedExpandableLayoutHelper.addSection("Daily Helps", items1);

                            items1 = (ArrayList<DailyHelpsListpojo.ResponseBean.SlistBean>) responseBeans.get(1).getSlist();
                            sectionedExpandableLayoutHelper.addSection("vendors", items1);

                            items1 = (ArrayList<DailyHelpsListpojo.ResponseBean.SlistBean>) responseBeans.get(1).getSlist();
                            sectionedExpandableLayoutHelper.addSection("Tutors", items1);

                            items1 = (ArrayList<DailyHelpsListpojo.ResponseBean.SlistBean>) responseBeans.get(1).getSlist();
                            sectionedExpandableLayoutHelper.addSection("Food", items1);

                            items1 = (ArrayList<DailyHelpsListpojo.ResponseBean.SlistBean>) responseBeans.get(1).getSlist();
                            sectionedExpandableLayoutHelper.addSection("Society admin staff", items1);


                            sectionedExpandableLayoutHelper.notifyDataSetChanged();

                            //checking if adding single item works
//                            sectionedExpandableLayoutHelper.addItem("CIVILWAR (U/A) ENGLISH", new Item("06:30 PM",5));
                            sectionedExpandableLayoutHelper.notifyDataSetChanged();


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
                gv_daily_helps.setVisibility(View.VISIBLE);
                addServiceAdapter = new AddServiceAdapter(getActivity(), dailyHelpsListpojo.getResponse().get(0).getSlist());
                gv_daily_helps.setAdapter(addServiceAdapter);
                gv_daily_helps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        service_category_name.setText(dailyHelpsListpojo.getResponse().get(0).getSlist().get(position).getTitle());
                        addservicetype_img.setImageDrawable(getResources().getDrawable(R.drawable.add_services_checked));
                        service_id = dailyHelpsListpojo.getResponse().get(0).getSlist().get(position).getId();
                    }
                });
                break;
            case R.id.add_service_fulltimehelps:
                gv_fulltimehelps.setVisibility(View.VISIBLE);
                fulltimeHelpsAdapter = new AddServiceAdapter(getActivity(), dailyHelpsListpojo.getResponse().get(4).getSlist());
                gv_fulltimehelps.setAdapter(fulltimeHelpsAdapter);
                gv_fulltimehelps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        service_category_name.setText(dailyHelpsListpojo.getResponse().get(4).getSlist().get(position).getTitle());
                        addservicetype_img.setImageDrawable(getResources().getDrawable(R.drawable.add_services_checked));
                        service_id = dailyHelpsListpojo.getResponse().get(4).getSlist().get(position).getId();
                    }
                });
                break;
            case R.id.add_service_socityAdmin:
                gv_societadmin.setVisibility(View.VISIBLE);
                socity_admin_Adapter = new AddServiceAdapter(getActivity(), dailyHelpsListpojo.getResponse().get(7).getSlist());
                gv_societadmin.setAdapter(socity_admin_Adapter);
                gv_societadmin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        service_category_name.setText(dailyHelpsListpojo.getResponse().get(7).getSlist().get(position).getTitle());
                        addservicetype_img.setImageDrawable(getResources().getDrawable(R.drawable.add_services_checked));
                        service_id = dailyHelpsListpojo.getResponse().get(7).getSlist().get(position).getId();
                    }
                });
                break;
            case R.id.add_service_tutors:
                gv_tutors.setVisibility(View.VISIBLE);
                tutorsAdapter = new AddServiceAdapter(getActivity(), dailyHelpsListpojo.getResponse().get(5).getSlist());
                gv_tutors.setAdapter(tutorsAdapter);
                gv_tutors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        service_category_name.setText(dailyHelpsListpojo.getResponse().get(5).getSlist().get(position).getTitle());
                        addservicetype_img.setImageDrawable(getResources().getDrawable(R.drawable.add_services_checked));
                        service_id = dailyHelpsListpojo.getResponse().get(5).getSlist().get(position).getId();
                    }
                });
                break;
            case R.id.add_service_vendors:
                gv_vendors.setVisibility(View.VISIBLE);
                gv_daily_helps.setVisibility(View.GONE);
                vendorsAdapter = new AddServiceAdapter(getActivity(), dailyHelpsListpojo.getResponse().get(3).getSlist());
                gv_vendors.setAdapter(vendorsAdapter);
                gv_vendors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        service_category_name.setText(dailyHelpsListpojo.getResponse().get(3).getSlist().get(position).getTitle());
                        addservicetype_img.setImageDrawable(getResources().getDrawable(R.drawable.add_services_checked));
                        service_id = dailyHelpsListpojo.getResponse().get(3).getSlist().get(position).getId();
                    }
                });
                break;
            case R.id.add_service_transport:
                gv_tranport.setVisibility(View.VISIBLE);
                transport_adapter = new AddServiceAdapter(getActivity(), dailyHelpsListpojo.getResponse().get(6).getSlist());
                gv_tranport.setAdapter(transport_adapter);
                gv_tranport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        service_category_name.setText(dailyHelpsListpojo.getResponse().get(6).getSlist().get(position).getTitle());
                        addservicetype_img.setImageDrawable(getResources().getDrawable(R.drawable.add_services_checked));
                        service_id = dailyHelpsListpojo.getResponse().get(6).getSlist().get(position).getId();
                    }
                });
                break;


            case R.id.add_service_medical_help:
                gv_medical_help.setVisibility(View.VISIBLE);

                medical_help_adapter = new AddServiceAdapter(getActivity(), dailyHelpsListpojo.getResponse().get(9).getSlist());
                gv_medical_help.setAdapter(medical_help_adapter);
                gv_medical_help.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        service_category_name.setText(dailyHelpsListpojo.getResponse().get(9).getSlist().get(position).getTitle());
                        addservicetype_img.setImageDrawable(getResources().getDrawable(R.drawable.add_services_checked));
                        service_id = dailyHelpsListpojo.getResponse().get(9).getSlist().get(position).getId();
                    }
                });
                break;
            case R.id.add_service_handymen:
                gv_handymen.setVisibility(View.VISIBLE);

                handymen_adapter = new AddServiceAdapter(getActivity(), dailyHelpsListpojo.getResponse().get(8).getSlist());
                gv_handymen.setAdapter(handymen_adapter);
                gv_handymen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        service_category_name.setText(dailyHelpsListpojo.getResponse().get(8).getSlist().get(position).getTitle());
                        addservicetype_img.setImageDrawable(getResources().getDrawable(R.drawable.add_services_checked));
                        service_id = dailyHelpsListpojo.getResponse().get(8).getSlist().get(position).getId();
                    }
                });
                break;

            case R.id.add_service_socityMaintanance:
                gv_maintance.setVisibility(View.VISIBLE);

                mainttance_adpater = new AddServiceAdapter(getActivity(), dailyHelpsListpojo.getResponse().get(2).getSlist());
                gv_maintance.setAdapter(mainttance_adpater);
                gv_maintance.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        service_category_name.setText(dailyHelpsListpojo.getResponse().get(2).getSlist().get(position).getTitle());
                        addservicetype_img.setImageDrawable(getResources().getDrawable(R.drawable.add_services_checked));
                        service_id = dailyHelpsListpojo.getResponse().get(2).getSlist().get(position).getId();
                    }
                });
                break;

            case R.id.add_service_security:
                gv_security.setVisibility(View.VISIBLE);

                security_adpater = new AddServiceAdapter(getActivity(), dailyHelpsListpojo.getResponse().get(1).getSlist());
                gv_security.setAdapter(security_adpater);
                gv_security.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        service_category_name.setText(dailyHelpsListpojo.getResponse().get(1).getSlist().get(position).getTitle());
                        addservicetype_img.setImageDrawable(getResources().getDrawable(R.drawable.add_services_checked));
                        service_id = dailyHelpsListpojo.getResponse().get(1).getSlist().get(position).getId();
                    }
                });
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


    @Override
    public void itemClicked(DailyHelpsListpojo.ResponseBean item) {

    }

    @Override
    public void itemClicked(Section section) {

    }
}
