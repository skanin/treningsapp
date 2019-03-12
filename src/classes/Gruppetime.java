package classes;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Gruppetime  extends ActiveDomainObject{
    Integer id = null;
    String navn = null;
    String beskrivelse = null;

    List<Øvelse> øvelser = new ArrayList<Øvelse>();  //(0, N)

    public void Gruppetime(Integer id, String navn, String beskrivelse, List<Øvelse> øvelser){
        this.id = id;
        this.navn = navn;
        this.beskrivelse = beskrivelse;
        this.øvelser = øvelser;
    }


    @Override
    public void initialize(Connection conn) {

    }

    @Override
    public void refresh(Connection conn) {

    }

    @Override
    public void save(Connection conn) {

    }
}
