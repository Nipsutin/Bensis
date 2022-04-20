package bensis;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * @version 0.0.1
 * @author Malila Valtteri, Käyhkö Joona
 *
 * Ohjelma toimii kuvitteellisen polttoaineaseman hintanäytön hallintatyökaluna. Ohjelmalla voidaan näyttää hinnat tolpassa sekä päivittää tiedot
 *
 */

/**
 * The type Main.
 */

public class Main {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws InterruptedException  the interrupted exception
     * @throws FileNotFoundException the file not found exception
     *
     */
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
    Scanner lukija = new Scanner(System.in);
    Console komentorivi = System.console();

        if (komentorivi == null){ // Tarkistetaan, onko käyttäjä käyttämässä ohjelmaa komentorivillä. Mikäli ei, annetaan virheilmoitus eikä jatketa ohjelman suroittamista
            System.out.println("Ohjelma vaatii toimiakseen komentoriviä. Ajathan ohjelman tätä kautta.");
            return;
        }
        // Luetaan käyttäjän salasana
        String salasana = null;
        lueKirjautuminen(salasana);
    }

    /**
     * Lue kirjautuminen. Käyttäjältä kysytään salasana, ja mikäli se syötetään oikein, näytetään päävalikko. Jos salasana näytetään väärin, mennään takaisin salasanan syöttöön.
     *
     * @param salasana Lukee salasanan sisällön käyttäjän syötteestä ja tarkistaa vastaako se ennaltamääriteltyä salasanasyötettä
     * @throws InterruptedException  the interrupted exception
     * @throws FileNotFoundException the file not found exception
     */

    public static void lueKirjautuminen(String salasana) throws InterruptedException, FileNotFoundException {

        String passu = "Keke", syote;
        do {
                System.out.print("Anna salasanasi: ");
                syote = String.valueOf(System.console().readPassword());
                
        }
        while (!syote.equals(passu));

        String onnistunut = "\nKirjautuminen onnistui"; // Määritetään teksti, joka tullaan näyttämään käyttäjälle kirjautumisen onnistuttua
        
        int laskeKirjaimet = onnistunut.length(); // Lasketaan onnistunut - merkkimäärä
        for (int i = 0; i < laskeKirjaimet; i++){
            System.out.print("="); // Tulostetaan yhtäsuurimerkit kirjautumisen onnistumisen kunniaksi tekstin alle
        }

        System.out.println(onnistunut); // Annetaan onnistunut tekstisyöte käyttäjälle
        Thread.sleep(1000); // Odotetaan sekuntti ennen päävalikon avaamista

        paaValikko(); // Avataan metodi paaValikko();
    }

    /**
     * PaaValikko. Nöytetään ohjelman päävalikko, josta voidaan valita toiminnallisuudet
     *
     * @throws InterruptedException  the interrupted exception
     * @throws FileNotFoundException the file not found exception
     */

    public static void paaValikko() throws InterruptedException, FileNotFoundException {

        System.out.println("Valitse toiminto");
        Thread.sleep(300); // Odotetaan 0,3 sekuntia ennen seuraavan toiminnon tulostamista
        System.out.println("1) Tarkista hinnat");
        Thread.sleep(500); // Odotetaan 0,5 sekuntia ennen seuraavan toiminnon tulostamista
        System.out.println("2) Syota uudet hinnat");
        Thread.sleep(500); // Odotetaan 0,5 sekuntia ennen seuraavan toiminnon tulostamista
        System.out.println("3) Lopeta ohjelma");
        int paatos = Integer.parseInt(System.console().readLine());

            if (paatos == 1){
                Thread.sleep(500);
                System.out.print("Valitsit hintojen tarkistamisen. Ladataan hintatietoja, odota hetki");
                for(int i = 0; i < 5; ++i) {
                    Thread.sleep(500);
                    System.out.print("..."); // Tulostetaan käyttäjälle hieno pisteanimaatio ja uskotellaan, että koodi hakisi jotain isoa ja suurta
                    Thread.sleep(400);
                }
                System.out.println("\nLataus suoritettu onnistuneesti\n"); // Näytetään onnistunut latausviesti


                naytaHinnat(); // Haetaan hinnat naytaHinnat(); metodilla
            }

            if (paatos == 2){
                Thread.sleep(500);
                System.out.println("Siirrytään syöttämään hintoja \n \n");
                Thread.sleep(3000);
                syotaHinnat();
            }

            if (paatos == 3){
                nakemiin();
            }
    }

    /**
     * Syota hinnat.
     *
     * @throws InterruptedException  the interrupted exception
     * @throws FileNotFoundException the file not found exception
     */

    public static void syotaHinnat() throws InterruptedException, FileNotFoundException {


        Scanner hintapaivittaja = new Scanner(System.in);
        Double hinta;

        System.out.print("Anna laadun 95 uusi hinta: ");
        Double ysiviisuusi = hintapaivittaja.nextDouble();

        System.out.print("Anna laadun 98 uusi hinta: ");
        Double ysikasiuusi = hintapaivittaja.nextDouble();

        System.out.print("Anna laadun diesel uusi hinta: ");
        Double dieseluusi = hintapaivittaja.nextDouble();

        try{

            File uudethinnat = new File("resources\\hinnat.txt"); // Määritetään tiedosto, johon muutokset tehdään

            PrintWriter kirjoita = new PrintWriter(uudethinnat);
            kirjoita.write("\n");
            kirjoita.write("95;"+ysiviisuusi+"\n");
            kirjoita.write("98;"+ysikasiuusi+"\n");
            kirjoita.write("Diesel;"+dieseluusi+"\n");
            kirjoita.close(); // Suljetaan kirjoitin kirjoituksen päättymisen myötä ja tallennetaan tiedot
        }

        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Hinnat päivitetty tolpalle.");
        haluatkoJatkaa();
    }

    /**
     * Nayta hinnat.
     *
     * @throws InterruptedException  the interrupted exception
     * @throws FileNotFoundException the file not found exception
     */

    public static void naytaHinnat() throws InterruptedException, FileNotFoundException {
        Main.class.getResourceAsStream("resources\\hinnat.txt");
        Scanner inputStream = new Scanner(new File("resources\\hinnat.txt"));
        Scanner lukija = new Scanner(System.in);
        String rivi = inputStream.nextLine();

        while (inputStream.hasNextLine()){
            rivi = inputStream.nextLine();
            String[] taulu = rivi.split(";");
            String tyyppi = taulu[0];
            double litrahinta = Double.parseDouble(taulu[1]);

            System.out.println("Tuotteen " + tyyppi + " litrahinta tolpassa on " + litrahinta + " euroa litralta.");
        }
        haluatkoJatkaa();
    }

    /**
     * Haluatko jatkaa.
     *
     * @throws InterruptedException  the interrupted exception
     * @throws FileNotFoundException the file not found exception
     */

    public static void haluatkoJatkaa() throws InterruptedException, FileNotFoundException {

        Scanner lukija = new Scanner(System.in);
        System.out.println("Haluatko jatkaa ohjelman suorittamista? y/n");

        String vastaus = lukija.nextLine();

        if (vastaus.equals("y")){
            paaValikko();
        }

        if (vastaus.equals("n")){
            nakemiin();
        }
    }

    /**
     * Nakemiin.
     *
     * @throws InterruptedException the interrupted exception
     */

    public static void nakemiin() throws InterruptedException {
        System.out.println("Suljetaan ohjelma turvallisesti");
        Thread.sleep(2000);
        System.exit(1);
    }
}