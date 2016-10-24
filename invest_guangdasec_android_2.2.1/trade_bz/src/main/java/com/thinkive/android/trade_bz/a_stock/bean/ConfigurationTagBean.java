package com.thinkive.android.trade_bz.a_stock.bean;

import java.util.ArrayList;

public class ConfigurationTagBean {
    private String tagName;
    private String name;
    private String description;
    private ArrayList<String> value;
    private String priorityValue;
    private boolean route;

    public ConfigurationTagBean() {
    }

    public boolean isRoute() {
        return this.route;
    }

    public void setRoute(boolean route) {
        this.route = route;
    }

    public String getTagName() {
        return this.tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getValue() {
        return this.value;
    }

    public void setValue(ArrayList<String> value) {
        this.value = value;
    }

    public String getPriorityValue() {
        return this.priorityValue;
    }

    public void setPriorityValue(String priorityValue) {
        this.priorityValue = priorityValue;
    }
}