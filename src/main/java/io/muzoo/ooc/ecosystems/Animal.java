package io.muzoo.ooc.ecosystems;

import java.util.List;
import java.util.Random;

abstract public class Animal {


    // A shared random number generator to control breeding.
    protected static final Random rand = new Random();
    // The animal's age.
    protected int age;
    // Whether the animal is alive or not.
    protected boolean alive;
    // The animal's position
    protected Location location;

    public abstract void act(Field currentField, Field updatedField, List newAnimals);

    /**
     * Tell the animal that it's dead now :(
     */
    public void setEaten() {
        alive = false;
    }

    /**
     * Check whether the animal is alive or not.
     *
     * @return True if the animal is still alive.
     */
    public boolean isAlive() {
        return alive;
    }


    /**
     * Set the animal's location.
     *
     * @param row The vertical coordinate of the location.
     * @param col The horizontal coordinate of the location.
     */
    public void setLocation(int row, int col) {
        this.location = new Location(row, col);
    }

    /**
     * Set the animal's location.
     *
     * @param location The animal's location.
     */
    public void setLocation(Location location) {
        this.location = location;
    }






}



