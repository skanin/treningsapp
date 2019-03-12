package add;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;

public class Add {

    private Connection conn;
    private final String id = null;

    public Add(Connection conn) {
        try {
            this.conn = conn;
        } catch (Exception e) {
            System.out.println("Failed to connect to database");
        }
    }

    public boolean addApparat(String navn, String beskrivelse) {
        final String sql = "INSERT INTO Apparat(navn, beskrivelse)"
                + " VALUES (?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            setParameters(statement, navn, beskrivelse);
            statement.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean addOvelse(String navn, String beskrivelse){
        final String sql = "INSERT INTO Ovelse(navn, beskrivelse)"
                + " VALUES (?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            setParameters(statement, navn, beskrivelse);
            statement.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    

    private static void setParameters(PreparedStatement statement, Object... parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            // Parameters are 1-indexed
            statement.setObject(i + 1, parameters[i]);
        }
    }
}