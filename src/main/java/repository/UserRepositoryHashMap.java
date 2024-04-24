package repository;

import constants.UserType;
import exception.InvalidUserException;
import lombok.Setter;
import model.Player;
import model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
public class UserRepositoryHashMap implements UserRepository {
    private Map<String, List<Player>> userTeams = new HashMap<>();
    private Map<Integer, User> userMap = new HashMap<>();

    @Override
    public List<Player> getUserTeam(User user, int matchId) {
        return userTeams.get(String.valueOf(user.getId()) + matchId);
    }

    @Override
    public void setTeamForUser(User user, List<Player> players, int matchId) throws InvalidUserException {
        if(user.getUserType()== UserType.ADMIN) throw new InvalidUserException();
        userTeams.put(String.valueOf(user.getId()) + matchId, players);
    }

    @Override
    public void deductUserCredits(int userId, int credits) {
        User user = userMap.get(userId);
        user.deductCredits(credits);
    }

    @Override
    public User getUserById(int userId) {
        return userMap.get(userId);
    }


}
