package com.xkcd.xkcd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class InfoPage extends AppCompatActivity {

    ImageView imageView;
    TextView name;
    TextView address;
    ArrayList<Checkpoints> checkpoints = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Places");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + scoreList.size() + " scores");

                    ParseObject object = scoreList.get(0);
//                    Picasso.with(getApplicationContext()).load(object.get("Image").toString()).into(imageView);
//                    name.setText(object.get("Name").toString());
//                    address.setText(object.get("Address").toString());

                    String objectId = object.getObjectId();
//                    getCheckpoints(objectId);

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);


                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

    }

    void getCheckpoints(String objectId){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore");
        query.whereEqualTo("Place", objectId);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + scoreList.size() + " scores");
                    for(int  i =0; i<scoreList.size(); i++){
//                        checkpoints.add(new Checkpoints());
                    }
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

    }

}
