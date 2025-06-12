package com.pluralsight.models;

public class SalesContract extends Contract {
    private double salesTax;
    private double recordingFee;
    private boolean finance;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean finance) {
        super(date, customerName, customerEmail, vehicleSold);
        this.recordingFee = 100;
        this.finance = finance;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        double price = getVehicleSold().getPrice();
        return (price < 1000) ? 295 : 495;
    }

    public boolean isFinance() {
        return finance;
    }

    public void setFinance(boolean finance) {
        this.finance = finance;
    }

    @Override
    public double getTotalPrice(){
        double basePrice = getVehicleSold().getPrice();
        salesTax  = basePrice * 0.05;
        return basePrice + salesTax + recordingFee +  getProcessingFee();
    }

    @Override
    public double getMonthlyPayment(){
        if (!finance) {
            return 0.0;
        }

        double basePrice = getVehicleSold().getPrice();
        double totalPrice = getTotalPrice();
        double interestRate;
        int termInMonths;

        if (basePrice >= 10000) {
            interestRate = 0.0425;
            termInMonths = 48;
        } else {
            interestRate = 0.0525;
            termInMonths = 24;
        }

        return super.calcLoanPayment(totalPrice, interestRate, termInMonths );

      //  double monthlyRate =  interestRate / 12;

     //   return (totalPrice * monthlyRate) / (1 - Math.pow(1  + monthlyRate, -termInMonths));
    }

    public String encodeSalesContract() {
        return "SALE|" +
                getDate() + "|" +
                getCustomerName() + "|" +
                getCustomerEmail() + "|" +
                getVehicleSold().getVin() + "|" +
                getVehicleSold().getYear() + "|" +
                getVehicleSold().getMake() + "|" +
                getVehicleSold().getModel() + "|" +
                getVehicleSold().getVehicleType() + "|" +
                getVehicleSold().getColor() + "|" +
                getVehicleSold().getOdometer() + "|" +
                getVehicleSold().getPrice() + "|" +
                getTotalPrice() + "|" +
                getRecordingFee() + "|" +
                getProcessingFee() + "|" +
                isFinance();
    }
}


