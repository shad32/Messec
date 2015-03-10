package com.example.mihail.messsec;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by Mihail on 10.01.2015.
 */
public class RegistrationActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);
        final ParseUser user = new ParseUser();
        final EditText user_name = (EditText)findViewById(R.id.user_name);
        final EditText pass_first = (EditText)findViewById(R.id.pass_first);
        final EditText pass_second = (EditText)findViewById(R.id.pass_second);
        Button btnReg = (Button)findViewById(R.id.reg_btn);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!user_name.getText().toString().isEmpty() && !pass_first.getText().toString().isEmpty()) {
                    if (pass_first.getText().toString().equals(pass_second.getText().toString())) {
                        user.setUsername(user_name.getText().toString());
                        user.setPassword(pass_first.getText().toString());


                        user.signUpInBackground(new SignUpCallback() {
                                                    @Override
                                                    public void done(ParseException e) {
                                                        if (e == null) {
                                                            user_name.setText("");
                                                            pass_first.setText("");
                                                            pass_second.setText("");
                                                            Toast toast = Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT);
                                                            toast.show();

                                                            ParseUser parseUser = ParseUser.getCurrentUser();
                                                           // System.out.println(parseUser.getObjectId() + "8888888888888888");
//                                                            ParseObject parseObject = new ParseObject("UserMessage");
//                                                            parseObject.put("userID",parseUser.getObjectId());
//                                                            parseObject.saveInBackground();

                                                            Intent intent = new Intent(RegistrationActivity.this,MainActivity.class);
                                                            startActivity(intent);
                                                            finish();

                                                        } else {
                                                            user_name.setText("");
                                                            pass_first.setText("");
                                                            pass_second.setText("");
                                                            Toast toast = Toast.makeText(getApplicationContext(), "already username", Toast.LENGTH_SHORT);
                                                            toast.show();
                                                        }
                                                    }
                                                }

                        );
                    } else {
                        user_name.setText("");
                        pass_first.setText("");
                        pass_second.setText("");
                        Toast toast = Toast.makeText(getApplicationContext(), "Wrong pass", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Enter User name or pass", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
}
