package src.myDatastructures;

public class HighArray {

    private double[] a;
    private int nElems;

    public HighArray(int max) {
        this.a = new double[max];
        this.nElems = 0;
    }

    public void insert (double value) {
        a[nElems] = value;
        nElems++;
    }

    public boolean find (double value) {
        for (int j = 0; j < nElems; j++) {
            if (a[j] == value) {
                return true;
            }
        }
        return false;
    }

    public boolean delete (double value) {
        int j;
        for (j = 0; j < nElems; j++) {
            if (a[j] == value) {
                break;
            }
        }
        if ( j == nElems ) {
            return false;
        }
        else {
            for (int k = j; k < nElems; k++) {
                a[k] = a[k + 1];    // runs into indexOutOfBounds if nElems == max
            }
            nElems--;
            return true;
        }
    }

    public void display () {
        for (int j = 0; j < nElems; j++) {
            System.out.print(a[j] + " ");
        }
        System.out.println("");
    }

    public void bubbleSort() {
        int in, out;
        for (out = nElems - 1; out > 1; out --) {
            for (in = 0; in < out; in++) {
                if (a[in] > a[in + 1]) {
                    swap(in, in + 1);
                }
            }
        }
    }

    private void swap(int indexA, int indexB) {
        double temp = a[indexA];
        a[indexA] = a[indexB];
        a[indexB] = temp;
    }
}
