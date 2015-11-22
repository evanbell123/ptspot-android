package com.theptspot.ptspot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

/**
 * Created by Evan on 11/21/2015.
 */
public class TrainerList {
    private String url;
    private String order;
    //JSONArray trainerResults;
    ArrayList<Trainer> trainerResults;
    private static final String TAG = LoginService.class.getName();


    TrainerList(JSONArray trainerArray) throws JSONException {
        url = "http://www.theptspot.com/api/search";
        order = "PTScore";
        trainerResults = new ArrayList<Trainer>();
        setTrainerResults(trainerArray);
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public ArrayList<Trainer> getTrainerResults() {
        return this.trainerResults;
    }

    public void setTrainerResults(JSONArray trainerJSONArray) throws JSONException {
        Trainer trainer;
        User user;
        UserBuilder userBuilder = new UserBuilder();
        //JSONObject trainerJSONObject = new JSONObject();
        for (int i = 0; i < trainerJSONArray.length(); i++) {
            JSONObject trainerJSONObject = trainerJSONArray.getJSONObject(i);
            user = userBuilder
                    .id(trainerJSONObject.getInt("id"))
                    .firstName(trainerJSONObject.getString("firstName"))
                    .lastName(trainerJSONObject.getString("lastName"))
                    .totalReviews(trainerJSONObject.getInt("totalReviews"))
                    .buildUser();
            trainer = new Trainer(user,
                    trainerJSONObject.getDouble("rating"),
                    trainerJSONObject.getDouble("PTScore"),
                    trainerJSONObject.getInt("clarity"),
                    trainerJSONObject.getInt("effectiveness"),
                    trainerJSONObject.getInt("motivation"),
                    trainerJSONObject.getInt("intensity"));
            this.trainerResults.add(trainer);
        }
    }

    //get json array of trainers from api
    public static void updateTrainerResults() {

    }

    /*
    sort trainerResults by order passed in as parameter
    order can be rating, PTScore, totalReviews, clarity,
    effectiveness, motivation, and intensity
     */
    public void sort(String order) {
        switch(order) {
            case "PTScore":
                Collections.sort(trainerResults, Trainer.ptScoreCompare);
                break;
            default:
                break;
        }
    }
}
