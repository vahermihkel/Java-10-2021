package ee.mihkel.item;

import ee.mihkel.World;

public class Sword extends Item {
    private static final int defaultDurability = 5;

    public Sword(World world) {
        super(5.0, defaultDurability, "Mõõk", world);
    }

    @Override
    public void reboost() {
        setDurability(defaultDurability);
    }
}
