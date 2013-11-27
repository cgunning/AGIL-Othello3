package kth.game.othello.player.movestrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

public class RandomStrategy extends MoveStrategyImpl {

	public RandomStrategy() {
		super("RandomStrategy");
	}

	@Override
	public Node move(String playerId, Rules rules, Board board) {
		List<Node> nodes = board.getNodes();
		List<Node> possibleMoves = new ArrayList<Node>();
		Random rand = new Random();
		for (Node node : nodes) {
			if (rules.isMoveValid(playerId, node.getId())) {
				possibleMoves.add(node);
			}
		}
		if(possibleMoves.size() > 0) 
			return possibleMoves.get(rand.nextInt(possibleMoves.size()));
		
		return null;
	}

}
