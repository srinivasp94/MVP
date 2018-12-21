package com.iprismech.alertnikki.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Response.FoodModel;
import com.iprismech.alertnikki.Response.OnlineModel;
import com.iprismech.alertnikki.adapters.DeliveryAdapter;
import com.iprismech.alertnikki.adapters.OnlineDeliveryAdapter;

import java.util.ArrayList;

public class Delivery_Fragment extends BaseAbstractFragment<Class> {

    private RecyclerView rv_mfood, rv_mOnline;
//    private LinearLayoutManager managerFood;
    private DeliveryAdapter deliveryAdapter;
    private OnlineDeliveryAdapter onlineDeliveryAdapter;
    private ArrayList<FoodModel> list_food = new ArrayList();
    private ArrayList<OnlineModel> list_online = new ArrayList();

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

        rv_mfood = view.findViewById(R.id.rv_Food);
        rv_mOnline = view.findViewById(R.id.rv_Onlineshopping);


        list_food.add(new FoodModel("Swiggy", R.mipmap.ic_launcher));
        list_food.add(new FoodModel("Food Panda", R.mipmap.ic_launcher));
        list_food.add(new FoodModel("Zomoto", R.mipmap.ic_launcher));
        list_food.add(new FoodModel("Uber Eats", R.mipmap.ic_launcher));
        list_food.add(new FoodModel("Other", R.mipmap.ic_launcher));

        list_online.add(new OnlineModel("Paytm", R.mipmap.ic_launcher));
        list_online.add(new OnlineModel("Amazon", R.mipmap.ic_launcher));
        list_online.add(new OnlineModel("FlipKart", R.mipmap.ic_launcher));
        list_online.add(new OnlineModel("Myntra", R.mipmap.ic_launcher));
        list_online.add(new OnlineModel("Other", R.mipmap.ic_launcher));


        LinearLayoutManager managerFood = new LinearLayoutManager(getActivity());
        managerFood.setOrientation(LinearLayoutManager.HORIZONTAL);
        deliveryAdapter = new DeliveryAdapter(getActivity(), list_food);
        rv_mfood.setLayoutManager(managerFood);
        rv_mfood.setAdapter(deliveryAdapter);

        LinearLayoutManager managerOnline = new LinearLayoutManager(getActivity());
        managerOnline.setOrientation(LinearLayoutManager.HORIZONTAL);
        onlineDeliveryAdapter = new OnlineDeliveryAdapter(getActivity(), list_online);
        rv_mOnline.setLayoutManager(managerOnline);
        rv_mOnline.setAdapter(onlineDeliveryAdapter);


    }
}
