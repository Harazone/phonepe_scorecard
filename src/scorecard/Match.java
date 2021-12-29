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
	List<Innings> innings;
	Integer overLimit;
	Integer totalTeamPlayers;

	public Match(Integer overLimit, Integer totalTeamPlayers, String[] team1, String[] team2) {
		innings = new ArrayList<>();
		innings.add(new Innings(team1));
		innings.add(new Innings(team2));
		this.overLimit = overLimit;
		this.totalTeamPlayers = totalTeamPlayers;
	}

	public Innings getCurrentInnings() {
		for (Innings i : innings) {
			if (i.getStatus().equals(InningsStatus.INPROGRESS)) {
				return i;
			}
		}

		return null;
	}

	public Innings getNextInnings() {
		for (Innings i : innings) {
			if (i.getStatus().equals(InningsStatus.PENDING)) {
				i.setStatus(InningsStatus.INPROGRESS);
				i.initializePlayer();
				return i;
			}
		}

		return null;
	}

	public boolean validateInnings() {
		Innings inngs = getCurrentInnings();
		if (inngs == null) {
			inngs = getNextInnings();
			if (inngs == null) {
				return false;
			}
		}
		if (ifMatchIsWon()) {
			return false;
		}
		if (inngs.getPlayedOvers() <= overLimit && inngs.getWickets() < totalTeamPlayers - 1) {
			return true;
		} else {
			inngs.setStatus(InningsStatus.COMPLETED);
			Innings nextInngs = getNextInnings();
			if (nextInngs != null) {
				return true;
			}
		}

		return false;
	}

	public boolean ifMatchIsWon() {
		Innings ings = getCurrentInnings();
		boolean firstInngsCompleted = false;
		Integer scoreOfFirstInngs = 0;
		for (Innings innings : this.innings) {
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
		if (innings.get(0).getScore() > innings.get(1).getScore()) {
			ScoreBoardDisplay.displayMessage(
					"Team 1 won the match by " + (innings.get(0).getScore() - innings.get(1).getScore()) + " runs");
		} else if (innings.get(0).getScore() == innings.get(1).getScore()) {
			ScoreBoardDisplay.displayMessage("Match is drawn");
		} else {
			ScoreBoardDisplay.displayMessage(
					"Team 2 won the match by " + (innings.get(1).getScore() - innings.get(0).getScore()) + " runs");
		}
	}

	public List<Innings> getInnings() {
		return innings;
	}

	public void setInnings(List<Innings> innings) {
		this.innings = innings;
	}
}
