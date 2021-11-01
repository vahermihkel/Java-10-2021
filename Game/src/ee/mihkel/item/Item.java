package ee.mihkel.item;

import ee.mihkel.World;

public abstract class Item {
    private int xCoord;
    private int yCoord;
    private final char symbol;
    private final double strength;
    private int durability;
    private final String name;

    // parem klõps -> generate -> constructor -> võtan kõik
    public Item(double strength, int durability, String name, World world) {
        this.symbol = 'I';
        this.strength = strength;
        this.durability = durability;
        this.name = name;
        randomiseCoordinates(world);
    }

    public void randomiseCoordinates(World world) {
        xCoord = (int)(Math.random()*(world.getWidth() - 2)) + 1;
        yCoord = (int)(Math.random()*(world.getHeight() - 2)) + 1;
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

    public double getStrength() {
        return strength;
    }

    public int getDurability() {
        return durability;
    }

    public String getName() {
        return name;
    }

    public void increaseDurability() {
        this.durability++;
    }

    public void decreaseDurability() {
        this.durability--;
    }

    public abstract void reboost();

    protected void setDurability(int durability) {
        this.durability = durability;
    }

    @Override
    public String toString() {
        return name +
                "(" + strength +
                "), kasutuskordi: " + durability;
    }
}
