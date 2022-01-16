package very.cool.application.Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="member")
public class Member {

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

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "points")
    private int points;

    public Member(String username, String password, int id, int points) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.points = points;
    }

    public Member() {

    }

    public String getUsername() { return this.username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return this.password; }
    public void setPassword(String password) { this.password = password; }

    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }

    public int getPoints() { return this.points; }
    public void setPoints(int points) { this.points = points; }

    public boolean deductPoints(int bet) {
        if((this.points - bet) > 0 || (this.points - bet) == 0) {
            setPoints(this.points - bet);
            return true;
        } else {
            return false;
        }
    }
    
    public void addPoints(int bet) {
        setPoints(this.points + bet);
    }

}
