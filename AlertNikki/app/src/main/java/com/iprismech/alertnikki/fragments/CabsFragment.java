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
import com.iprismech.alertnikki.Response.FoodModel;
import com.iprismech.alertnikki.adapters.DeliveryAdapter;
import com.iprismech.alertnikki.app.factories.constants.AppConstants;
import com.iprismech.alertnikki.app.factories.controllers.ApplicationController;

import java.util.ArrayList;

public class CabsFragment extends BaseAbstractFragment<Class> {

    private SearchView searchView;
    private RecyclerView rv_cabs;
    private LinearLayoutManager manager;
    private DeliveryAdapter adapter;
    private ArrayList<FoodModel> arrayList = new ArrayList<>();

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.cab_fragment, null);
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

        searchView = view.findViewById(R.id.search_cab);
        rv_cabs = view.findViewById(R.id.rv_cabs);

        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);

        arrayList.add(new FoodModel("OLA", R.drawable.ic_ola));
        arrayList.add(new FoodModel("Uber", R.drawable.ic_uber));
        arrayList.add(new FoodModel("Bla Bla", R.drawable.ic_blabla));
        adapter = new DeliveryAdapter(getActivity(), arrayList);
        rv_cabs.setLayoutManager(manager);
        rv_cabs.setAdapter(adapter);
        adapter.setOnItemClickListener(new DeliveryAdapter.OnitemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("Key_Title", arrayList.get(position).getTitle());
                bundle.putInt("Key_Image", arrayList.get(position).getImg_id());

                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_DELIVERY_BOY_SCREEN, bundle);
            }
        });
    }
}
