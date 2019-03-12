package db;

import java.sql.*;

public class DBConn {
    protected Connection conn;

    public DBConn() {
    }

    public void connect() {
        try {
            // db parameters
            String url       = "jdbc:mysql://mysql.stud.ntnu.no:3306/sanderbl_dbproject";
            String user      = "sanderbl_dbproject";
            String password  = "1234";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected sucessfully");
        } catch (Exception e) {
            throw new RuntimeException("Unable to connect", e);
        }
    }/*

    public static void main(String[] args) {
        DBConn conn = new DBConn();

        conn.connect();

    }*/
}
