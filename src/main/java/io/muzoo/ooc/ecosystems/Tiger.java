package io.muzoo.ooc.ecosystems;

import java.util.Iterator;
import java.util.List;

public class Tiger extends Carnivore {


    private static final int FOOD_VALUE = 3;
    private static final int MAX_AGE = 200;
    private static final double BREEDING_PROBABILITY = 0.05;
    private static final int BREEDING_AGE = 15;
    private static final int MAX_LITTER_SIZE = 6;

    public Tiger(boolean randomAge){

        age = 0;
        alive = true;
        if (randomAge) {
            age = rand.nextInt(MAX_AGE);
            foodLevel = rand.nextInt(FOOD_VALUE);
        }
        else {
            // leave age at 0

            foodLevel = FOOD_VALUE;
        }
    }

    @Override
    public void act(Field currentField, Field updatedField, List newTigers) {

        incrementAge();
        incrementHunger();
        if (alive) {
            // New foxes are born into adjacent locations.
            int births = breed();
            for (int b = 0; b < births; b++) {
                Tiger newTiger = new Tiger(false);
                newTigers.add(newTiger);
                //System.out.println(location);
                Location loc = updatedField.randomAdjacentLocation(location);
                newTiger.setLocation(loc);
                updatedField.place(newTiger, loc);
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


    protected void incrementHunger() {
        foodLevel--;
        if (foodLevel <= 0) {
            alive = false;
        }
    }

    @Override
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
            }else if(animal instanceof Fox){
                Fox fox = (Fox) animal;
                if (fox.isAlive()) {
                    fox.setEaten();
                    foodLevel = FOOD_VALUE;
                    return where;
                }
            }

        }
        return null;
    }
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


}
