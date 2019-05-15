package io.muzoo.ooc.ecosystems;

import java.util.List;
import java.util.Iterator;

/**
 * A simple model of a fox.
 * Foxes age, move, eat rabbits, and die.
 *
 * @author David J. Barnes and Michael Kolling
 * @version 2002.10.28
 */
public class Fox extends Carnivore {


    // The food value of a single rabbit. In effect, this is the
    // number of steps a fox can go before it has to eat again.
    // The age at which a fox can start to breed.
    private static final int BREEDING_AGE = 10;
    // The age to which a fox can live.
    private static final int MAX_AGE = 150;
    // The likelihood of a fox breeding.
    private static double BREEDING_PROBABILITY = 0.11;
    // The maximum number of births.
    private static int MAX_LITTER_SIZE = 6;
    private static int FOOD_VALUE = 4;


    // The food value of a single rabbit. In effect, this is the
    // number of steps a fox can go before it has to eat again.

    // Individual characteristics (instance fields).

    // The fox's food level, which is increased by eating rabbits.


    /**
     * Create a fox. A fox can be created as a new born (age zero
     * and not hungry) or with random age.
     *
     * @param randomAge If true, the fox will have random age and hunger level.
     */
    public Fox(boolean randomAge) {

        age = 0;
        alive = true;
        if (randomAge) {
            age = rand.nextInt(MAX_AGE);
            foodLevel = rand.nextInt(FOOD_VALUE);
        } else {
            // leave age at 0
            foodLevel = FOOD_VALUE;
        }
    }





    /**
     * Make this fox more hungry. This could result in the fox's death.
     */


    /**
     * Tell the fox to look for rabbits adjacent to its current location.
     *
     * @param field    The field in which it must look.
     * @param location Where in the field it is located.
     * @return Where food was found, or null if it wasn't.
     */
    protected Location findFood(Field field, Location location) {
        Iterator adjacentLocations =
                field.adjacentLocations(location);
        while (adjacentLocations.hasNext()) {
            Location where = (Location) adjacentLocations.next();
            Object animal = field.getObjectAt(where);
            if (animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if (rabbit.isAlive()) {
                    rabbit.setEaten();
                    foodLevel = FOOD_VALUE;
                    return where;
                }
            }
        }
        return null;
    }
    /**
     * This is what the fox does most of the time: it hunts for
     * rabbits. In the process, it might breed, die of hunger,
     * or die of old age.
     *
     * @param currentField The field currently occupied.
     * @param updatedField The field to transfer to.
     * @param newFoxes     A list to add newly born foxes to.
     */


    @Override
    public void act(Field currentField, Field updatedField, List newFoxes) {
        incrementAge();
        incrementHunger();
        if (alive) {
            // New foxes are born into adjacent locations.
            int births = breed();
            for (int b = 0; b < births; b++) {
                Fox newFox = new Fox(false);
                newFoxes.add(newFox);
                //System.out.println(location);
                Location loc = updatedField.randomAdjacentLocation(location);
                newFox.setLocation(loc);
                updatedField.place(newFox, loc);
            }
            // Move towards the source of food if found.
            Location newLocation = findFood(currentField, location);
            if (newLocation == null) {  // no food found - move randomly
                newLocation = updatedField.freeAdjacentLocation(location);
            }
            if (newLocation != null) {
                setLocation(newLocation);
                updatedField.place(this, newLocation);
            } else {
                // can neither move nor stay - overcrowding - all locations taken
                alive = false;
            }
        }
    }
    /**
     * Generate a number representing the number of births,
     * if it can breed.
     *
     * @return The number of births (may be zero).
     */
    private int breed() {
        int births = 0;
        if (canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }

    /**
     * An animal can breed if it has reached the breeding age.
     */
    private boolean canBreed() {
        return age >= BREEDING_AGE;
    }
    /**
     * Increase the age. This could result in the animal's death.
     */
    private void incrementAge() {
        age++;
        if (age > MAX_AGE) {
            alive = false;
        }
    }

}
