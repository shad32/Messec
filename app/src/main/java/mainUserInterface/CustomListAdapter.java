package mainUserInterface;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mihail.messsec.R;

import java.util.ArrayList;

/**
 * Created by Mihail on 27.01.2015.
 */
public class CustomListAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater=null;
  //  public Resources res;
    ListModel tempValues=null;

    public CustomListAdapter(Activity a, ArrayList d) {

        /********** Take passed values **********/
        activity = a;
        data=d;
        //res = resLocal;
        //System.out.println("test__1");

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = ( LayoutInflater )activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       // System.out.println("test__1a");
    }
    public static class ViewHolder{

        public TextView firstLine;
        public TextView secondLine;
       // public TextView textWide;
       // public ImageView image;

    }
    @Override
    public int getCount() {

        if(data.size()<=0)
            return 1;
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // System.out.println("test__2");
        View vi = convertView;
        ViewHolder holder;

        if(convertView==null){

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.bubble_mess, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();
            holder.firstLine = (TextView) vi.findViewById(R.id.firstLine);
            holder.secondLine=(TextView)vi.findViewById(R.id.secondLine);
            //holder.image=(ImageView)vi.findViewById(R.id.image);

            /************  Set holder with LayoutInflater ************/
            vi.setTag( holder );
        }
        else
            holder=(ViewHolder)vi.getTag();

        if(data.size()>0)

        {
            /***** Get each Model object from Arraylist ********/

           // tempValues=null;
            tempValues = ( ListModel ) data.get( position );

            /************  Set Model values in Holder elements ***********/

            holder.firstLine.setText( tempValues.userID );
            holder.secondLine.setText( tempValues.userMess );
//            holder.image.setImageResource(
//                    res.getIdentifier(
//                            "com.androidexample.customlistview:drawable/"+tempValues.getImage()
//                            ,null,null));

            /******** Set Item Click Listner for LayoutInflater for each row *******/

          //  vi.setOnClickListener(new OnItemClickListener( position ));
        }
        return vi;
    }
}
