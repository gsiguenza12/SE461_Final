import static org.junit.Assert.*;

import java.awt.Graphics;

import org.junit.Before;
import org.junit.Test;
import org.psnbtech.BoardPanel;
import org.psnbtech.SnakeGame;

public class BoardPanelTest {

	@Test
	public void testClearBoard() {
		BoardPanel bp = new BoardPanel(null); // no snake game as argument
		
		bp.setTile(0, 0, bp.getTile(5,5));
		
		bp.clearBoard();
		
		assertEquals(bp.getTile(0, 0), null);
		
		try
		{
			Graphics g = (Graphics) bp.getGraphics();
			
			assertNull(g);
			
		} finally {
			
		}

		
		
		
	}		
}
