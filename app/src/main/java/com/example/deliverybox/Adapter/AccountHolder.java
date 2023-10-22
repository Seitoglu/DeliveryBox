package com.example.deliverybox.Adapter;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deliverybox.Model.Account;
import com.example.deliverybox.R;

public class AccountHolder extends RecyclerView.ViewHolder {

    TextView accID, name, password, email, address, phone, type;

    public AccountHolder(@NonNull View itemView) {
        super(itemView);
        
        accID = itemView.findViewById(R.id.textViewAccID);
        email = itemView.findViewById(R.id.TextViewAccEmail);
        password = itemView.findViewById(R.id.TextViewAccPassword);
        name = itemView.findViewById(R.id.TextViewAccPersonName);
        address = itemView.findViewById(R.id.TextViewAccAddress);
        phone = itemView.findViewById(R.id.TextViewAccPhone);
        type = itemView.findViewById(R.id.TextViewAccType);


    }

    public void setOnClickListener(View.OnClickListener listener){
        this.itemView.setOnClickListener(listener);
    }

}


