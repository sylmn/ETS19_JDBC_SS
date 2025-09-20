package com.eurotech.tests.day2;

import com.eurotech.utilities.ConfigurationReader;
import org.testng.annotations.Test;

import java.sql.*;

public class C01_MovingCursor {

        String dbUrl = ConfigurationReader.get("url");
        String dbUsername = ConfigurationReader.get("username");
        String dbPassword = ConfigurationReader.get("password");


        @Test
        public void test1() throws SQLException {
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            Statement statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery("select * from employees;");



            //find how many rows that table has

            //first way
            int count = 0;
            while (resultSet.next()){
                count++;
            }
            System.out.println("row count = " + count);

            //second way
            resultSet.last(); //tablonun en son satirina gider
            int rowCount = resultSet.getRow();
            System.out.println("rowCount = " + rowCount);

            //how to go to first row
            resultSet.first();
            int firstRow = resultSet.getRow();
            System.out.println("firstRow = " + firstRow);

            //how to go to the particular row
            resultSet.absolute(7);
            System.out.println("resultSet.getString(\"firstname\") = " + resultSet.getString("firstname"));

            //how to go a particular row from where pointer is
            resultSet.relative(2);
            System.out.println("resultSet.getRow() = " + resultSet.getRow());
            resultSet.relative(-3);
            System.out.println("resultSet.getRow() = " + resultSet.getRow());

            resultSet.beforeFirst();
            resultSet.afterLast();



            connection.close();
            statement.close();
            resultSet.close();
        }


}
