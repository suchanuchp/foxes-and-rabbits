package io.muzoo.ooc.ecosystems;

import java.util.LinkedHashMap;
import java.util.Map;

public class ActorFactory {

    private static final double FOX_PROB = 0.3;
    private static final double RABBIT_PROB = 0.4;
    private static final double TIGER_PROB = 0.25;
    private static final double HUNTER_PROB = 0.05;

    private static Map<String, Double > probabilities = new LinkedHashMap<String, Double>() {{
        put("fox", FOX_PROB);
        put("rabbit", RABBIT_PROB);
        put("tiger", TIGER_PROB);
        put("hunter", HUNTER_PROB);
    }};


    public static Actor createActor(){
        double random = Math.random();
        for (Map.Entry<String, Double> entry : probabilities.entrySet()) {
            String type = entry.getKey();
            Double prob = entry.getValue();
            if(random < prob) {
                return getActor(type);
            } else{
                random = random - prob;
            }
        }
        return null;
    }

    public static Actor getActor(String type){
        if(type.equals("fox"))
            return new Fox(true);
        else if(type.equals("rabbit"))
            return new Rabbit(true);
        else if(type.equals("tiger"))
            return new Tiger(true);
        else
            return new Hunter();
    }
}
