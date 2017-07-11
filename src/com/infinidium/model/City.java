package com.infinidium.model;

import com.infinidium.AddressHandler;

/**
 * Created by abdullaht on 11.07.2017.
 */
public class City{
    private String cityName;
    private State[] states;
    public City(String cityName, State[] states) {
        this.cityName = cityName;
        this.states = states;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public State[] getStates() {
        return states;
    }

    public void setStates(State[] states) {
        this.states = states;
    }
}