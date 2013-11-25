package kth.game.othello.score;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import kth.game.othello.board.Node;

public class ScoreImpl extends Observable implements Score, Observer {
	
	List<ScoreItem> playersScore;
	
	public ScoreImpl() {
		
	}
	
	@Override
	public void addObserver(Observer observer) {
		super.addObserver(observer);
	}

	@Override
	public List<ScoreItem> getPlayersScore() {
		return playersScore;
	}

	@Override
	public int getPoints(String playerId) {
		for(ScoreItem playerScore : playersScore) 
			if(playerScore.getPlayerId().equals(playerId))
				return playerScore.getScore();
		return -1;
	}

	@Override
	public void update(Observable o, Object arg) {
		Node node = ((Node) o);
		if(node != null) {
			for(int i = 0; i < playersScore.size(); i++) {
				ScoreItem score = playersScore.get(i);
				if(score.getPlayerId().equals(node.getOccupantPlayerId())) {
					playersScore.set(i, new ScoreItem(score.getPlayerId(), score.getScore() + 1));
				}
			}
		}
		
		for(int i = 0; i < playersScore.size(); i++) {
			ScoreItem score = playersScore.get(i);
			if(score.getPlayerId().equals(arg)) {
				playersScore.set(i, new ScoreItem(score.getPlayerId(), score.getScore() - 1));
			}
		}
		
		super.notifyAll();
	}
	
}
