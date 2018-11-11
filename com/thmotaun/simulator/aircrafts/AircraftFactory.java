package com.thmotaun.simulator.aircrafts;

import com.thmotaun.simulator.aircrafts.Coordinates;
import com.thmotaun.simulator.interfaces.Flyable;
import com.thmotaun.simulator.aircrafts.Balloon;
import com.thmotaun.simulator.aircrafts.Helicopter;
import com.thmotaun.simulator.aircrafts.JetPlane;

public class AircraftFactory {

    public Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        Coordinates coordinates = new Coordinates(longitude, latitude, height);
        //System.out.println("Creating new aircraft...");

        Helicopter heli;
        Balloon ball;
        JetPlane jet;

        if (type.equalsIgnoreCase("Helicopter")) {
            heli = new Helicopter(name, coordinates);
            //System.out.println("Helicopter created...");
            return heli;
        } else if (type.equalsIgnoreCase("Balloon") || type.equalsIgnoreCase("Baloon")) {
            ball = new Balloon(name, coordinates);
            //System.out.println("Balloon created...");
            return ball;
        } else if (type.equalsIgnoreCase("JetPlane")) {
            jet = new JetPlane(name, coordinates);
            //System.out.println("JetPlane created...");
            return jet;
        } else {
            System.out.println("No such Aircraft: " + type);
            return null;
        }
    }
}