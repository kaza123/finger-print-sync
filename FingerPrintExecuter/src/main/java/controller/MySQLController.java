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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import model.TEmployeeAssingment;
import model.TFingerPrint;

/**
 *
 * @author 'Kasun Chamara'
 */
public class MySQLController {

    private final DataSourceWrapper mysqlDataSourceWrapper;

    public MySQLController() throws SQLException {
        String dbName = null;
        String user = null;
        String pswd = null;
//        try {

//            input = new FileInputStream("config.properties");

            // load a properties file
//            prop.load(input);

            // get the property value and print it out
            System.out.println("set database name : jdbc:mysql://35.237.72.149:3306/whiteglo_hr");
            // set value into variable
            dbName = "jdbc:mysql://35.237.72.149:3306/whiteglo_hr";
            user = "root";
            pswd = "Rockball789";

//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
        this.mysqlDataSourceWrapper = new DataSourceWrapper(dbName, user, pswd);
//        this.mysqlDataSourceWrapper = new DataSourceWrapper("jdbc:mysql://localhost:3306/care_point", "root", "mysql");
//        this.mysqlDataSourceWrapper = new DataSourceWrapper("jdbc:mysql://123.231.62.166:3306/care_point", "kavishmanjitha", "kavishmanjitha");
    }

    public Integer saveFingerPrint(ArrayList<TFingerPrint> list) throws SQLException {
        try (Connection connection = mysqlDataSourceWrapper.getConnection()) {
            String insertSql = "insert into attendance_data_collector_log (strEmpNo,dtmEffectDate,strDataRow,dtmDate,intInputId) values (?,?,?,?,?)";
            PreparedStatement preparedStatementInsert = connection.prepareStatement(insertSql);
            int value = 0;
            for (int i = 0; i < list.size(); i++) {
                preparedStatementInsert.setInt(1, Integer.valueOf(list.get(i).getEmp_id()));
                preparedStatementInsert.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(new Date(list.get(i).getClkdate())));
                preparedStatementInsert.setString(3, list.get(i).getClktime().replace(".", ":"));
                preparedStatementInsert.setString(4, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(list.get(i).getChecktime())));
                preparedStatementInsert.setInt(5, list.get(i).getId());

                //finger print save
                int execute = preparedStatementInsert.executeUpdate();

                if (execute > 0) {
                    value++;
                }

            }
            if (value == list.size()) {
                return value;

            }
            return -1;
        }

    }

   

    public Integer getMaxNo() throws SQLException {
         try (Connection conn = mysqlDataSourceWrapper.getConnection()) {
            String query = "select max(intInputId) as max from attendance_data_collector_log;";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                return rst.getInt(1);
            }
            return 1152739;
        }
    }

}
