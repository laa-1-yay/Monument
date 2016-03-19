package com.xkcd.xkcd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class InfoPage extends AppCompatActivity {

    ImageView imageView;
    TextView name;
    Button tour;
    TextView address;
    ArrayList<Checkpoints> checkpoints = new ArrayList<>();
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);


        toolbar = (Toolbar) findViewById(R.id.toolbarInfo);
        setSupportActionBar(toolbar);

        imageView = (ImageView) findViewById(R.id.InfoImage);
        address = (TextView) findViewById(R.id.InfoContent);
        tour = (Button) findViewById(R.id.infobutton);

        tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Rastraparti Bhawan");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Places");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + scoreList.size() + " scores");

                    ParseObject object = scoreList.get(0);
                    Picasso.with(getApplicationContext()).load(object.get("Image").toString()).into(imageView);
                    getSupportActionBar().setTitle(" " +object.get("Name").toString());
                    address.setText(" "+object.get("Adress").toString());

                    Log.d("Tag","  "+object.get("Name").toString() );
                    Log.d("Tag","  "+object.get("Adress").toString() );
                    String objectId = object.getObjectId();
                    getCheckpoints(objectId);




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
