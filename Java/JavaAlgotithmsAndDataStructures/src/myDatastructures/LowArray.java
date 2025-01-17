package src.myDatastructures;

public class LowArray {
    private double[] a;

    public LowArray (int max) {
        this.a = new double[max];
    }

    public void setElem (int index, double value) {
        a[index] = value;
    }

    public double getElem (int index) {
        return a[index];
    }
}
