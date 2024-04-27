import java.util.Random;

public class Dice {

    // Genererar random funktionen
    Random random = new Random();
    int faceValue = 1;

    // Public metoder:
    public void rollDice() {
        faceValue = random.nextInt(6);
    }

    public int getFaceValue() {
        return faceValue;
    }
}
