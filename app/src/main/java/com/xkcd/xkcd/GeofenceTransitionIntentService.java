package com.xkcd.xkcd;

import android.app.Dialog;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karan on 19-03-2016.
 */
public class GeofenceTransitionIntentService extends IntentService {

    private static final String TAG = "TransitionIntentService";
    private NotificationManager myNotificationManager;
    private int notificationIdOne = 110;
    private Handler mHandler;

    public GeofenceTransitionIntentService(){
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mHandler = new Handler();

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()) {
            String errorMessage = GeofenceErrorMessages.getErrorString(this,
                    geofencingEvent.getErrorCode());
            Log.e(TAG, errorMessage);
            return;
        }

        // Get the transition type.
        int geofenceTransition = geofencingEvent.getGeofenceTransition();

        // Test that the reported transition was of interest.
        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER ||
                geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {

            // Get the geofences that were triggered. A single event can trigger multiple geofences.
            List<Geofence> triggeringGeofences = geofencingEvent.getTriggeringGeofences();

            // Get the transition details as a String.
            String geofenceTransitionDetails = getGeofenceTransitionDetails(
                    this,
                    geofenceTransition,
                    triggeringGeofences
            );


            /*new CountDownTimer(7000, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    Log.d("aa", "s");

                }

                public void onFinish() {
                    createNotification();
                    mHandler.post(new ToastRunnable());

                }

            }.start();*/


            createNotification();

            /*Intent intent1 = new Intent(GeofenceTransitionIntentService.this , DialogActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent1);*/

            //mHandler.post(new ToastRunnable());

            Log.i(TAG, geofenceTransitionDetails);
            //Toast.makeText(this,"aaa" +geofenceTransitionDetails,Toast.LENGTH_SHORT).show();
        } else {
            // Log the error.
            Log.e(TAG, "Geofence transition error: invalid transition type " + geofenceTransition);
            Toast.makeText(this,"Geofence transition error: invalid transition type " + geofenceTransition,Toast.LENGTH_SHORT).show();
        }
    }


    private String getGeofenceTransitionDetails(
            Context context,
            int geofenceTransition,
            List<Geofence> triggeringGeofences) {

        String geofenceTransitionString = getTransitionString(geofenceTransition);

        // Get the Ids of each geofence that was triggered.
        ArrayList triggeringGeofencesIdsList = new ArrayList();
        for (Geofence geofence : triggeringGeofences) {
            triggeringGeofencesIdsList.add(geofence.getRequestId());
        }
        String triggeringGeofencesIdsString = TextUtils.join(", ",  triggeringGeofencesIdsList);

        return geofenceTransitionString + ": " + triggeringGeofencesIdsString;
    }

    private String getTransitionString(int transitionType) {
        switch (transitionType) {
            case Geofence.GEOFENCE_TRANSITION_ENTER:
                return "ENTERED";
            case Geofence.GEOFENCE_TRANSITION_EXIT:
                return "EXITED";
            default:
                return "UNKNOWN";
        }
    }

    private class ToastRunnable implements Runnable {

        public ToastRunnable() {

        }

        @Override
        public void run() {
            showDialog();
        }
    }


    public void showDialog(){

        Dialog dialog = new Dialog(GeofenceTransitionIntentService.this);
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





                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
        dialog.show();

    }
    protected void createNotification() {

        // Invoking the default notification service
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("GEOFENCE");
        mBuilder.setContentText("Something happened");

        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        // Increase notification number every time a new notification arrives
        // Creates an explicit intent for an Activity in your app
        // Intent resultIntent = new Intent(context, NotificationRecieverActivity.class);

        Intent resultIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

        //This ensures that navigating backward from the Activity leads out of the app to Home page
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);


        stackBuilder.addParentStack(MainActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_ONE_SHOT //can only be used once
                );
        // start the activity when the user clicks the notification text
        mBuilder.setContentIntent(resultPendingIntent);
        mBuilder.setAutoCancel(true);
        myNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // pass the Notification object to the system
        myNotificationManager.notify(notificationIdOne, mBuilder.build());
    }

}