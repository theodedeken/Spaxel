package code.engine;

/**
 * Created by theo on 24/06/17.
 */
public class GameProperties {
    private int score;
    private int gameTime;
    private long timeOverflow;
    private boolean debug;
    private boolean logging;

    public int getScore() {
        return score;
    }

    public void addScore(int value){
        score += value;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getGameTime() {
        return gameTime;
    }

    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public boolean isLogging() {
        return logging;
    }

    public void setLogging(boolean logging) {
        this.logging = logging;
    }

    public void addTime(long updateTime){
        timeOverflow += updateTime;
        gameTime += timeOverflow / 1000000000;
        timeOverflow %= 1000000000L;
    }
}