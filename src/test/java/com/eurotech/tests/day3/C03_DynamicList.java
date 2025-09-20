package com.eurotech.tests.day3;

import com.eurotech.utilities.ConfigurationReader;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class C03_DynamicList {
    String dbUrl = ConfigurationReader.get("url");
    String dbUsername = ConfigurationReader.get("username");
    String dbPassword = ConfigurationReader.get("password");

    @Test
    public void test() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select firstname, lastname, salary, jobid from employees;");

        ResultSetMetaData metaData = resultSet.getMetaData(); //sütun isimleri icin gerekli

        List<Map<String, Object>> queryData = new ArrayList<>();

        int columnCount = metaData.getColumnCount();

        while (resultSet.next()) {
            Map<String, Object> row = new LinkedHashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                row.put(metaData.getColumnName(i), resultSet.getString(i));
            }
            queryData.add(row);
        }
        System.out.println("queryData = " + queryData);

        resultSet.close();
        statement.close();
        connection.close();
    }
}
