package com.wichteln;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

public class Persist {

    private Matching matching;

    public Persist(Matching matching) {
        this.matching = matching;
    }

    public void save() {
        LocalDateTime ts = LocalDateTime.now();
        String path = "ziehung_" + ts.toString();
        new File(path).mkdirs();
        var paarungen = matching.getPaarungen();
        var spielerList = matching.getSpieler();
        for (int i = 0; i < matching.getAnzahlSpieler(); i++) {
            String spieler = spielerList.get(i);
            List<String> beschenkte = paarungen.get(spieler);
            String content = "Spieler:in: " + spieler + "\nBeschenkte: "
                    + beschenkte.stream().reduce("", (a, b) -> a + b + " ");
            try {
                java.nio.file.Files.writeString(java.nio.file.Paths.get(path + "/" + spieler + ".txt"), content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
