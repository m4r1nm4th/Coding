public class Runner {
    public static void main(String[] args) {
        run5();
    }

    private static void run() {
        Strategy[] strategies = { new ThreeCardStrategy(null), new GreedyPointsStrategy(null, 23) };
        Game g = new Game(strategies);
        g.runGame();
    }

    private static void run2() {
        for (int i = 13; i < 50; i++) {
            Strategy st = new GreedyPointsStrategy(null, i);
            System.out.println("GreedyStrategy (target: " + Integer.toString(i) + ")");
            simulateAVGPointsPerRound(st, 100000);
        }
    }

    private static void run3() {
        compareStrategies(new GreedyPointsStrategy(null, 23), new ThreeCardStrategy(null), 100000);
    }

    private static void run4() {
        Strategy[] strategies = { new ThreeCardStrategy(null), new GreedyPointsStrategy(null, 23),
                new MaxExpectationStrategy(null) };
        Game g = new Game(strategies);
        g.runGame();
    }

    private static void run5() {
        compareStrategies(new GreedyPointsStrategy(null, 23), new MaxExpectationStrategy(null), 100000000);
    }

    private static void simulateAVGPointsPerRound(Strategy st, int rounds) {
        Strategy[] strategies = { st };
        Game g = new Game(strategies);
        SimulationDriver sd = new SimulationDriver(null, strategies);
        DependencyInjector.inject(g, strategies, sd);
        sd.setRounds(rounds);
        g.runGame(sd);
    }

    private static void compareStrategies(Strategy st1, Strategy st2, int rounds) {
        Strategy[] strategies = { st1, st2 };
        Game g = new Game(strategies);
        CompareStrategyDriver csd = new CompareStrategyDriver(g.getGameState(), strategies);
        csd.setRounds(rounds);
        g.runGame(csd);
    }
}
