import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Random;

public class Dice extends JPanel {

    // Genererar random funktionen
    Random random = new Random();
    int faceValue = 1;

    // Public metoder:
    // Konstruktor som skapar en tärning med specifika dimensioner och position
    public Dice(int xCoord, int yCoord, int width, int height) {
        setBorder(new LineBorder(new Color(0, 0, 0))); // Sätter en svart kant
        setBounds(xCoord, yCoord, width, height); // Sätter position och storlek
    }

    // Metod som ritar tärningen
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Anropar superklassens paintComponent-metod

        // Ritar svarta cirklar baserat på aktuellt värde
        if(faceValue == 1) {
            g.fillOval(getWidth()/2 - 5/2, getHeight()/2 - 5/2, 5, 5);
        } else if(faceValue == 2) {
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
        } else if(faceValue == 3) {
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 - 5/2, getHeight()/2 - 5/2, 5, 5);
        } else if(faceValue == 4) {
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 - 15, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 + 10, 5, 5);
        } else if(faceValue == 5) {
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 - 15, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 - 5/2, getHeight()/2 - 5/2, 5, 5);
        } else {
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 - 15, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 - 15, getHeight()/2 - 5/2, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 5/2, 5, 5);
        }
    }

    // Rullar tärningen och uppdaterar värden
    public void rollDice() {
        // Genererar ett slumpmässigt värde
        faceValue = random.nextInt(6);
    }

    // Hämtar värdet på tärningen
    public int getFaceValue() {
        return faceValue;
    }

    // Konstruktor
    public Dice(int xCoord, int yCoord, int width, int height, String labelString) {
        setBorder(new LineBorder(new Color(0, 0, 0)));
        setBounds(xCoord, yCoord, width, height);
    }
}
