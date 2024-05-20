import java.awt.*;

public class Enemy extends Player {

    // Arrayer som lagrar x- och y-koordinater för fiendens positioner
    int[] xLocationsOfEnemy = {61, 191, 291, 361, 461, 561,
            561, 561, 561, 561, 561,
            461, 361, 261, 161, 61,
            61, 61, 61, 61};

    int[] yLocationsOfEnemy = {33, 33, 33, 33, 33, 33,
            133, 233, 333, 433, 533,
            533, 533, 533, 533, 533,
            433, 333, 233, 133};

    // Konstruktor för fienden
    public Enemy(int playerNumber, Color color) {
        super(playerNumber, color);
    }

    // Uppdaterar rörelsen av fienden
    @Override
    public void move(int dicesTotal) {

        // Om fienden hamnar på eller passerar start får den 200 som belöning
        if (getCurrentSquareNumber() + dicesTotal > 19) {
            depositToWallet(200);
        }

        // Beräknar målrutan efter att spelaren har flyttat
        int targetSquare = (getCurrentSquareNumber() + dicesTotal) % 20;
        currentSquareNumber = targetSquare;

        // Flyttar fienden till den nya målrutan baserat
        this.setLocation(xLocationsOfEnemy[targetSquare], yLocationsOfEnemy[targetSquare]);

        // Uppdaterar informationen i infokonsolen om den nya rutan tillhör en spelaren
        if(ledger.containsKey(this.getCurrentSquareNumber())) {
            Game.infoConsole.setText("Denna egenskap tillhör fienden " + ledger.get(this.getCurrentSquareNumber()));
        }
    }
}
