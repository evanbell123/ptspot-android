package com.theptspot.ptspot;

import android.content.Context;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Evan on 11/21/2015.
 */
public class Trainer extends User {
    private Double rating;
    private Double PTScore;
    private Integer clarity;
    private Integer effectiveness;
    private Integer motivation;
    private Integer intensity;

    Trainer(User user, Double rating, Double PTScore, Integer clarity, Integer effectiveness, Integer motivation, Integer intensity) {
        super(user);
        this.rating = rating;
        this.PTScore = PTScore;
        this.clarity = clarity;
        this.effectiveness = effectiveness;
        this.motivation = motivation;
        this.intensity = intensity;
    }

    private static Trainer sTrainer;
    private List<Trainer> mTrainers;

    public static Trainer get(Context context) {
        if (sTrainer == null) {
            sTrainer = new Trainer(context);
        }
        return sTrainer;
    }

    private Trainer(Context context) {
        UserBuilder userBuilder = new UserBuilder();
        mTrainers = new ArrayList<>();
        Random random = new Random();
        //Generate random list of trainers
        for (Integer i = 1; i <= 26; i++) {
            User user =
                    userBuilder.id(i)
                    .firstName("Evan")
                    .lastName("Bell")
                    .totalReviews(random.nextInt(20) + 10)
                    .buildUser();

            Trainer trainer;
            trainer = new Trainer(user,
                    random.nextInt(5) + random.nextDouble(),
                    random.nextInt(100) + random.nextDouble(),
                    random.nextInt(5),
                    random.nextInt(5),
                    random.nextInt(5),
                    random.nextInt(5));
            mTrainers.add(trainer);
        }
    }

    public List<Trainer> getTrainers() {
        return mTrainers;
    }

    public Trainer getTrainer(UUID id) {
        for (Trainer trainer : mTrainers) {
            if (trainer.getID().equals(id)) {
                return trainer;
            }
        }
        return null;
    }

    public static Comparator<Trainer> ptScoreCompare = new Comparator<Trainer>() {
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