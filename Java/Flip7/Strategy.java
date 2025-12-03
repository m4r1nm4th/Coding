
public interface Strategy {

    public PlayerAction nextMove();

    public void setGameState(GameState gs);

}
