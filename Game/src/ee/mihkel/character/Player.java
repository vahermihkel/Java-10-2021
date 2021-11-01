package ee.mihkel.character;

import ee.mihkel.World;
import ee.mihkel.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player extends Character {
    private Map<EnemyType, Integer> killedEnemies = new HashMap<>();
    private List<Item> inventory = new ArrayList<>();
    private Direction direction = Direction.UP;

    public Player(World world) {
        super('X', world);
    }

    public void addToKilledEnemies(EnemyType enemyType) {
        this.killedEnemies.put(enemyType,this.killedEnemies.get(enemyType)+1);
    }

    // 1. väljakuvamine --- void showKilledEnemies
    // sout Ant: 3
    // Dragon: 5

    public boolean inventoryEmpty() {
        return this.inventory.isEmpty();
    }

    public void addToInventory(Item item) {
        if (this.inventory.contains(item)) {
            item.increaseDurability();
        } else {
            if (item.getDurability() == 0) {
                item.reboost();
            }
            this.inventory.add(item);
        }
    }

    public Item useItem(int itemIndex) {
        Item item = this.inventory.get(itemIndex-1);
        item.decreaseDurability();
        if (item.getDurability() == 0) {
            this.inventory.remove(item);
        }
        System.out.println("Valisid relva: " + item.getName());
        return item;
    }

    public void showInventory() {
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println(i+1 + ". " + inventory.get(i));
        }
    }

    public void move(String input, World world) {
        switch (input) {
            case "a":
                this.direction = Direction.LEFT;
                break;
            case "s":
                this.direction = Direction.DOWN;
                break;
            case "d":
                this.direction = Direction.RIGHT;
                break;
            case "w":
                this.direction = Direction.UP;
                break;
        }

        switch (this.direction) {
            case LEFT:
                if (xCoord > 1) {
                    xCoord--;
                }
                break;
            case DOWN:
                if (yCoord < world.getHeight()-2) {
                    yCoord++;
                }
                break;
            case RIGHT:
                if (xCoord < world.getWidth()-2) {
                    xCoord++;
                }
                break;
            case UP:
                if (yCoord > 1) {
                    yCoord--;
                }
                break;
        }

    }
}
