package com.example.deliverybox.Retrofit;


import com.example.deliverybox.Model.Account;
import com.example.deliverybox.Model.Shipment;
import com.example.deliverybox.Model.User;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AccountApi {

    @GET("/account/get-all")
    Call<List<Account>> getAllAccounts();

    @POST("/account/check-clients")
    Call<List<Account>> checkClients(@Body HashMap<String, Integer> type);

    @POST("/account/check-employees")
    Call<List<Account>> checkEmployees(@Body HashMap<String, Integer> type);

    @PUT("/account/{id}")
    Call<Account> updateAccount(@Path("id") Integer id, @Body Account account);

    @POST("/account/save")
    Call<Account> save(@Body Account account);


    @POST("/user/login")
    Call<User> userLogin(@Body User user);

}
