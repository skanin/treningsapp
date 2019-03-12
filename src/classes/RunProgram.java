package classes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import db.DBConn;
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
        if(err == "") return;
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
                switch(i){
                    case 0:
                        return false;
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

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
        System.out.print("Fra hvilken dato ønsker du å vise resultatlog? skriv i formatet yyyy-mm-dd");

        try{
            String begindato = br.readLine();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd "); /*HH:mm:ssZ*/
            Date begin = sdf.parse(begindato);

            System.out.print("Til hvilken dato ønsker du å vise resultatlog? yyyy-mm-dd");
            String sluttdato = br.readLine();
            Date slutt = sdf.parse(sluttdato);
        }catch(NumberFormatException nfe){
            err = "Ugyldig format";
        }
        catch( ParseException p){
            err = "Ugyldig format på kalenderen";
        }
        return true;
    }
    public boolean lagøvelsegrupper(BufferedReader br)throws IOException {
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
