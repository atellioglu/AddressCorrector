package com.infinidium.model;

import com.infinidium.AddressHandler;

/**
 * Created by abdullaht on 11.07.2017.
 */
public class State{
    private String state;
    private Street[] streets;

    public State(String state, Street[] streets) {
        this.state = state;
        this.streets = streets;
    }

    public State(){

    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Street[] getStreets() {
        return streets;
    }

    public void setStreets(Street[] streets) {
        this.streets = streets;
    }
}
