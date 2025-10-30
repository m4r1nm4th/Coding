package com.wichteln;
import java.util.List;
import java.util.Scanner;


public class UserInteraction {
    private List<String> spieler;
    private int anzahlGeschenke;

    public UserInteraction() {
        this.spieler = new java.util.ArrayList<>();
        script();
    }

    private void script() {
        Scanner scanner = new Scanner(System.in);
        Boolean save = false;
        do {
        System.out.print("Bitte liste alle Teilnehmenden auf: ");
        if (scanner.hasNextLine()) {
            Scanner sc2 = new Scanner(scanner.nextLine());
            while(sc2.hasNext()){
                this.spieler.add(sc2.next());
            }
            sc2.close();
        }
        System.out.print("Wie viele Geschenke sollen verteilt werden? ");
        this.anzahlGeschenke = scanner.nextInt();
        System.out.print("Moechtest du die Ziehung speichern? (j/n)");
        save = scanner.next().equalsIgnoreCase("j");
        System.out.print("Moechtest du die Eingaben bestaetigen? (j/n) ");
    } while(!scanner.next().equalsIgnoreCase("j"));
        scanner.close();
        Matching matching = new Matching(this.spieler);
        matching.generierePaarungen(this.anzahlGeschenke);
        if(save){
        Persist persist = new Persist(matching);
        persist.save();
        }else{
            System.out.println(matching.toString());
        }
    }
}
