import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

// klassen föreställer alla spelarna
public class Player extends JPanel {

    // Privata fälter:
    private int playerNumber;
    JLabel labelPlayerNumber;
    static int totalPlayers = 0;
    static HashMap<Integer, Integer> ledger = new HashMap<>();

    private int currentSquareNumber = 0; // Var spelaren befinner sig (0 - 19)
    private ArrayList<Integer> titleDeeds = new ArrayList<Integer>(); // Rutor som spelaren har
    private int wallet = 1500; // Antalet pengar man börjar med

    // Public metoder:
    // Hämtar spelarens nummer
    public int getPlayerNumber() {
        return playerNumber;
    }

    // Hämtar spelarens plånbokssaldo
    public int getWallet() {
        return wallet;
    }

    // Drar ett belopp från spelarens plånbok
    public void withdrawFromWallet(int withdrawAmount) {
        if (withdrawAmount > wallet) {
            //setVisible(false);
            System.out.println("Spelaren " + playerNumber + " gick i konkurs!");
        } else {
            wallet -= withdrawAmount;
        }
    }

    // Lägger till ett belopp till spelarens plånbok
    public void depositToWallet(int depositAmount) {
        wallet += depositAmount;
        System.out.println("Avlöningsdag för spelaren" + playerNumber + ". Du fick " + depositAmount + "$!");
    }

    // Hämtar spelarens nuvarande position på spelbrädet
    public int getCurrentSquareNumber() {
        return currentSquareNumber;
    }

    // Hämtar en lista över de rutor som spelare äger
    public ArrayList<Integer> getTitleDeeds() {
        return titleDeeds;
    }

    // Kontrollerar om spelaren äger en ruta
    public boolean hasTitleDeed(int squareNumber) {
        return titleDeeds.contains(squareNumber) ? true: false;
    }

    // Låter dig att köpa en ruta och lägga till den i spelarens äganderätt
    public void buyTitleDeed(int squareNumber) {
        if(ledger.containsKey(squareNumber)) {
            System.out.println("Det är redan köpt av någon!");
        } else {
            // Varje gång spelaren köper en titleDeed skrivs den i ledger
            titleDeeds.add(this.getCurrentSquareNumber());
            ledger.put(squareNumber, this.getPlayerNumber());
        }
    }

    // Konstruktor som skapar en spelare med specifika dimensioner och positition
    public Player(int xCoord, int yCoord, int width, int height) {
        setBorder(new LineBorder(new Color(0, 0, 0)));
        setBounds(xCoord, yCoord, 20, 20);
        this.setLayout(null);
    }

    // Konstruktor som skapar en spelare med ett givet nummer och färg
    public Player(int playerNumber, Color color) {
        this.playerNumber = playerNumber;
        this.setBackground(color);
        labelPlayerNumber = new JLabel(""+playerNumber);
        labelPlayerNumber.setFont(new Font("Times New Roman", Font.BOLD, 15));
        labelPlayerNumber.setForeground(Color.WHITE);
        this.add(labelPlayerNumber);
        this.setBounds(playerNumber*30, 33, 20, 28);
        totalPlayers++;
    }

    // Ritar spelaren på spelbrädet
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    // Arrayer som lagrar x- och y-koordinater för spelarens positioner
    int[] xLocationsOfPlayer1 = {31, 131, 231, 331, 431, 531,
                                 531, 531, 531, 531, 531,
                                 431, 331, 231, 131, 31,
                                 31, 31, 31, 31};

    int[] yLocationsOfPlayer1 = {33, 33, 33, 33, 33, 33,
                                 133, 233, 333, 433, 533,
                                 533, 533, 533, 533, 533,
                                 433, 333, 233, 133};

    int[] xLocationsOfPlayer2 = {61, 191, 291, 361, 461, 561,
                                 561, 561, 561, 561, 561,
                                 461, 361, 261, 161, 61,
                                 61, 61, 61, 61};

    int[] yLocationsOfPlayer2 = {33, 33, 33, 33, 33, 33,
                                 133, 233, 333, 433, 533,
                                 533, 533, 533, 533, 533,
                                 433, 333, 233, 133};

    // Flyttar spelaren på spelbrädet
    public void move(int dicesTotal) {

        // Om spelaren hamnar på eller passerar start får den 200 som belöning
        if(currentSquareNumber + dicesTotal > 19) {
            depositToWallet(200);
        }

        // Beräknar målrutan efter att spelaren har flyttat
        int targetSquare = (currentSquareNumber + dicesTotal) % 20;
        currentSquareNumber = targetSquare;

        // Flyttar spelaren till den nya målrutan baserat på vilken spelare som spelar
        if(Game.nowPlaying == 0) {
            this.setLocation(xLocationsOfPlayer1[targetSquare], yLocationsOfPlayer1[targetSquare]);
        } else {
            this.setLocation(xLocationsOfPlayer2[targetSquare], yLocationsOfPlayer2[targetSquare]);
        }

        // Uppdaterar informationen i infokonsolen om den nya rutan tillhör en anna spelare
        if(ledger.containsKey(this.getCurrentSquareNumber())) {
            Game.infoConsole.setText("Denna egenskap tillhör spelaren " + ledger.get(this.getCurrentSquareNumber()));
        }
    }

    // Genom att jämföra spelarens koordinater enligt Board kvadratnummer
    public int getCurrentSquareNumberByCoordinates() {

        int x = this.getX();
        int y = this.getY();

        if(x < 100) {
            if (y < 100) {
                return 0;
            } else if (y > 100 && y < 200) {
                return 19;
            } else if (y > 200 && y < 300) {
                return 18;
            } else if (y > 300 && y < 400) {
                return 17;
            } else if (y > 400 && y < 500) {
                return 16;
            } else {
                return 15;
            }
        } else if (x > 100 && x < 200) {
            if (y < 100) {
                return 1;
            } else {
                return 14;
            }
        } else if (x > 200 && x < 300) {
            if (y < 100) {
                return 2;
            } else {
                return 13;
            }
        } else if (x > 300 && x < 400) {
            if (y < 100) {
                return 3;
            } else {
                return 12;
            }
        } else if (x > 400 && x < 500) {
            if (y < 100) {
                return 4;
            } else {
                return 11;
            }
        } else {
            if(y < 100) {
                return 5;
            } else if(y > 100 && y < 200) {
                return 6;
            } else if(y > 200 && y < 300) {
                return 7;
            } else if(y > 300 && y < 400) {
                return 8;
            } else if(y > 400 && y < 500) {
                return 9;
            } else {
                return 10;
            }
    }
    }
}
