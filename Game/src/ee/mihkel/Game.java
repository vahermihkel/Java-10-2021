package ee.mihkel;

import ee.mihkel.character.Character;
import ee.mihkel.character.Enemy;
import ee.mihkel.character.Player;
import ee.mihkel.character.QuestMaster;
import ee.mihkel.item.Dagger;
import ee.mihkel.item.Hammer;
import ee.mihkel.item.Item;
import ee.mihkel.item.Sword;

import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        // kontroll, kas ese on juba olemas, siis suurenda kasutuskorda
        // kui esemeid ei ole, siis võitlema ei saa minna ja ütleb, et esemeid pole
        // kui kasutan eset, siis väheneb kasutuskord
        // kui kasutuskorrad saavad otsa, siis eemalda ese inventory'st

        // kui eset kasutan, siis võta vastavalt tugevusele vaenlaselt elusid
        // pean tegema nii playerile kui vaenlasele elud
        // kui elud lähevad nulli, siis juhtub midagi
        // kui playeril saavad elud otsa, saab mäng läbi (throw new exception)
        // kui enemyl saavad elud otsa, lisa ta tapetute listi: map(K,V) keda sa tapnud oled, valueks mitu tükki

        // enemyle mingisuguse enumi tüüpidest, lisame juhuslikult
        // iga kord vahetub enumi tüüp
        //vastavalt enumi tüübile tekivad ka talle elud

        // static punktide jaoks
        // thread - taimer
        // geneerika - väljaspool loogikat näidata

	    World world = new World(3,5);

	    Player player = new Player(world);
	    world.addCharacter(player);
        Enemy enemy = new Enemy(world);
        world.addCharacter(enemy);
        QuestMaster questMaster = new QuestMaster(world);
        world.addCharacter(questMaster);

        Dagger dagger = new Dagger(world);
        world.addItem(dagger);
        Hammer hammer = new Hammer(world);
        world.addItem(hammer);
        Sword sword = new Sword(world);
        world.addItem(sword);

        world.printMap();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("end")) {
            player.move(input, world);
            GameUtil.checkIfPlayerCanInteract(world, player, enemy, questMaster, dagger, hammer, sword);
            world.printMap();
            input = scanner.nextLine();
        }
    }
}
