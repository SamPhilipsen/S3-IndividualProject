package very.cool.application.DTO;

public class ReceiveCointossDataRequest {
    private int userId;
    private Object gameData;
    private int pointsBet;

    public int getUserId() {
        return this.userId;
    }
    public void setUserId(int id) {
        this.userId = id;
    }
    public Object getGameData() {
        return this.gameData;
    }
    public void setGameData(Object gameData) {
        this.gameData = gameData;
    }
    public int getPointsBet() { return this.pointsBet; }
    public void setPointsBet(int pointsBet) { this.pointsBet = pointsBet; }

    public ReceiveCointossDataRequest(int id, Object gameData, int points) {
        this.userId = id;
        this.gameData = gameData;
        this.pointsBet = points;
    }
}
