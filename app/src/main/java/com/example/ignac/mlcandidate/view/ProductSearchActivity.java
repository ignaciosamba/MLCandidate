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
import com.example.ignac.mlcandidate.model.AvailableFilter;
import com.example.ignac.mlcandidate.model.Result;
import com.example.ignac.mlcandidate.model.Results;
import com.example.ignac.mlcandidate.presenter.ItemsPresenter;
import com.example.ignac.mlcandidate.utils.LocationFilterList;
import com.example.ignac.mlcandidate.view.Adapter.ProductAdapterClickListener;
import com.example.ignac.mlcandidate.view.Adapter.ProductItemAdapter;

import static java.lang.String.valueOf;

public class ProductSearchActivity extends AppCompatActivity implements IResultList {

    /**
     * String uses by the intent to open the filter activity.
     */
    private final String SEARCH_PRODUCTS = "search_new_product";

    /**
     * String of product Title.
     */
    private TextView mTitleSearch;
    /**
     * String uses if the search doesn't retrieve anything.
     */
    private TextView mNotFoundProducts;
    /**
     * Progress bar used meanwhile the search is performing.
     */
    private ProgressBar mProgressBar;
    /**
     * Button uses to open the filter screen.
     */
    private Button mFilter;

    /**
     * Adapter used to show the list of products searched.
     */
    private ProductItemAdapter mProductItemAdapter;
    /**
     * String of the product Title.
     */
    private String mProductTitle;
    /**
     * String of the condition filter.
     */
    private String mCondition = null;
    /**
     * String of the location filter.
     */
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
        String CONDITION_SEARCH = "condition_search";
        String LOCATION_SEARCH = "location_search";
        if (intent.hasExtra(SEARCH_PRODUCTS) || intent.hasExtra(CONDITION_SEARCH) ||
                intent.hasExtra(LOCATION_SEARCH)) {
            mProductTitle = intent.getStringExtra(SEARCH_PRODUCTS);
            mTitleSearch.setText(mProductTitle);
            mCondition = intent.getStringExtra(CONDITION_SEARCH);
            mlocationId = intent.getStringExtra(LOCATION_SEARCH);
        }
        ItemsPresenter mItemsPresenter = new ItemsPresenter(this);
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
        RecyclerView mProductListRecycler = findViewById(R.id.productList_recyclerview);
        Results mResultsItem = new Results();
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
        for (AvailableFilter filter : results.getFiltersList()){
            if (filter.getId().equals("state")){
                for (int i = 0 ; i<filter.getValues().size(); i++)
                    LocationFilterList.getInstance().getLocationsFilter().put(filter.getValues().get(i).getId(),
                            filter.getValues().get(i).getName());
            }
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
            String PRODUCT_ID = "product_id";
            intent.putExtra(PRODUCT_ID, product.getId());
            String PRODUCT_TITLE = "product_title";
            intent.putExtra(PRODUCT_TITLE, product.getTitle());
            String PRODUCT_LINK = "product_link";
            intent.putExtra(PRODUCT_LINK, product.getPermalink());
            String PRODUCT_PRICE = "product_price";
            intent.putExtra(PRODUCT_PRICE, valueOf(product.getPrice()));
            startActivity(intent);
        }
    };
}
