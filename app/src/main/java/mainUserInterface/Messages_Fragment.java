package mainUserInterface;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mihail.messsec.R;

/**
 * Created by Mihail on 16.01.2015.
 */
public class Messages_Fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.messages_layout,container,false);
    }
}
