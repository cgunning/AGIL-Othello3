package kth.game.othello.board;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Test the OthelloBoard class
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 *
 */
public class BoardTest {
	
	/**
	 * 
	 */
	@Test
	public void testBoard() {
		BoardImpl board = new BoardImpl(8);
		Assert.assertEquals(64, board.getNodes().size());
	}
}
