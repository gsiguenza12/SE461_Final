import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;


public class SnakeGameTest {
    public SnakeGame snake;
    BoardPanel B;

    @Before
    public void setUp() throws Exception {
        snake = new SnakeGame();
        B = new BoardPanel(snake);
    }

    @Test
    public void testNull() {
        assertNotNull(snake);
    }

    @Test
    public void testEnums(){

            Direction.values();
            TileType.values();
    }

    @Test
    public void testClearBoard(){
        B.clearBoard();
    }

    @Test
    public void testUpdateSnake(){
        LinkedList<Direction> Dlist = new LinkedList<>();
        Dlist.add(Direction.North);
        Dlist.add(Direction.East);
        Dlist.add(Direction.West);
        Dlist.add(Direction.South);
        snake.setDirections(Dlist);
        snake.updateSnake();
    }
}
