package io.muzoo.ooc.ecosystems;

import java.util.List;
import java.util.Random;

abstract public class Animal extends Actor{


    // A shared random number generator to control breeding.
    protected static final Random rand = new Random();
    // The animal's age.
    protected int age;
    // Whether the animal is alive or not.
    protected boolean alive;
    // The animal's position




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







}



