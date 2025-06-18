package com.pluralsight.dao;

import com.pluralsight.models.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private BasicDataSource dataSource;
    private int dealershipId = 1;

    public VehicleDao(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Example: Search by price range
    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {
        List<Vehicle> vehicles = new ArrayList<>();

        // SQL query with placeholders (? marks)
        String sql = "SELECT * FROM vehicles WHERE price BETWEEN ? AND ? AND SOLD = FALSE";

        try (Connection c = dataSource.getConnection();
             PreparedStatement s = c.prepareStatement(sql);
             ResultSet resultSet = s.executeQuery();
        ) {

            // Set parameters (replace ? marks)
            s.setDouble(1, minPrice);  // First ? becomes minPrice
            s.setDouble(2, maxPrice);  // Second ? becomes maxPrice



            // Convert each row to Vehicle object
            while (resultSet.next()) {
                s.setDouble(1, minPrice);  // First ? becomes minPrice
                s.setDouble(2, maxPrice);
                Vehicle vehicle = createVehicleFromResultSet(resultSet);
                vehicles.add(vehicle);
            }

        } catch (SQLException e) {
            System.err.println("Error searching vehicles by price: " + e.getMessage());
        }

        return vehicles;
    }

    // Example: Search by make and model
    public List<Vehicle> searchByMakeModel(String make, String model) {
        List<Vehicle> vehicles = new ArrayList<>();

        String sql = ""; // What should this query be?

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // statement.setString(?, ?);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Vehicle vehicle = createVehicleFromResultSet(resultSet);
                vehicles.add(vehicle);
            }

        } catch (SQLException e) {
            System.err.println("Error searching vehicles by make/model: " + e.getMessage());
        }

        return vehicles;
    }

    // Helper method to convert ResultSet row to Vehicle object
    private Vehicle createVehicleFromResultSet(ResultSet rs) throws SQLException {
        return new Vehicle(
                rs.getInt("VIN"),
                rs.getInt("year"),
                rs.getString("make"),
                rs.getString("model"),
                rs.getString("type"),
                rs.getString("color"),
                rs.getInt("odometer"),
                rs.getDouble("price")
//                rs.getBoolean("SOLD")
        );
    }

    public List<Vehicle> searchByYearRange(int minYear, int maxYear) {
        return new ArrayList<>();
    }

    public List<Vehicle> searchByColor(String color) {
        return new ArrayList<>();
    }

    public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage) {
        return new ArrayList<>();
    }

    public List<Vehicle> searchByType(String type) {
        return new ArrayList<>();
    }

    public List<Vehicle> getAllAvailableVehicles(int dealershipId) {
        // Get all vehicles where SOLD = FALSE
        return new ArrayList<>();
    }


    public void addVehicle(Vehicle vehicle, int dealershipId){
        //go into the database, create a vehicle record, then create an inventory record.

    }
}
