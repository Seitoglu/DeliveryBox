package com.example.deliverybox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminActivity extends AppCompatActivity {

    private TextView tvName;
    private Button checkAcc, checkClients, checkEmployees, createShipment, createAcc, editAcc, checkShipment, extBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        this.setTitle("ADMIN PANEL");

        tvName = findViewById(R.id.textViewAdminName);
        createShipment = findViewById(R.id.btnCreateShipment1);
        createAcc = findViewById(R.id.btnCreateAccount1);
        editAcc = findViewById(R.id.btnEditAccount);
        checkAcc = findViewById(R.id.btnCheckAccount1);
        checkClients = findViewById(R.id.btnCheckClient1);
        checkEmployees = findViewById(R.id.btnCheckEmployee1);
        checkShipment = findViewById(R.id.btnCheckShipment1);
        extBtn = findViewById(R.id.btnAdminExit);
        extBtn.setBackgroundColor(Color.RED);

        String full_name= getIntent().getStringExtra("full_name");
        String create_by = getIntent().getStringExtra("id_account");
        String type = getIntent().getStringExtra("account_type");

        tvName.setText(full_name);
        extBtn.setBackgroundColor(Color.RED);


        editAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, AccountListActivityAdmin.class);
                intent.putExtra("full_name", full_name);
                intent.putExtra("id_account", create_by);
                intent.putExtra("account_type", type);

                String str = "all";
                intent.putExtra("str", str);
                startActivity(intent);
                finish();
            }
        });

        checkClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, AccountListActivity.class);
                intent.putExtra("full_name", full_name);
                intent.putExtra("id_account", create_by);
                intent.putExtra("account_type", type);

                String str = "clients";
                intent.putExtra("str", str);
                startActivity(intent);
                finish();
            }
        });

        checkEmployees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, AccountListActivity.class);
                intent.putExtra("full_name", full_name);
                intent.putExtra("id_account", create_by);
                intent.putExtra("account_type", type);

                String str = "employees";
                intent.putExtra("str", str);
                startActivity(intent);
                finish();
            }
        });

        checkAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, AccountListActivity.class);
                intent.putExtra("full_name", full_name);
                intent.putExtra("id_account", create_by);
                intent.putExtra("account_type", type);

                String str = "all";
                intent.putExtra("str", str);
                startActivity(intent);
                finish();
            }
        });

        checkShipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, ShipmentListActivity.class);
                intent.putExtra("full_name", full_name);
                intent.putExtra("id_account", create_by);
                intent.putExtra("account_type", type);
                startActivity(intent);
                finish();
            }
        });

        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, CreateAccountActivity.class);
                String str = "creating1";

                intent.putExtra("str", str);
                intent.putExtra("full_name", full_name);
                intent.putExtra("id_account", create_by);
                intent.putExtra("account_type", type);
                startActivity(intent);
                finish();
            }
        });

        createShipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, CreateShipmentActivity.class);
                intent.putExtra("full_name", full_name);
                intent.putExtra("id_account", create_by);
                intent.putExtra("account_type", type);

                startActivity(intent);
                finish();
            }
        });
        
        extBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                signUserOut();
            }
        });
    }

    public void signUserOut(){
        tvName.setText(null);
        Intent intent = new Intent(AdminActivity.this, LoginActivity.class);
        intent.putExtra("logout", "logout successful");
        startActivity(intent);
        finish();
    }
}