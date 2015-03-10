package mainUserInterface;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.mihail.messsec.R;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Mihail on 25.01.2015.
 */
public class Messaging extends ActionBarActivity {//обмен сообщениями

    ActionBar actionBar;
    ListView list;
    CustomListAdapter adapter;
    public ArrayList<ListModel> CustomListViewValuesArr = new ArrayList<ListModel>();

    EditText editText;
    Button btnSend;
    static ParseObject currentExchange;
    static String idOption[] = new String[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messaging);
        actionBar = getSupportActionBar();
     //   strUsersList = new ArrayList();

//        for (int i = 0; i < 20; i++) {
//            strUsersList.add("asd " +i);
//        }
        list = (ListView) findViewById(R.id.b_mess);
      //  setListData();

      //  ListView listView = (ListView)findViewById(R.id.b_mess);
        editText = (EditText)findViewById(R.id.editMessage);
        btnSend  = (Button)findViewById(R.id.btnSend);
        final Intent intent = getIntent();

        idOption[0] = intent.getStringExtra("userID").toString() + ParseUser.getCurrentUser().getObjectId().toString();
        idOption[1] = ParseUser.getCurrentUser().getObjectId().toString() + intent.getStringExtra("userID").toString();
        System.out.println(idOption[0] + "  idOption  " + idOption[1]);

        System.out.println(intent.getStringExtra("userID") + "00000000000000");

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("objectId",intent.getStringExtra("userID"));
        query.getFirstInBackground(new GetCallback<ParseUser>() {
            @Override
            public void done(ParseUser parseUser1, ParseException e) {
                actionBar.setTitle(parseUser1.getUsername());

            }
        });

        ParseQuery<ParseObject> queryId = ParseQuery.getQuery("UserMessage");
        queryId.whereContainedIn("userID", Arrays.asList(idOption));
        queryId.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if(parseObject == null){
                    ParseObject pObject = new ParseObject("UserMessage");
                    pObject.put("userID",idOption[0]);
                    pObject.saveInBackground();
                    System.out.println("save - hhh");
                //    parseObject.remove("newMess");
                    currentExchange = parseObject;
                    //currentExchange.remove("newMess");
               //     parseObject.saveInBackground();
                }
                else{

                    //////////////-----------------------------------------------------
                    try{
                        JSONArray jArray = new JSONArray(parseObject.get("newMess").toString());
                        for (int i = 0; i < jArray.length(); i++) {
                            JSONObject jObject = jArray.getJSONArray(i).getJSONObject(0);


                                final ListModel sched = new ListModel();

                                /******* Firstly take data in model object ******/

                                if(intent.getStringExtra("userID").toString().equals(jObject.get("userID").toString())) {
                                    sched.userID = intent.getStringExtra("userName");//(jObject.get("userID").toString());
                                    sched.userMess = (jObject.get("userMess").toString());
                                }else{
                                    sched.userID = ParseUser.getCurrentUser().getUsername().toString();//(jObject.get("userID").toString());
                                    sched.userMess = (jObject.get("userMess").toString());
                                }


                                /******** Take Model Object in ArrayList **********/

                                CustomListViewValuesArr.add(sched);
                                adapter=new CustomListAdapter( Messaging.this, CustomListViewValuesArr );

                                list.setAdapter( adapter );
                        }

                    }catch (Exception ex){
                        ex.printStackTrace();
                    }

                    /////////////-------------------------------------
                    System.out.println("exist - hhh");
                    currentExchange = parseObject;

                }

              //  System.out.println(currentExchange + "   parseOBJ1");
            }
        });


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentExchange == null){
                        ParseQuery<ParseObject> queryId = ParseQuery.getQuery("UserMessage");
                        queryId.whereContainedIn("userID", Arrays.asList(idOption));
                        queryId.getFirstInBackground(new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject parseObject, ParseException e) {


                            //System.out.println(currentExchange + "   parseOBJ2");
                            JSONObject jsonObject = new JSONObject();
                            try {
                                jsonObject.put("userID", ParseUser.getCurrentUser().getObjectId());
                                jsonObject.put("userMess", editText.getText().toString());
                                final ListModel sched = new ListModel();

                                /******* Firstly take data in model object ******/
                                sched.userID = (ParseUser.getCurrentUser().getUsername().toString());
                                sched.userMess = (editText.getText().toString());
                                //   }
                                // sched.setUrl("http:\\www."+i+".com");

                                /******** Take Model Object in ArrayList **********/

                                CustomListViewValuesArr.add(sched);
                                adapter = new CustomListAdapter(Messaging.this, CustomListViewValuesArr);

                                list.setAdapter(adapter);
                            } catch (JSONException ec) {
                                ec.printStackTrace();
                            }

                            JSONArray jArray = new JSONArray();
                            jArray.put(jsonObject);
                            parseObject.add("newMess", jArray);
                            //currentObject.remove("newMess");
                            parseObject.saveInBackground();
                            editText.setText("");
                            // System.out.println(editText.getText() + "000000000000");
                        }
                    });
                }
                else{
                    //System.out.println(currentExchange + "   parseOBJ2");
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("userID", ParseUser.getCurrentUser().getObjectId());
                        jsonObject.put("userMess", editText.getText().toString());
                        final ListModel sched = new ListModel();

                        /******* Firstly take data in model object ******/
                        sched.userID = (ParseUser.getCurrentUser().getUsername().toString());
                        sched.userMess = (editText.getText().toString());
                        //   }
                        // sched.setUrl("http:\\www."+i+".com");

                        /******** Take Model Object in ArrayList **********/

                        CustomListViewValuesArr.add(sched);
                        adapter = new CustomListAdapter(Messaging.this, CustomListViewValuesArr);

                        list.setAdapter(adapter);
                    } catch (JSONException ec) {
                        ec.printStackTrace();
                    }

                    JSONArray jArray = new JSONArray();
                    jArray.put(jsonObject);
                    currentExchange.add("newMess", jArray);
                    //currentObject.remove("newMess");
                    currentExchange.saveInBackground();
                    editText.setText("");
                    // System.out.println(editText.getText() + "000000000000");
                }
            }
        });




    }
//    public void setListData()
//    {
//
//        for (int i = 0; i < 11; i++) {
//
//            final ListModel sched = new ListModel();
//
//            /******* Firstly take data in model object ******/
//            sched.userID = ("userID" + i);
//            sched.userMess = ("userMess" + i);
//
//            /******** Take Model Object in ArrayList **********/
//            CustomListViewValuesArr.add( sched );
//        }
//
//    }


}
