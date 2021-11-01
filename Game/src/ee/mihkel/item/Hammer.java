package ee.mihkel.item;

import ee.mihkel.World;

public class Hammer extends Item {
    private static final int defaultDurability = 3;

    public Hammer(World world) {
        super(3.0, defaultDurability, "Haamer", world);
    }

    @Override
    public void reboost() {
        setDurability(defaultDurability);
    }
}
