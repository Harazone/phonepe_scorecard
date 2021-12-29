package scorecard;

public class ScoreBoardDisplay {
 
	public static void display() {
		
	}
	
	public static void displayMessage(String message) {
		System.out.println(message);
	}
	
	public static void displayInputMessage() {
		System.out.println("To update No ball,wide and wicket  only allowed strings are as below");
		System.out.println("NB->No ball");
		System.out.println("WD->Wide ball");
		System.out.println("W->Wickets ball");
		System.out.println();
	}
	public static void displayMessage(String playerName,String score,String fours,String sixes,String balls) {
		System.out.format("%32s%10s%10s%10s%10s", playerName, score, fours,sixes,balls);
		System.out.println();
	}
}
