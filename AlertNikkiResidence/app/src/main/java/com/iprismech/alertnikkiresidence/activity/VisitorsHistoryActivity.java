package com.iprismech.alertnikkiresidence.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.fragments.AllowFragmnet;
import com.iprismech.alertnikkiresidence.fragments.DenyFragment;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;

import java.util.ArrayList;
import java.util.List;

public class VisitorsHistoryActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewpager);
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);


    }

    private void setupViewPager(ViewPager viewPager) {
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AllowFragmnet(), "Allow");
        adapter.addFragment(new DenyFragment(), "Deny");
//
//        adapter.addFragment(new Inside_Visitors_fragment(), "Inside");
//        adapter.addFragment(new WaitingVisitorFragment(), "Waiting");
        viewPager.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
/*        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        });*/
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
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_visitors_history, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {

    }
}
