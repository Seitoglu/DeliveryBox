package com.example.deliverybox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EmployeeActivity extends AppCompatActivity {

    private TextView tvName;
    private Button  createShipment, checkShipment, checkAcc, editAcc, createAcc, checkEmployees, checkClients, extBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        this.setTitle("EMPLOYEE PANEL");

        tvName = findViewById(R.id.textViewEmployeeName);
        createShipment = findViewById(R.id.btnCreateShipment2);
        createAcc = findViewById(R.id.btnCreateAccount2);
        checkAcc = findViewById(R.id.btnCheckAccount2);
        checkClients = findViewById(R.id.btnCheckClient2);
        checkEmployees = findViewById(R.id.btnCheckEmployee2);
        checkShipment = findViewById(R.id.btnCheckShipment2);
        editAcc = findViewById(R.id.btnCheckEmployeeAccount);
        extBtn = findViewById(R.id.btnEmployeeExit);

        String full_name= getIntent().getStringExtra("full_name");
        String create_by = getIntent().getStringExtra("id_account");
        String type = getIntent().getStringExtra("account_type");

        String email = getIntent().getStringExtra("email");
        String password = getIntent().getStringExtra("password");
        String phone = getIntent().getStringExtra("phone_number");
        String address = getIntent().getStringExtra("address");

        tvName.setText(full_name);
        extBtn.setBackgroundColor(Color.RED);

        editAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeActivity.this, EditAccountsActivity.class);
                String editor = "2";
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
        checkClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeActivity.this, AccountListActivity.class);
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
                Intent intent = new Intent(EmployeeActivity.this, AccountListActivity.class);
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
                Intent intent = new Intent(EmployeeActivity.this, AccountListActivity.class);
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
                Intent intent = new Intent(EmployeeActivity.this, ShipmentListActivity.class);
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
                Intent intent = new Intent(EmployeeActivity.this, CreateAccountActivity.class);
                String str = "creating2";

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
                Intent intent = new Intent(EmployeeActivity.this, CreateShipmentActivity.class);
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
        Intent intent = new Intent(EmployeeActivity.this, LoginActivity.class);
        intent.putExtra("logout", "logout successful");
        startActivity(intent);
        finish();
    }
}