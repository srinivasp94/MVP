package com.iprismech.alertnikki.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.base.BaseAbstractActivity;
import com.iprismech.alertnikki.fragments.BaseAbstractFragment;
import com.iprismech.alertnikki.fragments.Inside_Visitors_fragment;
import com.iprismech.alertnikki.fragments.WaitingVisitorFragment;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.Common;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DummyActivity extends BaseAbstractFragment<Class> implements RetrofitResponseListener {

    //    private RecyclerView dummy_rview;
    private Object obj;
    ArrayList<dummyresponse.Responselist> arrayList = new ArrayList<>();
    private LinearLayoutManager layoutManager;
//    private DummyAdapter dummyAdapter;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_dummy, null);
        return view;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();

        viewPager = (ViewPager) view.findViewById(R.id.dummyviewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

    }

    @Override
    protected void setListenerToViews() {

        super.setListenerToViews();
    }

  /*  @Override
    protected View getView() {
        View view =  getLayoutInflater().inflate(R.layout.activity_dummy, null);
        return view;
    }*/


    @Override
    public void setPresenter() {

    }

    /*
        @Override
        protected void initializeViews() {
            super.initializeViews();


    //        dummy_rview = findViewById(R.id.rview_dummy);

            viewPager = (ViewPager) findViewById(R.id.dummyviewpager);
            setupViewPager(viewPager);

            tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);

            layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);


           *//* Visitor visitor = new Visitor();
        visitor.adminId = "2";
        //visitor.adminId = "2";
        //for waiting status as 0
        visitor.status = "0";
        visitor.search = "";
        try {
            obj = Class.forName(Visitor.class.getName()).cast(visitor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "visitors", true);*//*
    }*/
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new Inside_Visitors_fragment(), "ONE");
        adapter.addFragment(new WaitingVisitorFragment(), "TWO");

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
//        WaitingVisitors

        if (objectResponse == null || objectRequest.equals("")) {
            Common.showToast(getActivity(), "PLease Try again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject object = new JSONObject(jsonString);
                if (object.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            dummyresponse response = Common.getSpecificDataObject(objectResponse, dummyresponse.class);
                            arrayList = (ArrayList<dummyresponse.Responselist>) response.response;

                            if (arrayList != null || arrayList.size() > 0) {
//                                dummy_rview.setLayoutManager(layoutManager);
//                                dummyAdapter = new DummyAdapter(getActivity(), arrayList);
//                                dummy_rview.setAdapter(dummyAdapter);
                            }


                            break;

                    }
                } else {
                    Common.showToast(getActivity(), object.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}
