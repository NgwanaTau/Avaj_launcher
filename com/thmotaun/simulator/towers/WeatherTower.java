package com.thmotaun.simulator.towers;

import com.thmotaun.simulator.aircrafts.Coordinates;
import com.thmotaun.simulator.towers.Tower;
import com.thmotaun.simulator.weather.WeatherProvider;

public class WeatherTower extends Tower{

    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    void changeWeather() {
        conditionsChanged();
    }

    public void runSimulation(int sim_count) {
        while (sim_count-- > 0) {
            changeWeather();
        }
        
        writeToFile();
    }
}