package com.example.deliverybox.Adapter;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deliverybox.EditAccountsActivity;
import com.example.deliverybox.EditShipmentActivity;
import com.example.deliverybox.Model.Shipment;
import com.example.deliverybox.R;

import java.util.List;

public class ShipmentAdapter extends RecyclerView.Adapter<ShipmentHolder> {

    private List<Shipment> shipmentList;

    public ShipmentAdapter(List<Shipment> shipmentList){
        this.shipmentList = shipmentList;
    }


    @NonNull
    @Override
    public ShipmentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_shipment_item, parent,false );
        return new ShipmentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShipmentHolder holder, int position) {
        Shipment shipment = shipmentList.get(position);
        holder.receiverName.setText(shipment.getFull_name_receiver().trim());
        holder.receiverPhone.setText(shipment.getPhone_number_receiver().trim());
        holder.receiverEmail.setText(shipment.getEmail_receiver().trim());
        holder.receiverCity.setText(shipment.getCity_receiver().trim());
        holder.receiverAddress.setText(shipment.getAddress_receiver().trim());

        holder.senderName.setText(shipment.getFull_name_sender().trim());
        holder.senderPhone.setText(shipment.getPhone_number_sender().trim());
        holder.senderEmail.setText(shipment.getEmail_sender().trim());
        holder.senderCity.setText(shipment.getCity_sender().trim());
        holder.senderAddress.setText(shipment.getAddress_sender().trim());
        holder.Description.setText(shipment.getDescription().trim());

    }

    @Override
    public int getItemCount() {
        return shipmentList.size();
    }
}
