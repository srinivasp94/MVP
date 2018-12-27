package com.iprismech.alertnikki.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iprismech.alertnikki.R;

public class AddMobileNumber extends BaseAbstractFragment<Class> {

    private EditText et_mobie_number, et_name, et_address;
    private TextView btn_next_mobile;
    private FragmentManager fragmentManager;
    private String service_id,mobile_number,name,address;

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.add_service_mobile_number, null);
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
        et_mobie_number = view.findViewById(R.id.et_add_service_number);
        btn_next_mobile = view.findViewById(R.id.btn_next_mobile);
        et_address = view.findViewById(R.id.et_address);
        et_name = view.findViewById(R.id.et_name);
        fragmentManager = getFragmentManager();
        Bundle bundle = getArguments();
        if (bundle != null) {
             service_id = bundle.getString("service_id", "");
        }
        btn_next_mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_name.getText().toString()==""||et_name.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Enter Name", Toast.LENGTH_SHORT).show();
                }else if(et_address.getText().toString().isEmpty()||et_address.getText().toString()==""){
                    Toast.makeText(getActivity(), "Enter Address", Toast.LENGTH_SHORT).show();
                }
                else if (et_mobie_number.getText().toString() == "" || et_mobie_number.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                } else if (et_mobie_number.getText().length() < 10) {
                    Toast.makeText(getActivity(), "Number must be 10 digits", Toast.LENGTH_SHORT).show();
                } else {
                    name=et_name.getText().toString();
                    address=et_address.getText().toString();
                    mobile_number=et_mobie_number.getText().toString();

                    UploadDocumentFragment fragment = new UploadDocumentFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("service_id",service_id);
                    bundle.putString("mobile_number",mobile_number);
                    bundle.putString("name",name);
                    bundle.putString("address",address);
                    fragment.setArguments(bundle);
                    fragmentManager.beginTransaction().replace(R.id.fm_container, fragment, "").addToBackStack("").commit();

                }
            }
        });
    }

}
