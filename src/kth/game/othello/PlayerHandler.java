package kth.game.othello;

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

	List<Player> players;
	Player playerInTurn;

	/**
	 * Creates a PlayerHandler that handles the players of an othello game
	 * 
	 * @param players
	 *            The players that plays the game
	 */
	PlayerHandler(List<Player> players) {
		playerInTurn = players.get(0);
		this.players = players;
	}

	/**
	 * Method for getting a Player from an Id
	 * 
	 * @param playerId
	 *            Id of the player
	 * @return the Player corresponding to the right Id
	 */
	Player getPlayerFromId(String playerId) throws IllegalArgumentException {
		for (Player player : players)
			if (player.getId().equals(playerId))
				return player;
		throw new IllegalArgumentException("No player with ID: " + playerId);
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
	 * Method for getting the next player in turn
	 * 
	 * @param currentPlayerId
	 *            Id for the player in turn
	 * @return The next player in turn
	 */
	Player getNextPlayer(String playerId) throws IllegalArgumentException {
		int i = players.indexOf(getPlayerFromId(playerId));
		return players.get((i + 1) % players.size());
	}

	/**
	 * Changes the player in turn
	 */
	void changePlayerInTurn() {
		playerInTurn = getNextPlayer(playerInTurn.getId());
	}

	/**
	 * Method to return the players in a game
	 * 
	 * @return The players in a game
	 */
	List<Player> getPlayers() {
		return players;
	}

	/**
	 * Gets a random player from the game
	 * 
	 * @return A random player of the game
	 */
	Player getRandomPlayer() {
		Random rand = new Random();
		return players.get(rand.nextInt(players.size()));
	}

}
