package repository;

import constants.PlayerType;
import lombok.Setter;
import model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
public class PlayerRepositoryHashMap implements PlayerRepository {
    private Map<PlayerType, List<Player>> playerTypeMap = new HashMap<>();
    private Map<String, Integer> playerPoints = new HashMap<>();
    private Map<Integer,Player> playerMap = new HashMap();

    @Override
    public Player getPlayerById(int id) {
        return playerMap.get(id);
    }

    @Override
    public List<Player> getPlayerByType(PlayerType playerType) {
       return playerTypeMap.get(playerType);
    }

    @Override
    public void setPlayerPoints(Map<Integer, Integer> points, int matchId) {
        for (int playerId : points.keySet()) {
            playerPoints.put(String.valueOf(playerId) + String.valueOf(matchId), points.get(playerId));
        }
    }

    @Override
    public int getTotalPoints(List<Player> playerList, int matchId) {
        int points = 0;
        for(Player player:playerList){
            points += playerPoints.get(String.valueOf(player.getId()) + String.valueOf(matchId));
        }
        return points;
    }

}
