package test;

import db.DBConn;

import java.sql.*;

public class TestInsert extends DBConn {
    public TestInsert(){
        connect();
    }

    public void test(){
        try {

            PreparedStatement preparedStmt = conn.prepareStatement("INSERT INTO Apparat VALUES (null, 'test2', 'Dette er en test2');");

            preparedStmt.execute();



        } catch (java.sql.SQLException e){
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args){
        TestSelect select = new TestSelect();
        TestInsert insert = new TestInsert();

        insert.test();
        select.test();
    }
}

