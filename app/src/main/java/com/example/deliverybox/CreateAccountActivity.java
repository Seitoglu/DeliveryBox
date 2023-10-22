package com.example.deliverybox;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.deliverybox.Helpers.StringHelper;

import java.util.HashMap;
import java.util.Map;



public class CreateAccountActivity extends AppCompatActivity {

    private EditText Email, Password, ConfirmPass, Name, Address, Phone;

    private String email, pass, confirmPass, name, address, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        String str= getIntent().getStringExtra("str");
        String full_name= getIntent().getStringExtra("full_name");
        String create_by = getIntent().getStringExtra("id_account");
        String type = getIntent().getStringExtra("account_type");

        switch (str){
            case "creating1":
            case "creating2":
                this.setTitle("Създаване на акаунт");
                break;
            case "registration":
                this.setTitle("Регистриране в системата");
                break;
        }
        //this.setTitle("Регистриране в системата");

        ImageView imageViewCreateAcc = findViewById(R.id.imgViewCreateAcc);

        imageViewCreateAcc.setImageResource(R.drawable.ic_icon_createacc);
        Email = findViewById(R.id.editTextEmail);
        Password = findViewById(R.id.editTextPass);
        ConfirmPass = findViewById(R.id.editTextConfirmPass);
        Name = findViewById(R.id.editTextFullName);
        Address = findViewById(R.id.editTextAddress);
        Phone = findViewById(R.id.editTextPhone);
        Button createAcc = findViewById(R.id.btnCreateAccount);
        Button exit = findViewById(R.id.btnExitAccount);

        email = Email.getText().toString();
        pass = Password.getText().toString();
        confirmPass = ConfirmPass.getText().toString();
        name = Name.getText().toString();
        address = Address.getText().toString();
        phone = Phone.getText().toString();

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (str){
                    case "creating1":
                        Intent intent = new Intent(CreateAccountActivity.this, AdminActivity.class);
                        startActivity(intent);
                        intent.putExtra("full_name", full_name);
                        intent.putExtra("id_account", create_by);
                        intent.putExtra("account_type", type);

                        startActivity(intent);
                        finish();
                        break;
                    case "creating2":
                        Intent intent1 = new Intent(CreateAccountActivity.this, EmployeeActivity.class);
                        intent1.putExtra("full_name", full_name);
                        intent1.putExtra("id_account", create_by);
                        intent1.putExtra("account_type", type);
                        startActivity(intent1);
                        finish();
                        break;
                    case "registration":
                        Intent intent2 = new Intent(CreateAccountActivity.this, LoginActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                }

            }
        });
        createAcc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                processFormFields();
            }
        });
    }

    public void processFormFields(){

        if (!validateEmail() || !validatePasswordAndConfirm() || !validateName() || !validateAddress() || !validatePhone()) {

            return;
        }

        RequestQueue queue = Volley.newRequestQueue(CreateAccountActivity.this);
        String url = "http://192.168.1.2:9000/user/register";
        //String url = "http://192.168.17.60:9000/user/register";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                    Toast.makeText(CreateAccountActivity.this, "Успешна регистрация!", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CreateAccountActivity.this, "Възникна грешка!", Toast.LENGTH_SHORT).show();

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Integer acctype = 3;

                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", pass);
                params.put("full_name", name);
                params.put("address", address);
                params.put("phone_number", phone);
                params.put("type", acctype.toString());
                return params;
            }
        };
        queue.add(stringRequest);

        Email.setText(null);
        Password.setText(null);
        ConfirmPass.setText(null);
        Name.setText(null);
        Address.setText(null);
        Phone.setText(null);
    }

    public  boolean validatePhone(){
        phone = Phone.getText().toString();

        if(TextUtils.isEmpty(phone)){
            Phone.setError("Моля въведете телефонен номер!");
            Phone.requestFocus();
            return false;
        }
        else if(!StringHelper.regexValidationPattern(phone)){
            Phone.setError("Моля въведете валиден номер:\n 0881234567");
            Phone.requestFocus();
            return false;
        }
        else{
            Phone.setError(null);
            return true;
        }
    }

    public boolean validateAddress(){
        address = Address.getText().toString();

        if(TextUtils.isEmpty(address)){
            Address.setError("Моля въведете адрес!");
            Address.requestFocus();
            return false;
        }
        else{
            Address.setError(null);
            return true;
        }
    }

    public boolean validateName(){
        name = Name.getText().toString();

        if(TextUtils.isEmpty(name)){
            Name.setError("Моля въведете имена!");
            Name.requestFocus();
            return false;
        }
        else{
            Name.setError(null);
            return true;
        }
    }

    public boolean validatePasswordAndConfirm() {
        pass = Password.getText().toString();
        confirmPass = ConfirmPass.getText().toString();
        if(pass.isEmpty()){
            Password.setError("Моля въведете парола!");
            Password.requestFocus();
            return false;
        }
        else if(!StringHelper.regexValidationPattern(pass)){
            Password.setError("Паролата е твърде слаба!");
            Password.requestFocus();
            return false;
        }
        else if(confirmPass.isEmpty()){
            ConfirmPass.setError("Моля попълнете парола!");
            ConfirmPass.requestFocus();
            Password.setError(null);
            return false;
        }
        else if(!pass.equals(confirmPass)){
            Password.setError("Паролите трябва да съвпадат!");
            Password.requestFocus();
            ConfirmPass.setError("Паролите трябва да съвпадат !");
            ConfirmPass.requestFocus();
            return false;
        }
        else{
            Password.setError(null);
            ConfirmPass.setError(null);
            return true;
        }
    }

    public boolean validateEmail() {
        email = Email.getText().toString();
        if(TextUtils.isEmpty(email)){
            Email.setError("Моля въведете мейл адрес!");
            Email.requestFocus();
            return false;
        }
        else if(!StringHelper.regexValidationPattern(email)){
            Email.setError("Моля въведете валиден мейл адрес!");
            Email.requestFocus();
            return false;
        }
        else{
            Email.setError(null);
            return true;
        }
    }
}