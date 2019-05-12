package io.muzoo.ooc.ecosystems;

import java.util.Iterator;
import java.util.List;

public class Hunter extends Actor {



    @Override
    public void act(Field currentField, Field updatedField, List newAnimals) {
        Location newLocation  = hunt(currentField, location);

        if (newLocation == null) {  // no animal found - move randomly
            newLocation = updatedField.freeAdjacentLocation(location);
        }
        if (newLocation != null) {
            setLocation(newLocation);
            updatedField.place(this, newLocation);
        }
    }

    protected Location hunt(Field field, Location location) {
        Iterator adjacentLocations =
                field.adjacentLocations(location);

        while (adjacentLocations.hasNext()) {
            Location where = (Location) adjacentLocations.next();
            Object animal = field.getObjectAt(where);
            if (animal instanceof Animal) {
               ((Animal) animal).setEaten();
               return where;
            }

        }
        return null;
    }

}
