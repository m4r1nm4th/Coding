package WichtelnApp2dot0;

import java.util.Random;

public class Ziehung {

    private int size;

    public Ziehung(int n) {
        this.size = n;
    }

    public int[] randPerm() {
        int[] result = new int[size];
        // initialize array
        for (int i = 0; i < size; i++) {
            result[i] = i;
        }
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int diceroll = random.nextInt(i, size);
            swap(result, i, diceroll);
        }
        return result;
    }

    public int[] cyclicRotation(int[] arr) {
        int l = arr.length;
        if (l == 0)
            return arr;
        int first = arr[0];
        for (int i = 0; i < l - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[l - 1] = first;
        return arr;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
