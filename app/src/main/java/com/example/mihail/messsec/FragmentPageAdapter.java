package com.example.mihail.messsec;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import mainUserInterface.Messages_Fragment;
import mainUserInterface.User_Fragment;

/**
 * Created by Mihail on 16.01.2015.
 */
public class FragmentPageAdapter extends FragmentPagerAdapter {
    public FragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0: {
                return new User_Fragment();
            }
            case 1: {
                return new Messages_Fragment();
            }
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
