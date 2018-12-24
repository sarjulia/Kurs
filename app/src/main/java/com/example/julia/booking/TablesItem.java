package com.example.julia.booking;

public class TablesItem {
    private Object id;
    private Object tableid;
    private Object roomName;
    private Object maxCapacity;


    public TablesItem(Object id, Object tableid, Object roomName, Object maxCapacity)
    {
        this.id = id;
        this.tableid = tableid;
        this.roomName = roomName;
        this.maxCapacity = maxCapacity;
    }

    public Object getTableid() {
        return tableid;
    }

    public void setTableid(Object tableid) {
        this.tableid = tableid;
    }

    public Object getRoomName() {
        return roomName;
    }

    public void setRoomName(Object roomName) {
        this.roomName = roomName;
    }

    public Object getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Object maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }
}
