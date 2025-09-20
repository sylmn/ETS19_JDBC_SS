package com.eurotech.tests.day2;

import com.eurotech.utilities.ConfigurationReader;
import org.testng.annotations.Test;

import java.sql.*;

public class C02_CrudPractice {
    String dbUrl = ConfigurationReader.get("url");
    String dbUsername = ConfigurationReader.get("username");
    String dbPassword = ConfigurationReader.get("password");

    @Test
    public void failDelete() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet= statement.executeQuery("select * from employees;");
        resultSet.next();
        resultSet.deleteRow();
        //org.postgresql.util.PSQLException: Eş zamanlama CONCUR_READ_ONLY olan ResultSet'ler değiştirilemez





        resultSet.close();
        statement.close();
        connection.close();
    }

    String query="create table jdbcTable(\n" +
            "\tid int primary key,\n" +
            "\tfirstname varchar(20),\n" +
            "\tlastname varchar(20));";

    @Test
    public void failToCreate() throws SQLException {
        System.out.println("-----------DB connection is starting--------");
        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement= connection.createStatement();


        ResultSet resultSet=statement.executeQuery(query);
        //code calisir hata verir.



        System.out.println("----------DB connection is closing------------");
        resultSet.close();
        statement.close();
        connection.close();
    }
}
