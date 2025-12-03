public class GreedyPointsStrategy implements Strategy {

    private GameState gs;
    private int targetScore;

    public GreedyPointsStrategy(GameState gs, int targetScore) {
        this.gs = gs;
        this.targetScore = targetScore;
    }

    @Override
    public PlayerAction nextMove() {
        if (gs.getActivePlayerHandCards().score() < targetScore) {
            return PlayerAction.DRAW;
        } else {
            return PlayerAction.CLOSE;
        }
    }

    @Override
    public void setGameState(GameState gs) {
        this.gs = gs;
    }

}
