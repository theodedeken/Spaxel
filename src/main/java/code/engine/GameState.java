package code.engine;

import code.Constants;
import code.math.VectorD;

/**
 * Represents the state of the current game
 * 
 * Created by theo on 24/06/17.
 */
public class GameState {
    private int score;
    private int gameTime;
    private long timeOverflow;
    private boolean debug;
    private boolean logging;
    private VectorD cursorFollow;
    private VectorD screenOffset;
    private double updateTime;

    public GameState() {
        super();
        this.cursorFollow = new VectorD(Constants.HALF_GAME_WIDTH, Constants.HALF_GAME_HEIGHT);
        this.screenOffset = new VectorD(0, 0);
    }

    public int getScore() {
        return score;
    }

    public void addScore(int value) {
        score += value;
    }

    public int getGameTime() {
        return gameTime;
    }

    public boolean isDebug() {
        return debug;
    }

    public void toggleDebug() {
        debug = !debug;
    }

    public boolean isLogging() {
        return logging;
    }

    public void toggleLogging() {
        logging = !logging;
    }

    public void addTime(long updateTime) {
        timeOverflow += updateTime;
        gameTime += timeOverflow / Constants.NS_PER_SECOND;
        timeOverflow %= Constants.NS_PER_SECOND;
    }

    public VectorD getCursorFollow() {
        return cursorFollow;
    }

    public void setCursorFollow(VectorD cursorFollow) {
        this.cursorFollow = cursorFollow;
    }

    /**
     * @return the screenOffset
     */
    public VectorD getScreenOffset() {
        return screenOffset;
    }

    /**
     * @param screenOffset the screenOffset to set
     */
    public void setScreenOffset(VectorD screenOffset) {
        this.screenOffset = screenOffset;
    }

    /**
     * @return the updateTime
     */
    public double getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime the updateTime to set
     */
    public void setUpdateTime(double updateTime) {
        this.updateTime = updateTime;
    }
}
