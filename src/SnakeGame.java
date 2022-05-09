import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;

/**
 * The {@code SnakeGame} class is responsible for handling much of the game's logic.
 * @author Brendan Jones
 *
 */
public class SnakeGame extends JFrame {

	protected final SnakeGameTestClass snakeGameTestClass = new SnakeGameTestClass();

	/**
	 * Creates a new SnakeGame instance. Creates a new window,
	 * and sets up the controller input.
	 */
	@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
	public SnakeGame() {
		super("Snake Remake");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
				
		/*
		 * Initialize the game's panels and add them to the window.
		 */
		this.snakeGameTestClass.setBoard(new BoardPanel(this));
		this.snakeGameTestClass.setSide(new SidePanel(this));
		
		add(snakeGameTestClass.getBoard(), BorderLayout.CENTER);
		add(snakeGameTestClass.getSide(), BorderLayout.EAST);
		
		/*
		 * Adds a new key listener to the frame to process input. 
		 */
		addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) { // switch node 2

				/*
				 * If the game is not paused, and the game is not over...
				 * 
				 * Ensure that the direction list is not full, and that the most
				 * recent direction is adjacent to North before adding the
				 * direction to the list.
				 */
				case KeyEvent.VK_W:
				case KeyEvent.VK_UP: // node 3
					if(!snakeGameTestClass.isPaused() && !snakeGameTestClass.isGameOver()) {
						if(snakeGameTestClass.getDirections().size() < SnakeGameTestClass.MAX_DIRECTIONS) { //if true,->
							Direction last = snakeGameTestClass.getDirections().peekLast(); //
							if(last != Direction.South && last != Direction.North) { //
								snakeGameTestClass.getDirections().addLast(Direction.North);
							}
						}
					}
					break;

				/*
				 * If the game is not paused, and the game is not over...
				 * 
				 * Ensure that the direction list is not full, and that the most
				 * recent direction is adjacent to South before adding the
				 * direction to the list.
				 */	
				case KeyEvent.VK_S:
				case KeyEvent.VK_DOWN: // node 4
					if(!snakeGameTestClass.isPaused() && !snakeGameTestClass.isGameOver()) {
						if(snakeGameTestClass.getDirections().size() < SnakeGameTestClass.MAX_DIRECTIONS) {
							Direction last = snakeGameTestClass.getDirections().peekLast();
							if(last != Direction.North && last != Direction.South) {
								snakeGameTestClass.getDirections().addLast(Direction.South);
							}
						}
					}
					break;
				
				/*
				 * If the game is not paused, and the game is not over...
				 * 
				 * Ensure that the direction list is not full, and that the most
				 * recent direction is adjacent to West before adding the
				 * direction to the list.
				 */						
				case KeyEvent.VK_A:
				case KeyEvent.VK_LEFT: // node 5
					if(!snakeGameTestClass.isPaused() && !snakeGameTestClass.isGameOver()) {
						if(snakeGameTestClass.getDirections().size() < SnakeGameTestClass.MAX_DIRECTIONS) {
							Direction last = snakeGameTestClass.getDirections().peekLast();
							if(last != Direction.East && last != Direction.West) {
								snakeGameTestClass.getDirections().addLast(Direction.West);
							}
						}
					}
					break;
			
				/*
				 * If the game is not paused, and the game is not over...
				 * 
				 * Ensure that the direction list is not full, and that the most
				 * recent direction is adjacent to East before adding the
				 * direction to the list.
				 */		
				case KeyEvent.VK_D:
				case KeyEvent.VK_RIGHT: // node 6
					if(!snakeGameTestClass.isPaused() && !snakeGameTestClass.isGameOver()) {
						if(snakeGameTestClass.getDirections().size() < SnakeGameTestClass.MAX_DIRECTIONS) {
							Direction last = snakeGameTestClass.getDirections().peekLast();
							if(last != Direction.West && last != Direction.East) {
								snakeGameTestClass.getDirections().addLast(Direction.East);
							}
						}
					}
					break;
				
				/*
				 * If the game is not over, toggle the paused flag and update
				 * the logicTimer's pause flag accordingly.
				 */
				case KeyEvent.VK_P: // node 7
					if(!snakeGameTestClass.isGameOver()) {
						snakeGameTestClass.setPaused(!snakeGameTestClass.isPaused());
						snakeGameTestClass.getLogicTimer().setPaused(snakeGameTestClass.isPaused());
					}
					break;
				
				/*
				 * Reset the game if one is not currently in progress.
				 */
				case KeyEvent.VK_ENTER: // node 8
					if(snakeGameTestClass.isNewGame() || snakeGameTestClass.isGameOver()) {
						resetGame();
					}
					break;
				}
			}
			
		});
		
		/*
		 * Resize the window to the appropriate size, center it on the
		 * screen and display it.
		 */
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * The Serial Version UID.
	 */
	public static long getSerialVersionUID() {
		return SnakeGameTestClass.serialVersionUID;
	}

	/**
	 * The number of milliseconds that should pass between each frame.
	 */
	public static long getFrameTime() {
		return SnakeGameTestClass.FRAME_TIME;
	}

	/**
	 * The minimum length of the snake. This allows the snake to grow
	 * right when the game starts, so that we're not just a head moving
	 * around on the board.
	 */
	public static int getMinSnakeLength() {
		return SnakeGameTestClass.MIN_SNAKE_LENGTH;
	}

	/**
	 * The maximum number of directions that we can have polled in the
	 * direction list.
	 */
	public static int getMaxDirections() {
		return SnakeGameTestClass.MAX_DIRECTIONS;
	}

	/**
	 * Starts the game running.
	 */
	@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
	public void startGame() {
		/*
		 * Initialize everything we're going to be using.
		 */
		this.snakeGameTestClass.setRandom(new Random());
		this.snakeGameTestClass.setSnake(new LinkedList<>());
		this.snakeGameTestClass.setDirections(new LinkedList<>());
		this.snakeGameTestClass.setLogicTimer(new Clock(9.0f));
		this.snakeGameTestClass.setNewGame(true);
		
		//Set the timer to paused initially.
		snakeGameTestClass.getLogicTimer().setPaused(true);

		/*
		 * This is the game loop. It will update and render the game and will
		 * continue to run until the game window is closed.
		 */
		while(true) {
			//Get the current frame's start time.
			long start = System.nanoTime();
			
			//Update the logic timer.
			snakeGameTestClass.getLogicTimer().update();
			
			/*
			 * If a cycle has elapsed on the logic timer, then update the game.
			 */
			if(snakeGameTestClass.getLogicTimer().hasElapsedCycle()) {
				updateGame();
			}
			
			//Repaint the board and side panel with the new content.
			snakeGameTestClass.getBoard().repaint();
			snakeGameTestClass.getSide().repaint();
			
			/*
			 * Calculate the delta time between since the start of the frame
			 * and sleep for the excess time to cap the frame rate. While not
			 * incredibly accurate, it is sufficient for our purposes.
			 */
			long delta = (System.nanoTime() - start) / 1000000L;
			if(delta < SnakeGameTestClass.FRAME_TIME) {
				try {
					Thread.sleep(SnakeGameTestClass.FRAME_TIME - delta);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Updates the game's logic.
	 */
	@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
	public void updateGame() {
		/*
		 * Gets the type of tile that the head of the snake collided with. If 
		 * the snake hit a wall, SnakeBody will be returned, as both conditions
		 * are handled identically.
		 */
		TileType collision = updateSnake();
		
		/*
		 * Here we handle the different possible collisions.
		 * 
		 * Fruit: If we collided with a fruit, we increment the number of
		 * fruits that we've eaten, update the score, and spawn a new fruit.
		 * 
		 * SnakeBody: If we collided with our tail (or a wall), we flag that
		 * the game is over and pause the game.
		 * 
		 * If no collision occurred, we simply decrement the number of points
		 * that the next fruit will give us if it's high enough. This adds a
		 * bit of skill to the game as collecting fruits more quickly will
		 * yield a higher score.
		 */
		if(collision == TileType.Fruit) {
			snakeGameTestClass.setFruitsEaten(snakeGameTestClass.getFruitsEaten() + 1);
			snakeGameTestClass.setScore(snakeGameTestClass.getScore() + snakeGameTestClass.getNextFruitScore());
			spawnFruit();
		} else if(collision == TileType.SnakeBody) {
			snakeGameTestClass.setGameOver(true);
			snakeGameTestClass.getLogicTimer().setPaused(true);
		} else if(snakeGameTestClass.getNextFruitScore() > 10) {
			snakeGameTestClass.setNextFruitScore(snakeGameTestClass.getNextFruitScore() - 1);
		}
	}
	
	/**
	 * Updates the snake's position and size.
	 * @return Tile tile that the head moved into.
	 */
	@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
	public TileType updateSnake() {

		/*
		 * Here we peek at the next direction rather than polling it. While
		 * not game breaking, polling the direction here causes a small bug
		 * where the snake's direction will change after a game over (though
		 * it will not move).
		 */
		Direction direction = snakeGameTestClass.getDirections().peekFirst();
				
		/*
		 * Here we calculate the new point that the snake's head will be at
		 * after the update.
		 */		
		Point head = new Point(snakeGameTestClass.getSnake().peekFirst());
		switch(direction) {
		case North:
			head.y--;
			break;
			
		case South:
			head.y++;
			break;
			
		case West:
			head.x--;
			break;
			
		case East:
			head.x++;
			break;
		}
		
		/*
		 * If the snake has moved out of bounds ('hit' a wall), we can just
		 * return that it's collided with itself, as both cases are handled
		 * identically.
		 */
		if(head.x < 0 || head.x >= BoardPanel.COL_COUNT || head.y < 0 || head.y >= BoardPanel.ROW_COUNT) {
			return TileType.SnakeBody; //Pretend we collided with our body.
		}
		
		/*
		 * Here we get the tile that was located at the new head position and
		 * remove the tail from of the snake and the board if the snake is
		 * long enough, and the tile it moved onto is not a fruit.
		 * 
		 * If the tail was removed, we need to retrieve the old tile again
		 * incase the tile we hit was the tail piece that was just removed
		 * to prevent a false game over.
		 */
		TileType old = snakeGameTestClass.getBoard().getTile(head.x, head.y);
		if(old != TileType.Fruit && snakeGameTestClass.getSnake().size() > SnakeGameTestClass.MIN_SNAKE_LENGTH) {
			Point tail = snakeGameTestClass.getSnake().removeLast();
			snakeGameTestClass.getBoard().setTile(tail, null);
			old = snakeGameTestClass.getBoard().getTile(head.x, head.y);
		}
		
		/*
		 * Update the snake's position on the board if we didn't collide with
		 * our tail:
		 * 
		 * 1. Set the old head position to a body tile.
		 * 2. Add the new head to the snake.
		 * 3. Set the new head position to a head tile.
		 * 
		 * If more than one direction is in the queue, poll it to read new
		 * input.
		 */
		if(old != TileType.SnakeBody) {
			snakeGameTestClass.getBoard().setTile(snakeGameTestClass.getSnake().peekFirst(), TileType.SnakeBody);
			snakeGameTestClass.getSnake().push(head);
			snakeGameTestClass.getBoard().setTile(head, TileType.SnakeHead);
			if(snakeGameTestClass.getDirections().size() > 1) {
				snakeGameTestClass.getDirections().poll();
			}
		}
				
		return old;
	}
	
	/**
	 * Resets the game's variables to their default states and starts a new game.
	 */
	@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
	public void resetGame() {
		/*
		 * Reset the score statistics. (Note that nextFruitPoints is reset in
		 * the spawnFruit function later on).
		 */
		this.snakeGameTestClass.setScore(0);
		this.snakeGameTestClass.setFruitsEaten(0);
		
		/*
		 * Reset both the new game and game over flags.
		 */
		this.snakeGameTestClass.setNewGame(false);
		this.snakeGameTestClass.setGameOver(false);
		
		/*
		 * Create the head at the center of the board.
		 */
		Point head = new Point(BoardPanel.COL_COUNT / 2, BoardPanel.ROW_COUNT / 2);

		/*
		 * Clear the snake list and add the head.
		 */
		snakeGameTestClass.getSnake().clear();
		snakeGameTestClass.getSnake().add(head);
		
		/*
		 * Clear the board and add the head.
		 */
		snakeGameTestClass.getBoard().clearBoard();
		snakeGameTestClass.getBoard().setTile(head, TileType.SnakeHead);
		 
		/*
		 * Clear the directions and add north as the
		 * default direction.
		 */
		snakeGameTestClass.getDirections().clear();
		snakeGameTestClass.getDirections().add(Direction.North);
		
		/*
		 * Reset the logic timer.
		 */
		snakeGameTestClass.getLogicTimer().reset();
		
		/*
		 * Spawn a new fruit.
		 */
		spawnFruit();
	}
	
	/**
	 * Whether or not we're running a new game.
	 */ /**
	 * Gets the flag that indicates whether or not we're playing a new game.
	 * @return The new game flag.
	 */
	public boolean isNewGame() {
		return snakeGameTestClass.isNewGame();
	}
	
	/**
	 * Whether or not the game is over.
	 */ /**
	 * Gets the flag that indicates whether or not the game is over.
	 * @return The game over flag.
	 */
	public boolean isGameOver() {
		return snakeGameTestClass.isGameOver();
	}
	
	/**
	 * Whether or not the game is paused.
	 */ /**
	 * Gets the flag that indicates whether or not the game is paused.
	 * @return The paused flag.
	 */
	public boolean isPaused() {
		return snakeGameTestClass.isPaused();
	}
	
	/**
	 * Spawns a new fruit onto the board.
	 */
	@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
	public void spawnFruit() {
		//Reset the score for this fruit to 100.
		this.snakeGameTestClass.setNextFruitScore(100);

		/*
		 * Get a random index based on the number of free spaces left on the board.
		 */
		int index = snakeGameTestClass.getRandom().nextInt(BoardPanel.COL_COUNT * BoardPanel.ROW_COUNT - snakeGameTestClass.getSnake().size());
		
		/*
		 * While we could just as easily choose a random index on the board
		 * and check it if it's free until we find an empty one, that method
		 * tends to hang if the snake becomes very large.
		 * 
		 * This method simply loops through until it finds the nth free index
		 * and selects uses that. This means that the game will be able to
		 * locate an index at a relatively constant rate regardless of the
		 * size of the snake.
		 */
		int freeFound = -1;
		for(int x = 0; x < BoardPanel.COL_COUNT; x++) {
			for(int y = 0; y < BoardPanel.ROW_COUNT; y++) {
				TileType type = snakeGameTestClass.getBoard().getTile(x, y);
				if(type == null || type == TileType.Fruit) {
					if(++freeFound == index) {
						snakeGameTestClass.getBoard().setTile(x, y, TileType.Fruit);
						break;
					}
				}
			}
		}
	}
	
	/**
	 * The current score.
	 */ /**
	 * Gets the current score.
	 * @return The score.
	 */
	public int getScore() {
		return snakeGameTestClass.getScore();
	}
	
	/**
	 * The number of fruits that we've eaten.
	 */ /**
	 * Gets the number of fruits eaten.
	 * @return The fruits eaten.
	 */
	public int getFruitsEaten() {
		return snakeGameTestClass.getFruitsEaten();
	}
	
	/**
	 * The number of points that the next fruit will award us.
	 */ /**
	 * Gets the next fruit score.
	 * @return The next fruit score.
	 */
	public int getNextFruitScore() {
		return snakeGameTestClass.getNextFruitScore();
	}
	
	/**
	 * Gets the current direction of the snake.
	 * @return The current direction.
	 */
	public Direction getDirection() {
		return snakeGameTestClass.getDirections().peek();
	}
	
	/**
	 * Entry point of the program.
	 * @param args Unused.
	 */
	public static void main(String[] args) {
			SnakeGame snake = new SnakeGame();
		snake.startGame();
	}

	/**
	 * The BoardPanel instance.
	 */
	public BoardPanel getBoard() {
		return snakeGameTestClass.getBoard();
	}

	public void setBoard(BoardPanel board) {
		this.snakeGameTestClass.setBoard(board);
	}

	/**
	 * The SidePanel instance.
	 */
	public SidePanel getSide() {
		return snakeGameTestClass.getSide();
	}

	public void setSide(SidePanel side) {
		this.snakeGameTestClass.setSide(side);
	}

	/**
	 * The random number generator (used for spawning fruits).
	 */
	public Random getRandom() {
		return snakeGameTestClass.getRandom();
	}

	public void setRandom(Random random) {
		this.snakeGameTestClass.setRandom(random);
	}

	/**
	 * The Clock instance for handling the game logic.
	 */
	public Clock getLogicTimer() {
		return snakeGameTestClass.getLogicTimer();
	}

	public void setLogicTimer(Clock logicTimer) {
		this.snakeGameTestClass.setLogicTimer(logicTimer);
	}

	public void setNewGame(boolean newGame) {
		snakeGameTestClass.setNewGame(newGame);
	}

	public void setGameOver(boolean gameOver) {
		snakeGameTestClass.setGameOver(gameOver);
	}

	public void setPaused(boolean paused) {
		snakeGameTestClass.setPaused(paused);
	}

	/**
	 * The list that contains the points for the snake.
	 */
	public LinkedList<Point> getSnake() {
		return snakeGameTestClass.getSnake();
	}

	public void setSnake(LinkedList<Point> snake) {
		this.snakeGameTestClass.setSnake(snake);
	}

	/**
	 * The list that contains the queued directions.
	 */
	public LinkedList<Direction> getDirections() {
		return snakeGameTestClass.getDirections();
	}

	public void setDirections(LinkedList<Direction> directions) {
		this.snakeGameTestClass.setDirections(directions);
	}

	public void setScore(int score) {
		this.snakeGameTestClass.setScore(score);
	}

	public void setFruitsEaten(int fruitsEaten) {
		this.snakeGameTestClass.setFruitsEaten(fruitsEaten);
	}

	public void setNextFruitScore(int nextFruitScore) {
		this.snakeGameTestClass.setNextFruitScore(nextFruitScore);
	}
}
