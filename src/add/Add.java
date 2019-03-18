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

    public boolean addTreningsokt(String dato, String tidspunkt, int varighet, int personligForm, String notat){
        final String sql = "INSERT INTO Treningsokt(dato, tidspunkt, varighet, personligForm, notat) " +
                "VALUES(?, ?, ?, ?, ?)";
        try(PreparedStatement statement = conn.prepareStatement(sql)){
            setParameters(statement, dato, tidspunkt, varighet, personligForm, notat);
            statement.execute();
            return true;
        } catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    public boolean addGruppe(String kategori, Object... parameters) throws SQLException {
        final String select = "SELECT * FROM OvelsesGruppe WHERE kategori = '" + kategori + "'";
        final String insert = "INSERT INTO OvelsesGruppe(kategori) VALUES(?)";
        final String reg = "INSERT INTO InngaarI(gruppeID, ovelseID) VALUES(?, ?)";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(select);
            if(rs.next()){
                return false;
            }

            PreparedStatement pst = conn.prepareStatement(insert);
            setParameters(pst, kategori);
            pst.execute();
            if(parameters.length >= 1){
                st = conn.createStatement();
                rs = st.executeQuery(select);
                if(rs.next()) {
                    int gruppeID = rs.getInt("ovelsesGruppeID");
                    for(int i = 0; i < parameters.length; i++){
                        pst = conn.prepareStatement(reg);
                        setParameters(pst, gruppeID, parameters[i]);
                        pst.execute();
                    }
                }
            }

        } catch(SQLException e){
            System.out.println("DB problemer");
        }
        return true;
    }

    private static void setParameters(PreparedStatement statement, Object... parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            // Parameters are 1-indexed
            statement.setObject(i + 1, parameters[i]);
        }
    }
}