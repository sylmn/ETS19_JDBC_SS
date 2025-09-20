package com.eurotech.tests.day1;

import com.eurotech.utilities.ConfigurationReader;

import java.sql.*;

public class C01_Jdbc_Intro {
    public static void main(String[] args) throws SQLException {

        //postgres url ==>jdbc:postgresql://[Hostname]:[Port]/[database]
        String dbUrl = ConfigurationReader.get("url");

        //username
        String dbUsername = ConfigurationReader.get("username");

        //password
        String dbPassword = ConfigurationReader.get("password");

        //create connection to DB
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        //baglantiyi kontrol edelim
        System.out.println("connection.getMetaData().getDatabaseProductName() = " + connection.getMetaData().getDatabaseProductName());

        //create statement object
        Statement statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE, //degisikliklere acik degil
                ResultSet.CONCUR_READ_ONLY                    //sadece data okuma yetkisi
        );

        //run query,get result
        ResultSet resultSet = statement.executeQuery("select * from employees;");

        //next() --> bir pointer, ilk satira indirir

        //move pointer to first row
        resultSet.next();

        System.out.println("-------------reading from db-------------");

        //get information by column name
        System.out.println("resultSet.getString(\"employeeid\") = " + resultSet.getString("employeeid"));
        System.out.println("resultSet.getString(\"firstname\") = " + resultSet.getString("firstname"));
        System.out.println("resultSet.getString(\"lastname\") = " + resultSet.getString("lastname"));
        System.out.println("resultSet.getString(\"email\") = " + resultSet.getString("email"));
        System.out.println("resultSet.getString(\"phonenumber\") = " + resultSet.getString("phonenumber"));
        System.out.println("resultSet.getString(\"jobid\") = " + resultSet.getString("jobid"));
        System.out.println("resultSet.getString(\"salary\") = " + resultSet.getString("salary"));
        System.out.println("resultSet.getString(\"managerid\") = " + resultSet.getString("managerid"));
        System.out.println("resultSet.getString(\"departmentid\") = " + resultSet.getString("departmentid"));
        System.out.println("resultSet.getString(\"locationid\") = " + resultSet.getString("locationid"));
        System.out.println("--------------info with column index----------------");

        //get information by column index
        System.out.println("employeeid = " + resultSet.getString(1));
        System.out.println("firstname = " + resultSet.getString(2));
        System.out.println("lastname = " + resultSet.getString(3));
        System.out.println("email = " + resultSet.getString(4));
        System.out.println("phonenumber = " + resultSet.getString(5));
        System.out.println("jobid = " + resultSet.getString(6));
        System.out.println("salary = " + resultSet.getString(7));
        System.out.println("managerid = " + resultSet.getString(8));
        System.out.println("departmentid = " + resultSet.getString(9));
        System.out.println("locationid = " + resultSet.getString(10));

        resultSet.next();
        System.out.println("resultSet.getString(\"email\") = " + resultSet.getString("email"));
        System.out.println("resultSet.getString(7) = " + resultSet.getString(7));

        //get all employeeid, firstname, lastname (start from 3rd row)
        while (resultSet.next()) {
            System.out.println("employeeid = " + resultSet.getString("employeeid") + " "
                    + "firstname =" + resultSet.getString("firstname") + " "
                    + "lastname =" + resultSet.getString("lastname"));
        }

        //test bitince database baglantisi kapatilir
        System.out.println("------DB connection is closing-------");
        resultSet.close();
        statement.close();
        connection.close();
    }
}
