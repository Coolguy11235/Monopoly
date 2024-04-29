import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Game extends JFrame {

    // Privata fälter:
    private JPanel contentIncluder;
    static JTextArea infoConsole;
    JPanel playerAssetsPanel;
    CardLayout c1 = new CardLayout();
    ArrayList<Player> players = new ArrayList<Player>();
    static int turnCounter = 0;
    JButton btnNextTurn;
    JButton btnRollDice;
    JButton btnPayRent;
    JButton btnBuy;
    JTextArea panelPlayer1TextArea;
    JTextArea panelPlayer2TextArea;
    Board gameBoard;
    Player player1;
    Player player2;
    Boolean doubleDiceForPlayer1 = false;
    Boolean doubleDiceForPlayer2 = false;
    static int nowPlaying = 0;

    // Public metoder:
    public Game() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setSize(1080, 720);
        contentIncluder = new JPanel();
        contentIncluder.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentIncluder);
        contentIncluder.setLayout(null);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBorder(new LineBorder(new Color(0, 0, 0)));
        layeredPane.setBounds(6, 6, 632, 630);
        contentIncluder.add(layeredPane);

        gameBoard = new Board(6, 6, 612, 612);
        gameBoard.setBackground(new Color(51, 255, 153));
        layeredPane.add(gameBoard, new Integer(0));

        player1 = new Player(1, Color.RED);
        players.add(player1);
        layeredPane.add(player1, new Integer(1));

        player2 = new Player(2, Color.BLUE);
        players.add(player2);
        layeredPane.add(player2, new Integer(1));

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.LIGHT_GRAY);
        rightPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        rightPanel.setBounds(634, 6, 419, 600);
        contentIncluder.add(rightPanel);
        rightPanel.setLayout(null);

        btnBuy = new JButton("Köp");
        btnBuy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Minska eftersom vi ökade i slutet av tärningen
                Player currentPlayer = players.get(nowPlaying);
                infoConsole.setText("Du köpte " + gameBoard.getAllSquares().get(currentPlayer.getCurrentSquareNumber()).getName());
                currentPlayer.buyTitleDeed(currentPlayer.getCurrentSquareNumber());
                int withdrawAmount = gameBoard.getAllSquares().get(currentPlayer.getCurrentSquareNumber()).getPrice();
                currentPlayer.withdrawFromWallet(withdrawAmount);
                btnBuy.setEnabled(false);
                updatePanelPlayer1TextArea();
                updatePanelPlayer2TextArea();
            }
        });

        btnBuy.setBounds(81, 478, 117, 29);
        rightPanel.add(btnBuy);
        btnBuy.setEnabled(false);

        btnBuyRent = new JButton("Betala ränta");
        btnBuyRent.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Gör autogenererad metodstub
                Player currentPlayer = players.get(nowPlaying);
                Player ownerOfTheSquare = players.get((Player.ledger.get(currentPlayer.getCurrentSquareNumber()))==1?0:1);
                infoConsole.setText("Du betalade till spelaren " + ownerOfTheSquare.getPlayerNumber());
            }
        });

    }
}
