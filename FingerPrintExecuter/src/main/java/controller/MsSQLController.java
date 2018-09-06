/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db_connections.DataSourceWrapper;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import model.TFingerPrint;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author 'Kasun Chamara'
 */
public class MsSQLController {

    private final DataSourceWrapper mssqlDataSourceWrapper;
    private static final Logger LOGGER = Logger.getLogger(MsSQLController.class);

    public MsSQLController() throws SQLException {
        Properties prop = new Properties();
        InputStream input = null;
        String dbName = null;
        String user = null;
        String pswd = null;
        try {

            input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println("get database name : "+prop.getProperty("getDataSourceName"));
            // set value into variable
            dbName = prop.getProperty("getDataSourceName");
            user = prop.getProperty("getDataSourceUser");
            pswd = prop.getProperty("getDataSourcePswd");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.mssqlDataSourceWrapper = new DataSourceWrapper(dbName, user, pswd);
//        this.mssqlDataSourceWrapper = new DataSourceWrapper("jdbc:sqlserver://localhost:1433;databaseName=RIMS_Attendance;", "sa", "123");
    }

    public ArrayList<TFingerPrint> getFingerPrintList(Integer max) throws SQLException {
        LOGGER.debug("reading fingerprint data");
        try (Connection connection = mssqlDataSourceWrapper.getConnection()) {
            String sql = "select id,clkdate,clktime,checktime,emp_no from vw1 where id >"+max;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<TFingerPrint> list = new ArrayList<>();
            while (resultSet.next()) {
                TFingerPrint fingerPrintData = new TFingerPrint();
                fingerPrintData.setId(resultSet.getInt(1));
                fingerPrintData.setClkdate(resultSet.getString(2));
                fingerPrintData.setClktime(resultSet.getString(3));
                fingerPrintData.setChecktime(resultSet.getString(4));
                fingerPrintData.setEmp_id(resultSet.getString(5));
                list.add(fingerPrintData);
            }
            LOGGER.debug(list.size() + " fingerprint data found");
            return list;
        }
    }

    public int deleteFingerPrint(ArrayList<TFingerPrint> list) throws SQLException {
        LOGGER.debug("deleting fingerprint data");
        try (Connection connection = mssqlDataSourceWrapper.getConnection()) {
            String deleteSql = "delete from ras_AttRecord where ID=?";
            PreparedStatement preparedStatementDelete = connection.prepareStatement(deleteSql);
            for (TFingerPrint data : list) {
                preparedStatementDelete.setInt(1, data.getId());
                preparedStatementDelete.execute();
                LOGGER.debug("fingerprint data deleted for " + data.getId());
            }
            return list.size();
        }
    }
}
