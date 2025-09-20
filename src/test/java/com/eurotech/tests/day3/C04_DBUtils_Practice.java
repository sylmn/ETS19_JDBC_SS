package com.eurotech.tests.day3;

import com.eurotech.utilities.DBUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class C04_DBUtils_Practice {

    @Test
    public void listOfMap() {
        DBUtils.createConnection();
        List<Map<String, Object>> resultTable = DBUtils.getQueryResultMap("select * from employees;");
        System.out.println("resultTable = " + resultTable);

        //son satirdan lastname i almak icin
        Object lastname = resultTable.get(resultTable.size() - 1).get("lastname");
        System.out.println("lastname = " + lastname);

        DBUtils.destroy();
    }
    @Test
    public void oneSingleRow() {
        DBUtils.createConnection();
        Map<String, Object> rowMap = DBUtils.getRowMap("select * from employees where firstname='Elif';");
        System.out.println("rowMap = " + rowMap);

        //email degerinin testini yapalim
        String expected = "esarıyıldız";
        String actual = (String) rowMap.get("email");
        Assert.assertEquals(actual, expected);
    }
}
