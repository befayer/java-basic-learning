package org.example.threads;

import org.example.vehicle.Vehicle;
import org.example.model.Auto;

import java.io.FileReader;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class ReadVehicleFromFile implements Runnable {
    private String filePath;
    private ArrayBlockingQueue arrayBlockingQueue;

    public ReadVehicleFromFile(String filePath, ArrayBlockingQueue arrayBlockingQueue){
        this.filePath = filePath;
        this.arrayBlockingQueue = arrayBlockingQueue;
    }

    @Override
    public void run() {
        try {
            FileReader fileReader = new FileReader(filePath);
            Scanner scanner = new Scanner(fileReader);
            Vehicle vehicle = new Auto(scanner.nextLine(),0);
            arrayBlockingQueue.put(vehicle);
            //arrayBlockingQueue.add(vehicle);
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
