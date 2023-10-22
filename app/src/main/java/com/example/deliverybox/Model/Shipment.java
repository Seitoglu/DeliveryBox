package com.example.deliverybox.Model;


public class Shipment {
    private int id_shipment;

    private String full_name_receiver;
    private String phone_number_receiver;
    private String email_receiver;
    private String country_receiver;
    private String city_receiver;
    private String address_receiver;

    private String full_name_sender;
    private String phone_number_sender;
    private String email_sender;
    private String country_sender;
    private String city_sender;
    private String address_sender;

    private String type;
    private Integer parts_count;
    private Double weight;
    private Double length;
    private Double width;
    private Double height;
    private String description;
    private Float cost;
    private Float price;
    private String payment_method;
    private String inspection;
    private String testing;
    private String choice;
    private Float courier_price;
    private Integer create_by;

    public Shipment() {

    }

    public int getId_shipment() {
        return id_shipment;
    }

    public void setId_shipment(int id_shipment) {
        this.id_shipment = id_shipment;
    }

    public String getFull_name_receiver() {
        return full_name_receiver;
    }

    public void setFull_name_receiver(String full_name_receiver) {
        this.full_name_receiver = full_name_receiver;
    }

    public String getPhone_number_receiver() {
        return phone_number_receiver;
    }

    public void setPhone_number_receiver(String phone_number_receiver) {
        this.phone_number_receiver = phone_number_receiver;
    }

    public String getEmail_receiver() {
        return email_receiver;
    }

    public void setEmail_receiver(String email_receiver) {
        this.email_receiver = email_receiver;
    }

    public String getCountry_receiver() {
        return country_receiver;
    }

    public void setCountry_receiver(String country_receiver) {
        this.country_receiver = country_receiver;
    }

    public String getCity_receiver() {
        return city_receiver;
    }

    public void setCity_receiver(String city_receiver) {
        this.city_receiver = city_receiver;
    }

    public String getAddress_receiver() {
        return address_receiver;
    }

    public void setAddress_receiver(String address_receiver) {
        this.address_receiver = address_receiver;
    }

    public String getFull_name_sender() {
        return full_name_sender;
    }

    public void setFull_name_sender(String full_name_sender) {
        this.full_name_sender = full_name_sender;
    }

    public String getPhone_number_sender() {
        return phone_number_sender;
    }

    public void setPhone_number_sender(String phone_number_sender) {
        this.phone_number_sender = phone_number_sender;
    }

    public String getEmail_sender() {
        return email_sender;
    }

    public void setEmail_sender(String email_sender) {
        this.email_sender = email_sender;
    }

    public String getCountry_sender() {
        return country_sender;
    }

    public void setCountry_sender(String country_sender) {
        this.country_sender = country_sender;
    }

    public String getCity_sender() {
        return city_sender;
    }

    public void setCity_sender(String city_sender) {
        this.city_sender = city_sender;
    }

    public String getAddress_sender() {
        return address_sender;
    }

    public void setAddress_sender(String address_sender) {
        this.address_sender = address_sender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getParts_count() {
        return parts_count;
    }

    public void setParts_count(Integer parts_count) {
        this.parts_count = parts_count;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getInspection() {
        return inspection;
    }

    public void setInspection(String inspection) {
        this.inspection = inspection;
    }

    public String getTesting() {
        return testing;
    }

    public void setTesting(String testing) {
        this.testing = testing;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public Float getCourier_price() {
        return courier_price;
    }

    public void setCourier_price(Float courier_price) {
        this.courier_price = courier_price;
    }

    public Integer getCreate_by() {
        return create_by;
    }

    public void setCreate_by(Integer create_by) {
        this.create_by = create_by;
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "id_shipment=" + id_shipment +
                ", full_name_receiver='" + full_name_receiver + '\'' +
                ", phone_number_receiver='" + phone_number_receiver + '\'' +
                ", email_receiver='" + email_receiver + '\'' +
                ", country_receiver='" + country_receiver + '\'' +
                ", city_receiver='" + city_receiver + '\'' +
                ", address_receiver='" + address_receiver + '\'' +
                ", full_name_sender='" + full_name_sender + '\'' +
                ", phone_number_sender='" + phone_number_sender + '\'' +
                ", email_sender='" + email_sender + '\'' +
                ", country_sender='" + country_sender + '\'' +
                ", city_sender='" + city_sender + '\'' +
                ", address_sender='" + address_sender + '\'' +
                ", type='" + type + '\'' +
                ", parts_count=" + parts_count +
                ", weight=" + weight +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", price=" + price +
                ", payment_method='" + payment_method + '\'' +
                ", inspection='" + inspection + '\'' +
                ", testing='" + testing + '\'' +
                ", choice='" + choice + '\'' +
                ", courier_price=" + courier_price +
                ", create_by='" + create_by + '\'' +
                '}';
    }
}
