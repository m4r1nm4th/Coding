import java.util.Stack;

public class Deck {

    private Stack<Integer> cards;
    private Stack<Integer> discardPile;
    private Stack<Integer> board;

    public Deck() {
        this.cards = loadStack(new FlipSevenDeck().getDeck());
        shuffleCards();
        this.discardPile = new Stack<>();
        this.board = new Stack<>();
    }

    public Integer nextCard() {
        if (this.cards.isEmpty()) {
            this.cards.addAll(this.discardPile); // need to be shuffled
            this.discardPile.removeAllElements();
            shuffleCards();
        }
        Integer card = this.cards.pop();
        this.board.push(card);
        return card;
    }

    private Stack<Integer> loadStack(Integer[] ints) {
        Stack<Integer> result = new Stack<>();
        for (Integer integer : ints) {
            result.push(integer);
        }
        return result;
    }

    private void shuffleCards() {
        var perm = new Permutation<Integer>(cards.toArray(new Integer[cards.size()]));
        perm.permute();
        this.cards.removeAllElements();
        this.cards = loadStack(perm.getElements());
    }

    public void clearBoard() {
        discardPile.addAll(board);
        board.removeAllElements();
    }

    public Integer[] getDiscardPile() {
        Integer[] a = new Integer[this.discardPile.size()];
        return this.discardPile.toArray(a);
    }

    public Integer[] getBoard() {
        Integer[] a = new Integer[this.board.size()];
        return this.board.toArray(a);
    }
}
