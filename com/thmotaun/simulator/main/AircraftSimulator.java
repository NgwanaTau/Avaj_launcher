package com.thmotaun.simulator.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;
import java.io.IOException;

import com.thmotaun.simulator.syntax.Syntax;
import com.thmotaun.simulator.interfaces.Flyable;
import com.thmotaun.simulator.towers.Tower;
import com.thmotaun.simulator.towers.WeatherTower;
import com.thmotaun.simulator.aircrafts.AircraftFactory;

public class AircraftSimulator {
    
    public static void main(String args[]){
        System.out.println("\nRunning Aircraft Simulator...\n");
        if (args.length > 1) {
            System.out.println("Too many arguments, try again...\n");
        } else if (args.length < 1) {
            System.out.println("Too few arguments, try again...\n");
        } else if (args.length == 1) {
            Syntax syntax = new Syntax(args[0]);
            runProgram(syntax.getFileContent());
        }
    }

    public static void runProgram(ArrayList<Map<String, String>> new_list) {
        
        int simulationLoopCount = 0;
        AircraftFactory factory = new AircraftFactory();
        WeatherTower weatherTower = new WeatherTower();
        Flyable flyable = null;
        
        for (Map<String, String> coordinateHashes : new_list) {
        
            Iterator<Map.Entry<String, String>> coordinateHashesEntries = coordinateHashes.entrySet().iterator();
        
            while (coordinateHashesEntries.hasNext()) {
                String[] splittedHashes;
        
                Map.Entry<String, String> coordinateHashesEntry = coordinateHashesEntries.next();
        
                splittedHashes  = coordinateHashesEntry.getValue().split("-");
        
                String type = splittedHashes[0];
                String name = splittedHashes[1];
                Integer latitude = Integer.parseInt(splittedHashes[2]);
                Integer longitude = Integer.parseInt(splittedHashes[3]);
                Integer height = Integer.parseInt(splittedHashes[4]);
                simulationLoopCount = Integer.parseInt(splittedHashes[5]);
        
                try {
                    //System.out.println("Adding new Aircraft: "+ type +" "+ name +" "+ latitude +" "+ longitude +" "+ height);
                    flyable = factory.newAircraft(type, name, longitude, latitude, height);
                } catch (Exception exception) {
                    System.out.println("AircraftSimulator Error (runProgram): Input value error. Closing program...");
                    System.exit(1);
                }
            }
        }
        weatherTower.runSimulation(simulationLoopCount);
    }
}