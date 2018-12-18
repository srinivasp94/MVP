package com.iprismech.alertnikki.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.adapters.InsideAdapter;

import java.util.ArrayList;

public class Inside_Visitors_fragment extends BaseAbstractFragment<Class> {
    private RecyclerView rv_visit_inside;
    private LinearLayoutManager manager;
    private InsideAdapter insideAdapter;
    private ArrayList arrayList;

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
        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        rv_visit_inside.setLayoutManager(manager);
        insideAdapter = new InsideAdapter(getActivity(), arrayList);
        if (arrayList != null && arrayList.size() > 0) {
            rv_visit_inside.setAdapter(insideAdapter);
        }

    }
}
