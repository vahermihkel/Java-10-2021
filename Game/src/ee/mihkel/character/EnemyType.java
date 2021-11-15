package ee.mihkel.character;

public enum EnemyType {
    ANT, RAT, CAT, DOG, HORSE, DRAGON, WIZARD;

    public static EnemyType getRandomEnemyType() {
        int index = (int) (Math.random()*values().length);
        return values()[index];
    }
}
