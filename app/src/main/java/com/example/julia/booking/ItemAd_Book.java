package com.example.julia.booking;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.julia.booking.BookItem;
import com.example.julia.booking.R;

import java.util.List;

public class ItemAd_Book  extends BaseAdapter {
    private Context mContext;
    private List<BookItem> myBooks;

    public ItemAd_Book(Context mContext, List<BookItem> myBooks) {
        this.mContext = mContext;
        this.myBooks = myBooks;
    }

    @Override
    public int getCount() {
        return myBooks.size();
    }

    @Override
    public Object getItem(int position) {
        return myBooks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        View v= View.inflate(mContext, R.layout.bookitem_view,null);
        TextView table_id_tv =  (TextView) v.findViewById(R.id.tablenumber);
        TextView roomname_tv = (TextView) v.findViewById(R.id.RoomNumber);
        TextView date_tv =  (TextView) v.findViewById(R.id.Date);
        TextView start_tv = (TextView) v.findViewById(R.id.start);
        TextView end_tv = (TextView) v.findViewById(R.id.end);

        table_id_tv.setText(String.valueOf(myBooks.get(position).getTable_id()));
        roomname_tv.setText(String.valueOf(myBooks.get(position).getRoomName()));
        date_tv.setText(String.valueOf(myBooks.get(position).getDate()));
        start_tv.setText(String.valueOf(myBooks.get(position).getStart()));
        end_tv.setText(String.valueOf(myBooks.get(position).getEnd()));

        v.setTag(myBooks.get(position).getId());
        return v;
    }
} //EndClass
