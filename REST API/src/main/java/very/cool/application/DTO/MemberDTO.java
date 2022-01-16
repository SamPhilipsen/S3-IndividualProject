package very.cool.application.DTO;

import lombok.Getter;
import lombok.Setter;

public class MemberDTO {

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private int points;

    public MemberDTO(String username, String password, int id, int points) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.points = points;
    }
}
