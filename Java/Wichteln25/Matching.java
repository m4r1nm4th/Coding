import java.util.List;
import java.util.Map;

public class Matching {

    private List<String> spieler;
    private Map<String, String> paarungen;
    private Permutation permutation;

    public Matching(List<String> spieler) {
        this.spieler = spieler;
        this.permutation = new Permutation(spieler.size());
    }

    public int getAnzahlSpieler() {
        return spieler.size();
    }

    public Map<String, String> generierePaarungen() {
        if(paarungen == null) {
            paarungen = new java.util.HashMap<>();
        }
        paarungen.clear();
        int[] zufallsPermutation = permutation.generateRandomPermutation();
        int[] shift = permutation.generateShift();
        for(int i = 0; i < spieler.size(); i++) {
            int a = zufallsPermutation[i];
            int b = shift[a];
            String spieler1 = spieler.get(a);
            String spieler2 = spieler.get(b);
            paarungen.put(spieler1, spieler2);
        }
        // Implement pairing logic here
        return paarungen;
    }

    public Map<String, String> getPaarungen() {
        return paarungen;
    }

    public List<String> getSpieler() {
        return spieler;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> eintrag : paarungen.entrySet()) {
            sb.append(eintrag.getKey()).append(" - ").append(eintrag.getValue()).append("\n");
        }
        return sb.toString().replaceAll("-","beschenkt");
    }
}
