package com.example.ignac.mlcandidate.presenter;

import android.app.Activity;
import android.util.Log;

import com.example.ignac.mlcandidate.model.Description;
import com.example.ignac.mlcandidate.model.Result;
import com.example.ignac.mlcandidate.model.Results;
import com.example.ignac.mlcandidate.utils.NetworkUtils;
import com.example.ignac.mlcandidate.view.IResultList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemsPresenter {

    private final String LOG_TAG = this.getClass().getName();
    /**
     * String used as base url for search the products list.
     */
    private static final String ML_BASE_QUERY= "https://api.mercadolibre.com/sites/MLA/";

    /**
     * NetworkUtils to call restClient.
     */
    private NetworkUtils mResultList;
    /**
     * Interface that is used to callback when the data retrieved of the products list.
     */
    private IResultList mIResultList;

    public ItemsPresenter(IResultList iResultList) {
        mIResultList = iResultList;
        if (mResultList == null) {
            mResultList = new NetworkUtils();
        }
    }

    /**
     *  Method to retrieve the list of products searched and the filters availables.
     *
     * @param       state_id  String that will be use to filter by location.
     * @param       condition_id String that will be use to filter by condition.
     * @param       product String that will be use to retrieve the product list.
     * @return      void
     */
    public void getListRestultItem (String state_id, String condition_id, String product) {
        Call<Results> call = mResultList.getRestClient(ML_BASE_QUERY)
                .getData(state_id, condition_id, product);

        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                switch (response.code()) {
                    case 200:
                        Results data = new Results();
                        data.setResultList(response.body().getResultList());
                        data.setFiltersList(response.body().getFiltersList());
                        for (Result item : data.getResultList()){
                        }
                        mIResultList.onResultListReady(data);
                        break;
                    case 404:
                        Log.e(LOG_TAG, "ERROR, INFORMATION NOT FOUND");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                Log.e("error", t.toString());
            }
        });
    }
}
