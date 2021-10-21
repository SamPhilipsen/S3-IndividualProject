package very.cool.application.Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator= "native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "points")
    private int points;

    public User(String name, String password, int id, int points) {
        this.name = name;
        this.password = password;
        this.id = id;
        this.points = points;
    }

    public User() {

    }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public String getPassword() { return this.password; }
    public void setPassword(String password) { this.password = password; }

    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }

    public int getPoints() { return this.points; }
    public void setPoints(int points) { this.points = points; }

    public boolean deductPoints(int deductingPoints)
    {
        int newPoints = this.points - deductingPoints;
        if(newPoints < 0) {
            return false;
        }
        else {
            this.points = newPoints;
            return true;
        }
    }
    public void addPoints(int points) {
        if(points > 0) { this.points += points; }
    }
}
