package com.example.ignac.mlcandidate.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.example.ignac.mlcandidate.R;
import com.example.ignac.mlcandidate.model.AvailableFilter;
import com.example.ignac.mlcandidate.model.Results;
import com.example.ignac.mlcandidate.presenter.ItemsPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FilterActivity extends AppCompatActivity implements IResultList {

    private final String SEARCH_PRODUCTS = "search_new_product";
    private final String CONDITION_SEARCH = "condition_search";
    private final String LOCATION_SEARCH = "location_search";

    private CheckBox mUsed;
    private CheckBox mNew;
    private Spinner mSpinerLocation;
    private Button mApplyFilter;

    private ItemsPresenter mItemsPresenter;
    private String mProduct;
    private String mUsedCheck = null;
    private String mNewCheck = null;
    private String mLocation_id = null;
    private Map<String, String> mLocations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_activity);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (intent.hasExtra(SEARCH_PRODUCTS)){
            mProduct = intent.getStringExtra(SEARCH_PRODUCTS);
        }
        mItemsPresenter = new ItemsPresenter(this);
        mItemsPresenter.getListRestultItem(null, null, mProduct);
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        if (prefs != null) {
            String restoredText = prefs.getString("Location_id", null);
            if (restoredText != null) {
                mLocation_id = restoredText;
            }
        }
    }

    private void initView() {
        mUsed = findViewById( R.id.checkbox_used);
        mNew = findViewById(R.id.checkbox_new);
        mSpinerLocation = findViewById(R.id.spinner_location);
        mApplyFilter = findViewById(R.id.btn_apply_filter);
        mApplyFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("Location_id", mLocation_id);
                editor.apply();
                Intent intent = new Intent(FilterActivity.this, ProductSearchActivity.class);
                if(mNewCheck != null) {
                    intent.putExtra(CONDITION_SEARCH, mNewCheck);
                } else if (mUsedCheck != null) {
                    intent.putExtra(CONDITION_SEARCH, mUsedCheck);
                }
                intent.putExtra(SEARCH_PRODUCTS, mProduct);
                intent.putExtra(LOCATION_SEARCH, mLocation_id);
                startActivity(intent);
                finish();
            }
        });
        mSpinerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mLocation_id = getLocationId(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(FilterActivity.this, ProductSearchActivity.class);
        if(mNewCheck != null) {
            intent.putExtra(CONDITION_SEARCH, mNewCheck);
        } else if (mUsedCheck != null) {
            intent.putExtra(CONDITION_SEARCH, mUsedCheck);
        }
        intent.putExtra(SEARCH_PRODUCTS, mProduct);
        intent.putExtra(LOCATION_SEARCH, mLocation_id);
        startActivity(intent);
        finish();
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.checkbox_new:
                if (checked) {
                    mNewCheck = "new";
                } else {
                    mNewCheck = null;
                }
                break;
            case R.id.checkbox_used:
                if (checked) {
                  mUsedCheck = "used";
                } else {
                    mUsedCheck = null;
                }
                break;
        }
    }


    private ArrayList<String> populateArrayForSpinner () {
        ArrayList<String> locations = new ArrayList<>(mLocations.values());
        return locations;
    }

    private ArrayList<String> getIDArrayForSpinner () {
        ArrayList<String> locations_id = new ArrayList<>(mLocations.keySet());
        return locations_id;
    }

    private String getLocationId (int position){
        return  getIDArrayForSpinner().get(position);
    }

    @Override
    public void onResultListReady(Results results) {
        mLocations = new HashMap<>();
        for (AvailableFilter filter : results.getFiltersList()){
            if (filter.getId().equals("state")){
                for (int i = 0 ; i<filter.getValues().size(); i++)
                mLocations.put(filter.getValues().get(i).getId(),
                        filter.getValues().get(i).getName());
            }
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, populateArrayForSpinner());
        mSpinerLocation.setAdapter(dataAdapter);
        int position = dataAdapter.getPosition(mLocations.get(mLocation_id));
        if (position == -1) {
            mSpinerLocation.setSelection(position + 1);
        } else {
            mSpinerLocation.setSelection(position);
        }
    }
}
