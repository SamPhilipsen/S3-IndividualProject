package very.cool.application.GameLogic;

import java.util.Random;

public class Cointoss {
    private String[] sides = {"Heads", "Tails"};

    private String chosenSide;
    private String coinSide;

    public String getChosenSide() {
        return this.chosenSide;
    }
    public String getCoinSide() {
        return this.coinSide;
    }

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
