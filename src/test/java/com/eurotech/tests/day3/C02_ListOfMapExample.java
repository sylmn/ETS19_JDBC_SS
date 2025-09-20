package com.eurotech.tests.day3;

import com.eurotech.utilities.ConfigurationReader;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.*;

public class C02_ListOfMapExample {
    String dbUrl = ConfigurationReader.get("url");
    String dbUsername = ConfigurationReader.get("username");
    String dbPassword = ConfigurationReader.get("password");

    @Test
    public void test() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from employees;");

        //bütün tabloyu icinde tutacak olan java objesi
        List<Map<String, Object>> queryData = new ArrayList<>();
        Map<String, Object> row1 = new LinkedHashMap<>();
        row1.put("firstname", "Eren");
        row1.put("lastname", "Cengel");
        row1.put("salary", 100000);
        row1.put("jobid", "QA");

        Map<String, Object> row2 = new LinkedHashMap<>();
        row2.put("firstname", "Alperen");
        row2.put("lastname", "Cengel");
        row2.put("salary", 120000);
        row2.put("jobid", "DEV");

        System.out.println("row1 = " + row1);
        System.out.println("row2 = " + row2);

        //fill thw queryData
        queryData.add(row1);
        queryData.add(row2);
        System.out.println("queryData = " + queryData);

        //how to get data from this list of map
        Object salary = queryData.get(0).get("salary");
        System.out.println("salary = " + salary);

        System.out.println("----------------------------------");
        //how to list of map with data that comes from database dynamically
        List<Map<String, Object>> queryData2 = new ArrayList<>();
        //tablonun verilerini bir java objesine atmak icin sütun isimlerine ihtiyacimiz var
        //bunu da metadata dan cekebiliriz

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        //satir verilerini key and value olarak koyacagimiz map olusturalim
        Map<String, Object> row1Dyna = new LinkedHashMap<>();
        resultSet.next();

        //bir map icine atalim
        row1Dyna.put(resultSetMetaData.getColumnName(1), resultSet.getString(1));
        row1Dyna.put(resultSetMetaData.getColumnName(2), resultSet.getString(2));
        row1Dyna.put(resultSetMetaData.getColumnName(3), resultSet.getString(3));
        row1Dyna.put(resultSetMetaData.getColumnName(4), resultSet.getString(4));

        System.out.println("row1Dyna = " + row1Dyna);
        resultSet.next();

        //2. satiri dynamic bir sekilde yükleyelim
        Map<String, Object> row2Dyna = new LinkedHashMap<>();
        row2Dyna.put(resultSetMetaData.getColumnName(1), resultSet.getString(1));
        row2Dyna.put(resultSetMetaData.getColumnName(2), resultSet.getString(2));
        row2Dyna.put(resultSetMetaData.getColumnName(3), resultSet.getString(3));
        row2Dyna.put(resultSetMetaData.getColumnName(4), resultSet.getString(4));

        System.out.println("row2Dyna = " + row2Dyna);

        //list of map icine atalim
        queryData2.add(row1Dyna);
        queryData2.add(row2Dyna);
        System.out.println("queryData2 = " + queryData2);

        Object firstname = queryData2.get(1).get(resultSetMetaData.getColumnName(2));
        //Object firstname1 = queryData2.get(1).get("firstname");
        System.out.println("firstname = " + firstname);

        resultSet.close();
        statement.close();
        connection.close();
    }
}
