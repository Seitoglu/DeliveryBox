package com.example.deliverybox;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import android.widget.Toast;

import com.example.deliverybox.Helpers.StringHelper;
import com.example.deliverybox.Model.Shipment;
import com.example.deliverybox.Retrofit.RetrofitService;
import com.example.deliverybox.Retrofit.ShipmentApi;

import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateShipmentActivity extends AppCompatActivity {

    private Spinner receiverCountry, senderCountry, shipmentType, paymentSpinner;
    private EditText receiverName, receiverPhone, receiverEmail, receiverCity, receiverAddress1, receiverAddress2, receiverAddress3,
            senderName, senderPhone, senderEmail, senderCity, senderAddress1, senderAddress2, senderAddress3,
            partsCount, shipmentWeight, shipmentLength, shipmentWidth, shipmentHeight, Description, Cost, Price, CourierPrice;

    private String full_name_receiver, phone_number_receiver, email_receiver, country_receiver, city_receiver, address_receiver,
            full_name_sender, phone_number_sender, email_sender, country_sender, city_sender, address_sender, type, description, payment_method;

    private CheckBox pregled, testvane, izbor;
    private Button exitbtn, createbtn, checkMoneyBtn;

    private Integer parts_count, shipmentPrice;
    private Double  weight, length, width, height;
    private Float  cost, price, courier_price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_shipment);
        this.setTitle("СЪЗДАВАНЕ НА ПРАТКА");

        receiverName = findViewById(R.id.imePoluchatel);
        receiverPhone = findViewById(R.id.telPoluchatel);
        receiverEmail = findViewById(R.id.emailPoluchatel);
        receiverCountry = findViewById(R.id.spinnerCountryPoluchatel);
        receiverCity = findViewById(R.id.gradPoluchatel);
        receiverAddress1 = findViewById(R.id.kvPoluchatel);
        receiverAddress2 = findViewById(R.id.ulPoluchatel);
        receiverAddress3 = findViewById(R.id.nmrPoluchatel);

        senderName = findViewById(R.id.imePodatel);
        senderPhone = findViewById(R.id.telPodatel);
        senderEmail = findViewById(R.id.emailPodatel);
        senderCountry = findViewById(R.id.spinnerCountryPodatel);
        senderCity = findViewById(R.id.gradPodatel);
        senderAddress1 = findViewById(R.id.kvPodatel);
        senderAddress2 = findViewById(R.id.ulPodatel);
        senderAddress3 = findViewById(R.id.nmrPodatel);

        shipmentType = findViewById(R.id.spinnerTipPratka);
        partsCount = findViewById(R.id.brChasti);
        shipmentWeight = findViewById(R.id.editTextTeglo);
        shipmentLength = findViewById(R.id.editTextLength);
        shipmentWidth = findViewById(R.id.editTextWidth);
        shipmentHeight = findViewById(R.id.editTextHeight);
        Description = findViewById(R.id.etDescription);
        Cost = findViewById(R.id.etStoinost);
        Price = findViewById(R.id.etCena);
        paymentSpinner = findViewById(R.id.spinnerVidPlashtane);
        pregled = findViewById(R.id.boxPregled);
        testvane = findViewById(R.id.boxTest);
        izbor = findViewById(R.id.boxIzbor);
        CourierPrice = findViewById(R.id.editTextCourierPrice);


        exitbtn = findViewById(R.id.backBtn);
        createbtn = findViewById(R.id.createShipment);
        checkMoneyBtn = findViewById(R.id.uslugaBtn);
        checkMoneyBtn.setBackgroundColor(Color.GREEN);
        exitbtn.setBackgroundColor(Color.RED);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,R.array.countriesArray, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        receiverCountry.setAdapter(spinnerAdapter);
        senderCountry.setAdapter(spinnerAdapter);

        ArrayAdapter<CharSequence> spinnerAdapter2 = ArrayAdapter.createFromResource(this, R.array.shipmentType, android.R.layout.simple_spinner_item);
        spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        shipmentType.setAdapter(spinnerAdapter2);

        ArrayAdapter<CharSequence> spinnerAdapter3 = ArrayAdapter.createFromResource(this, R.array.paymentType, android.R.layout.simple_spinner_item);
        spinnerAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_item);
        paymentSpinner.setAdapter(spinnerAdapter3);



        checkMoneyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                length = Double.parseDouble(shipmentLength.getText().toString());
                width = Double.parseDouble(shipmentWidth.getText().toString());
                height = Double.parseDouble(shipmentHeight.getText().toString());
                EditText CourierPrice = findViewById(R.id.editTextCourierPrice);
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
        });

        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shipmentFormFields();
                //Toast.makeText(CreateShipmentActivity.this, full_name, Toast.LENGTH_SHORT).show();

            }
        });

        exitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String full_name= getIntent().getStringExtra("full_name");
                String create_by = getIntent().getStringExtra("id_account");
                String type = getIntent().getStringExtra("account_type");

                Integer account_type = Integer.valueOf(type);


                switch (account_type){
                    case 1:
                        Intent intent = new Intent(CreateShipmentActivity.this, AdminActivity.class);
                        intent.putExtra("full_name", full_name);
                        intent.putExtra("id_account", create_by);
                        intent.putExtra("account_type", type);

                        startActivity(intent);
                        finish();
                        break;

                    case 2:
                        Intent intent1 = new Intent(CreateShipmentActivity.this, EmployeeActivity.class);
                        intent1.putExtra("full_name", full_name);
                        intent1.putExtra("id_account", create_by);
                        intent1.putExtra("account_type", type);

                        startActivity(intent1);
                        finish();
                        break;

                    case 3:
                        Intent intent2 = new Intent(CreateShipmentActivity.this, WelcomeActivity.class);
                        intent2.putExtra("full_name", full_name);
                        intent2.putExtra("id_account", create_by);
                        intent2.putExtra("account_type", type);

                        startActivity(intent2);
                        finish();
                        break;
                    default:
                        Toast.makeText(CreateShipmentActivity.this, "Error!", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }

    public void shipmentFormFields(){

        if (!validateEmail() || !validatePhone() || !validateFullName() || !validateCountryCityAddress()) {
            return;
        }

        RetrofitService retrofitService = new RetrofitService();
        ShipmentApi shipmentApi = retrofitService.getRetrofit().create(ShipmentApi.class);


        full_name_receiver = receiverName.getText().toString().trim();
        phone_number_receiver = receiverPhone.getText().toString().trim();
        email_receiver = receiverEmail.getText().toString().trim();
        country_receiver = receiverCountry.getSelectedItem().toString().trim();
        city_receiver = receiverCity.getText().toString().trim();
        String receiverAddress = "кв. " +  receiverAddress1.getText().toString().trim() + " ул. " + receiverAddress2.getText().toString().trim() + " №: " + receiverAddress3.getText().toString().trim();
        address_receiver = receiverAddress;

        full_name_sender = senderName.getText().toString().trim();
        phone_number_sender  = senderPhone.getText().toString().trim();
        email_sender  = senderEmail.getText().toString().trim();
        country_sender  = senderCountry.getSelectedItem().toString().trim();
        city_sender  = senderCity.getText().toString().trim();
        String senderAddress = "кв. " + senderAddress1.getText().toString().trim() + " ул. " + senderAddress2.getText().toString().trim() + " №: " + senderAddress3.getText().toString().trim();
        address_sender  = senderAddress;

        type = shipmentType.getSelectedItem().toString().trim();
        parts_count = Integer.parseInt(partsCount.getText().toString().trim());
        weight = Double.parseDouble(shipmentWeight.getText().toString().trim());
        length = Double.parseDouble(shipmentLength.getText().toString().trim());
        width = Double.parseDouble(shipmentWidth.getText().toString().trim());
        height = Double.parseDouble(shipmentHeight.getText().toString().trim());
        description = Description.getText().toString().trim();
        cost = Float.parseFloat(Cost.getText().toString().trim());
        price = Float.parseFloat(Price.getText().toString().trim());
        payment_method = paymentSpinner.getSelectedItem().toString().trim();
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


        shipmentApi.save(shipment).enqueue(new Callback<Shipment>() {
            @Override
            public void onResponse(Call<Shipment> call, Response<Shipment> response) {
                Toast.makeText(CreateShipmentActivity.this, "Пратката е създадена!", Toast.LENGTH_SHORT).show();

                receiverName.setText(null);
                receiverEmail.setText(null);
                receiverPhone.setText(null);
                receiverCity.setText(null);
                receiverAddress1.setText(null);
                receiverAddress2.setText(null);
                receiverAddress3.setText(null);

                senderName.setText(null);
                senderEmail.setText(null);
                senderPhone.setText(null);
                senderCity.setText(null);
                senderAddress1.setText(null);
                senderAddress2.setText(null);
                senderAddress3.setText(null);

                partsCount.setText(null);
                shipmentWeight.setText(null);
                shipmentLength.setText(null);
                shipmentWidth.setText(null);
                shipmentHeight.setText(null);
                Description.setText(null);
                Cost.setText(null);
                Price.setText(null);
                CourierPrice.setText(null);
            }

            @Override
            public void onFailure(Call<Shipment> call, Throwable t) {
                Toast.makeText(CreateShipmentActivity.this, "Неуспешно запазване!!", Toast.LENGTH_SHORT).show();
            }
        });


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

    }

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
                senderAddress3.requestFocus();

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