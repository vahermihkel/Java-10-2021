package ee.mihkel.character;

import ee.mihkel.World;

import java.util.List;
import java.util.stream.Collectors;

// abstraction
public abstract class Character {
    protected int xCoord;
    protected int yCoord;
    private char symbol;
    private boolean isVisible;
    private double health;

    public Character(char symbol, World world) {
        this.symbol = symbol;
        this.isVisible = true;
        this.health = 10;
    }

    public void randomiseCoordinates(int worldWidth, int worldHeight, List<Character> characters) {
        xCoord = (int)(Math.random()*(worldWidth - 2)) + 1;
        yCoord = (int)(Math.random()*(worldHeight - 2)) + 1;
        checkCoordinaatesUniqueness(worldWidth, worldHeight, characters);
    }

    private void checkCoordinaatesUniqueness(int worldWidth, int worldHeight, List<Character> characters) {
        // stream
        List<Character> charactersWithoutThis = characters.stream()
                .filter(e -> e.symbol != this.symbol)
                .collect(Collectors.toList());

        for (Character c: charactersWithoutThis) {
            if (c.xCoord == this.xCoord && c.yCoord == this.yCoord) {
                randomiseCoordinates(worldWidth, worldHeight, characters);
            }
        }
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

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisibility(boolean visible) {
        isVisible = visible;
    }

    public void takeHealth() {
        this.health--;
    }

    public double getHealth() {
        return health;
    }

    public void reboost() {
        this.health = 10;
    }
}
