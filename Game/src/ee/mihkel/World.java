package ee.mihkel;

import ee.mihkel.character.Character;
import ee.mihkel.character.Player;
import ee.mihkel.item.Item;

import java.util.ArrayList;
import java.util.List;

public class World {
    // encapsulation
    private int height;
    private int width;
    private List<Character> characters = new ArrayList<>();
    private List<Item> items = new ArrayList<>();


    // constructor overloading
    //public World() {}

    public World(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void addCharacter(Character player) {
        this.characters.add(player);
    }

    public void addItem(Item item) {
        this.items.add(item);
    }


    public void printMap() {
        char symbol; // siin deklareerin ehk annan mälukoha
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (y == 0 || y == height-1) { // kõige vasakpoolsem võiks olla mis on kõige suurema tõenäosusega "true"
                    symbol = '-';
                } else if (x == 0 || x == width-1) {
                    symbol = '|';
                } else {
                    symbol = ' ';
                }
                for (Item i : items) {
                    if (i.getxCoord() == x && i.getyCoord() == y) { // kõige vasakpoolsem võiks olla mis on kõige suurema tõenäosuega "false"
                        symbol = i.getSymbol();
                    }
                }
                for (Character p : characters) {
                    if (p.getxCoord() == x && p.getyCoord() == y && p.isVisible()) { // kõige vasakpoolsem võiks olla mis on kõige suurema tõenäosuega "false"
                        symbol = p.getSymbol();
                    }
                }
                System.out.print(symbol);
            }
            System.out.println();
        }
    }

}
