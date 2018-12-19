package com.example.julia.booking;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventItem
{
    private int id;
    private String name;
    private String desc;
    private int cur;
    private int max;
    private Date data;
    private Time start;
    private Time end;

    public EventItem(int id, String name, String desc, int cur, int max, Date data,Time start, Time end )
    {
        this.id = id;
        this.name =name;
        this.desc = desc;
        this.cur = cur;
        this.max = max;
        this.data = data;
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        DateFormat df = new SimpleDateFormat("hh:mm:ss");
        return df.format(start);
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnd() {
        DateFormat df = new SimpleDateFormat("hh:mm:ss");
        return df.format(end);
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getData() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        return df.format(data);
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getCur() {
        return cur;
    }

    public void setCur(int cur) {
        this.cur = cur;
    }
}
