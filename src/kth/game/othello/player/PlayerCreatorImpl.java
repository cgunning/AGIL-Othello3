package kth.game.othello.player;

import kth.game.othello.player.movestrategy.FirstPickStrategy;
import kth.game.othello.player.movestrategy.MoveStrategy;

public class PlayerCreatorImpl implements PlayerCreator {

	@Override
	public Player createComputerPlayer(String name) {
		return createComputerPlayer(name, new FirstPickStrategy());
	}

	@Override
	public Player createComputerPlayer(String name, MoveStrategy moveStrategy) {
		Player computer = new ComputerPlayer(name);
		computer.setMoveStrategy(moveStrategy);
		return computer;
	}

	@Override
	public Player createHumanPlayer(String name) {
		return new HumanPlayer(name);
	}

}
