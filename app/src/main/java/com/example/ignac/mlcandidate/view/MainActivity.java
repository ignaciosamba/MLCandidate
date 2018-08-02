package com.example.ignac.mlcandidate.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ignac.mlcandidate.R;
import com.example.ignac.mlcandidate.model.Result;
import com.example.ignac.mlcandidate.model.Results;
import com.example.ignac.mlcandidate.utils.RestClient;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText productSearch;
    private Button searchBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView(){
        productSearch = findViewById(R.id.search_txt);
        searchBtn = findViewById(R.id.search_btn);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("SAMBA ON CLICK");
                String productToSearch = productSearch.getText().toString();
                System.out.println("SAMBA PRODUCT IS: " + productToSearch);
                loadJSON(productToSearch);
            }
        });
    }


    private void loadJSON(String product){
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.mercadolibre.com/sites/MLA/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RestClient restClient = retrofit.create(RestClient.class);
        Call<Results> call = restClient.getData("TUxBUENPUmFkZGIw","used", product);

        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                switch (response.code()) {
                    case 200:
                        Results data = response.body();
                        for (Result item : data.getResultList()){
                            System.out.println("SAMBA " + item.getTitle() + " ");
                        }
//                        view.notifyDataSetChanged(data.getResults());
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
