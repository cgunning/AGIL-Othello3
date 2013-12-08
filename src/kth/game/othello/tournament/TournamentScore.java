package kth.game.othello.tournament;

import java.util.ArrayList;
import java.util.HashMap;

import kth.game.othello.Othello;
import kth.game.othello.player.Player;
import kth.game.othello.score.ScoreItem;

/**
 * The responsibility of this class is to manage the score of a tournament
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 * 
 */
public class TournamentScore {

	HashMap<String, Integer> scoreTable;
	ArrayList<Player> players;

	/**
	 * Creates an instance of TournamentScore
	 * 
	 * @param players
	 *            the players in the tournament
	 */
	TournamentScore(ArrayList<Player> players) {
		this.scoreTable = new HashMap<String, Integer>();
		this.players = players;
		for (Player player : players) {
			scoreTable.put(player.getId(), 0);
		}
	}

	/**
	 * Updates the score of the tournament
	 * 
	 * @param game
	 *            the finished game that is supposed to be count into the score
	 *            of the tournament
	 */
	void updateScore(Othello game) {
		ArrayList<ScoreItem> scores = (ArrayList<ScoreItem>) game.getScore()
				.getPlayersScore();

		ScoreItem bestScore = null;
		if (scores.get(0).getScore() > scores.get(1).getScore())
			bestScore = scores.get(0);
		else if (scores.get(0).getScore() < scores.get(1).getScore())
			bestScore = scores.get(1);

		if (bestScore == null) {
			scoreTable.put(scores.get(0).getPlayerId(),
					scoreTable.get(scores.get(0).getPlayerId()) + 1);
			scoreTable.put(scores.get(1).getPlayerId(),
					scoreTable.get(scores.get(1).getPlayerId()) + 1);
		} else {
			scoreTable.put(bestScore.getPlayerId(),
					scoreTable.get(bestScore.getPlayerId()) + 3);
		}
	}

	/**
	 * Prints the score of the tournament
	 */
	void printTournamentScore() {
		int i = 1;
		while (scoreTable.size() > 0) {
			int bestScore = 0;
			String bestPlayerId = "";
			for (String playerId : scoreTable.keySet()) {
				if (scoreTable.get(playerId) >= bestScore) {
					bestScore = scoreTable.get(playerId);
					bestPlayerId = playerId;
				}
			}
			String playerName = "";

			for (Player player : players) {
				if (player.getId().equals(bestPlayerId)) {
					playerName = player.getName();
				}
			}
			System.out.println(i + ": " + playerName + " - " + bestScore);
			scoreTable.remove(bestPlayerId);
			i++;
		}
	}
}
