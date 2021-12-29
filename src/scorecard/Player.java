package scorecard;

import java.util.LinkedList;
import java.util.List;

public class Player {
	private String name;
	private PlayerStatus status;
	private List<Ball> playedBalls;
	private Integer score;
	
	public Player(String name) {
		this.name=name;
		this.status=PlayerStatus.WAITING_TO_PLAY;
		this.playedBalls = new LinkedList<>();
		this.score=0;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PlayerStatus getStatus() {
		return status;
	}
	public void setStatus(PlayerStatus status) {
		this.status = status;
	}
	public List<Ball> getBalls() {
		return playedBalls;
	}
	public void updateBall(Ball b) {
		if(b.getType().equals(BallType.WICKET_BALL)) {
			this.status=PlayerStatus.OUT;
		}
		playedBalls.add(b);
		this.score=this.score+b.getScore();
	}
	public Integer getScore() {
		return score;
	}
	public void updateScore(Integer score) {
		this.score = this.score+score;
	}
	
	public Integer getFours() {
		Integer fourCount=0;
		for(Ball b: playedBalls) {
			if(b.getScore()==4) {
				fourCount++;
			}
		}
		
		return fourCount;
	}
	public Integer getSixes() {
		Integer sixCount=0;
		for(Ball b: playedBalls) {
			if(b.getScore()==6) {
				sixCount++;
			}
		}
		
		return sixCount;
	}
	
}
