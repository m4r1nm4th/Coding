package WichtelnApp2dot0;

public class Shuffle<T> {

    private T[] cards;

    public T[] getCards() {
        return cards;
    }

    public Shuffle(T[] cards) {
        this.cards = cards;
    }

    public void shuffle() {
        int n = this.cards.length;
        Ziehung ziehung = new Ziehung(n);
        int[] perm = ziehung.randPerm();
        for (int i = 0; i < n; i++) {
            swap(cards, i, perm[i]);
        }
    }

    private void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
