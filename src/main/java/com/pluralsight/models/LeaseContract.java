package com.pluralsight.models;

public class LeaseContract  extends Contract {
    private double expectedEndingValue;
    private double leaseFee;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);

        // Calculating values based on the vehicle price
        double vehiclePrice = vehicleSold.getPrice();
        this.expectedEndingValue = vehiclePrice * 0.5;
        this.leaseFee = vehiclePrice * 0.07;
    }
    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, double expectedEndingValue, double leaseFee, double totalPrice, double monthlyPayment) {
        super(date, customerName, customerEmail, vehicleSold);

        // Calculating values based on the vehicle price
        double vehiclePrice = vehicleSold.getPrice();
        this.expectedEndingValue = vehiclePrice * 0.5;
        this.leaseFee = leaseFee;
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }


    public double getLeaseFee() {
        // The total lease cost is just the lease fee since we only lease, not purchase
        return leaseFee;
    }


    @Override
    public double getTotalPrice() {

        return leaseFee;
    }

    @Override
    public double getMonthlyPayment() {

        double vehiclePrice = getVehicleSold().getPrice();
        double leaseAmount = vehiclePrice - expectedEndingValue + leaseFee;

        // Loan parameters
        int termInMonths = 36;
        double interestRate = 0.04;

        // Monthly payment calculation using loan formula
        double monthlyPayment = (leaseAmount * (interestRate / 12)) / (1 - Math.pow(1 + (interestRate / 12), -termInMonths));
        return monthlyPayment;
    }

    public String encodeLeaseContract() {
        return "LEASE|" +
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
                getExpectedEndingValue() + "|" +
                getLeaseFee() + "|" +
                getTotalPrice() + "|" +
                getMonthlyPayment();
    }

}
