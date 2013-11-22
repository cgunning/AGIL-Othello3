package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

public class RulesImpl implements Rules {
	
	private BoardHandler boardHandler;
	private RuleHandler ruleHandler;
	
	private RulesImpl(BoardHandler boardHandler, RuleHandler ruleHandler) {
		this.boardHandler = boardHandler;
		this.ruleHandler = ruleHandler;
	}
	
	RulesImpl(BoardHandler boardHandler) {
		this.boardHandler = boardHandler;
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
