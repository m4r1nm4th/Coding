public class Runner {
    public static void main(String[] args) {
        run2();
    }

    private static void run2() {
        Deck deck = new Deck();
        System.out.println("Drawing 7 cards from the deck:");
        for (int i = 0; i < 7; i++) {
            System.out.println(deck.nextCard());
        }
    }

    private static void run() {
        Integer[] deck = new FlipSevenDeck().getDeck();
        System.out.println("Original Deck:");
        System.out.println(java.util.Arrays.toString(deck));
        Permutation<Integer> perm = new Permutation<>(deck);
        perm.permute();
        Integer[] permutedInts = perm.getElements();
        System.out.println("Permuted Integers:");
        System.out.println(java.util.Arrays.toString(permutedInts));
    }
}
    
