package com.thmotaun.simulator.aircrafts;

import java.util.HashMap;

import com.thmotaun.simulator.aircrafts.Coordinates;
import com.thmotaun.simulator.interfaces.Flyable;
import com.thmotaun.simulator.towers.WeatherTower;

public class Balloon extends Aircraft implements Flyable {
    
    private WeatherTower weatherTower = new WeatherTower();

    Balloon(String name, Coordinates coordinates) {
        super(name, coordinates);
        registerTower(weatherTower);
    }

    public void updateConditions() {

        HashMap<String, String> reactions = new HashMap<String, String>();
    
        reactions.put("SUN", "Let's enjoy the good weather and take some pics.");
        reactions.put("RAIN", "Damn you rain! You messed up my balloon.");
        reactions.put("FOG", "Shit, I cant see a thing");
        reactions.put("SNOW", "It's snowing. We're gonna crash.");

        String weather = weatherTower.getWeather(this.coordinates);
        weatherTower.createPrintList("Balloon#   "+ name + "(" + id + ") : " + reactions.get(weather));
      //  System.out.println("Balloon#   "+ name + "(" + id + ") : " + reactions.get(weather));

        if (weather == "SUN") {
           coordinates.setHeight(coordinates.getHeight() + 4);
           coordinates.setLongitude(coordinates.getLongitude() + 2);
        }
        else if (weather == "RAIN") {
            coordinates.setHeight(coordinates.getHeight() - 5);
        }
        else if (weather == "FOG") {
            coordinates.setHeight(coordinates.getHeight() - 3);
        }
        else if (weather == "SNOW") {
            coordinates.setHeight(coordinates.getHeight() - 15);
        }
        if (coordinates.getHeight() <= 0) {
            weatherTower.createPrintList("Balloon#   "+ name + "(" + id + ") : " + "landing");
        //    System.out.println("Balloon#   "+ name + "(" + id + ") : " + "landing");
            unregisterTower(weatherTower);
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        weatherTower.register(this);
        weatherTower.createPrintList("Tower says       : " +"Balloon#"+ name + "(" + id + ") registered to weather tower.");
       // System.out.println("Tower says       : " +"Balloon#"+ name + "(" + id + ") registered to weather tower.");
    }
    
    public void unregisterTower(WeatherTower weatherTower) {
        weatherTower.unregister(this);
        weatherTower.createPrintList("Tower says       : " +"Balloon#"+ name + "(" + id + ") unregistered from weather tower.");
      //  System.out.println("Tower says       : " +"Balloon#"+ name + "(" + id + ") unregistered from weather tower.");
    }
}