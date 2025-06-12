package com.pluralsight.models;

public abstract class Contract {
    private String date;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;
    private double totalPrice;
    private double monthlyPayment;

    public Contract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    protected double calcLoanPayment(double amountBorrowed, double yearlyInterestRate, double loanLengthInMonths){
        double monthlyInterestRate = yearlyInterestRate / 12;

        return  (amountBorrowed * monthlyInterestRate) / ( 1 - Math.pow( 1 + monthlyInterestRate, -loanLengthInMonths));
    }



    public abstract double getTotalPrice();
    public abstract double getMonthlyPayment();
}
