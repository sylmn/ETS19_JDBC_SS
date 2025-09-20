package com.eurotech.tests.day2;

import com.eurotech.utilities.ConfigurationReader;
import org.testng.annotations.Test;

import java.sql.*;

public class C05_Crud_Update {
    String dbUrl = ConfigurationReader.get("url");
    String dbUsername = ConfigurationReader.get("username");
    String dbPassword = ConfigurationReader.get("password");


    @Test
    public void update() throws SQLException {
        System.out.println("-----------DB connection is starting--------");
        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        ResultSet resultSet=statement.executeQuery("select firstname,lastname from jdbctable where firstname='Samet'");

// güncellemeden önceki hali
        resultSet.next();
        System.out.println("Before update --> firstname : "+resultSet.getString("firstname")+
                ", lastname : "+resultSet.getString("lastname"));

//güncelleyelim
        int affectedRows = statement.executeUpdate("update jdbctable \n" +
                "\tset lastname=null\n" +
                "\twhere firstname='Samet';");
        System.out.println("affectedRows = " + affectedRows);


// güncellemeden sonra
        ResultSet afterResultSet=statement.executeQuery("select firstname,lastname from jdbctable where firstname='Samet'");
        afterResultSet.next();
        System.out.println("After update --> firstname : "+afterResultSet.getString("firstname")+
                ", lastname : "+afterResultSet.getString("lastname"));


        System.out.println("----------DB connection is closing------------");
        resultSet.close();
        statement.close();
        connection.close();
    }
}
