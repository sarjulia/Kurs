package com.example.julia.booking;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ItemAd_Game extends BaseAdapter {

   /* LayoutInflater mInf;
    Map<String,Double> map;
    List <String> names;
    List <String> descs;
    List <String> tagss;
    List <Double> mins;
    List <Double> maxs;
    List <Double> numbers;



    public ItemAd_Game(Context c, Map m)
    {
        mInf = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        map = m;
        names = new ArrayList<String>(map.keySet());
        mins = new ArrayList<Double>(map.values());

    }

    @Override
    public int getCount()
    {
        return map.size();
    }

    @Override
    public Object getItem(int position)
    {
        return names.get(position);

    }

    @Override
    public long getItemId (int position)
    {
        return position;

    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        View v= mInf.inflate(R.layout.gameitem_layout,null);
        TextView name =  (TextView) v.findViewById(R.id.name);
        TextView minpeop =  (TextView) v.findViewById(R.id.minpeop);

        name.setText(names.get(pos));
        minpeop.setText(mins.get(pos).toString());

        return v;


    }*/

   private Context mContext;
   private List<GameItem> myGames;

    public ItemAd_Game(Context mContext, List<GameItem> myGames) {
        this.mContext = mContext;
        this.myGames = myGames;
    }

    @Override
    public int getCount() {
        return myGames.size();
    }

    @Override
    public Object getItem(int position) {
        return myGames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        View v= View.inflate(mContext,R.layout.gameitem_layout,null);
        TextView nametv =  (TextView) v.findViewById(R.id.name_ev);
        TextView desctv = (TextView) v.findViewById(R.id.description);
        TextView mintv =  (TextView) v.findViewById(R.id.minpeop);
        TextView maxtv = (TextView) v.findViewById(R.id.Maxpeop);
        TextView tagstv = (TextView) v.findViewById(R.id.tags);

        nametv.setText(myGames.get(position).getName());
        desctv.setText(myGames.get(position).getDesc());
        mintv.setText(myGames.get(position).getMinpl());
        maxtv.setText(myGames.get(position).getMaxpl());
        tagstv.setText(myGames.get(position).getTags());

        v.setTag(myGames.get(position).getId());
        return v;
    }
} //EndClass
