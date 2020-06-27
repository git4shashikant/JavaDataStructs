package com.designpatterns.structural;

import java.util.HashMap;
import java.util.Map;

/*
* This design pattern is useful when its too costly to have many instances of the same object.
* So, its better to share the intrinsic properties/methods and implement extrinsic properties/methods
* afterwards once get the object.
*
* Example: Lets imagine a football match, both players are using a player at a time to hold the ball,
* at any time, we would need 2 instances of the players. So, its not good to create 22 instances for
* all the players. Instead, use the same player object from a team and assign it a name and expertise
* whenever user wishes to play with that player in the screen.
* */
public class FlyweightPattern {

    public static void main(String [] args) {

        for (int index = 0; index < 10 ; index++) {
            if (index % 2 == 0) {
                Player player2 = PlayerFactory.getPlayer("RealMadrid");
                player2.setName("RM-" + index);
                player2.setExpertise(Expertise.FORWARD);
                player2.performAction();
            } else {
                Player player2 = PlayerFactory.getPlayer("Barcelona");
                player2.setName("B-" + index);
                player2.setExpertise(Expertise.DEFENSE);
                player2.performAction();
            }
        }
    }

    enum Expertise {
        FORWARD,
        DEFENSE
    }

    static class Player {
        private String team;
        private String name;
        private Expertise expertise;

        public Player(String team) {
            this.team = team;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setExpertise(Expertise expertise) {
            this.expertise = expertise;
        }

        public void performAction() {
            switch (expertise) {
                case FORWARD:
                    System.out.println("Moving towards the opponent goal.");
                    break;
                case DEFENSE:
                    System.out.println("Running to kick for corner.");
                    break;
                default:
                    System.out.println("Kicks the ball.");
            }
        }
    }

    static class PlayerFactory {
        private static final int noOfTeams = 2;
        private static final Map playerMap = new HashMap(noOfTeams);

        public static synchronized Player getPlayer(String team) {
            if (!playerMap.containsKey(team)) {
                playerMap.put(team, new Player(team));
            }

            System.out.println("Number of players in the map: " + playerMap.size());
            return (Player) playerMap.get(team);
        }
    }

}
