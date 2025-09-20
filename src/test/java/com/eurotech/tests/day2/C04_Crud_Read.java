package com.eurotech.tests.day2;

import com.eurotech.utilities.ConfigurationReader;
import org.testng.annotations.Test;

import java.sql.*;

public class C04_Crud_Read {
    String dbUrl = ConfigurationReader.get("url");
    String dbUsername = ConfigurationReader.get("username");
    String dbPassword = ConfigurationReader.get("password");

    @Test
    public void read() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from jdbctable;");
        resultSet.next();
        System.out.println("resultSet.getString(\"firstname\") = " + resultSet.getString("firstname"));
        resultSet.absolute(5);
        System.out.println("resultSet.getString(\"firstname\") = " + resultSet.getString("firstname"));
        resultSet.last();
        System.out.println("resultSet.getString(\"firstname\") = " + resultSet.getString("firstname"));

        resultSet.close();
        statement.close();
        connection.close();
    }
}
