package relasjoner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Relation {

    private Connection conn;
    private final String id = null;

    public Relation(Connection conn) {
        try {
            this.conn = conn;
        } catch (Exception e) {
            System.out.println("Failed to connect to database");
        }
    }

    public boolean regOvelseTreningsokt(int ovelseID, int treningsoktID, int prestasjon){
        final String sql = "INSERT INTO OvelseITreningsokt(treningsoktID, ovelseID, prestasjon) " +
                "VALUES(?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            setParameters(statement, treningsoktID, ovelseID, prestasjon);
            statement.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean regApparatOvelse(int apparatID, int ovelseID, int kilo, int sett){
        final String sql = "INSERT INTO BrukerApparat(apparatID, ovelseID, kilo, sett) " +
                "VALUES(?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            setParameters(statement, apparatID, ovelseID, kilo, sett);
            statement.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean regTreningsoktGruppetime(int treningsoktID, int gruppetimeID, int prestasjon){
        final String sql = "INSERT INTO HarTime(treningsoktID, gruppetimeID, prestasjon) " +
                "VALUES(?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            setParameters(statement, treningsoktID, gruppetimeID, prestasjon);
            statement.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean regOvelseGruppe(int gruppeID, int ovelseID){
        final String sql = "INSERT INTO InngaarI(gruppeID, ovelseID) " +
                "VALUES(? ,?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            setParameters(statement, gruppeID, ovelseID);
            statement.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean regOvelseGruppetime(int gruppetimeID, int ovelseID, int prestasjon){
        final String sql = "INSERT INTO HarOvelse(gruppetimeID, ovelseID, prestasjon) " +
                "VALUES(?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            setParameters(statement,gruppetimeID, ovelseID, prestasjon);
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
