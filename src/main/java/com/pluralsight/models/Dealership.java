package com.pluralsight.models;

import java.util.ArrayList;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;


    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max){
        ArrayList<Vehicle> result = new ArrayList<Vehicle>();

        //loop through vehicles and add any mathcing vehicle to result...
        for(Vehicle v : inventory){
            if(v.getPrice() >= min && v.getPrice() <= max){
                result.add(v);
            }
        }
        return result;
    }

    public ArrayList<Vehicle> getVehicleByMakeModel(String make, String model){

        ArrayList<Vehicle> result = new ArrayList<Vehicle>();
        for (Vehicle v: inventory){
            if (v.getMake().equalsIgnoreCase(make) && v.getModel().equalsIgnoreCase(model)){
                result.add(v);
            }
        }
        return result;
    }

    public ArrayList<Vehicle> getVehicleByYear(int min, int max){
        ArrayList<Vehicle> result = new ArrayList<Vehicle>();
        for (Vehicle v: inventory){
            if (v.getYear() >= min && v.getYear() <= max){
                result.add(v);
            }
        }
        return result;
    }

    public ArrayList<Vehicle> getVehicleByColor(String color){
        ArrayList<Vehicle> result = new ArrayList<Vehicle>();
        for (Vehicle v: inventory){
            if (v.getColor().equalsIgnoreCase(color)){
                result.add(v);
            }
        }
        return result;
    }

    public ArrayList<Vehicle> getVehicleByMileage(int min, int max){
        ArrayList<Vehicle> result = new ArrayList<Vehicle>();
        for (Vehicle v: inventory){
            if (v.getOdometer() >= min && v.getOdometer() <= max){
                result.add(v);
            }
        }
        return result;
    }

    public ArrayList<Vehicle> getVehicleByType(String vehicleType){
        ArrayList<Vehicle> result = new ArrayList<Vehicle>();
        for (Vehicle v: inventory){
            if (v.getVehicleType().equalsIgnoreCase(vehicleType)){
                result.add(v);
            }
        }
        return result;
    }

    public ArrayList<Vehicle> getAllVehicles(){
        ArrayList<Vehicle> result = new ArrayList<Vehicle>();
        result.addAll(inventory);
        return result;
    }

    public void addVehicle(Vehicle vehicle){
        this.inventory.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle){
        this.inventory.remove(vehicle);
    }

    public Vehicle findVehicleByVin(int vin) {
        for (Vehicle v: inventory) {
            if (v.getVin() == vin) {
                return v;
            }
        }

        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFormattedDealershipText() {
        return String.format("%s | %s | %s", this.name, this.address, this.phone);
    }
}
