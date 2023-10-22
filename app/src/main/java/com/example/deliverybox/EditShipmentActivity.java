package com.example.deliverybox;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.deliverybox.Helpers.StringHelper;
import com.example.deliverybox.Model.Shipment;
import com.example.deliverybox.Retrofit.RetrofitService;
import com.example.deliverybox.Retrofit.ShipmentApi;

import java.text.DecimalFormat;

public class EditShipmentActivity extends AppCompatActivity {


    private Spinner  shipmentType;
    private EditText receiverName, receiverPhone, receiverEmail, receiverCity, receiverAddress, receiverCountry,
            senderName, senderPhone, senderEmail, senderCity, senderAddress,  senderCountry,
            partsCount, shipmentWeight, shipmentLength, shipmentWidth, shipmentHeight, Description, Cost, Price, CourierPrice, paymentType;

    private String full_name_receiver, phone_number_receiver, email_receiver, country_receiver, city_receiver, address_receiver,
            full_name_sender, phone_number_sender, email_sender, country_sender, city_sender, address_sender, type, description, payment_method;

    private CheckBox pregled, testvane, izbor;
    private Button editShipmentBtn, updateShipmentBtn, checkMoneyBtn;

    private Integer parts_count, shipmentPrice;
    private Double  weight, length, width, height;
    private Float  cost, price, courier_price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_shipment);
        this.setTitle("СЪЗДАВАНЕ НА ПРАТКА");

        receiverName = findViewById(R.id.updatedReceiverName);
        receiverPhone = findViewById(R.id.updatedReceiverPhone);
        receiverEmail = findViewById(R.id.updatedReceiverEmail);
        receiverCountry = findViewById(R.id.updatedReceiverCountry);
        receiverCity = findViewById(R.id.updatedReceiverCity);
        receiverAddress = findViewById(R.id.updatedReceiverAddress);
        //receiverAddress2 = findViewById(R.id.updatedUlPoluchatel);
        //receiverAddress3 = findViewById(R.id.updatedNmrPoluchatel);

        String ReceiverName = getIntent().getStringExtra("receiver_name");
        String ReceiverPhone = getIntent().getStringExtra("receiver_phone");
        String ReceiverEmail = getIntent().getStringExtra("receiver_email");
        String ReceiverCountry = getIntent().getStringExtra("receiver_country");
        String ReceiverCity = getIntent().getStringExtra("receiver_city");
        String ReceiverAddress = getIntent().getStringExtra("receiver_address");

        receiverName.setText(ReceiverName);
        receiverPhone.setText(ReceiverPhone);
        receiverEmail.setText(ReceiverEmail);
        receiverCountry.setText(ReceiverCountry);
        receiverCity.setText(ReceiverCity);
        receiverAddress.setText(ReceiverAddress);

        senderName = findViewById(R.id.updatedSenderName);
        senderPhone = findViewById(R.id.updatedSenderPhone);
        senderEmail = findViewById(R.id.updatedSenderEmail);
        senderCountry = findViewById(R.id.updatedSenderCountry);
        senderCity = findViewById(R.id.updatedSenderCity);
        senderAddress = findViewById(R.id.updatedSenderAddress);
        //senderAddress2 = findViewById(R.id.updatedUlPodatel);
        //senderAddress3 = findViewById(R.id.updatedNmrPodatel);

        String SenderName = getIntent().getStringExtra("sender_name");
        String SenderPhone = getIntent().getStringExtra("sender_phone");
        String SenderEmail = getIntent().getStringExtra("sender_email");
        String SenderCountry = getIntent().getStringExtra("sender_country");
        String SenderCity = getIntent().getStringExtra("sender_city");
        String SenderAddress = getIntent().getStringExtra("sender_address");

        senderName.setText(SenderName);
        senderPhone.setText(SenderPhone);
        senderEmail.setText(SenderEmail);
        senderCountry.setText(SenderCountry);
        senderCity.setText(SenderCity);
        senderAddress.setText(SenderAddress);

        //shipmentType = findViewById(R.id.updatedSpinnerTipPratka);
        partsCount = findViewById(R.id.updatedBrChasti);
        shipmentWeight = findViewById(R.id.updatedEditTextTeglo);
        shipmentLength = findViewById(R.id.updatedEditTextLength);
        shipmentWidth = findViewById(R.id.updatedEditTextWidth);
        shipmentHeight = findViewById(R.id.updatedEditTextHeight);
        Description = findViewById(R.id.updatedEtDescription);

        //String ShipmentType = getIntent().getStringExtra("shipment_type");
        String ShipmentPartsCount = getIntent().getStringExtra("shipment_count");
        String ShipmentWeight = getIntent().getStringExtra("shipment_weight");
        String ShipmentLength = getIntent().getStringExtra("shipment_length");
        String ShipmentWidth = getIntent().getStringExtra("shipment_width");
        String ShipmentHeight = getIntent().getStringExtra("shipment_height");
        String ShipmentDescription = getIntent().getStringExtra("shipment_description");


        partsCount.setText(ShipmentPartsCount);
        shipmentWeight.setText(ShipmentWeight);
        shipmentLength.setText(ShipmentLength);
        shipmentWidth.setText(ShipmentWidth);
        shipmentHeight.setText(ShipmentHeight);
        Description.setText(ShipmentDescription);



        Cost = findViewById(R.id.updatedEtStoinost);
        Price = findViewById(R.id.updatedEtCena);
        paymentType = findViewById(R.id.updatedShipmentPaymentType);
        //pregled = findViewById(R.id.updatedBoxPregled);
        //testvane = findViewById(R.id.updatedBoxTest);
        //izbor = findViewById(R.id.updatedBoxIzbor);
        CourierPrice = findViewById(R.id.updatedEditTextCourierPrice);

        String ShipmentCost = getIntent().getStringExtra("shipment_cost");
        String ShipmentPrice = getIntent().getStringExtra("shipment_price");
        String ShipmentPaymentType = getIntent().getStringExtra("shipment_payment");
        String ShipmentInspection= getIntent().getStringExtra("shipment_inspection");
        String ShipmentTesting = getIntent().getStringExtra("shipment_test");
        String ShipmentChoice = getIntent().getStringExtra("shipment_choice");
        String ShipmentCourierPrice = getIntent().getStringExtra("shipment_courierPrice");

        Cost.setText(ShipmentCost);
        Price.setText(ShipmentPrice);;
        paymentType.setText(ShipmentPaymentType);
        //pregled.setText(ShipmentInspection);
        //testvane.setText(ShipmentTesting);
        //izbor.setText(ShipmentChoice);
        //CourierPrice.setText(ShipmentCourierPrice);

        receiverName.setEnabled(false);
        receiverPhone.setEnabled(false);
        receiverEmail.setEnabled(false);
        receiverCountry.setEnabled(false);
        receiverCity.setEnabled(false);
        receiverAddress.setEnabled(false);

        senderName.setEnabled(false);
        senderPhone.setEnabled(false);
        senderEmail.setEnabled(false);
        senderCountry.setEnabled(false);
        senderCity.setEnabled(false);
        senderAddress.setEnabled(false);

        partsCount.setEnabled(false);
        shipmentWeight.setEnabled(false);
        shipmentLength.setEnabled(false);
        shipmentWidth.setEnabled(false);
        shipmentHeight.setEnabled(false);
        Description.setEnabled(false);

        Cost.setEnabled(false);
        Price.setEnabled(false);
        paymentType.setEnabled(false);
        //pregled.setEnabled(false);
        //testvane.setEnabled(false);
        //izbor.setEnabled(false);
        CourierPrice.setEnabled(false);



        updateShipmentBtn = findViewById(R.id.updateShipmentBtn);
        editShipmentBtn = findViewById(R.id.editShipmentBtn);
        checkMoneyBtn = findViewById(R.id.uslugaBtn2);
        checkMoneyBtn.setBackgroundColor(Color.GREEN);
        /*ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,R.array.countriesArray, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        receiverCountry.setAdapter(spinnerAdapter);
        senderCountry.setAdapter(spinnerAdapter);

        ArrayAdapter<CharSequence> spinnerAdapter2 = ArrayAdapter.createFromResource(this, R.array.shipmentType, android.R.layout.simple_spinner_item);
        spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        shipmentType.setAdapter(spinnerAdapter2);

        ArrayAdapter<CharSequence> spinnerAdapter3 = ArrayAdapter.createFromResource(this, R.array.paymentType, android.R.layout.simple_spinner_item);
        spinnerAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_item);
        paymentSpinner.setAdapter(spinnerAdapter3); */
