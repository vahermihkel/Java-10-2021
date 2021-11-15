package ee.mihkel;

import ee.mihkel.character.Enemy;
import ee.mihkel.character.Player;
import ee.mihkel.character.QuestMaster;
import ee.mihkel.exception.GameOverException;
import ee.mihkel.item.Dagger;
import ee.mihkel.item.Hammer;
import ee.mihkel.item.Sword;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Timer;

public class Game {

    public static void main(String[] args) {
	    World world = new World(4,7);
        Timer timer = new Timer();
        GameUtil.startTimer(timer);

        Player player = new Player();
        Enemy enemy = new Enemy();
        QuestMaster questMaster = new QuestMaster();
        world.setCharacters(Arrays.asList(player, enemy, questMaster));

        Dagger dagger = new Dagger(world);
        Hammer hammer = new Hammer(world);
        Sword sword = new Sword(world);
        world.setItems(Arrays.asList(dagger, hammer, sword));

        world.printMap();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        try {
            while (!input.equals("end")) {
                player.move(input, world);
                GameUtil.checkIfPlayerCanInteract(world, player, enemy, questMaster, dagger, hammer, sword, scanner);
                world.printMap();
                input = scanner.nextLine();
            }
        } catch (GameOverException e) {
            GameUtil.showGameOverMessages(player);
        }
    }
}
