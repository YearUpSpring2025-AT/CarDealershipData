package com.pluralsight;

import com.pluralsight.dao.DealershipDao;
import com.pluralsight.dao.VehicleDao;
import com.pluralsight.ui.UserInterface;
import org.apache.commons.dbcp2.BasicDataSource;

public class Main {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("You must run with three argument, <Username> <Password> <Url>");
            System.exit(1);
        }

        BasicDataSource basicDataSource = getBasicDataSourceFromArgs(args);
        DealershipDao dealershipDao = new DealershipDao(basicDataSource);
        VehicleDao vehicleDao = new VehicleDao(basicDataSource);
        UserInterface ui = new UserInterface(dealershipDao, vehicleDao);
        ui.display();
    }

    private static BasicDataSource getBasicDataSourceFromArgs(String[] args){
        String username = args[0];
        String password = args[1];
        String url = args[2];
        BasicDataSource bds = new BasicDataSource();
        bds.setUsername(username);
        bds.setPassword(password);
        bds.setUrl(url);
        return bds;
    }


}