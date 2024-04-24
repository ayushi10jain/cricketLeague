import constants.PlayerType;
import constants.UserType;
import exception.InvalidUserException;
import model.Player;
import model.User;
import repository.PlayerRepository;
import repository.PlayerRepositoryHashMap;
import repository.UserRepository;
import repository.UserRepositoryHashMap;
import service.PlayerService;
import service.PlayerServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws InvalidUserException {
        PlayerRepository playerRepository = new PlayerRepositoryHashMap();
        UserRepository userRepository = new UserRepositoryHashMap();
        User normal = new User(1, 100, UserType.NORMAL);
        User admin = new User(2, 0, UserType.ADMIN);
        Map<Integer, User> userMap = new HashMap<>();
        userMap.put(1, normal);
        userMap.put(2, admin);
        ((UserRepositoryHashMap) userRepository).setUserMap(userMap);

        HashMap<Integer,Player> playerMap = new HashMap();
        playerMap.put(1,new Player(1, 10, PlayerType.BATSMAN));
        playerMap.put(2,new Player(2, 4, PlayerType.BATSMAN));
        playerMap.put(3,new Player(3, 10, PlayerType.BATSMAN));
        playerMap.put(4,new Player(4, 7, PlayerType.BATSMAN));
        playerMap.put(5,new Player(5, 8, PlayerType.BATSMAN));
        playerMap.put(6,new Player(6, 8, PlayerType.BOWLER));
        playerMap.put(7,new Player(7, 6, PlayerType.BOWLER));
        playerMap.put(8,new Player(8, 6, PlayerType.BOWLER));
        playerMap.put(9,new Player(9, 7, PlayerType.BOWLER));
        playerMap.put(10,new Player(10, 7, PlayerType.KEEPER));
        playerMap.put(11,new Player(11, 1, PlayerType.KEEPER));
        playerMap.put(12,new Player(12, 2, PlayerType.BATSMAN));
        playerMap.put(13,new Player(13, 2, PlayerType.BATSMAN));

        ((PlayerRepositoryHashMap) playerRepository).setPlayerMap(playerMap);

        Map<PlayerType, List<Player>> playerTypeMap = new HashMap<>();
        playerTypeMap.put(PlayerType.BATSMAN,playerMap.values().stream().filter(player -> player.getPlayerType().equals
                (PlayerType.BATSMAN)).collect(Collectors.toList()));
        playerTypeMap.put(PlayerType.BOWLER,playerMap.values().stream().filter(player -> player.getPlayerType().equals
                (PlayerType.BOWLER)).collect(Collectors.toList()));
        playerTypeMap.put(PlayerType.KEEPER,playerMap.values().stream().filter(player -> player.getPlayerType().equals
                (PlayerType.KEEPER)).collect(Collectors.toList()));



        ((PlayerRepositoryHashMap) playerRepository).setPlayerTypeMap(playerTypeMap);

        PlayerService playerService = new PlayerServiceImpl(playerRepository, userRepository);

        playerService.selectPlayers(1, Arrays.asList(PlayerType.BATSMAN,PlayerType.BOWLER,PlayerType.KEEPER),1);

        Map<Integer, Integer> pointsMap = new HashMap<>();
        pointsMap.put(3, 3);
        pointsMap.put(4, 5);
        pointsMap.put(5, 5);
        pointsMap.put(2, 10);
        pointsMap.put(9, 9);
        pointsMap.put(10, 13);
        pointsMap.put(11, 2);
        pointsMap.put(1, 9);
        pointsMap.put(6, 13);
        pointsMap.put(7, 2);
        pointsMap.put(8, 2);
        pointsMap.put(9, 2);
        playerService.setPoints(2, pointsMap, 1);
        System.out.println(playerService.getPointsForUser(1, 1));
    }
}
