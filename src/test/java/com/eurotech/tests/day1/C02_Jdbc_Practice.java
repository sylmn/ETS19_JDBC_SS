package com.eurotech.tests.day1;

import com.eurotech.utilities.ConfigurationReader;

import java.sql.*;

public class C02_Jdbc_Practice {
    public static void main(String[] args) throws SQLException {

        String dbUrl = ConfigurationReader.get("url");

        String dbUsername = ConfigurationReader.get("username");
        String dbPassword = ConfigurationReader.get("password");
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        Statement statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

        //get locationid, streetaddress, postcode of first row from location table
        ResultSet resultSet = statement.executeQuery("select * from locations;");
        resultSet.next();
        System.out.println("locationid = " + resultSet.getString(1));
        System.out.println("streetaddress = " + resultSet.getString(2));
        System.out.println("postcode = " + resultSet.getString(3));

        //get locationid, streetAddress, city (2nd row)
        System.out.println("--------2 nd row--------");
        resultSet.next();
        System.out.println("locationid = " + resultSet.getString(1));
        System.out.println("streetaddress = " + resultSet.getString(2));
        System.out.println("city = " + resultSet.getString(4));

        //get all info from 3rd row
        System.out.println("--------3 rd row--------");
        resultSet.next();
        System.out.println("locationid = " + resultSet.getString(1));
        System.out.println("streetaddress = " + resultSet.getString(2));
        System.out.println("postcode = " + resultSet.getString(3));
        System.out.println("city = " + resultSet.getString(4));
        System.out.println("region = " + resultSet.getString(5));
        System.out.println("country = " + resultSet.getString(6));

        //get all info from all row
        System.out.println("--------all info from all row--------");
        resultSet.first();
        /* 1.yöntem
        while (resultSet.next()) {
            System.out.println("locationid = " + resultSet.getString("locationid") + " | "
                    + "streetaddress = " + resultSet.getString("streetaddress") + " | "
                    + "postcode = " + resultSet.getString("postcode") + " | "
                    + "city = " + resultSet.getString("city") + " | "
                    + "region ="  + resultSet.getString("region") + " | "
                    + "country = " + resultSet.getString("country"));
        }
        */

        //2. yöntem
        while (resultSet.next()){
            for (int i = 1; i <=6 ; i++) {
                System.out.println(" |*| " + resultSet.getString(i));
            }
            System.out.println();
        }
    }
}
