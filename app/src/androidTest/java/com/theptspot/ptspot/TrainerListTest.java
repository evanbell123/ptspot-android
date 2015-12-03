package com.theptspot.ptspot;

import junit.framework.TestCase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Evan on 11/21/2015.
 */
public class TrainerListTest extends TestCase {

    private static final String TAG = TrainerListTest.class.getName();
    private TrainerList trainerList;
    private JSONArray trainerJSON; //testing dataset


    public void setUp() throws JSONException {

        trainerJSON = new JSONArray();
        Random random = new Random();
        //Generate random list of trainers
        for (Integer i = 1; i <= 26; i++) {
            JSONObject trainer = new JSONObject();
            trainer.put("id", i);
            trainer.put("firstName", "Evan");
            trainer.put("lastName", "Bell");
            trainer.put("totalReviews", random.nextInt(20) + 10);
            trainer.put("PTScore", random.nextInt(100) + random.nextDouble());
            trainer.put("rating", random.nextInt(5));
            trainer.put("clarity", random.nextInt(5));
            trainer.put("effectiveness", random.nextInt(5));
            trainer.put("motivation", random.nextInt(5));
            trainer.put("intensity", random.nextInt(5));
            trainerJSON.put(trainer);
        }
        trainerList = new TrainerList(trainerJSON);

        assertTrue("Trainer List Set Up Failed", !trainerList.getTrainerResults().isEmpty());
    }

    public void tearDown() {

    }

    /*
    request trainer json from api
    throws exception if api call fails
     */
    public void testUpdateTrainerResults() throws Exception {

    }

    /*
    sort by PTScore
    throws exception if it's not sorted by PTScore
    */
    public void testIfSortedByPTScore() throws Exception {
        trainerList.sort("PTScore");

        Boolean isSorted = true;
        ArrayList<Trainer> trainerResults = trainerList.getTrainerResults();

        for (int i = 0; i < trainerResults.size() - 1; i++) {
            Double PTScore1 = trainerResults.get(i).getPTScore();
            Double PTScore2 = trainerResults.get(i + 1).getPTScore();
            if (PTScore1 < PTScore2) {
                isSorted = false;
            }
        }

        assertTrue(TAG + ": PTScore sort failed", isSorted);
    }

    /*
    sort by Rating
    throws exception if it's not sorted by Rating
    */
    public void testIfSortedByRating() throws Exception {

    }

    /*
    sort by Clarity
    throws exception if it's not sorted by Clarity
    */
    public void testIfSortedByClarity() throws Exception {

    }

    /*
    sort by Effectiveness
    throws exception if it's not sorted by Effectiveness
    */
    public void testIfSortedByEffectiveness() throws Exception {

    }

    /*
    sort by Motivation
    throws exception if it's not sorted by Motivation
    */
    public void testIfSortedByMotivation() throws Exception {

    }

    /*
    sort by Intensity
    throws exception if it's not sorted by Intensity
    */
    public void testIfSortedByIntensity() throws Exception {

    }

    /*
    sort by Total Reviews
    throws exception if it's not sorted by Total Reviews
    */
    public void testIfSortedByTotalReviews() throws Exception {

    }
}
