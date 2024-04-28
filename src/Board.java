import java.awt.*;
import java.util.ArrayList;

public class Board {

    // Privata fälter:
    private ArrayList<Square> allSquares = new ArrayList<Square>();
    private ArrayList<Square> unbuyableSquares = new ArrayList<Square>();

    // Public metoder:
    public ArrayList<Square> getAllSquares() {
        return allSquares;
    }

    public ArrayList<Square> getUnbuyableSquares() {
        return unbuyableSquares;
    }

    public Square getSquareAtIndex(int location) {
        return allSquares.get(location);
    }

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

        Square square0 = new Square();
        this.add(square0);
        allSquares.add(square0);
        unbuyableSquares.add(square0);
    }


}
