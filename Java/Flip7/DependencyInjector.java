public class DependencyInjector {
    public static void inject(Game g, Strategy[] strats, GameDriver gd) {
        GameState gs = g.getGameState();
        for (Strategy strategy : strats) {
            strategy.setGameState(gs);
        }
        gd.setGameState(gs);
    }
}
