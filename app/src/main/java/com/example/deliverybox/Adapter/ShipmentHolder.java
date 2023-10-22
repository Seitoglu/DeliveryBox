package com.example.deliverybox.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deliverybox.R;

public class ShipmentHolder extends RecyclerView.ViewHolder {

    TextView receiverName, receiverPhone, receiverEmail, receiverCity, receiverAddress,
            senderName, senderPhone, senderEmail, senderCity, senderAddress, Description;

    public ShipmentHolder(@NonNull View itemView) {
        super(itemView);
        receiverName = itemView.findViewById(R.id.receiverName);
        receiverPhone = itemView.findViewById(R.id.receiverPhone);
        receiverEmail = itemView.findViewById(R.id.receiverEmail);
        receiverCity = itemView.findViewById(R.id.receiverCity);
        receiverAddress = itemView.findViewById(R.id.receiverAddress);

        senderName = itemView.findViewById(R.id.senderName);
        senderPhone = itemView.findViewById(R.id.senderPhone);
        senderEmail = itemView.findViewById(R.id.senderEmail);
        senderCity = itemView.findViewById(R.id.senderCity);
        senderAddress = itemView.findViewById(R.id.senderAddress);
        Description = itemView.findViewById(R.id.tvOpisanie);

    }


    public void setOnClickListener(View.OnClickListener listener){
        this.itemView.setOnClickListener(listener);
    }
}
