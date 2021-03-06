package com.thmotaun.simulator.aircrafts;

import java.util.HashMap;

import com.thmotaun.simulator.aircrafts.Coordinates;
import com.thmotaun.simulator.interfaces.Flyable;
import com.thmotaun.simulator.towers.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {

    private WeatherTower weatherTower = new WeatherTower();

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
        registerTower(weatherTower);
    }

    public void updateConditions() {

        HashMap<String, String> reactions = new HashMap<String, String>();
    
        reactions.put("SUN", "This is hot.");
        reactions.put("RAIN", "Damn you rain! You messed up my baloon.");
        reactions.put("FOG", "Shit, I cant see a thing");
        reactions.put("SNOW", "My rotor is going to freeze!");

        String weather = weatherTower.getWeather(this.coordinates);
        weatherTower.createPrintList("Helicopter#"+ name + "(" + id + ") : " + reactions.get(weather));
      //  System.out.println("Helicopter#"+ name + "(" + id + ") : " + reactions.get(weather));

        if (weather == "SUN") {
           coordinates.setHeight(coordinates.getHeight() + 2);
           coordinates.setLongitude(coordinates.getLongitude() + 10);
        }
        else if (weather == "RAIN") {
            coordinates.setLongitude(coordinates.getLongitude() + 5);
        }
        else if (weather == "FOG") {
            coordinates.setLongitude(coordinates.getLongitude() + 1);
        }
        else if (weather == "SNOW") {
            coordinates.setHeight(coordinates.getHeight() - 12);
        }
        if (coordinates.getHeight() <= 0) {
            weatherTower.createPrintList("Helicopter#"+ name + "(" + id + ") : " + "landing");
         //   System.out.println("Helicopter#"+ name + "(" + id + ") : " + "landing");
            unregisterTower(weatherTower);
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        weatherTower.register(this);
        weatherTower.createPrintList("Tower says       : " +"Helicopter#"+ name + "(" + id + ") registered to weather tower.");
    //    System.out.println("Tower says       : " +"Helicopter#"+ name + "(" + id + ") registered to weather tower.");
    }

    public void unregisterTower(WeatherTower weatherTower) {
        weatherTower.unregister(this);
        weatherTower.createPrintList("Tower says       : " +"Helicopter#"+ name + "(" + id + ") unregistered from weather tower.");
    //    System.out.println("Tower says       : " +"Helicopter#"+ name + "(" + id + ") unregistered from weather tower.");
    }
}