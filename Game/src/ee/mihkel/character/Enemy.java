package ee.mihkel.character;

import ee.mihkel.World;

// inheritance
public class Enemy extends Character {
    private EnemyType enemyType;

    public Enemy() {
        super('Z'); // parenti constructor
        this.randomiseEnemyType();
    }

    public void randomiseEnemyType() {
        this.enemyType = EnemyType.getRandomEnemyType();
        reboost();
        setVisibility(true);
    }

    public EnemyType getEnemyType() {
        return enemyType;
    }
}
