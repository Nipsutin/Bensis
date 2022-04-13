package bensis;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
    Scanner lukija = new Scanner(System.in);
    Console komentorivi = System.console();

        if (komentorivi == null){
            System.out.println("Ohjelma vaatii toimiakseen komentoriviä. Ajathan ohjelman tätä kautta.");
            return;
        }

        // Luetaan käyttäjän salasana
        String salasana = null;
        lueKirjautuminen(salasana);
    }
    public static void lueKirjautuminen(String salasana) throws InterruptedException, FileNotFoundException {
        String passu = "Keke", syote;
        do {
                System.out.print("Anna salasanasi: ");
                syote = String.valueOf(System.console().readPassword());
                
        }
        while (!syote.equals(passu));
        String onnistunut = "\nKirjautuminen onnistui";
        
        int laskeKirjaimet = onnistunut.length();
        for (int i = 0; i < laskeKirjaimet; i++){
            System.out.print("=");
        }

        System.out.println(onnistunut);

            try{
                Thread.sleep(1000);
            }

            catch (InterruptedException e) {
                e.printStackTrace();
            }

        paaValikko();
    }
    public static void paaValikko() throws InterruptedException, FileNotFoundException {

        System.out.println("Valitse toiminto");
        Thread.sleep(300);
        System.out.println("1) Tarkista hinnat");
        Thread.sleep(500);
        System.out.println("2) Syota uudet hinnat");
        Thread.sleep(500);
        System.out.println("3) Lopeta ohjelma");
        int paatos = Integer.parseInt(System.console().readLine());

            if (paatos == 1){
                Thread.sleep(500);
                System.out.print("Valitsit hintojen tarkistamisen. Ladataan hintatietoja, odota hetki");
                for(int i = 0; i < 5; ++i) {
                    Thread.sleep(500);
                    System.out.print("...");
                    Thread.sleep(400);
                }
                System.out.println("\nLataus suoritettu onnistuneesti\n");


                naytaHinnat();
            }

            if (paatos == 2){
                Thread.sleep(500);
                System.out.println("Syotetaan uusia hintoja, hetki \n \n");
                Thread.sleep(3000);
                syotaHinnat();
            }

            if (paatos == 3){
                nakemiin();
            }
    }
    public static void syotaHinnat() throws InterruptedException, FileNotFoundException {
        System.out.println("Hintojen päivitys on poissa huoltokatkon myötä. Koitathan myöhemmin uudelleen. Palataan automaattisesti päävalikkoon.");
        paaValikko();
    }

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
        System.out.println("Haluatko jatkaa ohjelman suorittamista? y/n");

        String vastaus = lukija.nextLine();

        if (vastaus.equals("y")){
            paaValikko();
        }

        if (vastaus.equals("n")){
            nakemiin();
        }
    }

    public static void nakemiin() throws InterruptedException {
        System.out.println("Suljetaan ohjelma turvallisesti");
        Thread.sleep(2000);
        System.exit(1);
    }
}

