package com.wichteln;
public class Permutation {

    public int getSize() {
        return size;
    }

    private int size;

    public Permutation(int n) {
        this.size = n;
    }

    public int[] identity() {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = i;
        }
        return result;
    }

    public int[] generateShift(int[] cycle) {
        int temp = cycle[0];
        for (int i = 0; i < size - 1; i++) {
            cycle[i] = cycle[(i + 1) % size];
        }
        cycle[size - 1] = temp;
        return cycle;
    }

    public int[] generateLShift(int[] cycle) {
        int temp = cycle[size - 1];
        for (int i = size - 1; i > 0; i--) {
            cycle[i] = cycle[(i - 1) % size];
        }
        cycle[0] = temp;
        return cycle;
    }

    public int[] generateRandomPermutation() {
        int[] cycle = new int[size];
        for (int i = 0; i < size; i++) {
            cycle[i] = i;
        }

        // Shuffle the array to create a random permutation
        for (int i = size - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            // Swap cycle[i] with cycle[j]
            int temp = cycle[i];
            cycle[i] = cycle[j];
            cycle[j] = temp;
        }

        return cycle;
    }

}