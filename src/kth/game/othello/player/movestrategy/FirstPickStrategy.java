package kth.game.othello.player.movestrategy;

import java.util.List;

import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

public class FirstPickStrategy extends MoveStrategyImpl {

	 public FirstPickStrategy() {
		super("FirstPickStrategy");
	}

	@Override
	public Node move(String playerId, Rules rules, Board board) {
		List<Node> nodes = board.getNodes();
		for (Node node : nodes) {
			if (rules.isMoveValid(playerId, node.getId())) {
				return node;
			}
		}
		return null;
	}

}
