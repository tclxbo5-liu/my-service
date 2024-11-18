package com.elong.hotel.hotelmy.utils.database;

import org.yaml.snakeyaml.events.Event;

import java.sql.*;

/**
 * @author bobo
 * @date 2024/10/16 16:49
 **/
public class Jdbc {
    public static  void query() throws ClassNotFoundException, SQLException {
        //加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/elong_product", "root", "Lb556131");
        String str = "select * from  order_before_log where city_id = 'cityid'";
        //预编译sql
        PreparedStatement preparedStatement = connection.prepareStatement(str);
        //执行查询语句得到结果
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (resultSet.next()){
            System.out.println(resultSet.getInt("id"));
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        query();
    }
}
