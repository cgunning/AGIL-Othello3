package kth.game.othello.player;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.player.movestrategy.MoveStrategy;
import kth.game.othello.player.movestrategy.RandomStrategy;

public class PlayerCreatorImpl implements PlayerCreator{

	@Override
	public Player createComputerPlayer(String name) {
		return new ComputerPlayer(name);
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
