package com.iprismech.alertnikki.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Response.FoodModel;
import com.iprismech.alertnikki.Response.OnlineModel;
import com.iprismech.alertnikki.adapters.DeliveryAdapter;

import java.util.ArrayList;

public class Delivery_Fragment extends BaseAbstractFragment<Class> {
    private RecyclerView Rv_mFood, rv_mOnline;
    private LinearLayoutManager managerFood, managerOnline;
    private DeliveryAdapter deliveryAdapter;
    private ArrayList<FoodModel> list_food = new ArrayList();
    private ArrayList<FoodModel> list_online = new ArrayList();

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragments_delivery, null);
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
        managerFood = new LinearLayoutManager(getActivity());
        managerFood.setOrientation(LinearLayoutManager.HORIZONTAL);

        managerOnline = new LinearLayoutManager(getActivity());
        managerOnline.setOrientation(LinearLayoutManager.HORIZONTAL);

        Rv_mFood = view.findViewById(R.id.rv_Food);
        rv_mOnline = view.findViewById(R.id.rv_Onlineshopping);

        Rv_mFood.setLayoutManager(managerFood);
        rv_mOnline.setLayoutManager(managerOnline);

        list_food.add(new FoodModel("Swiggy", R.mipmap.ic_launcher));
        list_food.add(new FoodModel("Food Panda", R.mipmap.ic_launcher));
        list_food.add(new FoodModel("Zomoto", R.mipmap.ic_launcher));
        list_food.add(new FoodModel("Uber Eats", R.mipmap.ic_launcher));
        list_food.add(new FoodModel("Other", R.mipmap.ic_launcher));

        deliveryAdapter = new DeliveryAdapter(getActivity(), list_food);
        Rv_mFood.setAdapter(deliveryAdapter);

        list_food.add(new FoodModel("Paytm", R.mipmap.ic_launcher));
        list_food.add(new FoodModel("Amazon", R.mipmap.ic_launcher));
        list_food.add(new FoodModel("FlipKart", R.mipmap.ic_launcher));
        list_food.add(new FoodModel("Myntra", R.mipmap.ic_launcher));
        list_food.add(new FoodModel("Other", R.mipmap.ic_launcher));

        deliveryAdapter = new DeliveryAdapter(getActivity(), list_online);
        rv_mOnline.setAdapter(deliveryAdapter);


    }
}
