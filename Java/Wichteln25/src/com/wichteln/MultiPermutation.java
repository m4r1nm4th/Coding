package com.wichteln;
import java.util.HashMap;
import java.util.Map;

public class MultiPermutation {
    private int anzahlGeschenke;
    private Map<Integer, Integer[]> paarung;
    private Permutation perm;
    private int size;

    public MultiPermutation(Permutation perm, int anzahlGeschenke) {
        this.anzahlGeschenke = anzahlGeschenke;
        this.paarung = new HashMap<>();
        this.perm = perm;
        this.size = this.perm.getSize();
    }

    public MultiPermutation(int size, int anzahlGeschenke) {
        new MultiPermutation(new Permutation(size), anzahlGeschenke);
    }

    public Map<Integer, Integer[]> generateRandomPaarung() {
        if (paarung != null) {
            paarung.clear();
        }
        int[] randPerm = this.perm.generateRandomPermutation();
        for (int i = 0; i < this.size; i++) {
            Integer[] values = new Integer[this.anzahlGeschenke];
            for (int j = 0; j < this.anzahlGeschenke; j++) {
                values[j] = randPerm[(i + j + 1) % this.size];
            }
            this.paarung.put(randPerm[i], values);
        }
        return this.paarung;
    }

    public int getSize() {
        return this.size;
    }

    public int getAnzahlGeschenke() {
        return this.anzahlGeschenke;
    }

    public Map<Integer, Integer[]> getPaarung() {
        return this.paarung;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Integer key : this.paarung.keySet()) {
            sb.append(key.intValue());
            var value = this.paarung.get(key);
            sb.append("beschenkt").append(value[0]).append("und").append(value[1]).append("\n");
        }
        return sb.toString();
    }

}
