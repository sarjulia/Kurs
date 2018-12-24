package com.example.julia.booking;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookItem {
    private Object id;
    private Object roomName;
    private Object table_id;
    private Object userLogin;
    private Object date;
    private Object start;
    private Object end;
    public BookItem()
    {}
    public BookItem(Object id,  Object roomName,Object table_id, Object userLogin, Object date, Object start, Object end)
    {
        this.id = id;
        this.table_id =table_id;
        this.roomName = roomName;
        this.userLogin = userLogin;
        this.date = date;
        this.start = start;
        this.end = end;
    }

    public Object getTable_id() {
        return table_id;
    }

    public Object getUserLogin() {
        return userLogin;
    }

    public void setTable_id(Object table_id) {
        this.table_id = table_id;
    }

    public Object getStart() {
        return start;
    }

    public void setStart(Object start) {
        this.start = start;
    }

    public Object getRoomName() {
        return roomName;
    }

    public void setRoomName(Object roomName) {
        this.roomName = roomName;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }

    public Object getEnd() {
        return end;
    }

    public void setEnd(Object end) {
        this.end = end;
    }
}
