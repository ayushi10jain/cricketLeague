package model;

import constants.PlayerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class Player {
    int id;
    int credits;
    PlayerType playerType;

    @Override
    public boolean equals(Object o) {
        return ((Player)o).getId() == this.id;
    }
}
