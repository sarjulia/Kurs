package com.example.julia.booking;

public class GameItem {
    private int id;
    private String name;
    private String desc;
    private int minpl;
    private int maxpl;
    private String tags;

    public GameItem(int id, String name, String desc, int minpl, int maxpl, String tags)
    {
        this.id = id;
        this.name =name;
        this.desc = desc;
        this.minpl = minpl;
        this.maxpl = maxpl;
        this.tags = tags;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxpl() {
        return maxpl;
    }

    public void setMaxpl(int maxpl) {
        this.maxpl = maxpl;
    }

    public int getMinpl() {
        return minpl;
    }

    public void setMinpl(int minpl) {
        this.minpl = minpl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
