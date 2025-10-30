import java.util.Optional;
import java.util.Stack;

public class Deck {

    private Stack<Integer> cards;
    private Stack<Integer> discardPile;

    public Deck() {
        var perm = new Permutation<Integer>(new FlipSevenDeck().getDeck());
        perm.permute();
        this.cards = new Stack<>();
        for (Integer card : perm.getElements()) {
            this.cards.push(card);
        }   
        this.discardPile = new Stack<>();
    }

    public Integer nextCard() {
        if (this.cards.isEmpty()) {
            this.cards = this.discardPile; //need to be shuffled
            this.discardPile = new Stack<>();
        }
        Integer card = this.cards.pop();
        this.discardPile.push(card);
        return card;
    }
}
