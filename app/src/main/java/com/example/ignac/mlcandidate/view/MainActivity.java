package com.example.ignac.mlcandidate.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ignac.mlcandidate.R;
import com.example.ignac.mlcandidate.model.Result;
import com.example.ignac.mlcandidate.model.Results;
import com.example.ignac.mlcandidate.presenter.ItemsPresenter;
import com.example.ignac.mlcandidate.view.Adapter.ProductAdapterClickListener;
import com.example.ignac.mlcandidate.view.Adapter.ProductItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String SEARCH_PRODUCTS = "search_new_product";

    private EditText mProductSearch;
    private Button mSearchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView(){
        mProductSearch = findViewById(R.id.search_txt);
        mSearchBtn = findViewById(R.id.search_btn);
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productToSearch = mProductSearch.getText().toString();
                Intent searchProduct = new Intent(MainActivity.this, ProductSearchActivity.class);
                searchProduct.putExtra(SEARCH_PRODUCTS, productToSearch);
                startActivity(searchProduct);
            }
        });
    }
}
