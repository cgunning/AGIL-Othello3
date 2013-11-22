package kth.game.othello;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kth.game.othello.player.Player;

/**
 * Class that handles the player for an Othello game.
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 * 
 */
class PlayerHandler {

	ArrayList<Player> players;
	Player playerInTurn;

	PlayerHandler(List<Player> players) {
		// TODO - Player in turn
		playerInTurn = players.get(0);
		this.players = new ArrayList<Player>();
		this.players.addAll(players); 
	}

	/**
	 * Method for getting a Player from an Id
	 * 
	 * @param playerId
	 *            Id of the player
	 * @param players
	 *            List of all the players
	 * @return the Player corresponding to the right Id
	 */
	Player getPlayerFromId(String playerId) {
		for (Player player : players)
			if (player.getId().equals(playerId))
				return player;
		return null;
	}

	/**
	 * Method to return the player in turn
	 * 
	 * @return The Id of the player in turn
	 */
	Player getPlayerInTurn() {
		return playerInTurn;
	}

	/**
	 * Set the player in turn
	 * 
	 * @param player
	 *            Player to be set in turn
	 */
	void setPlayerInTurn(Player player) {
		playerInTurn = player;
	}

	/**
	 * Method for getting the opponent
	 * 
	 * @param currentPlayerId
	 *            Id for the player in turn
	 * @return the opponent
	 */
	Player getOpponent(String playerId) {
		for (Player player : players)
			if (!player.getId().equals(playerId))
				return player;
		return null;
	}

	void changePlayerInTurn() {
		playerInTurn = getOpponent(playerInTurn.getId());
	}

	/**
	 * Method to return the players in a game
	 * 
	 * @return The players in a game
	 */
	List<Player> getPlayers() {
		return players;
	}

	Player getRandomPlayer() {
		Random rand = new Random();
		return players.get(rand.nextInt(players.size()));
	}

}
