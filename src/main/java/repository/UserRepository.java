package repository;

import exception.InvalidUserException;
import model.Player;
import model.User;

import java.util.List;

public interface UserRepository {
    List<Player> getUserTeam(User user, int matchId);
    void setTeamForUser(User user, List<Player> players, int matchId) throws InvalidUserException;

    void deductUserCredits(int userId, int credits);

    User getUserById(int userId);
}