/*
        editShipmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                receiverName.setEnabled(true);
                receiverPhone.setEnabled(true);
                receiverEmail.setEnabled(true);
                receiverCountry.setEnabled(true);
                receiverCity.setEnabled(true);
                receiverAddress.setEnabled(true);

                senderName.setEnabled(true);
                senderPhone.setEnabled(true);
                senderEmail.setEnabled(true);
                senderCountry.setEnabled(true);
                senderCity.setEnabled(true);
                senderAddress.setEnabled(true);

                partsCount.setEnabled(true);
                shipmentWeight.setEnabled(true);
                shipmentLength.setEnabled(true);
                shipmentWidth.setEnabled(true);
                shipmentHeight.setEnabled(true);
                Description.setEnabled(true);

                Cost.setEnabled(true);
                Price.setEnabled(true);
                paymentType.setEnabled(true);
                //pregled.setEnabled(false);
                //testvane.setEnabled(false);
                //izbor.setEnabled(false);
                CourierPrice.setEnabled(true);

            }
        });
        checkMoneyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                length = Double.parseDouble(shipmentLength.getText().toString());
                width = Double.parseDouble(shipmentWidth.getText().toString());
                height = Double.parseDouble(shipmentHeight.getText().toString());
                EditText CourierPrice = findViewById(R.id.updatedEditTextCourierPrice);
                validateCena();

                if (length > 0.9 || width > 0.9 || height > 0.9) {
                    shipmentLength.setError(null);
                    shipmentWidth.setError(null);
                    shipmentHeight.setError(null);
                }
            }
        });

        pregled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(pregled.isChecked()){
                    testvane.setVisibility(View.VISIBLE);
                    izbor.setVisibility(View.VISIBLE);
                }
            }
        }); */
    }

    public void shipmentFormFields(){

        //if (!validateEmail() || !validatePhone() || !validateFullName() || !validateCountryCityAddress()) {
        //    return;
        //}

        RetrofitService retrofitService = new RetrofitService();
        ShipmentApi shipmentApi = retrofitService.getRetrofit().create(ShipmentApi.class);


        full_name_receiver = receiverName.getText().toString().trim();
        phone_number_receiver = receiverPhone.getText().toString().trim();
        email_receiver = receiverEmail.getText().toString().trim();
        //country_receiver = receiverCountry.getSelectedItem().toString().trim();
        city_receiver = receiverCity.getText().toString().trim();
        //String receiverAddress = "кв. " +  receiverAddress1.getText().toString().trim() + " ул. " + receiverAddress2.getText().toString().trim() + " №: " + receiverAddress3.getText().toString().trim();
        //address_receiver = receiverAddress;

        full_name_sender = senderName.getText().toString().trim();
        phone_number_sender  = senderPhone.getText().toString().trim();
        email_sender  = senderEmail.getText().toString().trim();
        //country_sender  = senderCountry.getSelectedItem().toString().trim();
        city_sender  = senderCity.getText().toString().trim();
        //String senderAddress = "кв. " + senderAddress1.getText().toString().trim() + " ул. " + senderAddress2.getText().toString().trim() + " №: " + senderAddress3.getText().toString().trim();
        //address_sender  = senderAddress;

        type = shipmentType.getSelectedItem().toString().trim();
        parts_count = Integer.parseInt(partsCount.getText().toString().trim());
        weight = Double.parseDouble(shipmentWeight.getText().toString().trim());
        length = Double.parseDouble(shipmentLength.getText().toString().trim());
        width = Double.parseDouble(shipmentWidth.getText().toString().trim());
        height = Double.parseDouble(shipmentHeight.getText().toString().trim());
        description = Description.getText().toString().trim();
        cost = Float.parseFloat(Cost.getText().toString().trim());
        price = Float.parseFloat(Price.getText().toString().trim());
        //payment_method = paymentSpinner.getSelectedItem().toString().trim();
        courier_price = Float.parseFloat(CourierPrice.getText().toString().trim());

        shipmentLength = findViewById(R.id.editTextLength);
        shipmentWidth = findViewById(R.id.editTextWidth);
        shipmentHeight = findViewById(R.id.editTextHeight);

        Shipment shipment = new Shipment();
        shipment.setFull_name_receiver(full_name_receiver);
        shipment.setPhone_number_receiver(phone_number_receiver);
        shipment.setEmail_receiver(email_receiver);
        shipment.setCountry_receiver(country_receiver);
        shipment.setCity_receiver(city_receiver);
        shipment.setAddress_receiver(address_receiver);
        shipment.setLength(length);
        shipment.setWidth(width);
        shipment.setHeight(height);
        shipment.setFull_name_sender(full_name_sender);
        shipment.setPhone_number_sender(phone_number_sender);
        shipment.setEmail_sender(email_sender);
        shipment.setCountry_sender(country_sender);
        shipment.setCity_sender(city_sender);
        shipment.setAddress_sender(address_sender);

        shipment.setType(type);
        shipment.setParts_count(parts_count);
        shipment.setWeight(weight);

        shipment.setDescription(description);
        shipment.setCost(cost);
        shipment.setPrice(price);
        shipment.setPayment_method(payment_method);
        shipment.setCourier_price(courier_price);

        String create_by = getIntent().getStringExtra("id_account");

        Integer create_byy = Integer.valueOf(create_by);

        //String str = create_byy.toString();
        //Toast.makeText(CreateShipmentActivity.this, str, Toast.LENGTH_SHORT).show();
        shipment.setCreate_by(create_byy);


        if(pregled.isChecked()){
            shipment.setInspection("DA");
        }
        else if(!pregled.isChecked()){
            shipment.setInspection("NE");
        }

        if(testvane.isChecked()){
            shipment.setTesting("DA");
        }
        else if(!testvane.isChecked()){
            shipment.setTesting("NE");
        }

        if(izbor.isChecked()){
            shipment.setChoice("DA");
        }
        else if(!izbor.isChecked()){
            shipment.setChoice("NE");
        }

    }
    public boolean validateFullName(){

        full_name_receiver = receiverName.getText().toString().trim();
        full_name_sender = senderName.getText().toString().trim();

        if(TextUtils.isEmpty(full_name_receiver)){
            receiverName.setError("Моля попълнете имена!");
            receiverName.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(full_name_sender)){
            senderName.setError("Моля попълнете имена!");
            senderName.requestFocus();
            return false;
        }
        else{
            receiverName.setError(null);
            senderName.setError(null);
            return true;
        }
    }

    public  boolean validatePhone(){

        phone_number_receiver = receiverPhone.getText().toString().trim();
        phone_number_sender = senderPhone.getText().toString().trim();

        if(TextUtils.isEmpty(phone_number_receiver)){
            receiverPhone.setError("Моля попълнете телефон!");
            receiverPhone.requestFocus();
            return false;
        }
        else if(!StringHelper.regexValidationPattern(phone_number_receiver)){
            receiverPhone.setError("Моля попълнете валиден телефон:\n 0881234567");
            receiverPhone.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(phone_number_sender)){
            senderPhone.setError("Моля попълнете телефон!");
            senderPhone.requestFocus();
            return false;
        }
        else if(!StringHelper.regexValidationPattern(phone_number_sender)){
            senderPhone.setError("Моля попълнете валиден телефон:\n 0881234567");
            senderPhone.requestFocus();
            return false;
        }
        else{
            receiverPhone.setError(null);
            senderPhone.setError(null);
            return true;
        }
    }

    public boolean validateEmail() {
        email_receiver = receiverEmail.getText().toString().trim();
        email_sender = senderEmail.getText().toString().trim();

        if(TextUtils.isEmpty(email_receiver)){
            receiverEmail.setError("Моля попълнете мейл адрес!");
            receiverEmail.requestFocus();
            return false;
        }
        else if(!StringHelper.regexValidationPattern(email_receiver)){
            receiverEmail.setError("Моля попълнете валиден мейл адрес!");
            receiverEmail.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(email_sender)){
            senderEmail.setError("Моля попълнете мейл адрес!");
            senderEmail.requestFocus();
            return false;
        }
        else if(!StringHelper.regexValidationPattern(email_sender)){
            senderEmail.setError("Моля попълнете валиден емайл адрес!");
            senderEmail.requestFocus();
            return false;
        }
        else{
            receiverEmail.setError(null);
            senderEmail.setError(null);
            return true;
        }
    }
