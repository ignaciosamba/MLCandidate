package com.example.ignac.mlcandidate.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.ignac.mlcandidate.R;
import com.example.ignac.mlcandidate.model.Description;
import com.example.ignac.mlcandidate.model.ItemDetails;
import com.example.ignac.mlcandidate.model.Picture;
import com.example.ignac.mlcandidate.presenter.DetailsPresenter;
import com.example.ignac.mlcandidate.presenter.PicturePresenter;
import com.example.ignac.mlcandidate.view.fragments.DetailsItemFragment;
import com.example.ignac.mlcandidate.view.fragments.ImageSliderFragment;

import java.util.ArrayList;

public class ProductDetailsActivity extends AppCompatActivity implements IDetailItem, IPicturesItem{

    private final String PRODUCT_ID = "product_id";
    private final String PRODUCT_TITLE = "product_title";
    private final String PRODUCT_LINK = "product_link";
    private final String PRODUCT_PRICE = "product_price";

    private DetailsPresenter mDetailsPresenter;
    private PicturePresenter mPicturePresenter;
    private String mProductTitle;
    private String mProductLink;
    private String mProductPrice;

    private DetailsItemFragment mDetailsItemFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail_activity);
        mDetailsItemFragment = (DetailsItemFragment) getSupportFragmentManager().findFragmentById(R.id.detail_fragment);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mDetailsPresenter = new DetailsPresenter(this);
        mPicturePresenter = new PicturePresenter(this);
        Intent intent = getIntent();
        if (intent.hasExtra(PRODUCT_ID) || intent.hasExtra(PRODUCT_LINK) ||
                intent.hasExtra(PRODUCT_PRICE)) {
            //Do the search for details.
            mProductTitle = intent.getStringExtra(PRODUCT_TITLE);
            mProductLink = intent.getStringExtra(PRODUCT_LINK);
            mProductPrice = intent.getStringExtra(PRODUCT_PRICE);
            mDetailsPresenter.getDetailsItem(intent.getStringExtra(PRODUCT_ID));
            mPicturePresenter.getPicturesItem(intent.getStringExtra(PRODUCT_ID));
        }
        mDetailsItemFragment.setTitle(mProductTitle);
        mDetailsItemFragment.setUrl(mProductLink);
        mDetailsItemFragment.setPrice(mProductPrice);
    }

    @Override
    public void onDetailItemReady(Description description) {
        mDetailsItemFragment.setDescription(description.getPlainText());
    }

    @Override
    public void onPictureItemReady(ItemDetails itemDetails) {
        ImageSliderFragment imageSliderFragment = (ImageSliderFragment) getSupportFragmentManager()
                .findFragmentById(R.id.image_fragment);
        imageSliderFragment.setArrayImages(new ArrayList<Picture>(itemDetails.getPictures()));
    }
}
