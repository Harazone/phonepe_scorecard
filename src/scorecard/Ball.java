package scorecard;

public class Ball {
	BallType type;
	Integer score;
	boolean isValidBall;
	
	public Ball(BallType type,Integer score,boolean isValidBall) {	
		this.type=type;
		this.score=score;
		this.isValidBall=isValidBall;
	}

	public BallType getType() {
		return type;
	}

	public void setType(BallType type) {
		this.type = type;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public boolean isValidBall() {
		return isValidBall;
	}

	public void setValidBall(boolean isValidBall) {
		this.isValidBall = isValidBall;
	}
	
	

}
