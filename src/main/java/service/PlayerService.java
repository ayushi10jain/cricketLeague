package service;

import constants.PlayerType;
import exception.InvalidUserException;

import java.util.List;
import java.util.Map;

public interface PlayerService {
    void selectPlayers(int userId, List<PlayerType> playerTypes, int matchId);

    void setPoints(int userId, Map<Integer, Integer> pointsMap, int matchId) throws InvalidUserException;

    int getPointsForUser(int userId, int matchId);
}
