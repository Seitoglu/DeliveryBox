package com.example.deliverybox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.deliverybox.Helpers.StringHelper;
import com.example.deliverybox.Model.User;
import com.example.deliverybox.Retrofit.RetrofitService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    //private static final int MY_SOCKET_TIMEOUT_MS = 1000;
    private EditText Username, Password;
    private Button loginBtn;
    private TextView accCreate;
    private ImageView imageViewPassword, imageViewLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        loginBtn = findViewById(R.id.button1);
        accCreate = findViewById(R.id.textView);
        Username = findViewById(R.id.editText1);
        Password = findViewById(R.id.editText2);
        imageViewLogo = findViewById(R.id.imgViewLogo);
        imageViewLogo.setImageResource(R.drawable.ic_app_logo);

        accCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);

                String str = "registration";
                intent.putExtra("str", str);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateUser();
            }
        });

        imageViewPassword = findViewById(R.id.imgView1);
        imageViewPassword.setImageResource(R.drawable.ic_show_password);
        imageViewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Password.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
            Password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            imageViewPassword.setImageResource(R.drawable.ic_show_password);
                } else{
            Password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            imageViewPassword.setImageResource(R.drawable.ic_hide_password);
                }
            }
        });


    }
    public void authenticateUser(){
        if (!validateUsername() || !validatePassword()) {
            return;
        }

        String username = Username.getText().toString();
        String password = Password.getText().toString();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        //RetrofitService retrofitService = new RetrofitService();


        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        String url = "http://192.168.1.2:9000/user/login";
        //String url = "http://192.168.17.60:9000/user/login";


        Map<String, String> params = new HashMap<String, String>();
        params.put("email", username);
        params.put("password", password);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                //Toast.makeText(LoginActivity.this, response.toString(), Toast.LENGTH_SHORT).show();

                try {
                    String full_name = (String) response.get("full_name");
                    String account_email = (String) response.get("email");
                    String acc_password = (String) response.get("password");
                    String acc_address = (String) response.get("address");
                    String acc_phone = (String) response.get("phone_number");

                    Integer type = (Integer) response.get("type");

                    Integer accId = (Integer) response.get("id_account");
                    String create_by = accId.toString();

                    switch (type){
                        case 1:
                            Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                            intent.putExtra("full_name", full_name);
                            intent.putExtra("id_account", create_by);
                            intent.putExtra("account_type", type.toString());

                            startActivity(intent);
                            finish();
                            break;

                        case 2:
                            Intent intent1 = new Intent(LoginActivity.this, EmployeeActivity.class);
                            intent1.putExtra("full_name", full_name);
                            intent1.putExtra("id_account", create_by);
                            intent1.putExtra("account_type", type.toString());

                            startActivity(intent1);
                            finish();
                            break;

                        case 3:
                            Intent intent2 = new Intent(LoginActivity.this, WelcomeActivity.class);
                            intent2.putExtra("full_name", full_name);
                            intent2.putExtra("id_account", create_by);
                            intent2.putExtra("account_type", type.toString());

                            intent2.putExtra("email", account_email);
                            intent2.putExtra("password", acc_password);
                            intent2.putExtra("phone_number", acc_phone);
                            intent2.putExtra("address", acc_address);

                            startActivity(intent2);
                            finish();
                            break;
                        default:
                            Toast.makeText(LoginActivity.this, "Wrong Type!", Toast.LENGTH_LONG).show();
                            break;
                    }

                } catch (JSONException e) {
                    Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Грешен мейл или парола!", Toast.LENGTH_LONG).show();
            }
        });
        queue.add(jsonObjectRequest);

    }

    public boolean validatePassword() {
        String password = Password.getText().toString();
        if(password.isEmpty()){
            Password.setError("Моля въведете парола!");
            Password.requestFocus();
            return false;
        }
        else{
            Password.setError(null);
            return true;
        }
    }

    public boolean validateUsername() {
        String username = Username.getText().toString();
        if(TextUtils.isEmpty(username)){
            Username.setError("Моля въведете емайл");
            Username.requestFocus();
            return false;
        }
        else if(!StringHelper.regexValidationPattern(username)){
            Username.setError("Моля въведете валиден емайл");
            Username.requestFocus();
            return false;
        }
        else{
            Username.setError(null);
            return true;
        }
    }
}