import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Board extends JPanel {

    // Privata fälter:
    private ArrayList<Square> allSquares = new ArrayList<Square>();
    private ArrayList<Square> unbuyableSquares = new ArrayList<Square>();

    // Public metoder:
    // Hämtar alla rutor på spelbrädet
    public ArrayList<Square> getAllSquares() {
        return allSquares;
    }

    // Hämtar alla oinköpbara rutor på spelbrädet
    public ArrayList<Square> getUnbuyableSquares() {
        return unbuyableSquares;
    }

    // Hämtar rutan vid en specifik position på spelbrädet
    public Square getSquareAtIndex(int location) {
        return allSquares.get(location);
    }

    // Konstruktor som skapar ett spelbräde med specifika dimensioner
    public Board(int xCoord, int yCoord, int width, int height) {
        setBorder(new LineBorder(new Color(0, 0, 0)));
        setBounds(xCoord, yCoord, 612, 612);
        this.setLayout(null);
        initializeSquares();
    }

    // Initialiserar alla rutor på spelbrädet
    private void initializeSquares() {
        String[] squareNames = {
                "Go",
                "Burger King",
                "Kista",
                "KFC",
                "McDonalds",
                "Rulla en gång",
                "Cola",
                "Chans",
                "Pepsi",
                "Fanta",
                "Fri parkering",
                "BMW",
                "Kista",
                "Tesla",
                "Volkswagen",
                "Fängelse",
                "Youtube",
                "Tik Tok",
                "Chans",
                "Instagram"
        };

        // Rutor med specifika namn och positioner (vissa är oinköpbara och andra kan köpas)
        // Rutor på toppen
        Square square0 = new Square(6, 6, 100, 100, squareNames[0], 135);
        this.add(square0);
        allSquares.add(square0);
        unbuyableSquares.add(square0);

        Square square1 = new Square(106, 6, 100, 100, squareNames[1], 180);
        this.add(square1);
        allSquares.add(square1);

        Square square2 = new Square(206, 6, 100, 100, squareNames[2], 180);
        this.add(square2);
        allSquares.add(square2);
        unbuyableSquares.add(square2);

        Square square3 = new Square(306, 6, 100, 100, squareNames[3], 180);
        this.add(square3);
        allSquares.add(square3);

        Square square4 = new Square(406, 6, 100, 100, squareNames[4], 180);
        this.add(square4);
        allSquares.add(square4);

        Square square5 = new Square(506, 6, 100, 100, squareNames[5], -135);
        this.add(square5);
        allSquares.add(square5);
        unbuyableSquares.add(square5);

        // Rutor åt höger
        Square square6 = new Square(506, 106, 100, 100, squareNames[6], -90);
        this.add(square6);
        allSquares.add(square6);

        Square square7 = new Square(506, 206, 100, 100, squareNames[7], -90);
        this.add(square7);
        allSquares.add(square7);
        unbuyableSquares.add(square7);

        Square square8 = new Square(506, 306, 100, 100, squareNames[8], -90);
        this.add(square8);
        allSquares.add(square8);

        Square square9 = new Square(506, 406, 100, 100, squareNames[9], -90);
        this.add(square9);
        allSquares.add(square9);

        Square square10 = new Square(506, 506, 100, 100, squareNames[10], -45);
        this.add(square10);
        allSquares.add(square10);
        unbuyableSquares.add(square10);

        // Rutor på botten
        Square square11 = new Square(406, 506, 100, 100, squareNames[11], 0);
        this.add(square11);
        allSquares.add(square11);

        Square square12 = new Square(306, 506, 100, 100, squareNames[12], 0);
        this.add(square12);
        allSquares.add(square12);
        unbuyableSquares.add(square12);

        Square square13 = new Square(206, 506, 100, 100, squareNames[13], 0);
        this.add(square13);
        allSquares.add(square13);

        Square square14 = new Square(106, 506, 100, 100, squareNames[14], 0);
        this.add(square14);
        allSquares.add(square14);

        Square square15 = new Square(6, 506, 100, 100, squareNames[15], 45);
        this.add(square15);
        allSquares.add(square15);
        unbuyableSquares.add(square15);

        // Rutor åt vänster
        Square square16 = new Square(6, 406, 100, 100, squareNames[16], 90);
        this.add(square16);
        allSquares.add(square16);

        Square square17 = new Square(6, 306, 100, 100, squareNames[17], 90);
        this.add(square17);
        allSquares.add(square17);

        Square square18 = new Square(6, 206, 100, 100, squareNames[18], 90);
        this.add(square18);
        allSquares.add(square18);
        unbuyableSquares.add(square18);

        Square square19 = new Square(6, 106, 100, 100, squareNames[19], 90);
        this.add(square19);
        allSquares.add(square19);

        // Sätter pris
        square1.setPrice(100);
        square3.setPrice(140);
        square4.setPrice(200);

        square6.setPrice(100);
        square8.setPrice(160);
        square9.setPrice(300);

        square11.setPrice(120);
        square13.setPrice(180);
        square14.setPrice(320);

        square16.setPrice(140);
        square17.setPrice(180);
        square19.setPrice(320);

        // Fastställer hyrespriser
        square1.setRentPrice(6);
        square3.setRentPrice(10);
        square4.setRentPrice(16);

        square6.setRentPrice(6);
        square8.setRentPrice(12);
        square9.setRentPrice(26);

        square11.setRentPrice(8);
        square13.setRentPrice(14);
        square14.setRentPrice(26);

        square16.setRentPrice(10);
        square17.setRentPrice(14);
        square19.setRentPrice(28);

        // Skapar en JLabel för "MONOPOLY" som placeras i mitten av spelbrädet
        JLabel labelMonopoly = new JLabel("MONOPOLY") {
            protected void paintComponent(Graphics g) {
                Graphics2D g2D = (Graphics2D)g;
                g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                AffineTransform aT = g2D.getTransform();
                Shape oldshape = g2D.getClip();
                double x = getWidth()/2.0;
                double y = getHeight()/2.0;
                g2D.setTransform(aT);
                g2D.setClip(oldshape);
                super.paintComponent(g);
            }
        };

        labelMonopoly.setForeground(Color.white);
        labelMonopoly.setBackground(Color.red);
        labelMonopoly.setOpaque(true);
        labelMonopoly.setHorizontalAlignment(SwingConstants.CENTER);
        labelMonopoly.setFont(new Font("Times New Roman", Font.BOLD, 40));
        labelMonopoly.setBounds(179, 277, 263, 55);
        this.add(labelMonopoly);
    }


}
