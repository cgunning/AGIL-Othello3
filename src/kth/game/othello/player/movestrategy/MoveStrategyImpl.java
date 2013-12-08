package kth.game.othello.player.movestrategy;

import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

public abstract class MoveStrategyImpl implements MoveStrategy {

	private String name;

	MoveStrategyImpl(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public abstract Node move(String playerId, Rules rules, Board board);
}
