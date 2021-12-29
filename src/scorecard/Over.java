package scorecard;

import java.util.LinkedList;
import java.util.List;

public class Over {
	private List<Ball> balls =new LinkedList<>();

	public List<Ball> getBalls() {
		return balls;
	}

	public void setBalls(List<Ball> balls) {
		this.balls = balls;
	}
    
	public Ball updateWideBall(Integer score) {
		Ball b = new Ball(BallType.WIDE_BALL,1,Boolean.FALSE);
		balls.add(b);
		return b;
	}
	
	public Ball updateNoBall(Integer score) {
		Ball b = new Ball(BallType.NO_BALL,1,Boolean.FALSE);
		balls.add(b);
		return b;
	}
	
	public Ball updateWicketBall(Integer score) {
		Ball b = new Ball(BallType.WICKET_BALL,0,Boolean.TRUE);
		balls.add(b);
		return b;
	}
	
	public Ball updateValidBall(Integer score) {
		Ball b = new Ball(BallType.VALID_BALL,score,Boolean.TRUE);
		balls.add(b);
		return b;
	}
	
	public Ball getLastBall() {
		return balls.get(balls.size()-1);
	}
	
	public Integer getActiveBallCount() {
		Integer count=0;
		for(Ball b : balls) {
			if(b.isValidBall) {
				count++;
			}
		}
		
		return count;
	}
}
