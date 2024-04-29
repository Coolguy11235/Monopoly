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
    // Konstruktorn för spelet som skapar gränssnittet och spelobjekten
    public Game() {

        // Inställningar för fönstret och layouten
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

        // Skapar spelbräde
        gameBoard = new Board(6, 6, 612, 612);
        gameBoard.setBackground(new Color(51, 255, 153));
        layeredPane.add(gameBoard, new Integer(0));

        // Skapar spelaren
        player1 = new Player(1, Color.RED);
        players.add(player1);
        layeredPane.add(player1, new Integer(1));

        player2 = new Player(2, Color.BLUE);
        players.add(player2);
        layeredPane.add(player2, new Integer(1));

        // Skapar högerpanelen för spelinformation
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.LIGHT_GRAY);
        rightPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        rightPanel.setBounds(634, 6, 419, 600);
        contentIncluder.add(rightPanel);
        rightPanel.setLayout(null);

        // Skapar Knappar
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

        btnPayRent = new JButton("Betala ränta");
        btnPayRent.addActionListener(new ActionListener() {

            @Override
            // Gör autogenererad metodstub
            public void actionPerformed(ActionEvent e) {
                Player currentPlayer = players.get(nowPlaying);
                Player ownerOfTheSquare = players.get((Player.ledger.get(currentPlayer.getCurrentSquareNumber()))==1?0:1);
                infoConsole.setText("Du betalade till spelaren " + ownerOfTheSquare.getPlayerNumber());

                int withdrawAmout = gameBoard.getAllSquares().get(currentPlayer.getCurrentSquareNumber()).getRentPrice();
                System.out.println(withdrawAmout);
                currentPlayer.withdrawFromWallet(withdrawAmout);
                ownerOfTheSquare.depositToWallet(withdrawAmout);

                btnNextTurn.setEnabled(true);
                btnPayRent.setEnabled(false);
                updatePanelPlayer1TextArea();
                updatePanelPlayer2TextArea();
            }
        });

        btnPayRent.setBounds(210, 478, 117, 29);
        rightPanel.add(btnPayRent);
        btnPayRent.setEnabled(false);

        // Skapar tärningar och lägger till dem på spelbrädet
        Dice dice1 = new Dice(244, 406, 40, 40);
        layeredPane.add(dice1, new Integer(1));

        Dice dice2 = new Dice(333, 406, 40, 40);
        layeredPane.add(dice2, new Integer(1));

        // Skapar knappen för att kasta tärning, köpa, betala ränta och gå till nästa tur
        btnRollDice = new JButton("Kasta tärning");
        btnRollDice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Spelarens 1 tur
                if(nowPlaying == 0) {
                    int dice1OldValue = dice1.getFaceValue();
                    int dice2OldValue = dice2.getFaceValue();
                    dice1.rollDice();
                    dice2.rollDice();
                    int dicesTotal = dice1.getFaceValue() + dice2.getFaceValue();
                    if(dice1.getFaceValue() == dice2.getFaceValue()) {
                        doubleDiceForPlayer1 = true;
                    } else {
                        doubleDiceForPlayer2 = false;
                    }
                    player1.move(dicesTotal);
                    // Om köpt av någon
                    if(Player.ledger.containsKey(player1.getCurrentSquareNumber()) && Player.ledger.get(player1.getCurrentSquareNumber()) != player1.getPlayerNumber()) {
                        btnBuy.setEnabled(false);
                        btnRollDice.setEnabled(false);
                        btnNextTurn.setEnabled(false);
                        btnPayRent.setEnabled(true);
                    }
                    // Om köpt av någon
                    if(Player.ledger.containsKey(player1.getCurrentSquareNumber()) && Player.ledger.get(player1.getCurrentSquareNumber()) == player1.getPlayerNumber()) {
                        btnBuy.setEnabled(false);
                        btnPayRent.setEnabled(false);
                        btnNextTurn.setEnabled(true);
                    }

                    if(gameBoard.getUnbuyableSquares().contains(gameBoard.getAllSquares().get(player1.getCurrentSquareNumber()))) {
                        btnBuy.setEnabled(false);
                        btnNextTurn.setEnabled(true);
                    } else if (!Player.ledger.containsKey(player1.getCurrentSquareNumber())) { // Om inte köpt av någon
                        btnBuy.setEnabled(true);
                        btnNextTurn.setEnabled(true);
                        btnPayRent.setEnabled(false);
                    }
                } else {
                    // Spelarens 2 tur
                    int dice1OldValue = dice1.getFaceValue();
                    int dice2OldValue = dice2.getFaceValue();
                    dice1.rollDice();
                    dice2.rollDice();
                    int dicesTotal = dice1.getFaceValue() + dice2.getFaceValue();
                    if(dice1.getFaceValue() == dice2.getFaceValue()) {
                        doubleDiceForPlayer1 = true;
                    } else {
                        doubleDiceForPlayer2 = false;
                    }
                    player2.move(dicesTotal);
                    // Om köpt av någon
                    if(Player.ledger.containsKey(player2.getCurrentSquareNumber()) && Player.ledger.get(player2.getCurrentSquareNumber()) != player2.getPlayerNumber()) {
                        btnBuy.setEnabled(false);
                        btnRollDice.setEnabled(false);
                        btnNextTurn.setEnabled(false);
                        btnPayRent.setEnabled(true);
                    }
                    // Om köpt av någon
                    if(Player.ledger.containsKey(player2.getCurrentSquareNumber()) && Player.ledger.get(player2.getCurrentSquareNumber()) == player2.getPlayerNumber()) {
                        btnBuy.setEnabled(false);
                        btnPayRent.setEnabled(false);
                        btnNextTurn.setEnabled(true);
                    }

                    if(gameBoard.getUnbuyableSquares().contains(gameBoard.getAllSquares().get(player2.getCurrentSquareNumber()))) {
                        btnBuy.setEnabled(false);
                        btnNextTurn.setEnabled(true);
                    } else if (!Player.ledger.containsKey(player2.getCurrentSquareNumber())) { // Om inte köpt av någon
                        btnBuy.setEnabled(true);
                        btnNextTurn.setEnabled(true);
                        btnPayRent.setEnabled(false);
                    }
                }

                btnRollDice.setEnabled(false);
                if(doubleDiceForPlayer1 || doubleDiceForPlayer2) {
                    infoConsole.setText("Klicka nästa tur för att låta spelaren " + (nowPlaying == 0 ? 1 : 2) + " att kasta tärning!");
                } else {
                    infoConsole.setText("Klicka nästa tur för att låta spelaren " + (nowPlaying == 0 ? 2 : 1) + " att kasta tärning!");
                }

                layeredPane.remove(gameBoard);
                layeredPane.add(gameBoard, new Integer(0));

                updatePanelPlayer1TextArea();
                updatePanelPlayer2TextArea();
            }
        });

        btnRollDice.setBounds(81, 413, 246, 53);
        rightPanel.add(btnRollDice);

        btnNextTurn = new JButton("Nästa tur");
        btnNextTurn.addActionListener(new ActionListener() {
            @Override
            // Gör autogenererad metodstub
            public void actionPerformed(ActionEvent e) {
                btnRollDice.setEnabled(true);
                btnBuy.setEnabled(false);
                btnPayRent.setEnabled(false);
                btnNextTurn.setEnabled(false);

                if(nowPlaying == 0 && doubleDiceForPlayer1) {
                    nowPlaying = 0;
                    doubleDiceForPlayer1 = false;
                } else if(nowPlaying == 1 && doubleDiceForPlayer2) {
                    nowPlaying = 1;
                    doubleDiceForPlayer2 = false;
                } else if(!doubleDiceForPlayer1 && !doubleDiceForPlayer2) {
                    nowPlaying = (nowPlaying + 1) % 2;
                }

                c1.show(playerAssetsPanel, "" + (nowPlaying == 0 ? 1 : 2));
                updatePanelPlayer1TextArea();
                updatePanelPlayer2TextArea();
                infoConsole.setText("Det är nu spelarens " + (nowPlaying == 0 ? 1 : 2) + " tur!");
            }
        });

        btnNextTurn.setBounds(81, 519, 246, 53);
        rightPanel.add(btnNextTurn);
        btnNextTurn.setEnabled(false);

        JPanel test = new JPanel();
        test.setBounds(81, 312, 246, 68);
        rightPanel.add(test);
        test.setLayout(null);

        playerAssetsPanel = new JPanel();
        playerAssetsPanel.setBounds(81, 28, 246, 189);
        rightPanel.add(playerAssetsPanel);
        playerAssetsPanel.setLayout(c1);

        JPanel panelPlayer1 = new JPanel();
        panelPlayer1.setBackground(Color.RED);
        playerAssetsPanel.add(panelPlayer1, "1");
        panelPlayer1.setLayout(null);

        JLabel panelPlayer1Title = new JLabel("Spelare 1 All Rikedom");
        panelPlayer1Title.setForeground(Color.white);
        panelPlayer1Title.setHorizontalAlignment(SwingConstants.CENTER);
        panelPlayer1Title.setBounds(0, 6, 240, 16);
        panelPlayer1.add(panelPlayer1Title);

        panelPlayer1TextArea = new JTextArea();
        panelPlayer1TextArea.setBounds(10, 34, 230, 149);
        panelPlayer1.add(panelPlayer1TextArea);

        JPanel panelPlayer2 = new JPanel();
        panelPlayer2.setBackground(Color.BLUE);
        playerAssetsPanel.add(panelPlayer2, "2");
        panelPlayer2.setLayout(null);
        c1.show(playerAssetsPanel, "" + nowPlaying);

        JLabel panelPlayer2Title = new JLabel("Spelare 2 All Rikedom");
        panelPlayer2Title.setForeground(Color.white);
        panelPlayer2Title.setHorizontalAlignment(SwingConstants.CENTER);
        panelPlayer2Title.setBounds(0, 6, 240, 16);
        panelPlayer2.add(panelPlayer2Title);

        panelPlayer2TextArea = new JTextArea();
        panelPlayer2TextArea.setBounds(10, 34, 230, 149);
        panelPlayer2.add(panelPlayer2TextArea);

        updatePanelPlayer1TextArea();
        updatePanelPlayer2TextArea();

        infoConsole = new JTextArea();
        infoConsole.setColumns(20);
        infoConsole.setRows(5);
        infoConsole.setBounds(6, 6, 234, 56);
        test.add(infoConsole);
        infoConsole.setLineWrap(true);
        infoConsole.setText("Spelare 1 börjar att kasta tärning!");
    }

    public void updatePanelPlayer1TextArea() {
        String result = "";
        result += "Aktuellt saldo: " + player1.getWallet() + "\n";
        result += "Lagfart: \n";
        for(int i = 0; i < player1.getTitleDeeds().size(); i++) {
            result += " - " + gameBoard.getAllSquares().get(player1.getTitleDeeds().get(i)).getName() + "\n";
        }
        panelPlayer1TextArea.setText(result);
    }

    public void updatePanelPlayer2TextArea() {
        String result = "";
        result += "Aktuellt saldo: " + player2.getWallet() + "\n";
        result += "Lagfart: \n";
        for(int i = 0; i < player2.getTitleDeeds().size(); i++) {
            result += " - " + gameBoard.getAllSquares().get(player2.getTitleDeeds().get(i)).getName() + "\n";
        }
        panelPlayer2TextArea.setText(result);
    }

    public static void errorBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.ERROR_MESSAGE);
    }

}
