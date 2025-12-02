public class GameStateInjector {

    private GameState gs;

    public GameStateInjector() {
        this.gs = new GameState(1);
    }

    public GameStateInjector(GameState gs) {
        this.gs = gs;
    }

    public GameState getGameState() {
        return gs;
    }

    public void setGameState(GameState gs) {
        this.gs = gs;
    }
}
