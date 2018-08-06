package com.example.ignac.mlcandidate.view.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ignac.mlcandidate.ApplicationContext;
import com.example.ignac.mlcandidate.R;
import com.example.ignac.mlcandidate.model.Picture;
import com.example.ignac.mlcandidate.view.Adapter.ImageSliderAdapter;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageSliderFragment extends android.support.v4.app.Fragment {

    private int currentPage = 0;
    private int NUM_PAGES = 0;

    private ArrayList<Picture> mArrayImages;
    private CirclePageIndicator mIndicator;

    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.image_slider_fragment, container, false);
        ButterKnife.bind(this, view);
        mIndicator =  view.findViewById(R.id.indicator);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    public void setArrayImages(ArrayList<Picture> pictures) {
        mArrayImages = pictures;
        initImageSlider();
    }

    private List<Picture> getImageList(){
        return mArrayImages;
    }

    private void initImageSlider(){

        //Set the pager with an adapter
        mViewPager.setAdapter(new ImageSliderAdapter(ApplicationContext.getAppContext(), getImageList()));


        mIndicator.setViewPager(mViewPager);

        final float density = getResources().getDisplayMetrics().density;

        //Set circle indicator radius
        mIndicator.setRadius(5 * density);

        NUM_PAGES = getImageList().size();

        // Auto start of viewpager
//        final Handler handler = new Handler();
//        final Runnable Update = new Runnable() {
//            public void run() {
//                if (currentPage == NUM_PAGES) {
//                    currentPage = 0;
//                }
//                mViewPager.setCurrentItem(currentPage++, true);
//            }
//        };
//        Timer swipeTimer = new Timer();
//        swipeTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(Update);
//            }
//        }, 3000, 3000);

        // Pager listener over indicator
        mIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
    }


}
