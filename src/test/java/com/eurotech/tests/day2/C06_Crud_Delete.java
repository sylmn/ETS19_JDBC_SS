package com.eurotech.tests.day2;

import com.eurotech.utilities.ConfigurationReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.sql.*;

public class C06_Crud_Delete {
    String dbUrl = ConfigurationReader.get("url");
    String dbUsername = ConfigurationReader.get("username");
    String dbPassword = ConfigurationReader.get("password");
    private static final Logger logger = LoggerFactory.getLogger(C06_Crud_Delete.class);


    @Test
    public void delete() throws SQLException {
        System.out.println("-----------DB connection is starting--------");
        try (
                Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ) {
            logger.info("Veritabanına bağlantı başarılı ✅");
            int deleted =statement.executeUpdate("delete from jdbctable where firstname='Samet';");
            if (deleted > 0) {
                logger.info("Samet kaydı silindi ✅");
            } else {
                logger.warn("Silinecek kayıt bulunamadı ⚠️");
            }
            ResultSet resultSet = statement.executeQuery("select * from jdbctable");

            while (resultSet.next()) {
                logger.debug("Kayıt: {} {}", resultSet.getString("firstname"), resultSet.getString("lastname"));
            }
            resultSet.close();

        } catch (SQLException e) {
            logger.error("Veritabanına bağlanırken hata oluştu ❌", e);
        }
        logger.info("----------DB connection is closing------------");

    }

    @Test
    public void drop() throws SQLException {
        System.out.println("-----------DB connection is starting--------");
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        statement.executeUpdate("truncate table jdbctable;");
        statement.executeUpdate("drop table jdbctable;");


        System.out.println("----------DB connection is closing------------");
        statement.close();
        connection.close();
    }
}
