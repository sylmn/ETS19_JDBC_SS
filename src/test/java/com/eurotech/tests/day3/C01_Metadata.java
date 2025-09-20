package com.eurotech.tests.day3;

import com.eurotech.utilities.ConfigurationReader;
import org.testng.annotations.Test;

import java.sql.*;

public class C01_Metadata {
    String dbUrl = ConfigurationReader.get("url");
    String dbUsername = ConfigurationReader.get("username");
    String dbPassword = ConfigurationReader.get("password");

    @Test
    public void test() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from employees;");

        //kullandigimiz mevcut databasein üst bilgilerini almak icin kullaniyoruz
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        System.out.println("databaseMetaData.getDatabaseProductName() = " + databaseMetaData.getDatabaseProductName());
        System.out.println("databaseMetaData.getDriverVersion() = " + databaseMetaData.getDriverVersion());
        System.out.println("databaseMetaData.getUserName() = " + databaseMetaData.getUserName());
        System.out.println("databaseMetaData.getDatabaseProductVersion() = " + databaseMetaData.getDatabaseProductVersion());

        //ilgili tabloya ait metadatalar icin
        ResultSetMetaData metaData = resultSet.getMetaData();

        //how many column that we have
        int columnCount = metaData.getColumnCount();
        System.out.println("columnCount = " + columnCount);

        //how to get first column name
        String firstColumnName = metaData.getColumnName(1);
        System.out.println("firstColumnName = " + firstColumnName);

        String secondColumnName = metaData.getColumnName(2);
        System.out.println("secondColumnName = " + secondColumnName);

        //get all column names with a for loop
        for (int i = 1; i <= columnCount; i++) {
            System.out.println(i + ". ColumnName = " + metaData.getColumnName(i));
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
