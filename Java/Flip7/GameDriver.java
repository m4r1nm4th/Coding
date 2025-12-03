
public class GameDriver {

    GameState gs;
    Strategy[] strategies;

    public GameDriver(GameState gs, Strategy[] strategies) {
        this.gs = gs;
        this.strategies = strategies;
        for (Strategy strategy : strategies) {
            strategy.setGameState(gs);
        }
    }

    public void driveGame() {

        while (!gameFinished()) {
            while (existRunningPlayer()) {
                while (!gs.getStates()[gs.getCurrentPlayer()].equals(State.RUNNING)) {
                    gs.setNextPlayer();
                }
                PlayerAction playerAction = strategies[gs.getCurrentPlayer()].nextMove();
                resolvePlayerAction(playerAction);
            }
            finishRound();
        }
        announceWinner();
    }

    protected void announceWinner() {
        int[] playerScores = gs.getPlayerScores();
        for (int i = 0; i < gs.getNumberOfPlayers(); i++) {
            System.out.println(
                    "Spieler " + Integer.toString(i) + " has " + Integer.toString(playerScores[i]) + " Points.");

        }
    }

    protected void finishRound() {
        gs.addPoints();
        gs.clearBoard();
        gs.setNextPlayer(); // dealer shifts
        gs.increaseRoundNumber();
    }

    protected boolean gameFinished() {
        int[] scores = gs.getPlayerScores();
        for (int i : scores) {
            if (i >= 200)
                return true;
        }
        return false;
    }

    protected boolean existRunningPlayer() {
        State[] states = this.gs.getStates();
        for (State state : states) {
            if (state.equals(State.RUNNING))
                return true;
        }
        return false;
    }

    protected void resolvePlayerAction(PlayerAction playerAction) {
        if (playerAction.equals(PlayerAction.CLOSE)) {
            gs.setCurrentPlayerState(State.CLOSED);
        }
        if (playerAction.equals(PlayerAction.DRAW)) {
            gs.currentPlayerDraws();
        }
        gs.setNextPlayer();
    }

    public void setGameState(GameState gs) {
        this.gs = gs;
    }

}
