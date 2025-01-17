package src.myDatastructures;

public class OrderedArray {

    private double[] a;
    private int nElems;

    public OrderedArray (int max) {
        this.a = new double[max];
        this.nElems = 0;
    }

    public int size() {
        return nElems;
    }

    public int find (double searchKey) {
        int lowerBound = 0;
        int upperBound = nElems - 1;
        int currIn;

        while (true) {
            currIn = (lowerBound + upperBound)/2;
            if (searchKey == a[currIn]) {
                return currIn;
            } else if (lowerBound > upperBound) {
                return nElems;
            }
            else if (searchKey > a[currIn]) {
                lowerBound = currIn + 1;
            } else {
                upperBound = currIn - 1;
            }
        }
    }

    public void insert (double value) {
       int j;
       for (j = 0; j < nElems; j++) {
           if (a[j] > value) {break;}
       }
       for (int k = nElems; k > j; k--) {
           a[k] = a[k-1];
       }
       a[j] = value;
       nElems++;
    }

    public boolean delete (double value) {
        int j = find(value);
        if (j == nElems) {
            return false;
        }
        else {
            for (int k = j; k < nElems; k++) {
                a[k] = a[k + 1];
            }
            nElems--;
            return true;
        }
    }

    public void display() {
        for (int j = 0; j < nElems; j++) {
            System.out.print(a[j] + " ");
        }
        System.out.println("");
    }
}
