package org.example.threads;

import org.example.vehicle.Vehicle;

public class ModelNameThread extends Thread {

    private Vehicle vehicle;
    public ModelNameThread(Vehicle veh){
        vehicle=veh;
    }

    @Override
    public void run(){
        String[] modelNames = vehicle.getModelNamesArray();
        for(int i = 0; i < vehicle.getModelArraySize(); i++){
            System.out.println(modelNames[i]);
        }
    }
}
