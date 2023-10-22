package com.example.deliverybox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.deliverybox.Adapter.ShipmentAdapter;
import com.example.deliverybox.Model.Account;
import com.example.deliverybox.Model.Shipment;
import com.example.deliverybox.Model.User;
import com.example.deliverybox.Retrofit.AccountApi;
import com.example.deliverybox.Retrofit.RetrofitService;
import com.example.deliverybox.Retrofit.ShipmentApi;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.transform.ErrorListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShipmentListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button backBtn;
    private Integer accId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipment_list);
        recyclerView = findViewById(R.id.shipmentList_recyclerView);
        backBtn = findViewById(R.id.bckBtn);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.setTitle("МОИТЕ ПРАТКИ");

        String full_name= getIntent().getStringExtra("full_name");
        String create_by = getIntent().getStringExtra("id_account");
        String type = getIntent().getStringExtra("account_type");
        Integer account_type = Integer.valueOf(type);
        accId = Integer.valueOf(create_by);

        loadShipments();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (account_type ){
                    case 1:
                        Intent intent = new Intent(ShipmentListActivity.this, AdminActivity.class);
                        intent.putExtra("full_name", full_name);
                        intent.putExtra("id_account", create_by);
                        intent.putExtra("account_type", type);

                        startActivity(intent);
                        finish();
                        break;

                    case 2:
                        Intent intent1 = new Intent(ShipmentListActivity.this, EmployeeActivity.class);
                        intent1.putExtra("full_name", full_name);
                        intent1.putExtra("id_account", create_by);
                        intent1.putExtra("account_type", type);

                        startActivity(intent1);
                        finish();
                        break;

                    case 3:
                        Intent intent2 = new Intent(ShipmentListActivity.this, WelcomeActivity.class);
                        intent2.putExtra("full_name", full_name);
                        intent2.putExtra("id_account", create_by);
                        intent2.putExtra("account_type", type);

                        startActivity(intent2);
                        finish();
                        break;
                    default:
                        Toast.makeText(ShipmentListActivity.this, "Error!", Toast.LENGTH_LONG).show();
                        break;
                }

            }
        });
    }

    private void loadShipments() {

        RetrofitService retrofitService = new RetrofitService();
        ShipmentApi shipmentApi = retrofitService.getRetrofit().create(ShipmentApi.class);

        //Toast.makeText(ShipmentListActivity.this, create_by.toString(), Toast.LENGTH_SHORT).show();

        String create_by = getIntent().getStringExtra("id_account");
        Integer create_byy = Integer.valueOf(create_by);

        Shipment creator = new Shipment();
        creator.setCreate_by(create_byy);


        HashMap<String,Integer> params = new HashMap<String, Integer>();
        params.put("create_by", create_byy);
        //Toast.makeText(ShipmentListActivity.this, params.toString(), Toast.LENGTH_SHORT).show();


        shipmentApi.checkCreator(params).enqueue(new Callback<List<Shipment>>() {
            @Override
            public void onResponse(Call<List<Shipment>> call, Response<List<Shipment>> response) {

                populateListView(response.body());

            }

            @Override
            public void onFailure(Call<List<Shipment>> call, Throwable throwable) {

            }
        });

    }


    private void loadShipments2() {
        RetrofitService retrofitService = new RetrofitService();
        ShipmentApi shipmentApi = retrofitService.getRetrofit().create(ShipmentApi.class);

        shipmentApi.getAllShipments().enqueue(new Callback<List<Shipment>>() {

            @Override
            public void onResponse(Call<List<Shipment>> call, Response<List<Shipment>> response) {
                populateListView(response.body());
                //Toast.makeText(ShipmentListActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Shipment>> call, Throwable throwable) {
                Toast.makeText(ShipmentListActivity.this, "Failed to load shipments", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateListView(List<Shipment> shipmentList) {
        ShipmentAdapter shipmentAdapter = new ShipmentAdapter(shipmentList);
        recyclerView.setAdapter(shipmentAdapter);
    }
}