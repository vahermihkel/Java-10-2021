package ee.mihkel.character;

import ee.mihkel.World;

// inheritance
public class Enemy extends Character {
    private EnemyType enemyType;

    public Enemy(World world) {
        super('Z', world); // parenti constructor
        // this.enemyType = EnemyType.getRandomEnemyType();
        // 4. Ei ole enam muutuja väärtuse andmine vaid kutsu selle
        // random funktsiooni välja
    }

    public EnemyType getEnemyType() {
        return enemyType;
    }
}
