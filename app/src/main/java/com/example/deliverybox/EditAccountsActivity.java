package com.example.deliverybox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deliverybox.Model.Account;
import com.example.deliverybox.Retrofit.AccountApi;
import com.example.deliverybox.Retrofit.RetrofitService;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAccountsActivity extends AppCompatActivity {
    private EditText Id_Account,  Email, Password, Name, Address, Phone, Type;
    private Button editData, updateAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_accounts);

        this.setTitle("Редактиране на Акаунт");

        //Id_Account = findViewById(R.id.et_id_account);
        Email = findViewById(R.id.et_account_email);
        Password = findViewById(R.id.et_account_password);
        Name = findViewById(R.id.et_account_full_name);
        Address = findViewById(R.id.et_account_address);
        Phone = findViewById(R.id.et_account_phone_number);
        Type = findViewById(R.id.et_account_type);

        editData = findViewById(R.id.btnEditAccData);
        updateAcc = findViewById(R.id.btnUpdateAccount);


        //String str= getIntent().getStringExtra("str");
        //String full_name= getIntent().getStringExtra("full_name");
        //String create_by = getIntent().getStringExtra("id_account");
        //String type = getIntent().getStringExtra("account_type");

        String id_account = getIntent().getStringExtra("id_account");
        String email = getIntent().getStringExtra("email");
        String password = getIntent().getStringExtra("password");
        String name = getIntent().getStringExtra("full_name");
        String address = getIntent().getStringExtra("address");
        String phone = getIntent().getStringExtra("phone_number");
        String accType = getIntent().getStringExtra("account_type");
        String editor = getIntent().getStringExtra("account_editor");

        //Id_Account.setText(id_account);
        Email.setText(email);
        Password.setText(password);
        Name.setText(name);
        Address.setText(address);
        Phone.setText(phone);
        Type.setText(accType);

        //Id_Account.setEnabled(false);
        Email.setEnabled(false);
        Password.setEnabled(false);
        Name.setEnabled(false);
        Address.setEnabled(false);
        Phone.setEnabled(false);
        Type.setEnabled(false);

        editData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer i =Integer.valueOf(editor);

                if(i == 1){
                    //Id_Account.setEnabled(true);
                    Email.setEnabled(true);
                    Password.setEnabled(true);
                    Name.setEnabled(true);
                    Address.setEnabled(true);
                    Phone.setEnabled(true);
                    Type.setEnabled(true);
                }else if (i == 2 || i == 3){
                    Email.setEnabled(true);
                    Password.setEnabled(true);
                    Name.setEnabled(true);
                    Address.setEnabled(true);
                    Phone.setEnabled(true);
                }
/*
                switch (accType){
                    case "1":
                        //Id_Account.setEnabled(true);
                        Email.setEnabled(true);
                        Password.setEnabled(true);
                        Name.setEnabled(true);
                        Address.setEnabled(true);
                        Phone.setEnabled(true);
                        Type.setEnabled(true);
                        break;
                    case "2":
                    case "3":
                        Email.setEnabled(true);
                        Password.setEnabled(true);
                        Name.setEnabled(true);
                        Address.setEnabled(true);
                        Phone.setEnabled(true);
                        break;
                } */
            }
        });

        updateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RetrofitService retrofitService = new RetrofitService();
                AccountApi accountApi = retrofitService.getRetrofit().create(AccountApi.class);

                Integer newId_Account = Integer.valueOf(id_account);
                String newEmail = Email.getText().toString();
                String newPassword = Password.getText().toString();
                String newName = Name.getText().toString();
                String newAddress = Address.getText().toString();
                String newPhone = Phone.getText().toString();
                Integer newType = Integer.valueOf(Type.getText().toString());

                Account account = new Account();

                account.setId_account(newId_Account);
                account.setEmail(newEmail);
                account.setPassword(newPassword);
                account.setFull_name(newName);
                account.setAddress(newAddress);
                account.setPhone_number(newPhone);
                account.setType(newType);


                Call<Account> accountCall = accountApi.updateAccount(newId_Account, account);
                //Toast.makeText(EditAccountsActivity.this, account.toString(), Toast.LENGTH_SHORT).show();

                accountCall.enqueue(new Callback<Account>() {
                    @Override
                    public void onResponse(Call<Account> call, Response<Account> response) {

                        if (response.isSuccessful()) {
                            Toast.makeText(EditAccountsActivity.this, "Успешно Запазване!", Toast.LENGTH_SHORT).show();
                        } else{
                            Toast.makeText(EditAccountsActivity.this, "Възникна грешка!", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Account> call, Throwable throwable) {
                        Toast.makeText(EditAccountsActivity.this, "Възникна грешка", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });




    }
}