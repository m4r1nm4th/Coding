public class Game {
    private GameState gs;
    private Strategy[] strategies;
    private GameDriver driver;

    public Game(int numberOfPlayers) {
        gs = new GameState(numberOfPlayers);
    }

    public Game(Strategy[] strats) {
        this.strategies = strats;
        this.gs = new GameState(strategies.length);
        for (Strategy strategy : this.strategies) {
            strategy.setGameState(gs);
        }
        runGame();
    }

    public void loadStrategies(Strategy[] strategies) {
        this.strategies = strategies;
    }

    public GameState getGameState() {
        return this.gs;
    }

    public void runGame() {
        this.driver = new GameDriver(gs, strategies);
        driver.driveGame();
    }

    public void runGame(GameDriver gd) {
        this.driver = gd;
        driver.driveGame();
    }

}
