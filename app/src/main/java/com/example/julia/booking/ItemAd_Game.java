package com.example.julia.booking;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ItemAd_Game extends BaseAdapter {


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

        nametv.setText(String.valueOf(myGames.get(position).getName()));
        desctv.setText(String.valueOf(myGames.get(position).getDesc()));
        mintv.setText(String.valueOf(myGames.get(position).getMinpl()));
        maxtv.setText(String.valueOf(myGames.get(position).getMaxpl()));
        tagstv.setText(String.valueOf(myGames.get(position).getTags()));

        v.setTag(myGames.get(position).getId());
        return v;
    }
} //EndClass
