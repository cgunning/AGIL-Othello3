package kth.game.othello;

import java.util.List;

import kth.game.othello.board.Node;

public class RulesImpl implements Rules {

	private RuleHandler ruleHandler;

	RulesImpl(BoardHandler boardHandler) {
		this.ruleHandler = new RuleHandler(boardHandler);
	}

	@Override
	public List<Node> getNodesToSwap(String playerId, String nodeId) {
		return ruleHandler.getNodesToSwap(playerId, nodeId);
	}

	@Override
	public boolean hasValidMove(String playerId) {
		return ruleHandler.hasValidMove(playerId);
	}

	@Override
	public boolean isMoveValid(String playerId, String nodeId) {
		return ruleHandler.isMoveValid(playerId, nodeId);
	}
}
