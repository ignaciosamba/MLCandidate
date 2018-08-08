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

    /**
     * String of product Title.
     */
    private String mProductTitle;
    /**
     * String of product Link.
     */
    private String mProductLink;
    /**
     * String of product Price.
     */
    private String mProductPrice;

    /**
     * DetailsItemFragment uses to display the product details.
     */
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
        DetailsPresenter mDetailsPresenter = new DetailsPresenter(this);
        PicturePresenter mPicturePresenter = new PicturePresenter(this);
        Intent intent = getIntent();
        String PRODUCT_ID = "product_id";
        String PRODUCT_LINK = "product_link";
        String PRODUCT_PRICE = "product_price";
        if (intent.hasExtra(PRODUCT_ID) || intent.hasExtra(PRODUCT_LINK) ||
                intent.hasExtra(PRODUCT_PRICE)) {
            //Do the search for details.
            String PRODUCT_TITLE = "product_title";
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
        if (itemDetails != null) {
            imageSliderFragment.setArrayImages(new ArrayList<Picture>(itemDetails.getPictures()));
        }
    }
}
