package io.muzoo.ooc.ecosystems;

import java.util.Random;

public class Animal {


    // The age at which a fox can start to breed.
    protected static int BREEDING_AGE;
    // The age to which a fox can live.
    protected static int MAX_AGE;
    // The likelihood of a fox breeding.
    protected static double BREEDING_PROBABILITY;
    // The maximum number of births.
    protected static int MAX_LITTER_SIZE;
    // The food value of a single rabbit. In effect, this is the
    // number of steps a fox can go before it has to eat again.

    // A shared random number generator to control breeding.
    protected static final Random rand = new Random();


    // The animal's age.
    protected int age;
    // Whether the animal is alive or not.
    protected boolean alive;
    // The animal's position
    protected Location location;




    /**
     * Increase the age. This could result in the animal's death.
     */
    protected void incrementAge() {
        age++;
        if (age > MAX_AGE) {
            alive = false;
        }
    }


    /**
     * Generate a number representing the number of births,
     * if it can breed.
     *
     * @return The number of births (may be zero).
     */
    protected int breed() {
        int births = 0;
        if (canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }

    /**
     * An animal can breed if it has reached the breeding age.
     */
    protected boolean canBreed() {
        return age >= BREEDING_AGE;
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



