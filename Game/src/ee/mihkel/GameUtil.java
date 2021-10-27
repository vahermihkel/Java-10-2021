package ee.mihkel;

import ee.mihkel.character.Enemy;
import ee.mihkel.character.Player;
import ee.mihkel.character.QuestMaster;
import ee.mihkel.item.Dagger;
import ee.mihkel.item.Hammer;
import ee.mihkel.item.Item;
import ee.mihkel.item.Sword;

public class GameUtil {
    protected static void checkIfPlayerCanInteract(World world, Player player, Enemy enemy, QuestMaster questMaster, Dagger dagger, Hammer hammer, Sword sword) {
        checkIfPlayerAndEnemyMet(player, enemy);
        checkIfPlayerAndQuestMasterMet(world, player, enemy, questMaster);
        addItemToInventory(player, dagger);
        addItemToInventory(player, hammer);
        addItemToInventory(player, sword);
    }

    private static void checkIfPlayerAndEnemyMet(Player player, Enemy enemy) {
        if (player.getxCoord() == enemy.getxCoord() &&
                player.getyCoord() == enemy.getyCoord() &&
                enemy.isVisible()) {
            System.out.println("Kohtusid vaenlasega! Vali millist relva võitlemiseks soovid:");
            player.showInventory();
            enemy.setVisibility(false);
        }
    }

    private static void checkIfPlayerAndQuestMasterMet(World world, Player player, Enemy enemy, QuestMaster questMaster) {
        if (player.getxCoord() == questMaster.getxCoord() && player.getyCoord() == questMaster.getyCoord()) {
            enemy.setVisibility(true);
            enemy.randomiseCoordinates(world);
        }
    }

    private static void addItemToInventory(Player player, Item dagger) {
        if (player.getxCoord() == dagger.getxCoord() && player.getyCoord() == dagger.getyCoord()) {
            player.addToInventory(dagger);
        }
    }
}
