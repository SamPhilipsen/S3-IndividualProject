package Classes;

public class Cointoss {
    public String Toss() {
        int random = (int) Math.floor(Math.random() * 2 + 0);
        if (random == 1) {
            return "heads";
        } else {
            return "tails";
        }
    }
}
