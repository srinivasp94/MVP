package com.iprismech.alertnikki.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.adapters.AlertsAdapter;

import java.util.List;

public class Alerts_Fragment extends BaseAbstractFragment<Class> {
    private RecyclerView rv_alerts;
    private LinearLayoutManager manager;
    private List list;
    private AlertsAdapter alertsAdapter;

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragments_alerts, null);
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
        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_alerts = view.findViewById(R.id.rv_alerts);

        rv_alerts.setLayoutManager(manager);
        alertsAdapter = new AlertsAdapter(getActivity(), list);
        if (list != null && list.size() > 0)
            rv_alerts.setAdapter(alertsAdapter);

    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();

    }
}

