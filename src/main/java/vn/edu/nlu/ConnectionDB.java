package vn.edu.nlu;


import vn.edu.nlu.donghonu.ListData;
import vn.edu.nlu.donghonu.ProductEntity;
import vn.edu.nlu.Data;
import javax.management.StandardEmitterMBean;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class ConnectionDB {
    static Connection con;

    public static Statement connect() throws ClassNotFoundException, SQLException {
        if (con == null || con.isClosed()) {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdongho?useUnicode=true&characterEncoding=utf-8", "root", "");
            return con.createStatement();

        } else {
            return con.createStatement();
        }
    }

    public static void main(String[] args) throws Exception {
//        ProductEntity pe=new ProductEntity();
//        System.out.println( pe.insertAll(Data.map.values()));
//        pe.insertAll(Data.map.values());Statement s=ConnectionDB.connect();
        Statement s=ConnectionDB.connect();
        ResultSet rs = s.executeQuery("select * from product");
        rs.last();
        System.out.println(rs.getRow());
        rs.beforeFirst();
        while (rs.next()) {
            System.out.println(rs.getString(2));
            // }
        }
    }
}