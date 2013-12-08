package kth.game.othello.score;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

public class ScoreImpl extends Observable implements Score, Observer {
	private List<ScoreItem> playersScore;

	/**
	 * Constructor for the score implementation.
	 * 
	 * @param players
	 *            A list of the players, playing the game
	 * @param board
	 *            The board that the game is played on
	 */
	public ScoreImpl(List<Player> players, Board board) {
		playersScore = new ArrayList<ScoreItem>();
		initScore(board, players);
	}

	/**
	 * Initializes the score on the board. Creates a new score item for each
	 * player and then counts how many pieces each player has.
	 * 
	 * @param board
	 *            The board that is played on.
	 */
	private void initScore(Board board, List<Player> players) {
		for (Player player : players) {
			playersScore.add(new ScoreItem(player.getId(), 0));
		}
		for (Node node : board.getNodes()) {
			if (node.getOccupantPlayerId() != null) {
				for (ScoreItem playerScore : playersScore) {
					if (playerScore.getPlayerId().equals(
							node.getOccupantPlayerId())) {
						playerScore.setScore(playerScore.getScore() + 1);
					}
				}
			}
		}
	}

	@Override
	public void addObserver(Observer observer) {
		super.addObserver(observer);
	}

	@Override
	public List<ScoreItem> getPlayersScore() {
		return playersScore;
	}

	@Override
	public int getPoints(String playerId) {
		for (ScoreItem playerScore : playersScore)
			if (playerScore.getPlayerId().equals(playerId)) {
				return playerScore.getScore();
			}
		// Returns -1 if there is no ScoreItem with the corresponding player id
		return -1;
	}

	/**
	 * Notifies the observers for the Score that it has changed.
	 */
	@Override
	public void update(Observable o, Object arg) {
		List<String> updatedScorePlayerIds = new ArrayList<String>();
		Node node = ((Node) o);
		if (arg != null) {
			for (int i = 0; i < playersScore.size(); i++) {
				ScoreItem score = playersScore.get(i);
				if (score.getPlayerId().equals(arg)) {
					score.setScore(score.getScore() - 1);
					playersScore.set(i, score);
					updatedScorePlayerIds.add(score.getPlayerId());
				}
			}
		}
		for (int i = 0; i < playersScore.size(); i++) {
			ScoreItem score = playersScore.get(i);
			if (score.getPlayerId().equals(node.getOccupantPlayerId())) {
				score.setScore(score.getScore() + 1);
				playersScore.set(i, score);
				updatedScorePlayerIds.add(score.getPlayerId());
			}
		}
		setChanged();
		notifyObservers(updatedScorePlayerIds);

	}
}
