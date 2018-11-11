package com.thmotaun.simulator.aircrafts;

import java.util.HashMap;

import com.thmotaun.simulator.aircrafts.Coordinates;
import com.thmotaun.simulator.interfaces.Flyable;
import com.thmotaun.simulator.towers.WeatherTower;

public class JetPlane extends Aircraft implements Flyable {

    private WeatherTower weatherTower = new WeatherTower();

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
        registerTower(weatherTower);
    }

    public void updateConditions() {

        HashMap<String, String> reactions = new HashMap<String, String>();
    
        reactions.put("SUN", "Let's enjoy the good weather and take some pics.");
        reactions.put("RAIN", "It's raining. Better watch out for lightings.");
        reactions.put("FOG", "Shit, I cant see a thing");
        reactions.put("SNOW", "OMG! Winter is coming!");

        String weather = weatherTower.getWeather(this.coordinates);
        weatherTower.createPrintList("JetPlane#  "+ name + "(" + id + ") : " + reactions.get(weather));
      //  System.out.println("JetPlane#  "+ name + "(" + id + ") : " + reactions.get(weather));

        if (weather == "SUN") {
           coordinates.setHeight(coordinates.getHeight() + 2);
           coordinates.setLatitude(coordinates.getLongitude() + 10);
        }
        else if (weather == "RAIN") {
            coordinates.setLongitude(coordinates.getLatitude() + 5);
        }
        else if (weather == "FOG") {
            coordinates.setLongitude(coordinates.getLatitude() + 1);
        }
        else if (weather == "SNOW") {
            coordinates.setHeight(coordinates.getHeight() - 7);
        }
        if (coordinates.getHeight() <= 0) {
            weatherTower.createPrintList("JetPlane#  "+ name + "(" + id + ") : " + "landing");
        //    System.out.println("JetPlane#  "+ name + "(" + id + ") : " + "landing");
            unregisterTower(weatherTower);
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        weatherTower.register(this);
        weatherTower.createPrintList("Tower says       : " +"JetPlane#"+ name + "(" + id + ") registered to weather tower.");
        //System.out.println("Tower says       : " +"JetPlane#"+ name + "(" + id + ") registered to weather tower.");
    }

    public void unregisterTower(WeatherTower weatherTower) {
        weatherTower.unregister(this);
        weatherTower.createPrintList("Tower says       : " +"JetPlane#"+ name + "(" + id + ") unregistered from weather tower.");
    //    System.out.println("Tower says       : " +"Helicopter#"+ name + "(" + id + ") unregistered from weather tower.");
    }
}