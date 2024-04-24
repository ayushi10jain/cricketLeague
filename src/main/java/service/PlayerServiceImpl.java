package service;

import constants.PlayerType;
import constants.UserType;
import exception.InsufficientCreditsException;
import exception.InvalidUserException;
import exception.PlayerLimitExceeededException;
import exception.UserDoesNotExistException;
import lombok.AllArgsConstructor;
import model.Player;
import model.User;
import repository.PlayerRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final UserRepository userRepository;

    @Override
    public void selectPlayers(int userId, List<PlayerType> playerTypes, int matchId) {
        List<Player> players = new ArrayList<>();
        try{
            User user = userRepository.getUserById(userId);
            if(user.getUserType() == UserType.ADMIN) throw new InvalidUserException();
            if(user == null) throw new UserDoesNotExistException();
            for(PlayerType playerType : playerTypes) {
                List<Player> player = playerRepository.getPlayerByType(playerType);
                if(playerType.equals(PlayerType.BATSMAN)){
                      players.addAll(getBatsman(PlayerType.BATSMAN,player));
                }
                else if(playerType.equals(PlayerType.KEEPER)){
                    players.addAll(getKeeper(PlayerType.KEEPER,player));

                }else if (playerType.equals(PlayerType.BOWLER)){
                    players.addAll(getBowlers(PlayerType.BOWLER,player));
                }
            }
            userRepository.deductUserCredits(user.getId(), validateCredits(players, user.getCredits()));
            userRepository.setTeamForUser(user, players, matchId);
        } catch (InsufficientCreditsException e){
            System.out.println(e);
        } catch (UserDoesNotExistException e){
            System.out.println(e);
        } catch (InvalidUserException e) {
            System.out.println(e);
        } catch (PlayerLimitExceeededException e) {
            System.out.println(e);
        }
    }

    private int validateCredits(List<Player> players, int userCredits) throws PlayerLimitExceeededException {
        int totalCredits = 0;
        if(players.size() > 11) throw new PlayerLimitExceeededException();
        for(Player player:players){
            totalCredits += player.getCredits();
            if(totalCredits>userCredits){
                throw  new InsufficientCreditsException("Insufficient Credit with the user");
            }
        }
        return totalCredits;
    }

    List<Player> getBowlers(PlayerType playerType, List<Player> playerList){
        int count =0;
        List<Player> bowlers= new ArrayList();
        for(int i=0; i<playerList.size(); i++){
         if(count == 4){
             break;
         }
         bowlers.add(playerList.get(i));
         count++;
        }
      return bowlers;
    }

    List<Player> getBatsman(PlayerType playerType, List<Player> playerList){
        int count =0;
        List<Player> batsMan= new ArrayList();
        for(int i=0; i<playerList.size(); i++){
            if(count == 5){
                break;
            }
            batsMan.add(playerList.get(i));
            count++;
        }
        return batsMan;
    }

    List<Player> getKeeper(PlayerType playerType, List<Player> playerList){
        int count =0;
        List<Player> keepers= new ArrayList();
        for(int i=0; i<playerList.size(); i++){
            if(count == 2){
                break;
            }
            keepers.add(playerList.get(i));
            count++;
        }
        return keepers;
    }

    @Override
    public void setPoints(int  userId, Map<Integer, Integer> pointsMap, int matchId) throws InvalidUserException {
        User adminUser = userRepository.getUserById(userId);
        if(adminUser.getUserType() == UserType.ADMIN) {
            playerRepository.setPlayerPoints(pointsMap, matchId);
            return;
        }
        throw new InvalidUserException();
    }

    @Override
    public int getPointsForUser(int userId, int matchId) {
        User user = userRepository.getUserById(userId);
        List<Player> players = userRepository.getUserTeam(user, matchId);
        return playerRepository.getTotalPoints(players, matchId);
    }
}
