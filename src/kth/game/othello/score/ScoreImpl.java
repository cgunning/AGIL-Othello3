package kth.game.othello.score;

import java.util.List;
import java.util.Observer;

public class ScoreImpl implements Score {
	
	List<ScoreItem> playersScore;
	
	public ScoreImpl() {
		
	}
	
	@Override
	public void addObserver(Observer observer) {
		// TODO Auto-generated method stub
		
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

}
