package com.iprismech.alertnikkiresidence.adapters;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.pojo.IntroModel;

import java.util.ArrayList;

public class SlidingImage_Adapter extends PagerAdapter {
    //    private ArrayList<Integer> IMAGES;
    private ArrayList<String> Titles;
    private ArrayList<String> Descript;
    private ArrayList<IntroModel> Intro;

    private LayoutInflater inflater;
    private Context context;


    public SlidingImage_Adapter(Context context, ArrayList<IntroModel> Intro) {
        this.context = context;
        this.Intro = Intro;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return Intro.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false);

        assert imageLayout != null;
        ImageView imageView = (ImageView) imageLayout.findViewById(R.id.images_intro);
        TextView tv_title = (TextView) imageLayout.findViewById(R.id.tv_introtitle);
        TextView tv_desc = (TextView) imageLayout.findViewById(R.id.tv_introdescription);
        try {
            String s = Intro.get(position).getTitle();
            String s1 = Intro.get(position).getDescript();
            imageView.setImageResource(Intro.get(position).getImages());
            tv_title.setText(s);
            tv_desc.setText(s1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        view.addView(imageLayout, null);
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


}