package com.xkcd.xkcd;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Karan on 19-03-2016.
 */
public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);

        final ImageView imageView = (ImageView)findViewById(R.id.img);
        final TextView title = (TextView)findViewById(R.id.title);
        final TextView des = (TextView)findViewById(R.id.des);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Check");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + scoreList.size() + " scores");

                    ParseObject object = scoreList.get(0);
                    Picasso.with(getApplicationContext()).load(object.get("Image").toString()).into(imageView);
                    title.setText(object.get("Name").toString());
                    des.setText(object.get("Des").toString());


                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);


                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

//        showDialog();
    }

    public void showDialog(){

        Dialog dialog = new Dialog(DialogActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
//        Drawable d = new ColorDrawable(getApplicationContext().getResources().getColor(R.color.gray_dark));
//        dialog.getWindow().setBackgroundDrawable(d);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.setContentView(R.layout.dialog);

        final ImageView imageView = (ImageView)dialog.findViewById(R.id.img);
        final TextView title = (TextView)dialog.findViewById(R.id.title);
        final TextView des = (TextView)dialog.findViewById(R.id.des);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Check");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + scoreList.size() + " scores");

                    ParseObject object = scoreList.get(0);
                    Picasso.with(getApplicationContext()).load(object.get("Image").toString()).into(imageView);
                    title.setText(object.get("Name").toString());
                    des.setText(object.get("Des").toString());


                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);


                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
        dialog.show();

    }
}
