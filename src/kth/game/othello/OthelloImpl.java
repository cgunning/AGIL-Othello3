package kth.game.othello;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.observer.MoveObserver;
import kth.game.othello.player.Player;
import kth.game.othello.player.Player.Type;
import kth.game.othello.player.movestrategy.MoveStrategy;
import kth.game.othello.score.Score;
import kth.game.othello.score.ScoreImpl;

/**
 * This class represents an Othello game.
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 * 
 */
public class OthelloImpl extends Observable implements Othello {

	private UUID id;
	private MoveHandler moveHandler;
	private PlayerHandler playerHandler;
	private BoardHandler boardHandler;
	private RulesImpl rules;
	private Score score;
	private MoveObserver moveObserver;

	/**
	 * FOR TESTING PURPOSE ONLY
	 */
	private OthelloImpl(MoveHandler moveHandler, PlayerHandler playerHandler,
			BoardHandler boardHandler, RulesImpl rules, Score score) {
		this.id = UUID.randomUUID();
		this.moveHandler = moveHandler;
		this.playerHandler = playerHandler;
		this.boardHandler = boardHandler;
		this.rules = rules;
		this.score = score;

		moveObserver = new MoveObserver();

	}

	/**
	 * Creates an Othello game
	 * 
	 * @param players
	 *            The list of players in the othello game
	 * @param board
	 *            Board to be played at
	 */
	public OthelloImpl(List<Player> players, Board board) {
		this.id = UUID.randomUUID();
		this.playerHandler = new PlayerHandler(players);
		this.boardHandler = new BoardHandler(board);
		this.rules = new RulesImpl(boardHandler);
		this.moveHandler = new MoveHandler(boardHandler, rules);
		this.moveObserver = new MoveObserver();
		this.score = new ScoreImpl(players, board);
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
		return rules.isMoveValid(playerId, nodeId);
	}

	@Override
	public List<Node> move() throws IllegalStateException {
		if (playerHandler.getPlayerInTurn().getType() != Type.COMPUTER) {
			throw new IllegalStateException();
		}
		MoveStrategy moveStrategy = playerHandler.getPlayerInTurn()
				.getMoveStrategy();
		Node node = moveStrategy.move(playerHandler.getPlayerInTurn().getId(),
				rules, boardHandler.getBoard());

		if (node == null) {
			playerHandler.changePlayerInTurn();
			return new ArrayList<Node>();
		}

		return move(playerHandler.getPlayerInTurn().getId(), node.getId());

	}

	@Override
	public List<Node> move(String playerId, String nodeId)
			throws IllegalArgumentException {
		List<Node> nodesToSwap = moveHandler.move(playerId, nodeId);
		playerHandler.changePlayerInTurn();
		moveObserver.setChanged();
		moveObserver.notifyObservers(nodesToSwap);
		checkIsGameFinished();
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
		for (Node node : boardHandler.getBoard().getNodes()) {
			node.addObserver((Observer) score);
		}
	}

	@Override
	public Score getScore() {
		return score;
	}

	@Override
	public void addGameFinishedObserver(Observer observer) {
		addObserver(observer);
	}

	@Override
	public void addMoveObserver(Observer observer) {
		moveObserver.addObserver(observer);
	}

	@Override
	public String getId() {
		return id.toString();
	}

	/**
	 * Check if the game is finished. If it is, the gameFinishedObserver will be
	 * notified.
	 */
	private void checkIsGameFinished() {
		if (!isActive()) {
			setChanged();
			notifyObservers();
		}
	}
}
