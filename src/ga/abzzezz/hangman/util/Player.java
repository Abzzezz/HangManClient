package ga.abzzezz.hangman.util;


public class Player {

    private final String playerName;
    private int playerScore;

    public Player(final String playerName, final int playerScore) {
        this.playerScore = playerScore;
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }


}