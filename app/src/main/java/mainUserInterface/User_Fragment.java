package mainUserInterface;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mihail.messsec.R;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Mihail on 16.01.2015.
 */
public class User_Fragment extends Fragment {
    private ListView myListView;
    private ArrayAdapter<String> adapter;
    private String[] strUsersList;

    TokenUser tokenUser = new TokenUser();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        System.out.println(  "+++++++++++++++++  " + tokenUser.tUser);
    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.users_layout,container,false);
        myListView = (ListView) rootView.findViewById(R.id.userList);

        final ParseUser user = ParseUser.getCurrentUser();
       // System.out.println(user.getUsername()+"+++++++++++++++++");

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> list, ParseException e) {
                strUsersList = new String[list.size()];
                for (int i = 0; i < list.size(); i++) {
                   // if(!list.get(i).getUsername().toString().equals(user.toString())) {
                        strUsersList[i] = list.get(i).getUsername().toString();
                        System.out.println(list.get(i).getUsername() + "++++");
                   // }
                }

                adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,strUsersList);
                myListView.setAdapter(adapter);


            }
        });

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(UserMainActivity, "You click " + strUsersList[position],5000).show();
                System.out.println(strUsersList[position] + " user");

                ParseQuery<ParseUser> queryID = ParseUser.getQuery();
                queryID.whereEqualTo("username",strUsersList[position].toString());
                queryID.getFirstInBackground(new GetCallback<ParseUser>() {
                    @Override
                    public void done(ParseUser parseObject, ParseException e) {
                        if(parseObject!=null){
                            System.out.println(parseObject.getObjectId() + "99999999999");
                            Intent intent = new Intent(getActivity(),Messaging.class);
                            intent.putExtra("userID",parseObject.getObjectId().toString());
                            intent.putExtra("userName",parseObject.getUsername().toString());
                            startActivity(intent);
                        }
                        else{
                            System.out.println("err   99999999999");}
                    }
                });
            }
        });
        return  rootView;
    }
}
