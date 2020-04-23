package com.example.trackcovid;

public class StateListItem {
    private String state, stActive, stConfirmed, stRecovered, stDeaths;
    private String stNewConfirmed, stNewRecovered, stNewDeaths;
    private int stNewActive;

    public StateListItem(){

    }

    public StateListItem(String state, String stActive, String stConfirmed, String stRecovered, String stDeaths, String stNewConfirmed, String stNewRecovered, String stNewDeaths, int stNewActive) {
        this.state = state;
        this.stActive = stActive;
        this.stConfirmed = stConfirmed;
        this.stRecovered = stRecovered;
        this.stDeaths = stDeaths;
        this.stNewConfirmed = stNewConfirmed;
        this.stNewRecovered = stNewRecovered;
        this.stNewDeaths = stNewDeaths;
        this.stNewActive = stNewActive;
    }

    public String getState() {
        return state;
    }

    public String getStActive() {
        return stActive;
    }

    public String getStConfirmed() {
        return stConfirmed;
    }

    public String getStRecovered() {
        return stRecovered;
    }

    public String getStDeaths() {
        return stDeaths;
    }

    public String getStNewConfirmed() {
        return stNewConfirmed;
    }

    public String getStNewRecovered() {
        return stNewRecovered;
    }

    public String getStNewDeaths() {
        return stNewDeaths;
    }

    public int getStNewActive() {
        return stNewActive;
    }
}