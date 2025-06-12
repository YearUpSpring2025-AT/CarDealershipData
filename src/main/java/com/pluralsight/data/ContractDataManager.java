package com.pluralsight.data;

import com.pluralsight.models.Contract;
import com.pluralsight.models.LeaseContract;
import com.pluralsight.models.SalesContract;
import com.pluralsight.models.Vehicle;

import java.io.FileWriter;

public class ContractDataManager {

    public void saveContract(Contract contract){

        // Create a file writer inside a try with  resources for auto close
        try (FileWriter fw  =  new FileWriter("contracts.csv", true)) {
            // Check the type of contract using instanceof

            if (contract instanceof SalesContract){
                System.out.println("Saving a SalesContract...");

                // Cast it to SalesContract to access its method
                SalesContract salesContract = (SalesContract) contract;

                String encodedString = salesContract.encodeSalesContract();

                fw.write(encodedString + "\n");


            } else if (contract instanceof LeaseContract) {
                System.out.println("Saving a LeaseContract...");

                LeaseContract leaseContract = (LeaseContract) contract;

                String encodedString = leaseContract.encodeLeaseContract();
                fw.write(encodedString + "\n");
            }
        } catch (Exception e) {
            System.out.println("Error saving contract....");
        }
    }

    public static SalesContract getSaleContractFromEncodedString(String encodedString){
        String[] parts = encodedString.split("\\|");

        if (parts.length != 18) {
            return null;
        }
        //SALE|20210928|Dana Wyatt|dana@texas.com|10112|1993|Ford|Explorer|SUV|Red|525123|995.00|49.75|100.00|295.00|1439.75|NO|0.00
        //0    1        2          3               4    5     6   7        8   9   10     11

//LEASE|20210928|Zachary Westly|zach@texas.com|37846|2021|Chevrolet|Silverado|truck|Black|2750|31995.00|15997.50|2239.65|18337.15|541.39

        String contractType = "SALE";
        String date = parts[1].trim();
        String customerName = parts[2].trim();
        String customerEmail = parts[3].trim();
        int vehicleVin = Integer.parseInt(parts[4].trim());
        int year = Integer.parseInt(parts[5].trim());
        String make = parts[6].trim();
        String model = parts[7].trim();
        String vehicleType = parts[8].trim();
        String color = parts[9].trim();
        int odometer = Integer.parseInt(parts[10].trim());
        double price = Double.parseDouble(parts[11].trim());
//        double saleTax = Double.parseDouble(parts[12].trim());
//        double recordingFee = Double.parseDouble(parts[13].trim());
//        double processingFee = Double.parseDouble(parts[14].trim());
        boolean finance = Boolean.parseBoolean(parts[12].trim());
        Vehicle v = new Vehicle(vehicleVin, year, make, model, vehicleType, color, odometer, price);
        return new SalesContract(date, customerName, customerEmail, v, finance);
    }

    public static LeaseContract getLeaseContractFromEncodedString(String encodedString){
        String[] parts = encodedString.split("\\|");

        if (parts.length != 18) {
            return null;
        }


        //LEASE|20210928|Zachary Westly|zach@texas.com|37846|2021|Chevrolet|Silverado|truck|Black|2750|31995.00|15997.50|2239.65|18337.15|541.39
        //0         1        2            3               4    5     6         7        8     9    10       11     12      13      14       15
        String contractType = "LEASE";
        String date = parts[1].trim();
        String customerName = parts[2].trim();
        String customerEmail = parts[3].trim();
        int vehicleVin = Integer.parseInt(parts[4].trim());
        int year = Integer.parseInt(parts[5].trim());
        String make = parts[6].trim();
        String model = parts[7].trim();
        String vehicleType = parts[8].trim();
        String color = parts[9].trim();
        int odometer = Integer.parseInt(parts[10].trim());
        double price = Double.parseDouble(parts[11].trim());
        double expectedEndingValue = Double.parseDouble(parts[12].trim());
        double leaseFee = Double.parseDouble(parts[13].trim());
        double totalPayment = Double.parseDouble(parts[14].trim());
        double monthlyPayment = Double.parseDouble(parts[15].trim());
        Vehicle v = new Vehicle(vehicleVin, year, make, model, vehicleType, color, odometer, price);
        return new LeaseContract(date, customerName, customerEmail, v, expectedEndingValue, leaseFee, totalPayment, monthlyPayment);
    }
}
