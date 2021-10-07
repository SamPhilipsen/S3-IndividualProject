package very.cool.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import very.cool.application.Game_logic.Cointoss;

import java.util.Locale;
import java.util.Scanner;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Would you like to play a game of cointoss?");
        String result = scanner.nextLine().toLowerCase();
        if(result.equals("yes")) {
            System.out.println("What side do you want to pick? (Heads/Tails)");
            result = scanner.nextLine().toLowerCase();
            Cointoss cointoss = new Cointoss();
            String coinside = cointoss.Flip();
            System.out.println("The winning side was: " + coinside);

            if(result.equals(coinside)) {
                System.out.println("You won!");
            }
            else {
                System.out.println("You lost!");
            }
        }
        else {
            System.out.println("okay then");
        }
    }
}
