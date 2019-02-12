package com.iprismech.alertnikki.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.google.gson.Gson;
import com.iprismech.alertnikki.MainActivity;
import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Request.AdminStaff;
import com.iprismech.alertnikki.Request.Visitor;
import com.iprismech.alertnikki.Response.ResponseVisitMember;
import com.iprismech.alertnikki.Response.WaitingVisitors;
import com.iprismech.alertnikki.activity.DummyActivity;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.Common;
import com.iprismech.alertnikki.utilities.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Visitors_Fragment extends BaseAbstractFragment<Class> implements RetrofitResponseListener {

    private SearchView search_visitors;
    private int inside_count, waiting_count;
    /* private TabLayout mtabLayout;
     private ViewPager mviewPager;
 */
    SharedPrefsUtils sharedPrefsUtils;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Object obj;
    private RetrofitResponseListener retrofitResponseListener;
    private ArrayList<ResponseVisitMember> arrayList_inside = new ArrayList<>();
    private ArrayList<ResponseVisitMember> arrayList_waiting = new ArrayList<>();

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_visitors, null);
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

  /*  @Override
    protected void initialiseViews() {
        super.initialiseViews();
        try {

            search_visitors = view.findViewById(R.id.search_visitors);
            mtabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
            mviewPager = (ViewPager)view.findViewById(R.id.viewpager);

            setupViewPager(mviewPager);
            mtabLayout.setupWithViewPager(mviewPager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    @Override
    protected void initialiseViews() {
        super.initialiseViews();
        sharedPrefsUtils=new SharedPrefsUtils(getActivity());
        inside_count = sharedPrefsUtils.getInsdiecount();
        waiting_count = sharedPrefsUtils.getwaitingcount();

        AdminStaff req = new AdminStaff();
        req.adminId = SharedPrefsUtils.getInstance(getActivity()).getAdmin();
        try {
            obj = Class.forName(AdminStaff.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "visitors_count", false);
       new RetrofitRequester(this).callPostServices(obj, 2, "alerts_count", true);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

// tabLayout.setScrollPosition(1,0f,true);
        viewPager.setCurrentItem(1);
        //  insidecount();
        //   waitingcount();
        //visitors_count();
    }

    private void setupViewPager(ViewPager viewPager) {
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new Inside_Visitors_fragment(), "Inside(" + inside_count + ")");
        adapter.addFragment(new WaitingVisitorFragment(), "Waiting(" + waiting_count + ")");
//
//        adapter.addFragment(new Inside_Visitors_fragment(), "Inside");
//        adapter.addFragment(new WaitingVisitorFragment(), "Waiting");
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                Log.d("Scrolled", "");
                try {
//                    getChildFragmentManager().beginTransaction().detach(adapter.mFragmentList.get(i)).attach(adapter.mFragmentList.get(i)).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onPageSelected(int i) {
                Log.d("Selected", "");
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                Log.d("", "ScrollState");
                try {
//                    getChildFragmentManager().beginTransaction().detach(adapter.mFragmentList.get(i)).attach(adapter.mFragmentList.get(i)).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
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
                if (object.optBoolean("status")) {
                    WaitingVisitors waitingVisitors = Common.getSpecificDataObject(objectResponse, WaitingVisitors.class);

                    switch (requestId) {
                        case 1:

//                        {"status":true,"message":"Data fetched Successfully!","visitors_count":8}

                            MainActivity activity = (MainActivity) getActivity();
                            TextView badgeVisitors = activity.findViewById(R.id.badge_notification_visitors);
                            int visitors_count = object.optInt("visitors_count") + object.optInt("visitors_count_inside");
                            badgeVisitors.setText("" + visitors_count);
                            sharedPrefsUtils.visitor_count(object.optInt("visitors_count_inside"), object.optInt("visitors_count"));



                            break;
                        case 2:
//                        {"status":true,"message":"Data fetched Successfully!","alerts_count":3}
                            MainActivity activity_alerts = (MainActivity) getActivity();
                            TextView badgeAlerts = activity_alerts.findViewById(R.id.badge_notification_alerts);
                            badgeAlerts.setText("" + object.optInt("alerts_count"));
                            break;
                    }
                }else {
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }


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






   /* private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new Inside_Visitors_fragment(), "Inside");
        adapter.addFragment(new WaitingVisitorFragment(), "Waiting");

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
    }*/
}
