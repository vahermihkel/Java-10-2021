package ee.mihkel;

import ee.mihkel.character.Enemy;
import ee.mihkel.character.Player;
import ee.mihkel.character.QuestMaster;
import ee.mihkel.exception.GameOverException;
import ee.mihkel.exception.InputNumberNotValidException;
import ee.mihkel.item.Dagger;
import ee.mihkel.item.Hammer;
import ee.mihkel.item.Item;
import ee.mihkel.item.Sword;

import java.util.Scanner;

public class GameUtil {
    protected static void checkIfPlayerCanInteract(World world, Player player,
                 Enemy enemy, QuestMaster questMaster, Dagger dagger,
                 Hammer hammer, Sword sword, Scanner scanner) throws GameOverException {
        checkIfPlayerAndEnemyMet(player, enemy, scanner);
        checkIfPlayerAndQuestMasterMet(world, player, enemy, questMaster);
        checkIfCanAddItemToInventory(player, dagger);
        checkIfCanAddItemToInventory(player, hammer);
        checkIfCanAddItemToInventory(player, sword);
    }

    private static void checkIfPlayerAndEnemyMet(Player player, Enemy enemy, Scanner scanner) throws GameOverException {
        if (player.getxCoord() == enemy.getxCoord() &&
                player.getyCoord() == enemy.getyCoord() &&
                enemy.isVisible()) {
            if (player.inventoryEmpty()) {
                System.out.println("Sul pole relvi, et võidelda, mine korja!");
            } else {
                chooseItem(player, enemy, scanner);
            }
        }
    }

    private static void chooseItem(Player player, Enemy enemy, Scanner scanner) throws GameOverException {
        System.out.println("Kohtusid vaenlasega! Vali millist relva võitlemiseks soovid:");
        player.showInventory();
        String input;
        Item item = null;
        while (item == null) {
            input = scanner.nextLine();
            try {
                item = player.useItem(Integer.parseInt(input));
                fightWithEnemy(player, enemy, scanner);
            } catch (NumberFormatException e) {
                System.out.println("Sisestasid numbri asemel muu sümboli!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Sisestasid liiga suure või väikse numbri!");
            }
        }
        enemy.setVisibility(false);
    }

    private static void fightWithEnemy(Player player, Enemy enemy, Scanner scanner) throws GameOverException {
        while (enemy.getHealth() > 0) {
            System.out.println("Vaenlasega võitlemiseks ütle üks number 1-3");
            int randomNumber = (int) (Math.random()*3)+1;
            String input;
            int userNumber = 0;
            while (userNumber == 0) {
                try {
                    input = scanner.nextLine();
                    userNumber = Integer.parseInt(input);
                    if (userNumber < 1 || userNumber > 3) {
                        userNumber = 0;
                        throw new InputNumberNotValidException();
                    }
                    if (randomNumber == userNumber) {
                        enemy.takeHealth();
                        if (enemy.getHealth() == 0) {
                            player.addToKilledEnemies(enemy.getEnemyType());
                        }
                    } else {
                        player.takeHealth();
                        if (player.getHealth() == 0) {
                            throw new GameOverException();
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Sisestasid numbri asemel muu sümboli!");
                } catch (InputNumberNotValidException e) {
                    System.out.println("Sisestasid liiga suure või väikse numbri!");
                }
            }
        }
    }

    private static void checkIfPlayerAndQuestMasterMet(World world, Player player,
                                                       Enemy enemy, QuestMaster questMaster) {
        if (player.getxCoord() == questMaster.getxCoord() && player.getyCoord() == questMaster.getyCoord()) {
            questMaster.setVisibility(false);
            enemy.setVisibility(true);
            enemy.randomiseCoordinates(world.getWidth(), world.getHeight(), world.getCharacters());
        } else if (!questMaster.isVisible()) {
            questMaster.setVisibility(true);
        }
    }

    private static void checkIfCanAddItemToInventory(Player player, Item dagger) {
        if (player.getxCoord() == dagger.getxCoord() && player.getyCoord() == dagger.getyCoord()) {
            player.addToInventory(dagger);
        }
    }
}
