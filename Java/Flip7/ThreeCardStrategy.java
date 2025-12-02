public class ThreeCardStrategy implements Strategy {

    private GameState gs;

    public ThreeCardStrategy(GameState gs) {
        this.gs = gs;
    }

    @Override
    public PlayerAction nextMove() {
        int handCards = gs.getActivePlayerHandCards().numberOfHandCards();
        return handCards < 3 ? PlayerAction.DRAW : PlayerAction.CLOSE;
    }

    @Override
    public void setGameState(GameState gs) {
        this.gs = gs;
    }

}
