public class CompareStrategyDriver extends GameDriver {

    private int rounds = 100;

    public CompareStrategyDriver(GameState gs, Strategy[] strategies) {
        super(gs, strategies);
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    @Override
    public void driveGame() {
        while (gs.getRoundNumber() < rounds) {
            while (existRunningPlayer()) {
                while (!gs.getStates()[gs.getCurrentPlayer()].equals(State.RUNNING)) {
                    gs.setNextPlayer();
                }
                PlayerAction playerAction = strategies[gs.getCurrentPlayer()].nextMove();
                resolvePlayerAction(playerAction);
            }
            finishRound();
        }
        printScores();
    }

    private void printScores() {
        StringBuilder sb = new StringBuilder();
        sb.append("Player 1 score: ");
        int[] scores = gs.getPlayerScores();
        sb.append(scores[0]);
        sb.append("\n");
        sb.append("Player 2 score: ");
        sb.append(scores[1]);
        sb.append("\n");
        System.out.println(sb.toString());
    }

}