package com.example.deliverybox.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deliverybox.Model.Account;
import com.example.deliverybox.R;

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountHolder> {
    private List<Account> accountList;


    public AccountAdapter(List<Account> accountList) {
        this.accountList = accountList;
    }



    @NonNull
    @Override
    public AccountHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_account_item, parent,false );
        return new AccountHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull AccountHolder holder, int position) {
        Account account = accountList.get(position);

        holder.accID.setText(String.valueOf(account.getId_account()));
        holder.email.setText(account.getEmail());
        holder.password.setText(account.getPassword());
        holder.name.setText(account.getFull_name());
        holder.address.setText(account.getAddress());
        holder.phone.setText(account.getPhone_number());
        holder.type.setText(String.valueOf(account.getType()));

/*
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), EditAccountsActivity.class);
                intent.putExtra("id_account", String.valueOf(account.getId_account()));
                intent.putExtra("email", account.getEmail());
                intent.putExtra("password", account.getPassword());
                intent.putExtra("name", account.getFull_name());
                intent.putExtra("address", account.getAddress());
                intent.putExtra("phone", account.getPhone_number());
                intent.putExtra("type", String.valueOf(account.getType()));

                view.getContext().startActivity(intent);
            }
        }); */



    }
    @Override
    public int getItemCount() {
        return accountList.size();
    }

}
