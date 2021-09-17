package very.cool.application.Game_logic;

import java.util.Random;

public class Cointoss {
    public Cointoss() {

    }
    public String Flip() {
        Random rand = new Random();
        int upperbound = 2;

        int int_random = rand.nextInt(upperbound);
        String coin;

        if(int_random == 1) {
            coin = "heads";
        }
        else {
            coin = "tails";
        }

        return coin;
    }
}
