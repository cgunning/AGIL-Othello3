package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.BoardCreator;
import kth.game.othello.board.BoardCreatorImpl;
import kth.game.othello.board.BoardImpl;
import kth.game.othello.board.Node;
import kth.game.othello.board.NodeImpl;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class BoardHandlerTest {

	@Test
	public void testUpdateMovesOnBoard() {
		Board board = mock(BoardImpl.class);
		
		BoardCreator boardCreator = mock(BoardCreatorImpl.class);
		List<Node> nodes = new ArrayList<Node>();
		List<Node> nodesToSwap = new ArrayList<Node>();
		
		for(int i = 0; i < 64; i++) {
			Node node = mock(NodeImpl.class);
			when(node.getId()).thenReturn(i/8 + ":" + i%8);
			when(node.getXCoordinate()).thenReturn(i/8);
			when(node.getYCoordinate()).thenReturn(i%8);
			nodes.add(node);
		}
		
		
		
		when(board.getNodes()).thenReturn(nodes);
		when(boardCreator.createBoard(nodes)).thenReturn(board);
		
		BoardHandler boardHandler = new BoardHandler(board, boardCreator);
		
		
	}
}
