package get;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

public class Get {
    private Connection conn;

    public Get(Connection conn){
        try {
            this.conn = conn;
        } catch (Exception e) {
            System.out.println("Failed to connect to database");
        }
    }

    public void getNTreningsokter(int n){
        final String sql = "SELECT * FROM Treningsokt ORDER BY dato DESC, tidspunkt DESC LIMIT " + n;
        System.out.println("ID |      Dato     |   Tidspunkt |   Varighet   | Personlig Form |   Notat");
        System.out.println("---------------------------------------------------------------------------");
        pstatement(sql);
        System.out.println("---------------------------------------------------------------------------");
    }

    public boolean getResultatLogg(String start, String end){
        final String sql = "SELECT dato, varighet, prestasjon " +
                "FROM OvelseITreningsokt " +
                "JOIN Ovelse ON OvelseITreningsokt.ovelseID = Ovelse.ovelseID " +
                "JOIN Treningsokt ON OvelseITreningsokt.treningsoktID = Treningsokt.treningsoktID " +
                "WHERE dato > '" + start + "' AND dato < '" + end + "'";

        System.out.println("Dato      |  Varighet  |  Prestasjon  |");
        System.out.println("---------------------------------------------------------------------------");
        pstatement(sql);
        System.out.println("---------------------------------------------------------------------------");

        return true;
    }

    private void pstatement (String sql){
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            while (rs.next()) {
                for(int i = 1; i < columnsNumber + 1; i++) {
                    if (i == columnsNumber) {
                        System.out.print(rs.getString(i));
                    } else {
                        System.out.print(rs.getString(i) + "  |   ");
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
