import java.awt.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;

public class SnakeGameTestClass implements Serializable {
    protected static final long serialVersionUID = 6678292058307426314L;
    protected static final long FRAME_TIME = 1000L / 50L;
    protected static final int MIN_SNAKE_LENGTH = 5;
    protected static final int MAX_DIRECTIONS = 3;
    protected BoardPanel board;

    public BoardPanel getBoard() {
        return board;
    }

    public void setBoard(BoardPanel board) {
        this.board = board;
    }

    protected SidePanel side;

    public SidePanel getSide() {
        return side;
    }

    public void setSide(SidePanel side) {
        this.side = side;
    }

    protected Random random;

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    protected Clock logicTimer;

    public Clock getLogicTimer() {
        return logicTimer;
    }

    public void setLogicTimer(Clock logicTimer) {
        this.logicTimer = logicTimer;
    }

    protected boolean isNewGame;

    public boolean isNewGame() {
        return isNewGame;
    }

    public void setNewGame(boolean newGame) {
        isNewGame = newGame;
    }

    protected boolean isGameOver;

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    protected boolean isPaused;

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    protected LinkedList<Point> snake;

    public LinkedList<Point> getSnake() {
        return snake;
    }

    public void setSnake(LinkedList<Point> snake) {
        this.snake = snake;
    }

    protected LinkedList<Direction> directions;

    public LinkedList<Direction> getDirections() {
        return directions;
    }

    public void setDirections(LinkedList<Direction> directions) {
        this.directions = directions;
    }

    protected int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    protected int fruitsEaten;

    public int getFruitsEaten() {
        return fruitsEaten;
    }

    public void setFruitsEaten(int fruitsEaten) {
        this.fruitsEaten = fruitsEaten;
    }

    protected int nextFruitScore;

    public int getNextFruitScore() {
        return nextFruitScore;
    }

    public void setNextFruitScore(int nextFruitScore) {
        this.nextFruitScore = nextFruitScore;
    }

    public SnakeGameTestClass() {
    }
}