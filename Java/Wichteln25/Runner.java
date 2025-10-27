import java.util.List;

public class Runner {

    private static List<String> SPIELER = List.of(
            "Barbara",
            "Marin",
            "Brigitte",
            "Norbert",
            "Lotte",
            "Felix"
    );
    
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        Matching matching = new Matching(SPIELER);
        matching.generierePaarungen();
        Persist persist = new Persist(matching);
        persist.save();
    }
}
