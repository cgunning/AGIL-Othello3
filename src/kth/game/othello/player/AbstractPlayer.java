package kth.game.othello.player;

import java.util.UUID;

import kth.game.othello.player.movestrategy.MoveStrategy;

/**
 * Abstract class for player
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 *
 */
public abstract class AbstractPlayer implements Player {
	
	private UUID id;
	private String name;
	private Type type;
	private MoveStrategy moveStrategy;
	
	/**
	 * Creates a new abstract player
	 * @param name Name for the player
	 * @param type Type of the player
	 */
	public AbstractPlayer(String name, Type type) {
		this.name = name;
		this.id = UUID.randomUUID();
		this.type = type;
	}
	
	@Override
	public String getId() {
		return id.toString();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public MoveStrategy getMoveStrategy() {
		return moveStrategy;
	}

	@Override
	public void setMoveStrategy(MoveStrategy moveStrategy) {
		this.moveStrategy = moveStrategy;
		
	}

}
