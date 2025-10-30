import java.util.List;

public class Hand {
    private List<Integer> cards;
    private boolean hasFaulted;

    public Hand() {
        this.cards = new java.util.ArrayList<>();
        this.hasFaulted = false;
    }

    public int score() {
        if (this.hasFaulted) {
            return 0;
        }
        return this.cards.stream().mapToInt(Integer::intValue).sum();
    }

    public void clear() {
        this.cards.clear();
        this.hasFaulted = false;
    }

    public boolean addCard(Integer card) {
        this.hasFaulted = this.cards.contains(card);      
        this.cards.add(card);
        return this.hasFaulted;
    }

}
