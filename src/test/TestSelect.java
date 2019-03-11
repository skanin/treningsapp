package test;

import db.DBConn;

import java.sql.*;

public class TestSelect extends DBConn {
    public TestSelect() {
        connect();

        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of LagAvtaleCtrl="+e);
        }
    }

    public void test(){
        try {
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM Apparat;");

            while (rs.next())
            {
                int id = rs.getInt("apparatID");
                String navn = rs.getString("navn");
                String beskrivelse = rs.getString("beskrivelse");

                // print the results
                System.out.format("%s, %s, %s\n", id, navn, beskrivelse);
            }
            st.close();

        } catch (java.sql.SQLException e){
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        TestSelect t = new TestSelect();
        t.test();
    }
}
