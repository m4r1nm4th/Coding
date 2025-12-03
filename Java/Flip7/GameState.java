public class GameState {
    private int numberOfPlayers;
    private int roundNumber;

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    private int currentPlayer;

    private Deck deck;
    private int[] playerScores;
    private State[] states;

    private Hand[] hands;

    public GameState(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        this.playerScores = new int[numberOfPlayers];
        this.states = new State[numberOfPlayers];
        this.hands = new Hand[numberOfPlayers];
        this.deck = new Deck();
        this.roundNumber = 1;

        for (int i = 0; i < numberOfPlayers; i++) {
            this.states[i] = State.RUNNING;
            this.hands[i] = new Hand();
        }
    }

    public State[] getStates() {
        return states;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setNextPlayer() {
        this.currentPlayer = (this.currentPlayer + 1) % this.numberOfPlayers;
    }

    public Hand getActivePlayerHandCards() {
        return this.hands[this.currentPlayer];
    }

    public int getCurrentPlayerScore() {
        return playerScores[currentPlayer];
    }

    public int[] getPlayerScores() {
        return this.playerScores;
    }

    public void setCurrentPlayerState(State state) {
        states[currentPlayer] = state;
    }

    public void currentPlayerDraws() {
        Integer card = deck.nextCard();
        boolean hasFaulted = hands[currentPlayer].addCard(card);
        if (hasFaulted) {
            states[currentPlayer] = State.FAULT;
        }
    }

    public void addPoints() {
        for (int i = 0; i < numberOfPlayers; i++) {
            playerScores[i] += hands[i].score();
        }
    }

    public void clearBoard() {
        for (int i = 0; i < numberOfPlayers; i++) {
            hands[i].clear();
            states[i] = State.RUNNING;
        }
        deck.clearBoard();
    }

    public void increaseRoundNumber() {
        this.roundNumber++;
    }

    public int getRoundNumber() {
        return this.roundNumber;
    }

    public Integer[] getDiscardPile() {
        return this.deck.getDiscardPile();
    }

    public Integer[] getBoard() {
        return this.deck.getBoard();
    }
}
