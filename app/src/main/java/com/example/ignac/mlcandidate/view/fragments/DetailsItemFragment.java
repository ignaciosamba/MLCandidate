package com.example.ignac.mlcandidate.view.fragments;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ignac.mlcandidate.R;


public class DetailsItemFragment extends android.support.v4.app.Fragment {


    private TextView mTitle;
    private TextView mNoDescription;
    private TextView mDescription;
    private TextView mPice;
    private Button mOpenOnMLButton;

    private String mUrlToOpenML;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.details_fragment, container, false);
        initView(view);
        mOpenOnMLButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse(mUrlToOpenML);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(webpage);
                String title = getResources().getString(R.string.chooser_title);
                Intent chooser = Intent.createChooser(intent, title);
                startActivity(chooser);
            }
        });
        return view;
    }

    public void initView(View view) {
        mTitle = view.findViewById(R.id.detail_title);
        mNoDescription = view.findViewById(R.id.detail_no_title);
        mDescription = view.findViewById(R.id.detail_text);
        mPice = view.findViewById(R.id.detail_price);
        mOpenOnMLButton = view.findViewById(R.id.btn_open_ml);
        mOpenOnMLButton.setVisibility(View.GONE);
        mOpenOnMLButton.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    public void setDescription(String description) {
        if (description != null || description.isEmpty()) {
            mDescription.setText(description);
        } else {
            mNoDescription.setVisibility(View.VISIBLE);
        }
        mOpenOnMLButton.setVisibility(View.VISIBLE);
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    public void setUrl(String urlString) {
        mUrlToOpenML = urlString;
        mOpenOnMLButton.setVisibility(View.VISIBLE);
    }

    public void setPrice(String price) {
        String priceToSet = "$ " + price;
        mPice.setText(priceToSet);
    }
}
