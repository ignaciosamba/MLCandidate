package com.example.ignac.mlcandidate.view.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ignac.mlcandidate.R;

import butterknife.ButterKnife;

public class DetailsItemFragment extends android.support.v4.app.Fragment {


    private TextView mTitle;
    private TextView mDescription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.details_fragment, container, false);
        initView(view);
        return view;
    }

    public void initView(View view) {
        mTitle = view.findViewById(R.id.detail_title);
        mDescription = view.findViewById(R.id.detail_text);
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    public void setmDescription(String description) {
        mDescription.setText(description);
    }
}
