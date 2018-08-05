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

    private static final String ML_BASE_QUERY= "https://api.mercadolibre.com/sites/MLA/";

    private NetworkUtils mResultList;
    private IResultList mIResultList;

    public ItemsPresenter(IResultList iResultList) {
        mIResultList = iResultList;
        if (mResultList == null) {
            mResultList = new NetworkUtils();
        }
    }

    public void getListRestultItem (String product) {
        Call<Results> call = mResultList.getRestClient(ML_BASE_QUERY)
                .getData("TUxBUENPUmFkZGIw","used", product);

        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                switch (response.code()) {
                    case 200:
                        Results data = new Results();
                        data.setResultList(response.body().getResultList());
                        for (Result item : data.getResultList()){
                            System.out.println("SAMBA SEARCH" + item.getTitle() + " ");
                        }
                        mIResultList.onResultListReady(data);
                        break;
                    case 401:
                        System.out.println("SAMBA FAIL 401");
                        break;
                    default:
                        System.out.println("SAMBA FAIL default");
                        break;
                }
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                System.out.println("SAMBA ONFAILURE");
                Log.e("error", t.toString());
            }
        });
    }
}
