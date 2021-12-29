package scorecard;

public class ScoreCard {
	
	public static void displayScore(BattingInnings inngs) {
		ScoreBoardDisplay.displayMessage("Player Name", "Score","4s", "6s","Balls");
		for(Player p : inngs.getTeam().getPlayers()) {
			String astrik=(p.getStatus().equals(PlayerStatus.PLAYING))?"*":"" ;			
			ScoreBoardDisplay.displayMessage(p.getName()+astrik,String.valueOf(p.getScore()),String.valueOf(p.getFours()),String.valueOf(p.getSixes()),String.valueOf(p.getBalls().size()));
		}
		ScoreBoardDisplay.displayMessage("Total: "+inngs.getScore()+"/"+inngs.getWickets());
		ScoreBoardDisplay.displayMessage(inngs.getPlayedOversToDisplay());
	}
}
