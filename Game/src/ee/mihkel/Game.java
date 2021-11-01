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

	    World world = new World(4,7);

	    Player player = new Player(world);
        Enemy enemy = new Enemy(world);
        QuestMaster questMaster = new QuestMaster(world);
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
            System.out.println("Mäng läbi!");
            System.out.println("Tapetud vaenlased: "); // võti-väärtus paaridega list
            // 2.
            System.out.println("Mänguks kogutud aeg: "); // thread ehk taimer
            System.out.println("Kogutud punktid: "); // static
        }
    }
}
