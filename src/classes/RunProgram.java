package classes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import add.Add;
import add.KategoriException;
import db.DBConn;
import get.Get;

public class RunProgram extends DBConn {
    String err = "";
    /*
    * Treningsdagboken må minimum ha følgende funksjonalitet:
        1. Registrere apparater, øvelser og treningsøkter med tilhørende data.
        2. Få opp informasjon om et antall n sist gjennomførte treningsøkter med notater, der n
        spesifiseres av brukeren.
        2
        3. For hver enkelt øvelse skal det være mulig å se en resultatlogg i et gitt tidsintervall
        spesifisert av brukeren.
        4. Lage øvelsegrupper og finne øvelser som er i samme gruppe.
        5.  for å registrere gruppetime, og lage en treningsøkt
    *
    *
    *
    *    List<Apparat> apparater = new ArrayList<Apparat>();
    List<Øvelse> øvelser = new ArrayList<Øvelse>();
    List<Øvelsegruppe> øvelsegrupper = new ArrayList<Øvelsegruppe>();
    List<Treningsøkt> treningsøkter = new ArrayList<Treningsøkt>();
    List<Gruppetime> gruppetimer = new ArrayList<Gruppetime>();
    *
    * */

    void printerr(){
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        if(err.equals("")) return;
        System.out.println("ERROR: " + err);
        err = "";
    }

    public boolean registerscreen(BufferedReader br)throws IOException {
        printerr();
        System.out.print("Hva ønsker du å registrere? Skriv tallet\n");
        System.out.print("  1 for å registrere apparater\n");
        System.out.print("  2 for å registrere øvelser\n");
        System.out.print("  3 for å registrere treningsøkter\n");
        System.out.print("  0 for å gå tilbake\n");
        try{
            int i = Integer.parseInt(br.readLine());
            if(i >= 0 && i <= 3){
                Add adder = new Add(conn);
                String[] values;
                switch(i){
                    case 0:
                        return false;
                    case 1:
                        System.out.println("Skriv inn navn og beskrivelse av apparatet. Navnet kan ikke inneholde mellomrom");
                        values = br.readLine().split(" ", 2);
                        if(adder.addApparat(values[0], values[1])){
                            System.out.println("Success");
                        }
                        break;
                    case 2:
                        System.out.println("Skriv inn navn og beskrivelse av øvelsen. Navnet kan ikke inneholde mellomrom");
                        values = br.readLine().split(" ", 2);
                        if(adder.addOvelse(values[0], values[1])){
                            System.out.println("Success");
                        }
                        break;
                    case 3:
                        System.out.println("Skriv inn dato (YYYY-MM-DD), tidspunkt (HH:MM:SS)," +
                                " varighet i minutter, personlig form på en skala fra 0-10 og et notat");
                        values = br.readLine().split(" ", 5);
                        if(adder.addTreningsokt(values[0], values[1], Integer.parseInt(values[2]),
                                Integer.parseInt(values[3]), values[4])) {
                            System.out.println("Success");
                        }
                        break;
                }
            }
            else{
                err ="Nummeret må være mellom 1 og 3";
            }
        }catch(NumberFormatException nfe){
            err = "Ugyldig format";
        }
        return true;
    }
    public boolean visgjennomførtetreniningsøkter(BufferedReader br)throws IOException {
        printerr();
        System.out.print("Hvor mange gjennomførte treninger har du lyst å se?\n");

        try{
            int i = Integer.parseInt(br.readLine());
            if(i == 0) return false;
            if(i >= 1){
                Get getter = new Get(conn);
                getter.getNTreningsokter(i);
                homescreen(br);
            }
            else{
                err ="Nummeret må være høyere enn 0";
            }
        }catch(NumberFormatException nfe){
            err = "Ugyldig format";
        }
        return true;
    }
    public boolean visresultatlog(BufferedReader br)throws IOException {
        printerr();
        System.out.print("Fra hvilken dato ønsker du å vise resultatlog? skriv i formatet YYYY-MM-DD YYYY-DD-MM\n");

        try{
            /*
            String begindato = br.readLine();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
            Date begin = sdf.parse(begindato);

            System.out.print("Til hvilken dato ønsker du å vise resultatlog? yyyy-mm-dd");
            String sluttdato = br.readLine();
            Date slutt = sdf.parse(sluttdato);
            */

            String[] dates = br.readLine().split(" ");
            Get getter = new Get(conn);
            getter.getResultatLogg(dates[0], dates[1]);

            homescreen(br);

        }catch(NumberFormatException nfe) {
            err = "Ugyldig format";
        }
        return true;
    }
    public boolean lagøvelsegrupper(BufferedReader br)throws IOException {
        printerr();
        System.out.print("Ønsker du å lage øvelsesgruppe eller finne øvelser i samme gruppe?\n1. Finne\n2. Lage");
        try {
            int i = Integer.parseInt(br.readLine());
            switch (i){
                case 1:
                    Get getter = new Get(conn);

                    System.out.println("Skriv inn id'en til gruppen");
                    boolean success = false;
                    while(!success){
                        try{
                            int id = Integer.parseInt(br.readLine());
                            getter.getGruppe(id);
                            success = true;
                        } catch (NumberFormatException e){
                            System.out.println("Du må skrive inn et tall");
                            System.out.println("Skriv inn id'en til gruppen");
                        }
                    }





                    break;
                case 2:
                    Add adder = new Add(conn);

                    System.out.println("Skriv inn kategorien på din øvelsesgruppe\n");
                    String value = br.readLine();
                   try {
                       while (!adder.addGruppe(value)){
                           System.out.println("Kategorien finnes allerede, skriv inn kategorien på din øvelsesgruppe igjen\n");
                           value = br.readLine();
                       }
                   } catch (SQLException e){
                       System.out.println("DB error");
                   }
            }
        } catch(NumberFormatException e){
            System.out.println("Du må velge 1 eller 2");
        }

        return true;
    }

    public boolean registrergruppetime(BufferedReader br)throws IOException {
        return true;
    }

    public void homescreen(BufferedReader br)throws IOException {
        printerr();
        System.out.print("Skriv tallet\n");
        System.out.print("  1 for å registrere apparater, øvelser og treningsøkter\n");
        System.out.print("  2 for å se n antall siste gjennomførte treningsøkter\n");
        System.out.print("  3 for å se resultatlogg\n");
        System.out.print("  4 for å lage øvelsegrupper og finne øvelser i samme gruppe\n");
        System.out.print("  5 for å registrere gruppetime på en ny treningsøkt\n");
        /*String s = br.readLine();
        System.out.print("Enter Integer:  " + s);*/
        try{
            int i = Integer.parseInt(br.readLine());
            if(i >= 1 && i <= 5){
                switch(i){
                    case 1:
                        while(registerscreen(br)){}
                            break;
                    case 2:
                        while(visgjennomførtetreniningsøkter(br)){}
                        break;
                    case 3:
                        while(visresultatlog(br));
                        break;
                    case 4:
                        while(lagøvelsegrupper(br));
                        break;
                    case 5:
                        while(registrergruppetime(br));
                        break;
                }
            }
            else{
                err ="Nummeret må være mellom 1 og 5";
            }
        }catch(NumberFormatException nfe){
            err ="Ugyldig format";
        }
    }

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        RunProgram main = new RunProgram();
        main.connect();
        while(true) {
            main.homescreen(br);
        }
    }

}
