package scorecard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputConsole {

	public static void executeInput() throws IOException {
		ScoreBoardDisplay.displayInputMessage();

		Integer numberOfPlayers = 0;
		Integer numberOfOvers = 0;
		int c = 0;
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		numberOfPlayers = updateNumberOfPlayer(numberOfPlayers, input);

		numberOfOvers = updateNumberOfOver(numberOfPlayers, numberOfOvers, input);

		ScoreBoardDisplay.displayMessage("Batting Order for team 1:");
		String[] teamList1 = new String[numberOfPlayers];

		updateTeams(numberOfPlayers, c, input, teamList1);

		ScoreBoardDisplay.displayMessage("Batting Order for team 2:");
		String[] teamList2 = new String[numberOfPlayers];
		c = 0;

		updateTeams(numberOfPlayers, c, input, teamList2);

		playMatch(numberOfPlayers, numberOfOvers, input, teamList1, teamList2);

	}

	private static void playMatch(Integer numberOfPlayers, Integer numberOfOvers, BufferedReader input,
			String[] teamList1, String[] teamList2) {
		Match match = new Match(numberOfOvers, numberOfPlayers, teamList1, teamList2);
		while (match.validateInnings()) {
			if (match.getCurrentInnings().isNewOver()) {
				ScoreBoardDisplay.displayMessage("Over " + match.getCurrentInnings().getOverNumberToPlay() + ":");
			}
			try {
				match.getCurrentInnings().updateScore(input.readLine());
			} catch (Exception e) {
				System.out.println("Enter valid input");
			}

		}
		match.displayMatchResults();
	}

	private static Integer updateNumberOfOver(Integer numberOfPlayers, Integer numberOfOvers, BufferedReader input)
			throws IOException {
		while (numberOfOvers < 1) {
			ScoreBoardDisplay.displayMessage("No. of Overs:");
			numberOfOvers = Integer.parseInt(input.readLine());
			if (numberOfPlayers < 1) {
				ScoreBoardDisplay.displayMessage("Minimum 1 over required");
			}
		}
		return numberOfOvers;
	}

	private static Integer updateNumberOfPlayer(Integer numberOfPlayers, BufferedReader input) throws IOException {
		while (numberOfPlayers < 2) {
			ScoreBoardDisplay.displayMessage("No. of players for each team:");
			numberOfPlayers = Integer.parseInt(input.readLine());
			if (numberOfPlayers < 2) {
				ScoreBoardDisplay.displayMessage("Minimum 2 teams are required");
			}
		}
		return numberOfPlayers;
	}

	private static void updateTeams(Integer numberOfPlayers, int c, BufferedReader input, String[] teamList1) {
		while (c < numberOfPlayers) {
			try {
				String s = input.readLine();
				if (s.length() > 32 || s.trim().length() == 0) {
					throw new Exception();
				}
				teamList1[c] = s;
				c++;
			} catch (Exception e) {
				System.out.println("Enter valid name");
			}
		}
	}

}
