import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxExpectationStrategy implements Strategy {

    private GameState gs;

    public MaxExpectationStrategy(GameState gs) {
        this.gs = gs;
    }

    @Override
    public PlayerAction nextMove() {
        int e = calculateExpectation();
        if (e > 0) {
            return PlayerAction.DRAW;
        } else {
            return PlayerAction.CLOSE;
        }
    }

    private int calculateExpectation() {
        Integer[] board = gs.getBoard();
        Integer[] discardPile = gs.getDiscardPile();
        Integer[] deck = calculateRemainingCards(board, discardPile);
        if (deck.length == 0) {
            return 0;
        }
        int handValue = gs.getActivePlayerHandCards().score();
        int result = 0;
        for (Integer card : deck) {
            result += handValueAfterAdding(card) - handValue;
        }

        return result / deck.length;
    }

    private int handValueAfterAdding(Integer card) {
        Hand hand = gs.getActivePlayerHandCards().clone();
        hand.addCard(card);
        return hand.score();
    }

    @Override
    public void setGameState(GameState gs) {
        this.gs = gs;
    }

    private Integer[] calculateRemainingCards(Integer[] board, Integer[] discardPile) {
        List<Integer> s = new ArrayList<>(Arrays.asList(board));
        s.addAll(Arrays.asList(discardPile));
        Integer[] flipSevenDeck = new FlipSevenDeck().getDeck();
        List<Integer> result = new ArrayList<Integer>(Arrays.asList(flipSevenDeck));
        for (Integer card : s) {
            result.remove(card);
        }
        int size = result.size();
        Integer[] remainingCards = new Integer[size];
        result.toArray(remainingCards);
        return remainingCards;
    }

}
