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
	private NodeHelper nodeHelper;
	private RuleHelper ruleHelper;
	
	private RulesImpl(BoardHandler boardHandler, NodeHelper nodeHelper, RuleHelper ruleHelper) {
		this.boardHandler = boardHandler;
		this.ruleHelper = ruleHelper;
		this.nodeHelper = nodeHelper;
	}
	
	RulesImpl(BoardHandler boardHandler, NodeHelper nodeHelper) {
		this.boardHandler = boardHandler;
		this.ruleHelper = new RuleHelper();
		this.nodeHelper = nodeHelper;
	}

	@Override
	public List<Node> getNodesToSwap(String playerId, String nodeId) {
		int[] coordinatesForNode = nodeHelper.getCoordinatesFromId(nodeId);
		List<Node> returnedNodes = new ArrayList<Node>();
		for (int change : changes) {
			List<Node> swappedNodes = ruleHelper.findValidMoveInDirection(
					boardHandler.getBoard(), coordinatesForNode[0],
					coordinatesForNode[1], playerId, change);
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
		int[] coordinatesForNode = nodeHelper.getCoordinatesFromId(nodeId);
		if (nodes.get(
				nodeHelper.getIndexFromCoordinates(board,
						coordinatesForNode[0], coordinatesForNode[1]))
				.isMarked()) {
			return false;
		}
		for (int change : changes) {
			if (ruleHelper.findValidMoveInDirection(board,
					coordinatesForNode[0], coordinatesForNode[1], playerId,
					change) != null)
				return true;
		}
		return false;
	}

}
