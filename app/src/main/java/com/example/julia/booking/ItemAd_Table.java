package com.example.julia.booking;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ItemAd_Table extends BaseAdapter {


    private Context mContext;
    private List<TablesItem> myTables;

    public ItemAd_Table(Context mContext, List<TablesItem> myTables) {
        this.mContext = mContext;
        this.myTables = myTables;
    }

    @Override
    public int getCount() {
        return myTables.size();
    }

    @Override
    public Object getItem(int position) {
        return myTables.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        View v= View.inflate(mContext,R.layout.tableitem_layout,null);
        TextView tableid_tv =  (TextView) v.findViewById(R.id.tablenumber);
        TextView roomName_tv = (TextView) v.findViewById(R.id.roomnumber);
        TextView max_tv =  (TextView) v.findViewById(R.id.max);


        tableid_tv.setText(String.valueOf(myTables.get(position).getTableid()));
        roomName_tv.setText(String.valueOf(myTables.get(position).getRoomName()));
        max_tv.setText(String.valueOf(myTables.get(position).getMaxCapacity()));


        v.setTag(myTables.get(position).getId());
        return v;
    }
} //EndClass
