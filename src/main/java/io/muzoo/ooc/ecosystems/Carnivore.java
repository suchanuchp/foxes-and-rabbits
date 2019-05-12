package io.muzoo.ooc.ecosystems;

import java.util.List;

abstract public class Carnivore extends Animal{
    protected int foodLevel;

    protected void incrementHunger() {
        foodLevel--;
        if (foodLevel <= 0) {
            alive = false;
        }
    }
    protected abstract Location findFood(Field field, Location location);


}
