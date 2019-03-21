package classes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import add.Add;
import db.DBConn;
import get.Get;
import relasjoner.Relation;

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
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
        if(err.equals("")) return;
        System.out.println("ERROR: " + err + "\n");
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
                try {
                    switch (i) {
                        case 0:
                            return false;
                        case 1:
                            System.out.println("Skriv inn navn og beskrivelse av apparatet. Navnet kan ikke inneholde mellomrom");
                            values = br.readLine().split(" ", 2);
                            if (adder.addApparat(values[0], values[1])) {
                                System.out.println("Success");
                            }
                            break;
                        case 2:
                            System.out.println("Skriv inn navn og beskrivelse av øvelsen. Navnet kan ikke inneholde mellomrom");
                            values = br.readLine().split(" ", 2);
                            if (adder.addOvelse(values[0], values[1])) {
                                System.out.println("Success");
                            }
                            break;
                        case 3:
                            System.out.println("Skriv inn dato (YYYY-MM-DD), tidspunkt (HH:MM:SS)," +
                                    " varighet i minutter, personlig form på en skala fra 0-10 og et notat");
                            values = br.readLine().split(" ", 5);
                            if (adder.addTreningsokt(values[0], values[1], Integer.parseInt(values[2]),
                                    Integer.parseInt(values[3]), values[4])) {
                                System.out.println("Success");
                            }
                            break;
                    }
                }
                catch(ArrayIndexOutOfBoundsException err){
                    this.err = "Ugyldig format, må være: navn <mellomrom> beskrivelse, beskrivelse kan ikke være tom";
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
        System.out.print("Hvor mange gjennomførte treninger har du lyst å se?\n0. for å gå tillbake: \n");

        try{
            int i = Integer.parseInt(br.readLine());
            if(i == 0) return false;
            if(i >= 1){
                Get getter = new Get(conn);
                getter.getNTreningsokter(i);
                System.out.println("Trykk enter for å fortsette");
                br.readLine();
                return false;
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
        System.out.print("Fra hvilken dato ønsker du å vise resultatlog? skriv i formatet YYYY-DD-MM YYYY-DD-MM\n");
        System.out.print("Skriv 0 for å gå tilbake\n");



        try{
            String[] dates = br.readLine().split(" ");
            if(dates.length == 1 && Integer.parseInt(dates[0]) == 0){
                return false;
            }

            if(dates.length != 2){
                throw new IllegalArgumentException();
            }
            Get getter = new Get(conn);
            getter.getResultatLogg(dates[0], dates[1]);
            System.out.println("Trykk enter for å fortsette");
            br.readLine();
            return false;

        }catch(IllegalArgumentException e) {
            err = "Ugyldig format";
        }
        return true;
    }
    public boolean lagøvelsegrupper(BufferedReader br)throws IOException {
        printerr();
        System.out.print("Ønsker du å lage øvelsesgruppe eller finne øvelser i samme gruppe?\n1. Finne\n2. Lage\n0. Gå tilbake\n");
        try {
            int i = Integer.parseInt(br.readLine());
            switch (i){
                case 0:
                    return false;
                case 1:
                    Get getter = new Get(conn);

                    System.out.println("Skriv inn id'en til gruppen");
                    boolean success = false;
                    while(!success){
                        try{
                            int id = Integer.parseInt(br.readLine());
                            getter.getGruppe(id);
                            System.out.println("Trykk enter for å fortsette");
                            br.readLine();
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
            System.out.println("Du må velge 1, 2 eller 0");
        }

        return true;
    }

    public boolean registrergruppetime(BufferedReader br)throws IOException {
        printerr();
        System.out.println("Skriv inn navnet på gruppetimen og en beskrivelse av timen, navnet kan ikke inneholde mellomrom \n" +
                "Skriv 0 for å gå tilbake: ");

        String[] values = br.readLine().split(" ", 2);

        if(values.length == 1 && Integer.parseInt(values[0]) == 0){
            return false;
        }

        while(values.length != 2){
            System.out.println("Du må skrive inn navnet på gruppetimen, et mellomrom, deretter en beskrivelse av timen: ");
            values = br.readLine().split(" ", 2);
        }

        Add adder = new Add(conn);
        adder.addGruppetime(values[0], values[1]);

        return true;
    }

    public boolean registrerRelasjoner(BufferedReader br) throws IOException{
        printerr();
        System.out.println("1. For å registrere øvelse på treningsøkt\n" +
                "2. For å registrere apparat på øvelse\n" +
                "3. For å registrere gruppetime på treningsøkt\n" +
                "4. For å registrere gruppe på øvelse\n" +
                "5. For å registrere øvelse på gruppetime\n" +
                "0. For å gå tilbake");

        try{
            int i = Integer.parseInt(br.readLine());
            Relation rel = new Relation(conn);
            String[] values;
            switch (i){
                case 0:
                    return false;
                case 1:
                    System.out.println("Skriv inn øvelseid'en og treningsøktid'en og prestasjon (1-10) med mellomrom. Eks (3 4 7): ");
                    values = br.readLine().split(" ");
                    while (values.length != 3){
                        System.out.println("Ugyldig format, skriv inn øvelseid, treningsøktid og presjasjon");
                        values = br.readLine().split(" ");
                    }

                    while (1 > Integer.parseInt(values[2]) || 10 < Integer.parseInt(values[2])) {
                        System.out.println("Prestasjon må være mellom 1 og 10");
                        values = br.readLine().split(" ");
                    }
                    if(!rel.regOvelseTreningsokt(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]))){
                        System.out.println("Treninsøkten har allerede denne øvelsen");
                        return true;
                    } else {
                        System.out.println("La til øvelsen på treningsøkten!");
                    }


                    break;
                case 2:
                    System.out.println("Skriv inn apparatid'en, øvelsesid'en, antall kilo og antall sett: ");
                    values = br.readLine().split(" ");

                    if(!rel.regApparatOvelse(Integer.parseInt(values[0]), Integer.parseInt(values[1]),
                            Integer.parseInt(values[2]), Integer.parseInt(values[3]))){
                        System.out.println("Dette apparatet har allerede denne øvelsen");
                        return true;
                    }else {
                        System.out.println("Registrerte apparat på øvelse.");
                    }

                    break;
                case 3:
                    System.out.println("Skriv inn treningsøktid'en, gruppetimeid'en og prestasjon (1-10): ");
                    values = br.readLine().split(" ");

                    if(!rel.regTreningsoktGruppetime(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]))){
                        System.out.println("Treningsøkten har allerede denne gruppetimen");
                        return true;
                    }else {
                        System.out.println("Registrerte gruppetimen på treningsøkten!");
                    }
                    break;
                case 4:
                    System.out.println("Skriv inn gruppeid'en og øvelsesid'en: ");
                    values = br.readLine().split(" ");

                    if(!rel.regOvelseGruppe(Integer.parseInt(values[0]), Integer.parseInt(values[1]))){
                        System.out.println("Gruppen har allerede denne øvelsen!");
                        return true;
                    } else {
                        System.out.println("Registrerte øvelsen på gruppen!");
                    }

                    break;
                case 5:
                    System.out.println("Skriv inn gruppetimeID, ØvelseID og prestasjon: ");
                    values = br.readLine().split(" ");

                    if(!rel.regOvelseGruppetime(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]))){
                        System.out.println("Gruppetimen har allerede denne øvelsen!");
                        return true;
                    }else {
                        System.out.println("Registrerte øvelsen på gruppetimen!");
                    }
                    break;
            }
        } catch(NumberFormatException nfe){
            err = "Ugyldig format";
        }
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
        System.out.print("  6 for å registrere relasjoner\n");
        /*String s = br.readLine();
        System.out.print("Enter Integer:  " + s);*/
        try{
            int i = Integer.parseInt(br.readLine());
            if(i >= 1 && i <= 6){
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
                    case 6:
                        while (registrerRelasjoner(br)){}
                        break;
                }
            }
            else{
                err ="Nummeret må være mellom 1 og 6";
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
