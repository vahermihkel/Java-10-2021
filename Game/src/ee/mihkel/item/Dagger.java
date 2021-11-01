package ee.mihkel.item;

import ee.mihkel.World;

public class Dagger extends Item {
    private static final int defaultDurability = 5;

    public Dagger(World world) {
        super(2.0, defaultDurability, "Pistoda", world);
    }

    @Override
    public void reboost() {
        setDurability(defaultDurability);
    }
}
