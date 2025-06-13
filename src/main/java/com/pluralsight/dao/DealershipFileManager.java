package com.pluralsight.dao;

import com.pluralsight.models.Dealership;
import com.pluralsight.models.Vehicle;

import java.io.*;

public class DealershipFileManager {

    private String fileName;

    public DealershipFileManager(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Dealership getDealership(){

        //create a dealership
        //create a bunch of vehicles.
        // add each vehicle to the dealership

        // return the fully constructed dealership object for use in the program.


        FileReader fr = null;
        Dealership d = null;
        try {
            fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            String firstLine = br.readLine();
            d = getDealershipFromEncodedString(firstLine);

            String line;

            while ((line = br.readLine()) != null){
                d.addVehicle(getVehicleFromEncodedString(line));
            }

            br.close();
        } catch (IOException e) {
            System.out.println("Error reading file...");
        }
        return d;
    }

    public static Vehicle getVehicleFromEncodedString(String encodedString){
        String[] parts = encodedString.split("\\|");

        if (parts.length != 8) {
            return null;
        }

        int vin = Integer.parseInt(parts[0].trim());
        int year = Integer.parseInt(parts[1].trim());
        String make = parts[2].trim();
        String model = parts[3].trim();
        String vehicleType =  parts[4].trim();
        String color = parts[5].trim();
        int odometer = Integer.parseInt(parts[6].trim());
        double price = Double.parseDouble(parts[7].trim().replace("$", "").replace(",", ""));
        return new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
    }

    public static Dealership getDealershipFromEncodedString(String encodedString){
        String[] parts = encodedString.split("\\|");

        if (parts.length != 3) {
            return null;
        }

        String name = parts[0].trim();
        String address = parts[1].trim();
        String phone = parts[2].trim();
        return new Dealership(name, address, phone);
    }

    public void saveDealership(Dealership dealership){

        // Open the file for writing
        FileWriter fw = null;
        try {
            fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);

            // Write all the vehicles
            bw.write(dealership.getFormattedDealershipText());
            bw.newLine();

            // Write all the vehicles
            for (Vehicle v : dealership.getAllVehicles()){
                bw.write(v.getFormattedVehicleText());
                bw.newLine();
            }

            bw.close();
        } catch (IOException e) {
            System.out.println("Error saving dealership information.");
        }

    }
}
