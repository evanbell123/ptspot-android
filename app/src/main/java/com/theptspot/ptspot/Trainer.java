package com.theptspot.ptspot;

import java.util.Comparator;

/**
 * Created by Evan on 11/21/2015.
 */
public class Trainer extends User {
    private final Double rating;
    private final Double PTScore;
    private final Integer clarity;
    private final Integer effectiveness;
    private final Integer motivation;
    private final Integer intensity;

    Trainer(User user, Double rating, Double PTScore, Integer clarity, Integer effectiveness, Integer motivation, Integer intensity) {
        super(user);
        this.rating = rating;
        this.PTScore = PTScore;
        this.clarity = clarity;
        this.effectiveness = effectiveness;
        this.motivation = motivation;
        this.intensity = intensity;
    }

    public static final Comparator<Trainer> ptScoreCompare = new Comparator<Trainer>() {
        @Override
        public int compare(Trainer lhs, Trainer rhs) {
            if (lhs.getPTScore() < rhs.getPTScore()) {
                return 1;
            }
            if (lhs.getPTScore() > rhs.getPTScore()) {
                return -1;
            }
            return 0;
        }
    };


    public Double getRating() {
        return rating;
    }

    public Double getPTScore() {
        return PTScore;
    }

    public Integer getClarity() {
        return clarity;
    }

    public Integer getEffectiveness() {
        return effectiveness;
    }

    public Integer getMotivation() {
        return motivation;
    }

    public Integer getIntensity() {
        return intensity;
    }
}




