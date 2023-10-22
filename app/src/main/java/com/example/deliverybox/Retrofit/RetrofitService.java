package com.example.deliverybox.Retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private Retrofit retrofit;
    private static  RetrofitService mInstance;

    public RetrofitService(){
        initializeRetrofit();
    }

    private void initializeRetrofit() {
        retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.2:9000")
                .addConverterFactory(GsonConverterFactory.create(new Gson())).build();

        //retrofit = new Retrofit.Builder().baseUrl("http://192.168.17.60:9000")
        //        .addConverterFactory(GsonConverterFactory.create(new Gson())).build();
    }

    public static synchronized RetrofitService getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitService();
        }
        return mInstance;
    }

    public AccountApi getAccountApi() {
        return retrofit.create(AccountApi.class);
    }


    public Retrofit getRetrofit(){
        return retrofit;
    }

}
