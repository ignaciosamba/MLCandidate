package com.example.ignac.mlcandidate.presenter;

import android.util.Log;

import com.example.ignac.mlcandidate.model.Description;
import com.example.ignac.mlcandidate.model.ItemDetails;
import com.example.ignac.mlcandidate.model.Picture;
import com.example.ignac.mlcandidate.utils.NetworkUtils;
import com.example.ignac.mlcandidate.view.IDetailItem;
import com.example.ignac.mlcandidate.view.IPicturesItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PicturePresenter {

    private static final String ML_BASE_DETAIL_ITEM = "https://api.mercadolibre.com/";

    private NetworkUtils mResultList;
    private IPicturesItem mIPicturesItem;

    public PicturePresenter(IPicturesItem iPicturesItem) {
        mIPicturesItem = iPicturesItem;
        if (mResultList == null) {
            mResultList = new NetworkUtils();
        }
    }

    public void getPicturesItem (String product_id) {
        Call<ItemDetails> call = mResultList.getRestClient(ML_BASE_DETAIL_ITEM).getPictureDetails(product_id);
        System.out.println("SAMBA URL IS: " + call.request().isHttps());
        call.enqueue(new Callback<ItemDetails>() {
            @Override
            public void onResponse(Call<ItemDetails> call, Response<ItemDetails> response) {
                switch (response.code()) {
                    case 200:
                        ItemDetails data = new ItemDetails();
                        data = response.body();
                        System.out.println("SAMBA SEARCH" + data.getPictures().size() + " ");
                        mIPicturesItem.onPictureItemReady(data);
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
            public void onFailure(Call<ItemDetails> call, Throwable t) {
                System.out.println("SAMBA ONFAILURE");
                Log.e("error", t.toString());
            }
        });
    }
}
