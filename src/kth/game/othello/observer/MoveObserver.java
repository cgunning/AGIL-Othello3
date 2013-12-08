package kth.game.othello.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Class
 * 
 * @author Nils Dahlbom Norgren & Christoffer Gunning
 * 
 */
public class MoveObserver extends Observable {

	@Override
	public void addObserver(Observer o) {
		super.addObserver(o);
	}

	@Override
	public void setChanged() {
		super.setChanged();
	}
}
