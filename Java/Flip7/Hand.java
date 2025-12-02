import java.util.ArrayList;
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

    public int numberOfHandCards() {
        return cards.size();
    }

    public List<Integer> getCards() {
        return this.cards;
    }

    public void setCards(List<Integer> cards) {
        this.cards = cards;
    }

    public void setHasFaulted(boolean hasFaulted) {
        this.hasFaulted = hasFaulted;
    }

    public Hand clone() {
        Hand hand = new Hand();
        hand.setCards(new ArrayList<Integer>(this.cards));
        hand.setHasFaulted(this.hasFaulted);
        return hand;
    }
}
