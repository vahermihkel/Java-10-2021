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

    public Character(char symbol, World world) {
        this.symbol = symbol;
        this.isVisible = true;
        randomiseCoordinates(world);
    }

    public void randomiseCoordinates(World world) {
        xCoord = (int)(Math.random()*(world.getWidth() - 2)) + 1;
        yCoord = (int)(Math.random()*(world.getHeight() - 2)) + 1;
        checkCoordinaatesUniqueness(world);
    }

    private void checkCoordinaatesUniqueness(World world) {
        // stream
        List<Character> charactersWithoutThis = world.getCharacters().stream()
                .filter(e -> e.symbol != this.symbol)
                .collect(Collectors.toList());

        for (Character c: charactersWithoutThis) {
            if (c.xCoord == this.xCoord && c.yCoord == this.yCoord) {
                randomiseCoordinates(world);
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
}
