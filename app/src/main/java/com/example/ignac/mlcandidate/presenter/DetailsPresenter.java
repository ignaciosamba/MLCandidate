package com.example.ignac.mlcandidate.presenter;

import android.util.Log;

import com.example.ignac.mlcandidate.model.Description;
import com.example.ignac.mlcandidate.utils.NetworkUtils;
import com.example.ignac.mlcandidate.view.IDetailItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsPresenter {

    private final String LOG_TAG = this.getClass().getName();
    /**
     * String used as base url for search the product description.
     */
    private static final String ML_BASE_DETAIL_ITEM = "https://api.mercadolibre.com/";

    /**
     * NetworkUtils to call restClient.
     */
    private NetworkUtils mResultList;
    /**
     * Interface that is used to callback when the data retrieved of the product description.
     */
    private IDetailItem mIDetailItem;

    public DetailsPresenter(IDetailItem iDetailItem) {
        mIDetailItem = iDetailItem;
        if (mResultList == null) {
            mResultList = new NetworkUtils();
        }
    }

    /**
     *  Method to retrieve the details of product.
     *
     * @param       product_id  String that will be use to get the data of the product.
     * @return      void
     */
    public void getDetailsItem (String product_id) {
        Call<Description> call = mResultList.getRestClient(ML_BASE_DETAIL_ITEM).getDetails(product_id);
        call.enqueue(new Callback<Description>() {
            @Override
            public void onResponse(Call<Description> call, Response<Description> response) {
                switch (response.code()) {
                    case 200:
                        Description data = new Description();
                        data = response.body();
                        mIDetailItem.onDetailItemReady(data);
                        break;
                    case 404:
                        Log.e(LOG_TAG, "ERROR, INFORMATION NOT FOUND");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Description> call, Throwable t) {
                Log.e("error", t.toString());
            }
        });
    }
}
