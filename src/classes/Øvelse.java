package classes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class Øvelse extends ActiveDomainObject{
    Integer id = null;
    String navn = null;
    String beskrivelse = null; //Can be null
    Apparat apparat = null;
    List<Øvelsegruppe> øvelsesgrupper = new ArrayList<Øvelsegruppe>();  //(0, N)
    List<Apparat> apparater = new ArrayList<Apparat>();  //(0, N)

    public Øvelse(Integer id){

    }
    public Øvelse(Integer id, String navn, Apparat apparat, List<Øvelsegruppe> øvelsesgrupper) {
        this.id = id;
        this.navn = navn;
        this.apparat = apparat;
        this.øvelsesgrupper = øvelsesgrupper;
    }

    @Override
    public void initialize(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select navn, timer, beskrivelse from avtaletype where aid=" + id);
            while (rs.next()) {
                navn = rs.getString("navn");
                beskrivelse = rs.getString("beskrivelse");

               // ResultSet rs = stmt.executeQuery("select starttid, timer, alarmtype from BrukerApparat where ovelseID=" + aid);
                //apparatID = rs.getInt("avtaletype");
            }

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
