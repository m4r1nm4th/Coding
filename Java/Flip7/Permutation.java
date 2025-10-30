import java.util.Random;

public class Permutation<T> {

    private Random rand;
    private T[] elements;
    private int size;

    public Permutation(T[] elements) {
        this.elements = elements;
        this.size = elements.length;
        this.rand = new Random();

    }

    public T[] getElements() {
        return elements;
    }

    public void permute() {
        for (int i = this.size - 1; i > 0 ; i--) {
            swap(i, rand.nextInt(i));
        }
    }

    private void swap(int i, int j) {
        T temp = this.elements[i];
        this.elements[i] = this.elements[j];
        this.elements[j] = temp;
    }
}