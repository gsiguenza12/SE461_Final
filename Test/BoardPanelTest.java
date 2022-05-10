import static org.junit.Assert.*;

import org.junit.Test;
import org.psnbtech.BoardPanel;

public class BoardPanelTest {

	@Test
	public void testClearBoard() {
		BoardPanel bp = new BoardPanel(null); // no snake game as argument
		
		bp.setTile(0, 0, bp.getTile(BoardPanel.ROW_COUNT-20,BoardPanel.COL_COUNT-20));
		
		bp.clearBoard();
		
		assertEquals(bp.getTile(0, 0), null);
	}		
}
