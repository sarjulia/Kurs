package com.example.julia.booking;

public class GameItem {
    private int id;
    private Object name;
    private Object desc;
    private Object minpl;
    private Object maxpl;
    private Object tags;

    public GameItem(int id, Object name, Object desc, Object minpl, Object maxpl, Object tags)
    {
        this.id = id;
        this.name =name;
        this.desc = desc;
        this.minpl = minpl;
        this.maxpl = maxpl;
        this.tags = tags;
    }

    public Object getMinpl() {
        return minpl;
    }

    public void setMinpl(Object minpl) {
        this.minpl = minpl;
    }

    public Object getMaxpl() {
        return maxpl;
    }

    public void setMaxpl(Object maxpl) {
        this.maxpl = maxpl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getDesc() {
        return desc;
    }

    public void setDesc(Object desc) {
        this.desc = desc;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getTags() {
        return tags;
    }

    public void setTags(Object tags) {
        this.tags = tags;
    }
}
