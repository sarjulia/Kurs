package com.example.julia.booking;

public class BookItem {
    private int id;
    private Object table_id;
    private Object roomName;
    private Object date;
    private Object start;
    private Object end;

    public BookItem(int id, Object table_id, Object roomName, Object date, Object start, Object end)
    {
        this.id = id;
        this.table_id =table_id;
        this.roomName = roomName;
        this.date = date;
        this.start = start;
        this.end = end;
    }

    public Object getTable_id() {
        return table_id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
