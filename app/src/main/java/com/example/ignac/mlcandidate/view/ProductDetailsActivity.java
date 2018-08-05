package com.example.ignac.mlcandidate.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.ignac.mlcandidate.R;
import com.example.ignac.mlcandidate.model.Description;
import com.example.ignac.mlcandidate.presenter.DetailsPresenter;
import com.example.ignac.mlcandidate.presenter.ItemsPresenter;
import com.example.ignac.mlcandidate.view.fragments.DetailsItemFragment;

public class ProductDetailsActivity extends AppCompatActivity implements IDetailItem{

    private final String PRODUCT_ID = "product_id";

//    private TextView mTitle;
//    private TextView mDetail;

    private DetailsPresenter mDetailsPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail_activity);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDetailsPresenter = new DetailsPresenter(this);
        Intent intent = getIntent();
        if (intent.hasExtra(PRODUCT_ID)) {
            //Do the search for details.
            System.out.println("SAMBA ONRESUME DETAIL HAS PRODUCT ID: " + intent.getStringExtra(PRODUCT_ID));
            mDetailsPresenter.getDetailsItem(intent.getStringExtra(PRODUCT_ID));
        }
    }

    private void initView() {
//        mTitle = findViewById(R.id.detail_title);
//        mDetail = findViewById(R.id.detail_text);
    }


    @Override
    public void onDetailItemReady(Description description) {
        System.out.println("SAMBA DESCRIPTION __________________ : " + description.getPlainText());
        DetailsItemFragment detailsItemFragment = (DetailsItemFragment) getSupportFragmentManager().findFragmentById(R.id.detail_fragment);
        detailsItemFragment.setmDescription(description.getPlainText());
//        mDetail.setText(description.getPlainText());
    }
}
