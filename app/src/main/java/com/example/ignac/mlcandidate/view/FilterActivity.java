package com.example.ignac.mlcandidate.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ignac.mlcandidate.ApplicationContext;
import com.example.ignac.mlcandidate.R;
import com.example.ignac.mlcandidate.model.AvailableFilter;
import com.example.ignac.mlcandidate.model.Results;
import com.example.ignac.mlcandidate.presenter.ItemsPresenter;
import com.example.ignac.mlcandidate.utils.LocationFilterList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FilterActivity extends AppCompatActivity {

    /**
     * String to retrieve for the intent and send by an intent the product searched.
     */
    private final String SEARCH_PRODUCTS = "search_new_product";
    /**
     * String to send by intent the condition filter.
     */
    private final String CONDITION_SEARCH = "condition_search";
    /**
     * String to  send by intent the location filter.
     */
    private final String LOCATION_SEARCH = "location_search";

    /**
     * Spinner to select the location filter.
     */
    private Spinner mSpinerLocation;
    /**
     * Button to apply filter.
     */
    private Button mApplyFilter;

    /**
     * Product being searched.
     */
    private String mProduct;
    /**
     * String to send used in condition filter.
     */
    private String mUsedCheck = null;
    /**
     * String to send new in condition filter.
     */
    private String mNewCheck = null;
    /**
     * String to the location id in location filter.
     */
    private String mLocation_id = null;


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
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        if (prefs != null) {
            String restoredText = prefs.getString("Location_id", null);
            if (restoredText != null) {
                mLocation_id = restoredText;
            }
        }
        setAdapterToSpinner();
    }

    private void initView() {
        mSpinerLocation = findViewById(R.id.spinner_location);
        mSpinerLocation.setPrompt("Seleccionar");
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
                mLocation_id = null;
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

    /**
     *  Method onClick to know which checkbox is selected.
     *
     * @param    view View.
     * @return   void
     */
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

    /**
     *  Method to populate the arrayList used in the spinner.
     *
     * @return   The ArrayList with all the Locations.
     */
    private ArrayList<String> populateArrayForSpinner () {
        ArrayList<String> locations = new ArrayList<>(LocationFilterList.getInstance().getLocationsFilter().values());
        return locations;
    }

    /**
     *  Method to get the locations ids array of the LocationFilterList.
     *
     * @return  The ArrayList with all the Locations id.
     */
    private ArrayList<String> getIDArrayForSpinner () {
        ArrayList<String> locations_id = new ArrayList<>(LocationFilterList.getInstance()
                .getLocationsFilter().keySet());
        return locations_id;
    }
    /**
     *  Method to get the location id array of the spinner.
     *
     * @return  The String with the Locations id.
     */
    private String getLocationId (int position){
        return  getIDArrayForSpinner().get(position);
    }

    /**
     *  Method to set the location names to the spinner.
     *
     * @return  void
     */
    private void setAdapterToSpinner() {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_center_text, populateArrayForSpinner());
        mSpinerLocation.setAdapter(dataAdapter);
        int position = dataAdapter.getPosition(LocationFilterList.getInstance().getLocationsFilter()
                .get(mLocation_id));
        if (position == -1) {
            mSpinerLocation.setSelection(position + 1);
        } else {
            mSpinerLocation.setSelection(position);
        }
    }
}
