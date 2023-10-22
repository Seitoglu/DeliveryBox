package com.example.deliverybox.Retrofit;


import com.example.deliverybox.Model.Shipment;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ShipmentApi {

    @POST("/shipment/save")
    Call<Shipment> save(@Body Shipment shipment);

    @POST("/shipment/check")
    Call<List<Shipment>> checkCreator(@Body HashMap<String, Integer> creator);

    @GET("/shipment/get-all")
    Call<List<Shipment>> getAllShipments();

}
