public class SimulationDriver extends GameDriver {

    private int rounds = 100;

    public SimulationDriver(GameState gs, Strategy[] strategies) {
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
        System.out.println("Average Points per Round (Round " + Integer.toString(gs.getRoundNumber()) + "): "
                + Double.toString(avgPointsPerRound()));
        System.out.println("Overall Points: " + Integer.toString(gs.getPlayerScores()[gs.getCurrentPlayer()]));
    }

    private double avgPointsPerRound() {
        return gs.getPlayerScores()[gs.getCurrentPlayer()] / gs.getRoundNumber();
    }
}
