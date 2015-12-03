package com.theptspot.ptspot;

import android.app.ListActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import junit.framework.Assert;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Evan on 12/2/2015.
 */
public class TrainerResultsActivity extends ListActivity{

    TrainerList trainerList;

    private class FetchTrainersTask extends AsyncTask<Void, Void, JSONArray> {

        @Override
        protected JSONArray doInBackground(Void... params) {
            GetRequestService getRequestService = new GetRequestService();
            try {
                JSONArray trainerJSON = getRequestService.getTrainerResults();
                return trainerJSON;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONArray trainerJSON) {
            super.onPostExecute(trainerJSON);
            try {
                trainerList.updateTrainerResults(trainerJSON);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            updateResults();
        }
    }

    private void updateResults() {
        setListAdapter(new TrainerArrayAdapter(this, trainerList.getTrainerResults()));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trainer_results);

        try {
            trainerList = new TrainerList(new JSONArray());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        new FetchTrainersTask().execute();

    }


}




class TrainerArrayAdapter extends ArrayAdapter {
    private final Context context;
    private final ArrayList<Trainer> values;

    public TrainerArrayAdapter(Context context, ArrayList<Trainer> values) {
        super(context, R.layout.trainer_list_element, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.trainer_list_element, parent, false);

        TextView titleView = (TextView) rowView.findViewById(R.id.tvTrainerResultsName);
        titleView.setText(values.get(position).getFirstName() + " " + values.get(position).getLastName());

        ImageView imageView = (ImageView) rowView.findViewById(R.id.ivProfilePic);
        imageView.setImageResource(values.get(position).getProfilePicture());

        return rowView;
    }
}

class TrainerList{
    //private String url;
    private String order;
    //JSONArray trainerResults;
    ArrayList<Trainer> trainerResults;
    private static final String TAG = LoginService.class.getName();



    TrainerList(JSONArray trainerJSON) throws JSONException {
        //url = "http://www.theptspot.com/api/search";
        order = "PTScore";
        trainerResults = new ArrayList<Trainer>();

        updateTrainerResults(trainerJSON);
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
    //and update the ArrayList of Trainers
    public void updateTrainerResults(JSONArray trainerJSON) throws JSONException {

        if (!trainerResults.isEmpty()) {
            trainerResults.clear();
        }

        setTrainerResults(trainerJSON);
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