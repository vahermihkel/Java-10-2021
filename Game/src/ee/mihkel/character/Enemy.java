package ee.mihkel.character;

import ee.mihkel.World;

// inheritance
public class Enemy extends Character {
    public Enemy(World world) {
        super('Z', world); // parenti constructor
    }
}
