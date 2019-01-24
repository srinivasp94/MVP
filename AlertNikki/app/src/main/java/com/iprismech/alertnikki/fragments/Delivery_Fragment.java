package com.iprismech.alertnikki.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Response.FoodModel;
import com.iprismech.alertnikki.Response.OnlineModel;
import com.iprismech.alertnikki.adapters.DeliveryAdapter;
import com.iprismech.alertnikki.adapters.OnlineDeliveryAdapter;
import com.iprismech.alertnikki.app.factories.constants.AppConstants;
import com.iprismech.alertnikki.app.factories.controllers.ApplicationController;

import java.util.ArrayList;

public class Delivery_Fragment extends BaseAbstractFragment<Class> {

    private RecyclerView rv_mfood, rv_mOnline;
    private SearchView search_company;
    //    private LinearLayoutManager managerFood;
    private DeliveryAdapter deliveryAdapter;
    private OnlineDeliveryAdapter onlineDeliveryAdapter;
    private ArrayList<FoodModel> list_food = new ArrayList();
    private ArrayList<OnlineModel> list_online = new ArrayList();
    private LinearLayoutManager managerFood,managerOnline;

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
        search_company = view.findViewById(R.id.search_company);

        list_food.add(new FoodModel("Swiggy", R.drawable.ic_swoggy));
        list_food.add(new FoodModel("Food Panda", R.drawable.ic_foodpanda));
        list_food.add(new FoodModel("Zomato", R.drawable.ic_zomato));
        list_food.add(new FoodModel("Uber Eats", R.drawable.ic_ubereats));
        list_food.add(new FoodModel("Other", R.drawable.dummy));

        list_online.add(new OnlineModel("Paytm", R.drawable.ic_paytm));
        list_online.add(new OnlineModel("Amazon", R.drawable.ic_amazon));
        list_online.add(new OnlineModel("FlipKart", R.drawable.ic_flipkart));
        list_online.add(new OnlineModel("Myntra", R.drawable.ic_myntra));
        list_online.add(new OnlineModel("Other", R.drawable.dummy));


        managerFood = new LinearLayoutManager(getActivity());
        managerFood.setOrientation(LinearLayoutManager.HORIZONTAL);

      managerOnline = new LinearLayoutManager(getActivity());
        managerOnline.setOrientation(LinearLayoutManager.HORIZONTAL);

        deliveryAdapter = new DeliveryAdapter(getActivity(), list_food);
        rv_mfood.setLayoutManager(managerFood);
        rv_mfood.setAdapter(deliveryAdapter);
        deliveryAdapter.setOnItemClickListener(new DeliveryAdapter.OnitemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                callIntent_delivery(position);
            }
        });

        /*LinearLayoutManager managerOnline = new LinearLayoutManager(getActivity());
        managerOnline.setOrientation(LinearLayoutManager.HORIZONTAL);*/
        onlineDeliveryAdapter = new OnlineDeliveryAdapter(getActivity(), list_online);
        rv_mOnline.setLayoutManager(managerOnline);
        rv_mOnline.setAdapter(onlineDeliveryAdapter);
        onlineDeliveryAdapter.setOnItemClickListener(new OnlineDeliveryAdapter.OnitemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                call_intent_online(position);
            }
        });


    }

    private void callIntent_delivery(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("Key_Title", list_food.get(position).getTitle());
        bundle.putInt("Key_Image", list_food.get(position).getImg_id());

        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_DELIVERY_BOY_SCREEN, bundle);

    }

    private void call_intent_online(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("Key_Title", list_online.get(position).getTitle());
        bundle.putInt("Key_Image", list_online.get(position).getImg_id());

        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_DELIVERY_BOY_SCREEN, bundle);

    }

    @Override
    public void onResume() {
        super.onResume();
        //initialiseViews();
    }
}
