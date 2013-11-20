package kth.game.othello.player;

import kth.game.othello.player.movestrategy.MoveStrategy;

public class PlayerCreatorImpl implements PlayerCreator{

	@Override
	public Player createComputerPlayer(String name) {
		return new ComputerPlayer(name);
	}

	@Override
	public Player createComputerPlayer(String name, MoveStrategy moveStrategy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player createHumanPlayer(String name) {
		return new HumanPlayer(name);
	}

}
