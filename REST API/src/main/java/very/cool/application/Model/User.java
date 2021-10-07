package very.cool.application.Model;

public class User {
    private String name;
    private String password;
    private int id;
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
