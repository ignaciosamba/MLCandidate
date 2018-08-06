package com.example.ignac.mlcandidate.view.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ignac.mlcandidate.R;
import com.example.ignac.mlcandidate.model.Picture;
import com.example.ignac.mlcandidate.utils.NetworkUtils;

import java.util.List;

public class ImageSliderAdapter extends PagerAdapter {

    private ImageView mImageView;
    private Context mContext;
    private List<Picture> imageList;
    private LayoutInflater inflater;

    public ImageSliderAdapter(Context context, List<Picture> list) {
        mContext = context;
        imageList = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        ViewGroup imageLayout = (ViewGroup) inflater.inflate(R.layout.slider_image, collection, false);
        mImageView = imageLayout.findViewById(R.id.imageView);
        NetworkUtils.LoadImage(imageList.get(position).getUrl(), mImageView);
        collection.addView(imageLayout);
        return imageLayout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        if (imageList != null) {
            return imageList.size();
        } else {
            return 0;
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}