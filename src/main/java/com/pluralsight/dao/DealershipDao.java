package com.pluralsight.dao;

import com.pluralsight.models.Dealership;
import com.pluralsight.models.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DealershipDao {

    private BasicDataSource dataSource;

    public DealershipDao(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Dealership getDealership(int dealershipId) {
        Dealership d = null;

        String dealershipSql = """
                SELECT
                *
                FROM
                dealerships
                WHERE
                dealership_id = ?
                """;
        try (Connection c = dataSource.getConnection();
             PreparedStatement s = c.prepareStatement(dealershipSql);
        ){
            s.setInt(1, dealershipId);

            try(ResultSet rs = s.executeQuery();){
                if (rs.next()) {
                    d = new Dealership(
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getString("phone")
                    );
                    List<Vehicle> vehicles = getVehiclesForDealership(dealershipId);

                    for (Vehicle v: vehicles) {
                        d.addVehicle(v);
                    }
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return d;
    }

    private List<Vehicle> getVehiclesForDealership(int dealershipId) {
        List<Vehicle> vehicles = new ArrayList<>();

        String sql = """
                SELECT v.*
                FROM vehicles v
                JOIN inventory i ON v.VIN = i.VIN
                WHERE i.dealership_id = ?
                """;
        try (Connection c = dataSource.getConnection();
             PreparedStatement s = c.prepareStatement(sql);
        ){
            s.setInt(1, dealershipId);

            try(ResultSet rs = s.executeQuery();){
                while (rs.next()) {
                    Vehicle vehicle = createVehicleFromResultSet(rs);
                    vehicles.add(vehicle);
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return vehicles;
    }

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
        );
    }
}
