package com.example.ignac.mlcandidate.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
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
    private final String CONDITION_SEARCH = "condition_search";
    private final String LOCATION_SEARCH = "location_search";

    private TextView mTitleSearch;
    private TextView mNotFoundProducts;
    private ProgressBar mProgressBar;
    private Button mFilter;

    private ItemsPresenter mItemsPresenter;
    private Results mResultsItem;
    private RecyclerView mProductListRecycler;
    private ProductItemAdapter mProductItemAdapter;
    private String mProductTitle;
    private String mCondition = null;
    private String mlocationId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        initView();
        mFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductSearchActivity.this, FilterActivity.class);
                intent.putExtra(SEARCH_PRODUCTS, mProductTitle);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFilter.setVisibility(View.INVISIBLE);
        Intent intent = getIntent();
        if (intent.hasExtra(SEARCH_PRODUCTS) || intent.hasExtra(CONDITION_SEARCH) ||
                intent.hasExtra(LOCATION_SEARCH)) {
            mProductTitle = intent.getStringExtra(SEARCH_PRODUCTS);
            mTitleSearch.setText(mProductTitle);
            mCondition = intent.getStringExtra(CONDITION_SEARCH);
            mlocationId = intent.getStringExtra(LOCATION_SEARCH);
        }
        mItemsPresenter = new ItemsPresenter(this);
        mItemsPresenter.getListRestultItem(mlocationId, mCondition, mProductTitle);
    }

    private void initView() {
        mFilter = findViewById(R.id.filter_btn);
        mTitleSearch = findViewById(R.id.title_search_txt);
        mNotFoundProducts = findViewById(R.id.not_found_product);
        mNotFoundProducts.setVisibility(View.INVISIBLE);
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

        if (results.getResultList().size() != 0) {
            mProductItemAdapter.setResultList(results);
        } else {
            mNotFoundProducts.setVisibility(View.VISIBLE);
        }
        mProgressBar.setVisibility(View.INVISIBLE);
        mFilter.setVisibility(View.VISIBLE);
    }

    /**
     * RecyclerItem click event listener
     * */
    private ProductAdapterClickListener recyclerItemClickListener = new ProductAdapterClickListener() {
        @Override
        public void onItemClick(Result product) {
            Intent intent = new Intent(ProductSearchActivity.this, ProductDetailsActivity.class);
            intent.putExtra(PRODUCT_ID, product.getId());
            intent.putExtra(PRODUCT_TITLE, product.getTitle());
            intent.putExtra(PRODUCT_LINK, product.getPermalink());
            intent.putExtra(PRODUCT_PRICE, valueOf(product.getPrice()));
            startActivity(intent);
        }
    };
}
