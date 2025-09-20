package com.eurotech.tests.day2;

import com.eurotech.utilities.ConfigurationReader;
import org.testng.annotations.Test;

import java.sql.*;

public class C03_Crud_Create {
    String dbUrl = ConfigurationReader.get("url");
    String dbUsername = ConfigurationReader.get("username");
    String dbPassword = ConfigurationReader.get("password");

    @Test
    public void createWithCreate() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        statement.executeUpdate("create table jdbcTable(\n" +
                "\tid int primary key,\n" +
                "\tfirstname varchar(20),\n" +
                "\tlastname varchar(20));");


        statement.close();
        connection.close();
    }

    @Test
    public void createWithInsert() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        //int affectedRow = statement.executeUpdate("insert into jdbcTable values (1,'Samet','Emsen');");
        //System.out.println("affectedRow = " + affectedRow);

        statement.executeUpdate("insert into jdbctable values (2,'Suleyman','Suleyman');");
        statement.executeUpdate("insert into jdbctable values (3,'Selda','Gehrmann');");
        statement.executeUpdate("insert into jdbctable values (4,'Tulay','Dag');");
        statement.executeUpdate("insert into jdbctable values (5,'Gulden','Tazeoglu');");
        statement.executeUpdate("insert into jdbctable values (6,'Irem','Yokus');");
        statement.executeUpdate("insert into jdbctable values (7,'Ayse','Iren');");
        statement.executeUpdate("insert into jdbctable values (8,'Alper','Savcı');");

        statement.close();
        connection.close();
    }
}
