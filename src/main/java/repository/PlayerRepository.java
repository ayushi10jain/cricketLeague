package repository;

import constants.PlayerType;
import model.Player;

import java.util.List;
import java.util.Map;

public interface PlayerRepository {
    Player getPlayerById(int id);

    List<Player> getPlayerByType(PlayerType playerType);

    void setPlayerPoints(Map<Integer, Integer> points, int matchId);

    int getTotalPoints(List<Player> playerList, int matchId);
}
