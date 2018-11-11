package com.thmotaun.simulator.interfaces;

import com.thmotaun.simulator.towers.WeatherTower;

public interface Flyable {

    public void updateConditions();
    public void registerTower(WeatherTower weatherTower);
}