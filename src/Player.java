import java.util.HashMap;

// klassen föreställer alla spelarna
public class Player {

    // Privata fälter:
    private int playerNumber;
    static int totalPlayers = 0;
    static HashMap<Integer, Integer> ledger = new HashMap<>();

    private int wallet = 1500; // Antalet pengar man börjar med

    // Public metoder:
    public int getPlayerNumber() {
        return playerNumber;
    }

    public int getWallet() {
        return wallet;
    }

    public void withdrawFromWallet(int withdrawAmount) {
        if (withdrawAmount > wallet) {
            //setVisible(false);
            System.out.println("Spelaren " + playerNumber + " gick i konkurs!");
        } else {
            wallet -= withdrawAmount;
        }
    }

    public void depositToWallet(int depositAmount) {
        wallet += depositAmount;
        System.out.println("Avlöningsdag för spelaren" + playerNumber + ". Du fick ###$!");
    }


}
