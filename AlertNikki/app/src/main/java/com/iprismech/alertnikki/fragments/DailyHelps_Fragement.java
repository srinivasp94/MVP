package com.iprismech.alertnikki.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.adapters.AdminStaffAdapter;
import com.iprismech.alertnikki.adapters.DailyHelpsAdapter;

import java.util.ArrayList;

public class DailyHelps_Fragement extends BaseAbstractFragment<Class> {

    private SearchView msearchView;
    private RecyclerView rv_DailyHelps;
    private DailyHelpsAdapter helpsAdapter;
    private LinearLayoutManager manager;
    private ArrayList arrayList;

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

        msearchView = view.findViewById(R.id.searchview_admin);
        rv_DailyHelps= view.findViewById(R.id.rv_adminstaff);
        rv_DailyHelps.setLayoutManager(manager);

        helpsAdapter  = new DailyHelpsAdapter(getActivity(),arrayList);
    }
}
