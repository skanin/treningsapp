package classes;
import java.sql.Connection;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Properties;

public class Treningsøkt extends ActiveDomainObject{
    Integer id = null;
    String navn = null;
    Calendar dato = null;
    Calendar tidspunkt = null;
    String personlig_form = null; //Can be null
    String notat = null; //can be null

    List<Øvelse> øvelser = new ArrayList<Øvelse>();  //(0, N)
    List<Gruppetime> gruppetimer = new ArrayList<Gruppetime>();  //(0, N). Gruppetimer som er i treningsøkten

    public void regØvelse (int øvelseid, Connection conn) {
        Øvelse b = new Øvelse (øvelseid);
        b.initialize (conn);
        øvelser.add(b);
    }

    @Override
    public void initialize(Connection conn) {
        try {
            /*
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select starttid, timer, alarmtype from Avtale where aid=" + aid);
            while (rs.next()) {
                startTid =  rs.getInt("starttid");
                timer = rs.getInt("timer");
                type = rs.getInt("avtaletype");
            }*/

        } catch (Exception e) {
            System.out.println("db error during select of avtale= "+e);
            return;
        }
    }

    @Override
    public void refresh(Connection conn) {

    }

    @Override
    public void save(Connection conn) {

    }
}
