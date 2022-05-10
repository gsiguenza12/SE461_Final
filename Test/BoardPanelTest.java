import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;
import org.psnbtech.BoardPanel;

public class BoardPanelTest {

	@Test
	public void testClearBoard() {
		BoardPanel bp = new BoardPanel(null); // no snake game as argument
		
		bp.setTile(0, 0, bp.getTile(BoardPanel.ROW_COUNT-20,BoardPanel.COL_COUNT-20));
		
		bp.clearBoard();
		
		Point pnt = new Point(BoardPanel.TILE_SIZE, BoardPanel.ROW_COUNT-15);
		
		bp.setTile(pnt, bp.getTile(0, 0));
		
		assertEquals(bp.getTile(0, 0), null);
	}		
}
