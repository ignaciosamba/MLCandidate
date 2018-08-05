package com.example.ignac.mlcandidate.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ignac.mlcandidate.R;
import com.example.ignac.mlcandidate.model.Result;
import com.example.ignac.mlcandidate.model.Results;
import com.example.ignac.mlcandidate.presenter.ItemsPresenter;
import com.example.ignac.mlcandidate.view.Adapter.ProductAdapterClickListener;
import com.example.ignac.mlcandidate.view.Adapter.ProductItemAdapter;

import static java.lang.String.valueOf;

public class ProductSearchActivity extends AppCompatActivity implements IResultList {

    private final String SEARCH_PRODUCTS = "search_new_product";
    private final String PRODUCT_ID = "product_id";
    private final String PRODUCT_TITLE = "product_title";
    private final String PRODUCT_LINK = "product_link";
    private final String PRODUCT_PRICE = "product_price";

    private TextView mTitleSearch;
    private TextView mNotFoundProducts;
    private ProgressBar mProgressBar;
    private ItemsPresenter mItemsPresenter;
    private Results mResultsItem;
    private RecyclerView mProductListRecycler;
    private ProductItemAdapter mProductItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (intent.hasExtra(SEARCH_PRODUCTS)) {
            mTitleSearch.setText(intent.getStringExtra(SEARCH_PRODUCTS));
        }
        mItemsPresenter = new ItemsPresenter(this);
        mItemsPresenter.getListRestultItem(mTitleSearch.getText().toString());
    }

    private void initView() {
        mTitleSearch = findViewById(R.id.title_search_txt);
        mNotFoundProducts = findViewById(R.id.not_found_product);
        mProgressBar = findViewById(R.id.searchProgressBar);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mProductListRecycler =  findViewById(R.id.productList_recyclerview);
        mResultsItem = new Results();
        mProductItemAdapter = new ProductItemAdapter(mResultsItem, recyclerItemClickListener);
        mProductListRecycler.setLayoutManager(llm);
        mProductListRecycler.setAdapter(mProductItemAdapter);
    }

    @Override
    public void onResultListReady(Results results) {
        System.out.println("SAMBA onResultListReady ----->" + results.getResultList().size());
        if (results.getResultList().size() != 0) {
            mProductItemAdapter.setResultList(results);
        } else {
            System.out.println("SAMBA NOT FOUND NOTHING");
            mNotFoundProducts.setVisibility(View.VISIBLE);
        }
        mProgressBar.setVisibility(View.INVISIBLE);

    }

    /**
     * RecyclerItem click event listener
     * */
    private ProductAdapterClickListener recyclerItemClickListener = new ProductAdapterClickListener() {
        @Override
        public void onItemClick(Result product) {
            Toast.makeText(ProductSearchActivity.this,
                    "Product title:  " + product.getTitle(),
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ProductSearchActivity.this, ProductDetailsActivity.class);
            intent.putExtra(PRODUCT_ID, product.getId());
            intent.putExtra(PRODUCT_TITLE, product.getTitle());
            intent.putExtra(PRODUCT_LINK, product.getPermalink());
            System.out.println("SAMBA PRICE IS::::::::::::::::::::::::::::::::::: " + valueOf(product.getPrice()));
            intent.putExtra(PRODUCT_PRICE, valueOf(product.getPrice()));
            startActivity(intent);
        }
    };
}