/*
    public boolean validateCountryCityAddress(){
        country_receiver = receiverCountry.getSelectedItem().toString().trim();
        country_sender = senderCountry.getSelectedItem().toString().trim();
        city_receiver = receiverCity.getText().toString().trim();
        city_sender = senderCity.getText().toString().trim();
        String receiverAdr1 = receiverAddress1.getText().toString().trim();
        String receiverAdr2 = receiverAddress2.getText().toString().trim();
        String receiverAdr3 = receiverAddress3.getText().toString().trim();
        String senderAdr1 = senderAddress1.getText().toString().trim();
        String senderAdr2 = senderAddress2.getText().toString().trim();
        String senderAdr3 = senderAddress3.getText().toString().trim();

        if(country_receiver.trim().equals("Избери")){
            TextView errorTv = (TextView) receiverCountry.getSelectedView();
            errorTv.setError("");
            errorTv.setTextColor(Color.RED);
            errorTv.setText("Моля изберете страна!");
            receiverCountry.requestFocus();
            //Toast.makeText(CreateShipmentActivity.this, "Моля изберете страна на получател!", Toast.LENGTH_SHORT).show();
            return false;
        }

        else if(TextUtils.isEmpty(city_receiver)){
            receiverCity.setError("Моля попълнете населено място!");
            receiverCity.requestFocus();
            return false;
        }

        else if(TextUtils.isEmpty(receiverAdr1)){
            receiverAddress1.setError("Моля попълнете квартал/местност!");
            receiverAddress1.requestFocus();
            return false;
        }
        else if(TextUtils.isEmpty(receiverAdr2)){
            receiverAddress2.setError("Моля попълнете улица!");
            receiverAddress2.requestFocus();
            return false;
        }
        else if(TextUtils.isEmpty(receiverAdr3)){
            receiverAddress3.setError("Моля попълнете номер!");
            receiverAddress3.requestFocus();
            return false;
        }

        else if(country_sender.trim().equals("Избери")){
            TextView errorTv = (TextView) receiverCountry.getSelectedView();
            errorTv.setError("");
            errorTv.setTextColor(Color.RED);
            errorTv.setText("Моля изберете страна!");
            senderCountry.requestFocus();
            //Toast.makeText(CreateShipmentActivity.this, "Моля изберете страна на подател!", Toast.LENGTH_SHORT).show();
            return false;
        }

        else if(TextUtils.isEmpty(city_sender)){
            senderCity.setError("Моля попълнете населено място!");
            senderCity.requestFocus();
            return false;
        }

        else if(TextUtils.isEmpty(senderAdr1)){
            senderAddress1.setError("Моля попълнете квартал/местност!");
            senderAddress1.requestFocus();
            return false;
        }
        else if(TextUtils.isEmpty(senderAdr2)){
            senderAddress2.setError("Моля попълнете улица!");
            senderAddress2.requestFocus();
            return false;
        }
        else if(TextUtils.isEmpty(senderAdr3)){
            senderAddress3.setError("Моля попълнете номер!");
            senderAddress3.requestFocus();
            return false;
        }

        else {
            receiverCity.setError(null);
            receiverCity.setError(null);

            receiverAddress1.setError(null);
            receiverAddress2.setError(null);
            receiverAddress3.setError(null);

            senderAddress1.setError(null);
            senderAddress2.setError(null);
            senderAddress3.setError(null);
            return true;
        }

    } */

    public boolean validateCena() {

        type = shipmentType.getSelectedItem().toString().trim();
        Cost = findViewById(R.id.etStoinost);
        Price = findViewById(R.id.etCena);
        EditText CourierPrice = findViewById(R.id.editTextCourierPrice);
        length = Double.parseDouble(shipmentLength.getText().toString().trim());
        width = Double.parseDouble(shipmentWidth.getText().toString().trim());;
        height = Double.parseDouble(shipmentHeight.getText().toString().trim());
        if (length == 0 || width == 0 || height == 0) {

            switch (type.trim()) {
                case "Избери":
                    TextView errorTv = (TextView) shipmentType.getSelectedView();
                    errorTv.setError("");
                    errorTv.setTextColor(Color.RED);
                    errorTv.setText("Моля изберете вид!");
                    //senderAddress3.requestFocus();

                    if (Cost.getText().toString().isEmpty() || Price.getText().toString().isEmpty()) {
                        Cost.setError("Моля попълнете стойност!");
                        Cost.requestFocus();
                        Price.setError("Моля попълнете цена!");
                        Price.requestFocus();
                        return false;
                    }
                    break;

                case "Документална":
                    shipmentPrice = 3;
                    if (Cost.getText().toString().isEmpty() || Price.getText().toString().isEmpty()) {
                        Cost.setError("Моля попълнете стойност!");
                        Cost.requestFocus();
                        Price.setError("Моля попълнете цена!");
                        Price.requestFocus();
                        return false;
                    } else {
                        Float stoinost = Float.parseFloat(Cost.getText().toString().trim());
                        Float cena = Float.parseFloat(Price.getText().toString().trim());
                        courier_price = shipmentPrice + (stoinost * (1.0f / 100.0f) + (cena * (1.0f / 100.0f)));

                        DecimalFormat decimalFormat = new DecimalFormat("0.00");
                        String formattedValue = decimalFormat.format(courier_price);
                        CourierPrice.setText(formattedValue);

                    }
                    break;

                case "Пощенска (до 5кг)":
                    shipmentPrice = 5;
                    if (Cost.getText().toString().isEmpty() || Price.getText().toString().isEmpty()) {
                        Cost.setError("Моля попълнете стойност!");
                        Cost.requestFocus();
                        Price.setError("Моля попълнете цена!");
                        Price.requestFocus();
                        return false;
                    } else {
                        Float stoinost = Float.parseFloat(Cost.getText().toString().trim());
                        Float cena = Float.parseFloat(Price.getText().toString().trim());
                        courier_price = shipmentPrice + (stoinost * (1.0f / 100.0f) + (cena * (1.0f / 100.0f)));
                        DecimalFormat decimalFormat = new DecimalFormat("0.00");
                        String formattedValue = decimalFormat.format(courier_price);
                        CourierPrice.setText(formattedValue);
                    }
                    break;

                case "Куриерска (до 20кг)":
                    shipmentPrice = 10;
                    if (Cost.getText().toString().isEmpty() || Price.getText().toString().isEmpty()) {
                        Cost.setError("Моля попълнете стойност!");
                        Cost.requestFocus();
                        Price.setError("Моля попълнете цена!");
                        Price.requestFocus();
                        return false;
                    } else {
                        Float stoinost = Float.parseFloat(Cost.getText().toString().trim());
                        Float cena = Float.parseFloat(Price.getText().toString().trim());
                        courier_price = shipmentPrice + (stoinost * (1.0f / 100.0f) + (cena * (1.0f / 100.0f)));
                        DecimalFormat decimalFormat = new DecimalFormat("0.00");
                        String formattedValue = decimalFormat.format(courier_price);
                        CourierPrice.setText(formattedValue);
                    }
                    break;

                case "Карго (до 50кг)":
                    shipmentPrice = 20;
                    if (Cost.getText().toString().isEmpty() || Price.getText().toString().isEmpty()) {
                        Cost.setError("Моля попълнете стойност!");
                        Cost.requestFocus();
                        Price.setError("Моля попълнете цена!");
                        Price.requestFocus();
                        return false;
                    } else {
                        Float stoinost = Float.parseFloat(Cost.getText().toString().trim());
                        Float cena = Float.parseFloat(Price.getText().toString().trim());
                        courier_price = shipmentPrice + (stoinost * (1.0f / 100.0f) + (cena * (1.0f / 100.0f)));
                        DecimalFormat decimalFormat = new DecimalFormat("0.00");
                        String formattedValue = decimalFormat.format(courier_price);
                        CourierPrice.setText(formattedValue);
                    }
                    break;

                case "Палетна (над 50кг)":
                    shipmentPrice = 30;
                    if (Cost.getText().toString().isEmpty() || Price.getText().toString().isEmpty()) {
                        Cost.setError("Моля попълнете стойност!");
                        Cost.requestFocus();
                        Price.setError("Моля попълнете цена!");
                        Price.requestFocus();
                        return false;
                    } else {
                        Float stoinost = Float.parseFloat(Cost.getText().toString().trim());
                        Float cena = Float.parseFloat(Price.getText().toString().trim());
                        courier_price = shipmentPrice + (stoinost * (1.0f / 100.0f) + (cena * (1.0f / 100.0f)));
                        DecimalFormat decimalFormat = new DecimalFormat("0.00");
                        String formattedValue = decimalFormat.format(courier_price);
                        CourierPrice.setText(formattedValue);
                    }
                    break;
            }
        }
        else {
            validateCena2();
        }
        return true;
    }

    public void validateCena2() {
        length = Double.parseDouble(shipmentLength.getText().toString().trim());
        width = Double.parseDouble(shipmentWidth.getText().toString().trim());
        height = Double.parseDouble(shipmentHeight.getText().toString().trim());
        EditText CourierPrice = findViewById(R.id.editTextCourierPrice);

        if (length > 0.9 || width > 0.9 || height > 0.9){
            Double max1, max2;
            if (length > width && length > height) {
                max1 = length;
            } else if (width > height) {
                max1 = width;
            } else {
                max1 = height;
            }

            if (length > width && length < height || length > height && length < width) {
                max2 = length;
            } else if (width > length && width < height || width > height && width < length) {
                max2 = width;
            } else {
                max2 = height;
            }
            Double d = (((max1 * max2) / 0.96f) / 2f) * 50.48f;
            String s = String.format("%.2f", d);
            courier_price = Float.parseFloat(s.toString());
            CourierPrice.setText(courier_price.toString());
        }
        else{
            shipmentLength.setError("Поне една от страните трябва да е над 0.90м");
            shipmentWidth.setError("Поне една от страните трябва да е над 0.90м");
            shipmentHeight.setError("Поне една от страните трябва да е над 0.90м");
            shipmentLength.requestFocus();
            shipmentWidth.requestFocus();
            shipmentHeight.requestFocus();
        }

    }

}