package com.thmotaun.simulator.towers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.PrintWriter;

import com.thmotaun.simulator.interfaces.Flyable;

public class Tower {
    private static List<Flyable> observers = new CopyOnWriteArrayList<Flyable>();
    private static ArrayList<String> list = new ArrayList<String>();
    private File file;
    private FileWriter writer;

    public void register(Flyable flyable) {
        try {
            observers.add(flyable);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void unregister(Flyable flyable) {
        try {
            observers.remove(flyable);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void createPrintList(String line) {
        try {
            list.add(line);
        } catch (Exception e) {

        }
    }

    protected void conditionsChanged() {
        for (Flyable observer: observers) {
            observer.updateConditions();
        }
    }

    /*public void writeToFile(String state, String text) {
        switch (state) {
            case "create" :
                try {
                    this.file = new File("simulation.txt");
                    this.writer = new FileWriter(file);
                    this.file.createNewFile();
                } catch (Exception exception) {}
                break ;
            case "write" :
                try {
                   // System.out.println("Received state: "+ state);
                    writer.write(text);
                   // System.out.println("Writing to file: "+ text);
                    writer.flush();
                } catch (Exception exception) {
                    System.out.println("ERROR #1: Can't write to file");
                }
                break;
            case "close" :
                try {
                    writer.close();
                } catch (Exception exception) {
                    System.out.println("ERROR #2: Can't close file");
                }
                break ;
        }
    }*/

    public void writeToFile() {
        list.toArray().toString();
        try {
            this.file = new File("simulation.txt");
            this.writer = new FileWriter(file);
            this.file.createNewFile();
            for(int i = 0; i < list.size(); i++) {
                writer.write(list.get(i) + "\n");
                writer.flush();
            }
        } catch (IOException e) {
            System.out.println("Cannot create and write to file.\nClosing program...");
            System.exit(1);
        } finally {
            try {
                System.out.println("simulation.txt available for reading.\n");
                writer.close();
            } catch (Exception e) {
                System.out.println("Cannot close file.\nClosing program...");
                System.exit(1);
            } 
        }
    }
}