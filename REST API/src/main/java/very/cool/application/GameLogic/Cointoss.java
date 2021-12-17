package very.cool.application.GameLogic;

import lombok.Getter;

import java.lang.reflect.Array;
import java.util.Random;

public class Cointoss {
    private String[] sides = {"Heads", "Tails"};

    @Getter
    private String chosenSide;

    @Getter
    private String coinSide;

    public Cointoss() {
    }

    public void chooseSide(String side) {
        this.chosenSide = side;
    }

    public boolean flipCoin() {
        Random random = new Random();
        int index = random.nextInt(sides.length);
        String result = sides[index];
        coinSide = result;
        if(result == chosenSide) {
            return true;
        }
        return false;
    }
}
