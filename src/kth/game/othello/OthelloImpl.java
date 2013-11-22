package kth.game.othello;

import java.util.List;
import java.util.Random;

import kth.game.othello.board.Board;
import kth.game.othello.board.BoardCreator;
import kth.game.othello.board.BoardCreatorImpl;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;
import kth.game.othello.score.Score;

/**
 * This class represents an Othello game.
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 * 
 */
public class OthelloImpl implements Othello {

	private MoveHandler moveHandler;
	private PlayerHandler playerHandler;
	private BoardHandler boardHandler;
	private RulesImpl rules;

	/**
	 * Creates an Othello game
	 * 
	 * @param blackPlayer
	 *            The black player
	 * @param whitePlayer
	 *            The white player
	 * @param board
	 *            Board to be played at
	 */
	public OthelloImpl(List<Player> players, Board board) {
		playerHandler = new PlayerHandler(players);
		boardHandler = new BoardHandler(board, new BoardCreatorImpl());
		rules = new RulesImpl(boardHandler);
		moveHandler = new MoveHandler(boardHandler, rules);
	}

	@Override
	public Board getBoard() {
		return boardHandler.getBoard();
	}

	@Override
	public List<Node> getNodesToSwap(String playerId, String nodeId) {
		return rules.getNodesToSwap(playerId, nodeId);
	}

	@Override
	public Player getPlayerInTurn() {
		return playerHandler.getPlayerInTurn();
	}

	@Override
	public List<Player> getPlayers() {
		return playerHandler.getPlayers();
	}

	@Override
	public boolean hasValidMove(String playerId) {
		return rules.hasValidMove(playerId);
	}

	@Override
	public boolean isActive() {
		for (Player player : playerHandler.getPlayers()) {
			if (rules.hasValidMove(player.getId()))
				return true;
		}
		return false;
	}

	@Override
	public boolean isMoveValid(String playerId, String nodeId) {
		return rules.isMoveValid(playerId,	nodeId);
	}

	@Override
	public List<Node> move() {
		List<Node> nodesToSwap = moveHandler.move(playerHandler
				.getPlayerInTurn().getId());
		playerHandler.changePlayerInTurn();
		return nodesToSwap;

	}

	@Override
	public List<Node> move(String playerId, String nodeId)
			throws IllegalArgumentException {
		List<Node> nodesToSwap = moveHandler.move(playerId, nodeId);
		playerHandler.changePlayerInTurn();
		return nodesToSwap;
	}

	@Override
	public void start() {
		Player player = playerHandler.getRandomPlayer();
		start(player.getId());
	}

	@Override
	public void start(String playerId) {
		playerHandler.setPlayerInTurn(playerHandler.getPlayerFromId(playerId));
	}

	@Override
	public Score getScore() {
		// TODO Auto-generated method stub
		return null;
	}
}
