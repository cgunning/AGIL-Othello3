package kth.game.othello;

import kth.game.othello.board.BoardCreatorImpl;
import kth.game.othello.board.Node;
import kth.game.othello.board.NodeCreatorImpl;
import kth.game.othello.board.factory.BoardFactory;
import kth.game.othello.player.Player;
import kth.game.othello.player.Player.Type;
import kth.game.othello.player.PlayerCreatorImpl;

import org.junit.Assert;
import org.junit.Test;

public class OthelloLab1IT {

	private Object getNumberOfOccupiedNodes(Othello othello) {
		int occupiedNodesCounter = 0;
		for (Node node : othello.getBoard().getNodes()) {
			if (node.isMarked()) {
				occupiedNodesCounter++;
			}
		}
		return occupiedNodesCounter;
	}

	private BoardFactory getBoardFactory() {
		return new BoardFactory(new NodeCreatorImpl(), new BoardCreatorImpl());
	}

	private OthelloFactory getOthelloFactory() {
		return new OthelloFactory(new OthelloCreatorImpl(), getBoardFactory(),
				new PlayerCreatorImpl());
	}

	private void makeAHumanMove(Othello othello, Player human) {
		for (Node node : othello.getBoard().getNodes()) {
			if (othello.isMoveValid(human.getId(), node.getId())) {
				othello.move(human.getId(), node.getId());
				return;
			}
		}
		throw new IllegalStateException();
	}

	@Test
	public void someMovesBetweenAComputerAndHumanTest() {
		Othello othello = getOthelloFactory()
				.createHumanVersusComputerGameOnOriginalBoard();
		Player human = null;
		if (othello.getPlayers().get(0).getType() == Type.COMPUTER) {
			human = othello.getPlayers().get(1);
		} else {
			human = othello.getPlayers().get(0);
		}
		othello.start(human.getId());
		makeAHumanMove(othello, human);
		othello.move();
		makeAHumanMove(othello, human);
		othello.move();
		makeAHumanMove(othello, human);

		othello.move();
		Assert.assertEquals(10, getNumberOfOccupiedNodes(othello));
	}

	// @Test
	// public void twoComputerOnAClassicalBoardTest() {
	// Othello othello =
	// getOthelloFactory().createComputerGameOnClassicalBoard();
	// othello.start(othello.getPlayers().get(0).getId());
	//
	// while (othello.isActive()) {
	// Assert.assertEquals(Type.COMPUTER, othello.getPlayerInTurn().getType());
	// othello.move();
	// }
	// }

}
