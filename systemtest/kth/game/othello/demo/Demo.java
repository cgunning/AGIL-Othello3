package kth.game.othello.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kth.game.othello.Othello;
import kth.game.othello.OthelloCreatorImpl;
import kth.game.othello.OthelloFactory;
import kth.game.othello.board.Board;
import kth.game.othello.board.BoardCreatorImpl;
import kth.game.othello.board.Node;
import kth.game.othello.board.NodeCreatorImpl;
import kth.game.othello.board.factory.BoardFactory;
import kth.game.othello.player.Player;
import kth.game.othello.player.Player.Type;
import kth.game.othello.player.PlayerCreator;
import kth.game.othello.player.PlayerCreatorImpl;
import kth.game.othello.player.movestrategy.FirstPickStrategy;
import kth.game.othello.player.movestrategy.MostTurnedNodesStrategy;
import kth.game.othello.player.movestrategy.MoveStrategy;
import kth.game.othello.player.movestrategy.RandomStrategy;

import org.junit.Assert;
import org.junit.Test;

public class Demo {

	private BoardFactory getBoardFactory() {
		return new BoardFactory(new NodeCreatorImpl(), new BoardCreatorImpl());
	}

	private MoveStrategy getNewMoveStrategy() {
		List<MoveStrategy> listOfMoveStrategies = new ArrayList<MoveStrategy>();
		Random random = new Random();
		listOfMoveStrategies.add(new RandomStrategy());
		listOfMoveStrategies.add(new MostTurnedNodesStrategy());
		listOfMoveStrategies.add(new FirstPickStrategy());
		return listOfMoveStrategies.get(random.nextInt(listOfMoveStrategies
				.size()));
	}

	private OthelloFactory getOthelloFactory() {
		return new OthelloFactory(new OthelloCreatorImpl(), getBoardFactory(),
				new PlayerCreatorImpl());
	}

	private PlayerCreator getPlayerCreator() {
		return new PlayerCreatorImpl();
	}

	private void makeNumberOfComputerMoves(int numberOfMoves, Othello othello) {
		for (int i = 0; i < numberOfMoves; i++) {
			Assert.assertEquals(Type.COMPUTER, othello.getPlayerInTurn()
					.getType());
			othello.move();
		}
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
	public void Demo4() {
		System.out.println("Demo 4");
		Othello othello = getOthelloFactory()
				.createComputerGameOnClassicalBoard();
		othello.start(othello.getPlayers().get(0).getId());
		while (othello.isActive()) {
			makeNumberOfComputerMoves(20, othello);
			othello.getPlayers().get(0).setMoveStrategy(getNewMoveStrategy());
		}
		int s1 = othello.getScore().getPoints(
				othello.getPlayers().get(0).getId());
		int s2 = othello.getScore().getPoints(
				othello.getPlayers().get(1).getId());
		System.out.println("Player 1 score:  " + s1 + " Player 2 score: " + s2);
		System.out.println();
	}

	@Test
	public void Demo5() {
		System.out.println("Demo 5");
		Othello othello = getOthelloFactory().createHumanGameOnOriginalBoard();
		Player p1 = othello.getPlayers().get(0);
		Player p2 = othello.getPlayers().get(1);

		othello.start(p1.getId());

		for (int i = 0; i < 4; i++) {
			makeAHumanMove(othello, p1);
			makeAHumanMove(othello, p2);
		}
		int s1 = othello.getScore().getPoints(
				othello.getPlayers().get(0).getId());
		int s2 = othello.getScore().getPoints(
				othello.getPlayers().get(1).getId());
		System.out.println("Player 1 score:  " + s1 + " Player 2 score: " + s2);
		System.out.println();
	}

	@Test
	public void Demo6() {
		System.out.println("Demo 6");
		BoardFactory boardFactory = getBoardFactory();
		List<Player> players = new ArrayList<Player>();
		players.add(getPlayerCreator().createComputerPlayer("black"));
		players.add(getPlayerCreator().createComputerPlayer("white"));
		players.add(getPlayerCreator().createComputerPlayer("orange"));
		int boardSize = 11;
		Board board = boardFactory.getDiamondBoard(players, boardSize);
		Othello othello = getOthelloFactory().createGame(board, players);
		othello.start();
		while (othello.isActive()) {
			othello.move();
		}

		Assert.assertFalse(othello.isActive());
		int s1 = othello.getScore().getPoints(
				othello.getPlayers().get(0).getId());
		int s2 = othello.getScore().getPoints(
				othello.getPlayers().get(1).getId());
		int s3 = othello.getScore().getPoints(
				othello.getPlayers().get(2).getId());
		System.out.println("Player 1 score:  " + s1 + " Player 2 score: " + s2
				+ " Player 3 score: " + s3);
		System.out.println();
	}
}
