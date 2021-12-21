package very.cool.application.DTO;

public class SendGameDataRequest {
    private int newPoints;
    private Object gameData;

    public int getNewPoints() { return this.newPoints; }
    public void setNewPoints(int newPoints) { this.newPoints = newPoints; }
    public Object getGameData() { return this.gameData; }
    public void setGameData(Object gameData) { this.gameData = gameData; }

    public SendGameDataRequest(int newPoints, Object gameData) {
        this.newPoints = newPoints;
        this.gameData = gameData;
    }
}
