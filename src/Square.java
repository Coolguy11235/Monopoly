public class Square {

    // Privata fälter:
    int number;
    private String name;
    String description;
    static int totalSquares = 0;
    private int price;
    private int rentPrice;
    private boolean isRentPaid = false;

    // Public metoder:
    public String getName() {
        return name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setRentPrice(int rentPrice) {
        this.rentPrice = rentPrice;
    }

    public int getRentPrice() {
        return rentPrice;
    }

    public boolean isRentPaid() {
        return isRentPaid;
    }

    public void setRentPaid(boolean pay) {
        isRentPaid = pay;
    }
}
