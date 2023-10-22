package com.example.deliverybox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.deliverybox.Adapter.AccountAdapter;
import com.example.deliverybox.Model.Account;
import com.example.deliverybox.Retrofit.AccountApi;
import com.example.deliverybox.Retrofit.RetrofitService;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_list);
        recyclerView = findViewById(R.id.accountList_recyclerView);

        backBtn = findViewById(R.id.bckBtn);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        String full_name= getIntent().getStringExtra("full_name");
        String create_by = getIntent().getStringExtra("id_account");
        String type = getIntent().getStringExtra("account_type");
        String str = getIntent().getStringExtra("str");
        Integer account_type = Integer.valueOf(type);

        switch(str){
            case "clients":
                this.setTitle("Клиенти");
                loadClients();
                break;
            case "employees":
                this.setTitle("Служители");
                loadEmployees();
                break;
            case "all":
                this.setTitle("Всички Акаунти");
                loadAccounts();
                break;
        }



        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (account_type){
                    case 1:
                        Intent intent = new Intent(AccountListActivity.this, AdminActivity.class);
                        intent.putExtra("full_name", full_name);
                        intent.putExtra("id_account", create_by);
                        intent.putExtra("account_type", type);

                        startActivity(intent);
                        finish();
                        break;

                    case 2:
                        Intent intent1 = new Intent(AccountListActivity.this, EmployeeActivity.class);
                        intent1.putExtra("full_name", full_name);
                        intent1.putExtra("id_account", create_by);
                        intent1.putExtra("account_type", type);

                        startActivity(intent1);
                        finish();
                        break;

                    case 3:
                        Intent intent2 = new Intent(AccountListActivity.this, WelcomeActivity.class);
                        intent2.putExtra("full_name", full_name);
                        intent2.putExtra("id_account", create_by);
                        intent2.putExtra("account_type", type);

                        startActivity(intent2);
                        finish();
                        break;
                    default:
                        Toast.makeText(AccountListActivity.this, "Error!", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });

    }

    private void loadAccounts() {
        //String create_by = getIntent().getStringExtra("full_name");
        RetrofitService retrofitService = new RetrofitService();

        AccountApi accountApi = retrofitService.getRetrofit().create(AccountApi.class);

        accountApi.getAllAccounts().enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                populateListView(response.body());
            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable throwable) {
                Toast.makeText(AccountListActivity.this, "Failed to load accounts", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void loadClients() {
        RetrofitService retrofitService = new RetrofitService();
        AccountApi accountApi = retrofitService.getRetrofit().create(AccountApi.class);

        Integer type = 3;

        Account client= new Account();
        client.setType(type);

        HashMap<String,Integer> params = new HashMap<String, Integer>();
        params.put("type", type);

        accountApi.checkClients(params).enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                populateListView(response.body());
            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable throwable) {
                Toast.makeText(AccountListActivity.this, "Failed to load accounts", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadEmployees() {
        RetrofitService retrofitService = new RetrofitService();
        AccountApi accountApi = retrofitService.getRetrofit().create(AccountApi.class);

        Integer type = 2;

        Account client= new Account();
        client.setType(type);

        HashMap<String,Integer> params = new HashMap<String, Integer>();
        params.put("type", type);

        accountApi.checkEmployees(params).enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                //Toast.makeText(AccountListActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();

                populateListView(response.body());
            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable throwable) {
                Toast.makeText(AccountListActivity.this, "Failed to load accounts", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateListView( List<Account> accountList) {
        AccountAdapter accountAdapter = new AccountAdapter( accountList);
        recyclerView.setAdapter(accountAdapter);

    }


}