public class GameState {
    private int numberOfPlayers;
    private int currentPlayer;
    private Deck deck;
    private int[] playerScores;
    private State[] states;
    private Hand[] hands;
    private Strategy[] strategies;

    public static GameState newGame(Strategy[] strategies) {

        GameState gameState = new GameState(strategies);

        this.strategies = strategies;
        this.numberOfPlayers = strategies.length;
        this.currentPlayer = 0;
        this.deck = new Deck();
        this.playerScores = new int[this.numberOfPlayers];
        this.states = new State[this.numberOfPlayers];
        for (int i = 0; i < this.numberOfPlayers; i++) {
            this.states[i] = State.RUNNING;
        }
        this.hands = new Hand[this.numberOfPlayers];
    }
}
