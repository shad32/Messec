package mainUserInterface;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;


import com.example.mihail.messsec.FragmentPageAdapter;
import com.example.mihail.messsec.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


/**
 * Created by Mihail on 10.01.2015.
 */
public class UserMainActivity extends ActionBarActivity implements ActionBar.TabListener{

    ActionBar actionBar;
    ViewPager viewPager;
    FragmentPageAdapter ft;
    TokenUser tokenUser = new TokenUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_main_activity);

        actionBar = getSupportActionBar();
        Intent intent = getIntent();

        tokenUser.tUser = intent.getStringExtra("token");
        ParseUser.becomeInBackground(intent.getStringExtra("token"), new LogInCallback() {
                                public void done(ParseUser parseUser, ParseException e) {
                                    if (parseUser != null) {
                                        // The current user is now set to user.
                                        actionBar.setTitle(parseUser.getUsername());

                                      //  parseUser.put("message","111");
                                      //  parseUser.saveInBackground();
                                    } else {
                                        // The token could not be validated.
                                    }
                                }
                            });

        viewPager = (ViewPager)findViewById(R.id.pager);
        ft = new FragmentPageAdapter(getSupportFragmentManager());

        viewPager.setAdapter(ft);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.addTab(actionBar.newTab().setText("Users").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("Messages").setTabListener(this));

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                actionBar.setSelectedNavigationItem(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

}
