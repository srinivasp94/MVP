package com.iprismech.alertnikki.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismech.alertnikki.Pojo.BuildingsPojo;
import com.iprismech.alertnikki.Pojo.FlatPojo;
import com.iprismech.alertnikki.Pojo.MoveinPojo;
import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Request.BuildingListRequest;
import com.iprismech.alertnikki.Request.FlatListRequest;
import com.iprismech.alertnikki.Request.MoveIn;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.Common;
import com.iprismech.alertnikki.utilities.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class MoveInFragment extends BaseAbstractFragment<Class> implements RetrofitResponseListener, AdapterView.OnItemSelectedListener {
    private Object obj;
    RetrofitResponseListener retrofitResponseListener;
    private EditText et_vehicle_number, et_flat_number, et_tenant_name, et_mobile_number, et_comment;
    private TextView btn_tv_submit;
    private Spinner sp_building, sp_flat;
    private ArrayAdapter<String> customadapter;
    private BuildingsPojo buildingsPojo;
    private FlatPojo flatPojo;
    private MoveinPojo moveinPojo;
    private ArrayList<String> building_array = new ArrayList<>();
    private ArrayList<String> flat_array = new ArrayList<>();
    private String building_id, flat_id;

        @Override
        protected View getFragmentView() {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.movein_layout, null);
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
        et_vehicle_number = view.findViewById(R.id.movein_et_vehicle_number);
        et_comment = view.findViewById(R.id.movein_et_comment);
        //et_flat_number = view.findViewById(R.id.movein_et_flat_number);
        et_mobile_number = view.findViewById(R.id.movein_et_mobile_number);
        et_tenant_name = view.findViewById(R.id.movein_et_tenant_name);
        btn_tv_submit = view.findViewById(R.id.movein_btn_submit);
        sp_building = view.findViewById(R.id.sp_building);
        sp_flat = view.findViewById(R.id.sp_flat);

        sp_building.setOnItemSelectedListener(this);
        sp_flat.setOnItemSelectedListener(this);


        BuildingListRequest buildingListRequest = new BuildingListRequest();
        buildingListRequest.admin_id = SharedPrefsUtils.getInstance(getActivity()).getAdmin();
        try {
            obj = Class.forName(BuildingListRequest.class.getName()).cast(buildingListRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(retrofitResponseListener).callPostServices(obj, 1, "buildings", true);

        btn_tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_vehicle_number.getText().toString() == "" || et_vehicle_number.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Enter Vehicle Number", Toast.LENGTH_SHORT).show();
                }
//                else if (et_flat_number.getText().toString() == "" || et_flat_number.getText().toString().isEmpty()) {
//                    Toast.makeText(getActivity(), "Enter Flat Number", Toast.LENGTH_SHORT).show();
//                }
                else if (et_tenant_name.getText().toString() == "" || et_tenant_name.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Enter Tenant Name", Toast.LENGTH_SHORT).show();
                } else if (et_mobile_number.getText().toString() == "" || et_mobile_number.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                } else if (et_mobile_number.getText().toString().length() < 10) {
                    Toast.makeText(getActivity(), "Mobile Number must be 10 digits", Toast.LENGTH_SHORT).show();
                } else if (et_comment.getText().toString() == "" || et_comment.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Please write Comments", Toast.LENGTH_SHORT).show();
                } else {

                    MoveIn moveIn = new MoveIn();
                    moveIn.adminId = SharedPrefsUtils.getInstance(getActivity()).getAdmin();
                    moveIn.security_id = String.valueOf(SharedPrefsUtils.getInstance(getActivity()).getsecurityId());
                    moveIn.building_id = building_id;
                    moveIn.flat_num = flat_id;
                   // moveIn.flat_num = "2";
                    moveIn.vehicle_num = et_vehicle_number.getText().toString();
                    moveIn.tenant_name = et_tenant_name.getText().toString();
                    moveIn.mobile_num = et_mobile_number.getText().toString();
                    moveIn.comments = et_comment.getText().toString();

                    try {
                        obj = Class.forName(MoveIn.class.getName()).cast(moveIn);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new RetrofitRequester(retrofitResponseListener).callPostServices(obj, 3, "move_in", true);
                }
            }
        });


        //   moveIn.

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
                            buildingsPojo = new Gson().fromJson(jsonString, BuildingsPojo.class);
                            //building_array = object.optJSONArray("response");
                            building_array.add("Select Building");
                            for (int i = 0; i < buildingsPojo.getResponse().size(); i++) {
                                building_array.add(buildingsPojo.getResponse().get(i).getTitle());
//                                if (user_country.equalsIgnoreCase(countryPojo.getResponse().get(i).getTitle())) {
//                                    // countryspinner.setSelection(i);
//                                    country_position = i + 1;
//                                }
                            }
                            customadapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, building_array);
                            customadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            sp_building.setAdapter(customadapter);
                            break;
                        case 2:
                            flatPojo = new Gson().fromJson(jsonString, FlatPojo.class);
                            //building_array = object.optJSONArray("response");
                            flat_array.add("Select Flat");
                            for (int i = 0; i < flatPojo.getResponse().size(); i++) {
                                flat_array.add(flatPojo.getResponse().get(i).getTitle());
//                                if (user_country.equalsIgnoreCase(countryPojo.getResponse().get(i).getTitle())) {
//                                    // countryspinner.setSelection(i);
//                                    country_position = i + 1;
//                                }
                            }
                            customadapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, flat_array);
                            customadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            sp_flat.setAdapter(customadapter);
                            break;
                        case 3:
                        moveinPojo=new Gson().fromJson(jsonString, MoveinPojo.class);
                            Toast.makeText(getActivity(), moveinPojo.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.sp_building:
                flat_array.clear();
                try {
                    if (position == 0) {
                    } else {
                        position = position - 1;
                        building_id = buildingsPojo.getResponse().get(position).getId();

                        FlatListRequest flatListRequest = new FlatListRequest();
                        flatListRequest.admin_id = SharedPrefsUtils.getInstance(getActivity()).getAdmin();
                        flatListRequest.building_id=building_id;
                        //flatListRequest.building_id="4";
                        try {
                            obj = Class.forName(FlatListRequest.class.getName()).cast(flatListRequest);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        new RetrofitRequester(retrofitResponseListener).callPostServices(obj, 2, "flats", true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.sp_flat:

                try {
                    if (position == 0) {
                    } else {
                        position = position - 1;
                        flat_id = flatPojo.getResponse().get(position).getId();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
