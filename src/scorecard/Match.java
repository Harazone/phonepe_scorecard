/**
 * 
 */
package scorecard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hppzo
 *
 */
public class Match {
	List<BattingInnings> battingInnings;
	Integer overLimit;
	Integer totalTeamPlayers;

	public Match(Integer overLimit, Integer totalTeamPlayers, String[] team1, String[] team2) {
		battingInnings = new ArrayList<>();
		battingInnings.add(new BattingInnings(team1));
		battingInnings.add(new BattingInnings(team2));
		this.overLimit = overLimit;
		this.totalTeamPlayers = totalTeamPlayers;
	}

	public BattingInnings getCurrentInnings() {
		for (BattingInnings i : battingInnings) {
			if (i.getStatus().equals(InningsStatus.INPROGRESS)) {
				return i;
			}
		}

		return null;
	}

	public BattingInnings getNextInnings() {
		for (BattingInnings i : battingInnings) {
			if (i.getStatus().equals(InningsStatus.PENDING)) {
				i.setStatus(InningsStatus.INPROGRESS);
				i.initializePlayer();
				return i;
			}
		}

		return null;
	}

	public boolean validateInnings() {
		BattingInnings inngs = getCurrentInnings();
		if (inngs == null) {
			inngs = getNextInnings();
			if (inngs == null) {
				return false;
			}
		}
		if (ifMatchIsWon()) {
			if(!inngs.isOverEnded()) {
				ScoreCard.displayScore(inngs);
			}
			return false;
		}
		if (inngs.getPlayedOvers() < overLimit && inngs.getWickets() < totalTeamPlayers - 1) {

			return true;
		} else {
			if(inngs.getWickets() == totalTeamPlayers - 1) {
				ScoreCard.displayScore(inngs);
			}
			inngs.setStatus(InningsStatus.COMPLETED);
			BattingInnings nextInngs = getNextInnings();
			if (nextInngs != null) {
				return true;
			}
		}

		return false;
	}

	public boolean ifMatchIsWon() {
		BattingInnings ings = getCurrentInnings();
		boolean firstInngsCompleted = false;
		Integer scoreOfFirstInngs = 0;
		for (BattingInnings innings : this.battingInnings) {
			if (innings.getStatus().equals(InningsStatus.COMPLETED)) {
				firstInngsCompleted = true;
				scoreOfFirstInngs = innings.getScore();
			}
		}
		if (firstInngsCompleted) {
			if (ings.getScore() > scoreOfFirstInngs) {
				return true;
			}
		}

		return false;
	}

	public void displayMatchResults() {
		if (battingInnings.get(0).getScore() > battingInnings.get(1).getScore()) {
			ScoreBoardDisplay.displayMessage(
					"Team 1 won the match by " + (battingInnings.get(0).getScore() - battingInnings.get(1).getScore()) + " runs");
		} else if (battingInnings.get(0).getScore() == battingInnings.get(1).getScore()) {
			ScoreBoardDisplay.displayMessage("Match is drawn");
		} else {
			ScoreBoardDisplay.displayMessage(
					"Team 2 won the match by " + (battingInnings.get(1).getScore() - battingInnings.get(0).getScore()) + " runs");
		}
	}

	public List<BattingInnings> getBattingInnings() {
		return battingInnings;
	}

	public void setBattingInnings(List<BattingInnings> innings) {
		this.battingInnings = innings;
	}
}
