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
    private GridView gv_tutors, gv_vendors, gv_societadmin, gv_fulltimehelps;
    private AddServiceAdapter addServiceAdapter, vendorsAdapter, tutorsAdapter, fulltimeHelpsAdapter, socity_admin_Adapter;
    private TextView tv_add_service_dailyhelps;
    private ImageView addservicetype_img;
    private TextView service_category_name;
    private String img_status = "false";
    private TextView btn_next_add_service_type;
    private FragmentManager fragmentManager;
    private String service_id = "";
    private EditText autocomplete;
    private LinearLayout ll_daily_helps, ll_vendors, ll_tuttors, ll_fulltimehelps, ll_societyAdmin;


    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    RecyclerView mRecyclerView;
    SectionedExpandableLayoutHelper sectionedExpandableLayoutHelper;
    ExpandableHeightGridView gv_daily_helps;

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
        //  tv_add_service_dailyhelps = view.findViewById(R.id.add_service_dailyhelps);
        gv_daily_helps = (ExpandableHeightGridView) view.findViewById(R.id.gv_add_service);
        gv_daily_helps.setExpanded(true);
        try {
            gv_daily_helps.setPadding(0, 0, 0, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }

        gv_vendors = view.findViewById(R.id.gv_vendor_service);
        gv_tutors = view.findViewById(R.id.gv_tutor);
        gv_fulltimehelps = view.findViewById(R.id.gv_fulltimehelps_service);
        gv_societadmin = view.findViewById(R.id.gv_societyadmin_service);

        addservicetype_img = view.findViewById(R.id.service_cat_img);
        service_category_name = view.findViewById(R.id.service_category_name);
        btn_next_add_service_type = view.findViewById(R.id.next_add_service_type);
        autocomplete = view.findViewById(R.id.autocomplete);
        ll_daily_helps = view.findViewById(R.id.add_service_dailyhelps);
        ll_fulltimehelps = view.findViewById(R.id.add_service_fulltimehelps);
        ll_societyAdmin = view.findViewById(R.id.add_service_socityAdmin);
        ll_tuttors = view.findViewById(R.id.add_service_tutors);
        ll_vendors = view.findViewById(R.id.add_service_vendors);

        ll_daily_helps.setOnClickListener(this);
        ll_fulltimehelps.setOnClickListener(this);
        ll_societyAdmin.setOnClickListener(this);
        ll_tuttors.setOnClickListener(this);
        ll_vendors.setOnClickListener(this);


        // get the listview
//        expListView = (ExpandableListView) view.findViewById(R.id.lvExp);

        // preparing list data

//        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
//        expListView.setAdapter(listAdapter);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        sectionedExpandableLayoutHelper = new SectionedExpandableLayoutHelper(getActivity(),
                mRecyclerView, this, 3);


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
                addServiceAdapter = new AddServiceAdapter(getActivity(), dailyHelpsListpojo.getResponse().get(3).getSlist());
                gv_fulltimehelps.setAdapter(addServiceAdapter);
                gv_fulltimehelps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        service_category_name.setText(dailyHelpsListpojo.getResponse().get(3).getSlist().get(position).getTitle());
                        addservicetype_img.setImageDrawable(getResources().getDrawable(R.drawable.add_services_checked));
                        service_id = dailyHelpsListpojo.getResponse().get(3).getSlist().get(position).getId();
                    }
                });
                break;
            case R.id.add_service_socityAdmin:
                gv_societadmin.setVisibility(View.VISIBLE);
                addServiceAdapter = new AddServiceAdapter(getActivity(), dailyHelpsListpojo.getResponse().get(4).getSlist());
                gv_societadmin.setAdapter(addServiceAdapter);
                gv_societadmin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        service_category_name.setText(dailyHelpsListpojo.getResponse().get(4).getSlist().get(position).getTitle());
                        addservicetype_img.setImageDrawable(getResources().getDrawable(R.drawable.add_services_checked));
                        service_id = dailyHelpsListpojo.getResponse().get(4).getSlist().get(position).getId();
                    }
                });
                break;
            case R.id.add_service_tutors:
                gv_tutors.setVisibility(View.VISIBLE);
                addServiceAdapter = new AddServiceAdapter(getActivity(), dailyHelpsListpojo.getResponse().get(2).getSlist());
                gv_tutors.setAdapter(addServiceAdapter);
                gv_tutors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        service_category_name.setText(dailyHelpsListpojo.getResponse().get(2).getSlist().get(position).getTitle());
                        addservicetype_img.setImageDrawable(getResources().getDrawable(R.drawable.add_services_checked));
                        service_id = dailyHelpsListpojo.getResponse().get(2).getSlist().get(position).getId();
                    }
                });
                break;
            case R.id.add_service_vendors:
                gv_vendors.setVisibility(View.VISIBLE);
                addServiceAdapter = new AddServiceAdapter(getActivity(), dailyHelpsListpojo.getResponse().get(1).getSlist());
                gv_vendors.setAdapter(addServiceAdapter);
                gv_vendors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
