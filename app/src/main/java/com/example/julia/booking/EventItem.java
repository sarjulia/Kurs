package com.example.julia.booking;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventItem
{
    private Object id;
    private Object name;
    private Object desc;
    private Object cur;
    private Object max;
    private Object data;
    private Object start;
    private Object end;
    public EventItem()
    {}
  public EventItem(Object id, Object name, Object desc, Object cur, Object max, Object data,Object start, Object end )
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

    public Object getStart() {
        return start;
    }

    public void setStart(Object start) {
        this.start = start;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getMax() {
        return max;
    }

    public void setMax(Object max) {
        this.max = max;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getEnd() {
        return end;
    }

    public void setEnd(Object end) {
        this.end = end;
    }

    public Object getDesc() {
        return desc;
    }

    public void setDesc(Object desc) {
        this.desc = desc;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getCur() {
        return cur;
    }

    public void setCur(Object cur) {
        this.cur = cur;
    }
}
