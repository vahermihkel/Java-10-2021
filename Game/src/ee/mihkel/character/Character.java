package ee.mihkel.character;

public class Character {
    protected int xCoord = 2;
    protected int yCoord = 3;
    private char symbol;

    public Character(char symbol) {
        this.symbol = symbol;
    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public char getSymbol() {
        return symbol;
    }
}
