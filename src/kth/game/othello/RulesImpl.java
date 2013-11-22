package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

public class RulesImpl implements Rules {
	
	private static int UP = -8, LEFT = -1, RIGHT = 1, DOWN = 8, UP_LEFT = -9,
			UP_RIGHT = -7, DOWN_LEFT = 7, DOWN_RIGHT = 9;
	private static int[] changes = { UP, LEFT, RIGHT, DOWN, UP_LEFT, UP_RIGHT,
			DOWN_LEFT, DOWN_RIGHT };
	private BoardHandler boardHandler;
	private RuleHelper ruleHelper;
	
	private RulesImpl(BoardHandler boardHandler, RuleHelper ruleHelper) {
		this.boardHandler = boardHandler;
		this.ruleHelper = ruleHelper;
	}
	
	RulesImpl(BoardHandler boardHandler) {
		this.boardHandler = boardHandler;
		this.ruleHelper = new RuleHelper();
	}

	@Override
	public List<Node> getNodesToSwap(String playerId, String nodeId) {
		Node node = ruleHelper.getNodeFromId(boardHandler.getBoard(), nodeId);
		List<Node> returnedNodes = new ArrayList<Node>();
		for (int change : changes) {
			List<Node> swappedNodes = ruleHelper.findValidMoveInDirection(
					boardHandler.getBoard(), node.getXCoordinate(),
					node.getYCoordinate(), playerId, change);
			if (swappedNodes != null) {
				returnedNodes.addAll(swappedNodes);
			}
		}
		return returnedNodes;
	}

	@Override
	public boolean hasValidMove(String playerId) {
		List<Node> nodes = boardHandler.getBoard().getNodes();
		for (Node node : nodes) {
			if (!node.isMarked() && isMoveValid(playerId, node.getId())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isMoveValid(String playerId, String nodeId) {
		Board board = boardHandler.getBoard();
		List<Node> nodes = board.getNodes();
		Node node = ruleHelper.getNodeFromId(board, nodeId);
		if (node.isMarked()) {
			return false;
		}
		for (int change : changes) {
			if (ruleHelper.findValidMoveInDirection(board,
					node.getXCoordinate(), node.getYCoordinate(), playerId,
					change) != null)
				return true;
		}
		return false;
	}
}
