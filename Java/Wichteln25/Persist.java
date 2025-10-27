import java.io.File;
import java.security.Timestamp;
import java.sql.Time;
import java.time.LocalDateTime;

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
        for(int i = 0; i < matching.getAnzahlSpieler(); i++) {
            String spieler = spielerList.get(i);
            String beschenkter = paarungen.get(spieler);
            String content = "Spieler: " + spieler + "\nBeschenkter: " + beschenkter;
            try {
                java.nio.file.Files.writeString(java.nio.file.Paths.get(path + "/" + spieler + ".txt"), content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
