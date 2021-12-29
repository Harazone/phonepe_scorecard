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
public class Team {
    private List<Player> players;
    
    public Team(String[] playerNames) {
    	players= new ArrayList<>();
    	for(String name: playerNames) {
    		Player p= new Player(name);
    		players.add(p);
    	}
    }
    
	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
     
	public Player getNextPlayer() {
		for(Player p: players) {
			if(p.getStatus().equals(PlayerStatus.WAITING_TO_PLAY)) {
				p.setStatus(PlayerStatus.PLAYING);
				return p;
			}
		}
		
		return null;
	}
     
}
