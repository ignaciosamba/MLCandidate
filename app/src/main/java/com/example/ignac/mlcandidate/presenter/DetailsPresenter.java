package com.example.ignac.mlcandidate.presenter;

import android.util.Log;

import com.example.ignac.mlcandidate.model.Description;
import com.example.ignac.mlcandidate.utils.NetworkUtils;
import com.example.ignac.mlcandidate.view.IDetailItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsPresenter {

    private static final String ML_BASE_DETAIL_ITEM = "https://api.mercadolibre.com/";

    private NetworkUtils mResultList;
    private IDetailItem mIDetailItem;

    public DetailsPresenter(IDetailItem iDetailItem) {
        mIDetailItem = iDetailItem;
        if (mResultList == null) {
            mResultList = new NetworkUtils();
        }
    }

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
                    case 401:
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
