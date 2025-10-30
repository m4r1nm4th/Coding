package com.wichteln;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Matching {

    private List<String> spieler;
    private Map<String, List<String>> paarungen;
    private Permutation permutation;

    public Matching(List<String> spieler) {
        this.spieler = spieler;
        this.permutation = new Permutation(spieler.size());
        this.paarungen = new java.util.HashMap<>();
    }

    public int getAnzahlSpieler() {
        return spieler.size();
    }

    public Map<String, List<String>> generierePaarungen(int anzahlGeschenke) {
        paarungen.clear();
        MultiPermutation multiperm = new MultiPermutation(this.permutation, anzahlGeschenke);
        Map<Integer, Integer[]> randomPaarung = multiperm.generateRandomPaarung();

        for (Integer key : randomPaarung.keySet()) {
            String spieler1 = spieler.get(key.intValue());
            List<String> beschenkte = new ArrayList<>();
            beschenkte = Arrays.stream(randomPaarung.get(key.intValue())).map(a -> spieler.get(a)).toList();
            paarungen.put(spieler1, beschenkte);
        }
        // Implement pairing logic here
        return paarungen;
    }

    public Map<String, List<String>> getPaarungen() {
        return paarungen;
    }

    public List<String> getSpieler() {
        return spieler;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<String>> eintrag : paarungen.entrySet()) {
            sb.append(eintrag.getKey()).append(" beschenkt ");
            sb.append(eintrag.getValue().stream().collect( Collectors.joining(" und "))).append(".\n");
        }
        return sb.toString();
    }
}
