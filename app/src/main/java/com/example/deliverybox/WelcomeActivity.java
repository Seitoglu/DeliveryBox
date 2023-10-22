package com.example.deliverybox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deliverybox.Model.Account;

public class WelcomeActivity extends AppCompatActivity {

    private TextView tvName;
    private Button createShipment, checkAcc, checkShipment, extBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();
        //this.setTitle(" ");

        tvName = findViewById(R.id.textViewName);
        createShipment = findViewById(R.id.btnCreateShipment);
        checkShipment = findViewById(R.id.btnCheckShipment);
        checkAcc = findViewById(R.id.btnEditClientAccount);
        extBtn = findViewById(R.id.extBtn_welcome);

        String full_name= getIntent().getStringExtra("full_name");
        String create_by = getIntent().getStringExtra("id_account");
        String type = getIntent().getStringExtra("account_type");

        String email = getIntent().getStringExtra("email");
        String password = getIntent().getStringExtra("password");
        String phone = getIntent().getStringExtra("phone_number");
        String address = getIntent().getStringExtra("address");

        tvName.setText(full_name);
        extBtn.setBackgroundColor(Color.RED);

        checkAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, EditAccountsActivity.class);
                String editor = "3";
                intent.putExtra("full_name", full_name);
                intent.putExtra("id_account", create_by);
                intent.putExtra("account_type", type);

                intent.putExtra("email", email);
                intent.putExtra("password", password);
                intent.putExtra("phone_number", phone);
                intent.putExtra("address", address);
                intent.putExtra("account_editor", editor);
                startActivity(intent);
                finish();

            }
        });

        createShipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, CreateShipmentActivity.class);
                intent.putExtra("full_name", full_name);
                intent.putExtra("id_account", create_by);
                intent.putExtra("account_type", type);
                startActivity(intent);
            }
        });

        extBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUserOut();
            }
        });

        checkShipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, ShipmentListActivity.class);
                intent.putExtra("full_name", full_name);
                intent.putExtra("id_account", create_by);
                intent.putExtra("account_type", type);
                startActivity(intent);
                //Toast.makeText(WelcomeActivity.this, create_by, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void signUserOut(){
        tvName.setText(null);
        Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
        intent.putExtra("logout", "logout successful");
        startActivity(intent);
        finish();
    }
}