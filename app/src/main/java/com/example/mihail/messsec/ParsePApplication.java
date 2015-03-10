package com.example.mihail.messsec;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.PushService;

/**
 * Created by andrew on 4/22/14.
 */
public class ParsePApplication extends Application {

    @Override
    public void onCreate(){
     //   super.onCreate();

      //  PushService.setDefaultPushCallback(this, MainActivity.class);

        super.onCreate();



        // Add your initialization code here
        Parse.initialize(this, getString(R.string.parseAppID), getString(R.string.parseClientID));
        PushService.setDefaultPushCallback(this, MainActivity.class);
       // ParseInstallation.getCurrentInstallation().saveInBackground();


    }
}


