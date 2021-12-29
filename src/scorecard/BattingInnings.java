/**
 * 
 */
package scorecard;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author hppzo
 *
 */
public class BattingInnings implements Innings{
	private Team team;
	private List<Over> overs;
	private LinkedList<Player> activeBattingPlayers;
    private Integer score;
    private Integer wickets;
    private InningsStatus status;
    
    public BattingInnings(String[] players) {
    	this.score=0;
    	this.wickets=0;
    	this.score=0;
    	this.status=InningsStatus.PENDING;
    	this.activeBattingPlayers=new LinkedList<>();
    	this.overs= new LinkedList<>();
    	team = new Team(players);
    }
    public Over getCurrentOver() {
    	if(overs!=null && overs.size()>0) {
    		Over o=overs.get(overs.size()-1);
    		if(o.getActiveBallCount()<6) {
    			return o;
    		}
    	}
    	if(overs.size()>0) {
    		Collections.reverse(activeBattingPlayers);
    	}
    	Over o = new Over();
    	overs.add(o);
    	return o;
    }
    
    public boolean isOverEnded() {
    	if(overs!=null && overs.size()>0) {
    		Over o=overs.get(overs.size()-1);
    		if(o.getActiveBallCount()==6) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    public void displayScore() {
    	ScoreCard.displayScore(this);
    }
    
    public boolean isNewOver() {
    	Over o=getCurrentOver();
    	return (o.getBalls().size()==0)?true:false;
    }
    public Player getCurrentPlayer() {
    	return activeBattingPlayers.get(0);
    }
    
    public void addPlayer(Player p) {
    	if(activeBattingPlayers.size()>1) {
    		removePlayer(getCurrentPlayer());
    		activeBattingPlayers.addFirst(p);
    		return;
    	}
    	activeBattingPlayers.add(p);
    }
    
    public void initializePlayer() {
    	addPlayer(team.getNextPlayer());
    	addPlayer(team.getNextPlayer());
    }
    public void removePlayer(Player p) {
    	activeBattingPlayers.remove(p);
    }
    
    public void declarePlayerOut(Ball b){
    	wickets++;
    	getCurrentPlayer().updateBall(b);
    	addPlayer(team.getNextPlayer());
    }
    
    public void updateScore(String score){
    	Over o=getCurrentOver();
    	switch(score.toUpperCase()) {
    	case "W" :
    		Ball b=o.updateWicketBall(0);
    		declarePlayerOut(b);
    		break;
    	case "WD":
    		o.updateWideBall(1);
    		addScoreToTeam(1);
    		break;
    	case "NB":
    		o.updateNoBall(1);
    		addScoreToTeam(1);
    		break;
    	default :
    		Ball db=o.updateValidBall(Integer.valueOf(score));
    		addScoreToTeamAndPlayer(Integer.valueOf(score),db);
    	}
    	if(isOverEnded()) {
    		displayScore();
    	}
    }
    public void addScoreToTeam(Integer scoreToUpdate) {
    	score=score+scoreToUpdate;
    }
    
    public void addScoreToTeamAndPlayer(Integer scoreToUpdate,Ball b){
    	 score=score+scoreToUpdate;
    	 getCurrentPlayer().updateBall(b);
    	 if(scoreToUpdate%2!=0) {
    		 Collections.reverse(activeBattingPlayers);
    	 }
    }
   
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public List<Over> getOvers() {
		return overs;
	}

	public void setOvers(List<Over> overs) {
		this.overs = overs;
	}
	
	public Integer getPlayedOvers() {
		Integer overCount=overs.size();
		if(overCount>0) {
			if(!isOverEnded()) {
				overCount=overCount-1;
			}
		}
		
		return overCount;
	}
	
	public Integer getOverNumberToPlay() {
		Integer overCount = overs.size();

		if (overCount == 0) {
			return 1;
		}

		return overCount;
	}
	
	public String getPlayedOversToDisplay() {
		String playedOvers="";
		Integer overCount=overs.size();
		playedOvers=String.valueOf(overCount);
		if(overCount>0) {
			Over o= overs.get(overs.size()-1);
			Integer c=o.getActiveBallCount();
			if(c<6) {
				overCount=overCount-1;
				playedOvers=String.valueOf(overCount);
				playedOvers=playedOvers+"."+String.valueOf(c);
			}
		}
		
		return playedOvers;
	}

	public Integer getWickets() {
		return wickets;
	}

	public InningsStatus getStatus() {
		return status;
	}

	public void setStatus(InningsStatus status) {
		this.status = status;
	}

	public Integer getScore() {
		return score;
	}

}
