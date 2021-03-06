package very.cool.application.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Card {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator= "native"
    )
    private int id;
    private String name;
    private int value;

    public Card(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public Card() {}
}
