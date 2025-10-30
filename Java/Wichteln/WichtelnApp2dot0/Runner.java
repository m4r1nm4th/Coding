package WichtelnApp2dot0;

public class Runner {

    public static void main(String[] args) {
        test();
        run();
    }

    public static void run() {

        String[] CANDIDATES = { "Marin", "Barbara", "Felix", "Lotte", "Brigitte", "Norbert" };

        Shuffle<String> shuffle = new Shuffle<>(CANDIDATES);
        shuffle.shuffle();
        String[] cards = shuffle.getCards();

        for (String card : cards) {
            System.out.println(card);
        }
    }

    public static void test() {
        Ziehung ziehung = new Ziehung(6);

        int[] arr = ziehung.randPerm();

        for (int i : arr) {
            System.out.println(Integer.toString(i));
        }

        int[] cycarr = ziehung.cyclicRotation(arr);
        for (int i : cycarr) {
            System.out.println(Integer.toString(i));
        }
    }
}
