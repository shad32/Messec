package com.example.mihail.messsec;

import android.content.Intent;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import mainUserInterface.UserMainActivity;


public class MainActivity extends ActionBarActivity   {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ParseUser user = new ParseUser();
        Button btnLogin = (Button)findViewById(R.id.login_btn);
        Button btnRegNew = (Button)findViewById(R.id.reg_new_btn);
        final EditText login = (EditText)findViewById(R.id.login);
        final EditText pass = (EditText)findViewById(R.id.pass);



//        ParseObject parseObject = new ParseObject("test");
//        parseObject.put("message","asdas");
//        parseObject.saveInBackground();


        btnRegNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.logInInBackground(//"test","test",new LogInCallback() {
                 login.getText().toString(),pass.getText().toString(),new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if(parseUser != null){
                            Toast toast = Toast.makeText(getApplicationContext(), "LogIn success\n"+parseUser.getSessionToken(), Toast.LENGTH_SHORT);
                            toast.show();
                            finish();

                            Intent intent = new Intent(MainActivity.this, UserMainActivity.class);
                            intent.putExtra("token",parseUser.getSessionToken().toString());
                            startActivity(intent);
                        }else{
                            Toast toast = Toast.makeText(getApplicationContext(), "Error login or password", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });
            }
        });

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
