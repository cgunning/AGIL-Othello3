package kth.game.othello.score;

/**
 * An instance of this class contains the score of a player.
 */
public class ScoreItem {

	private String playerId;
	private int score;

	public ScoreItem(String playerId, int score) {
		this.playerId = playerId;
		this.score = score;
	}

	/**
	 * @return the playerId
	 */
	public String getPlayerId() {
		return playerId;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Set the score of the ScoreItem
	 * 
	 * @param score
	 *            The new score to be set
	 */
	void setScore(int score) {
		this.score = score;
	}

}
