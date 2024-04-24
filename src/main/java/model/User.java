package model;

import constants.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class User {
    int id;
    @Setter
    int credits = 100;
    UserType userType;

    public boolean deductCredits( int credits){
        if(this.credits>credits){
            this.credits-=credits;
            return true;
        }
        return false;
    }
}
