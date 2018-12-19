package com.example.julia.booking;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ItemAd_Event extends BaseAdapter {

    private Context mContext;
    private List<EventItem> myEvent;

    public ItemAd_Event(Context mContext, List<EventItem> myEvent) {
        this.mContext = mContext;
        this.myEvent = myEvent;
    }

    @Override
    public int getCount() {
        return myEvent.size();
    }

    @Override
    public Object getItem(int position) {
        return myEvent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        View v= View.inflate(mContext,R.layout.eventitem_layout,null);
        TextView name_ev =  (TextView) v.findViewById(R.id.name_ev);
        TextView desc_ev = (TextView) v.findViewById(R.id.desc_ev);
        TextView cur_ev =  (TextView) v.findViewById(R.id.cur_ev);
        TextView max_ev = (TextView) v.findViewById(R.id.max_ev);
        TextView data_ev = (TextView) v.findViewById(R.id.data_ev);
        TextView start_ev = (TextView) v.findViewById(R.id.start_ev);
        TextView end_ev = (TextView) v.findViewById(R.id.end_ev);

        name_ev.setText(myEvent.get(position).getName());
        desc_ev.setText(myEvent.get(position).getDesc());
        cur_ev.setText(myEvent.get(position).getCur());
        max_ev.setText(myEvent.get(position).getMax());
        data_ev.setText(myEvent.get(position).getData());
        start_ev.setText(myEvent.get(position).getStart());
        end_ev.setText(myEvent.get(position).getEnd());


        v.setTag(myEvent.get(position).getId());
        return v;
    }
}
