package kth.game.othello.player.movestrategy;

import java.util.List;

import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.MoveHandler;

public class MostTurnedNodesStrategy extends MoveStrategyImpl {

	MostTurnedNodesStrategy(MoveHandler moveHandler) {
		super("MostTurnedNodesStrategy");
	}

	@Override
	public Node move(String playerId, Rules rules, Board board) {
		List<Node> nodes = board.getNodes();
		Node bestMoveNode = null;
		int mostTurnedNodes = 0;
		for (Node node : nodes) {
			if (rules.isMoveValid(playerId, node.getId()) && rules.getNodesToSwap(playerId, node.getId()).size() > mostTurnedNodes) {
				bestMoveNode = node;
				mostTurnedNodes = rules.getNodesToSwap(playerId, node.getId()).size();
			}
		}
		
		return bestMoveNode;
	}

}
