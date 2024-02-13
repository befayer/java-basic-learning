package org.example.threads;

import org.example.vehicle.Vehicle;

public class ModelPriceThread extends Thread{
    private Vehicle vehicle;
    public ModelPriceThread(Vehicle veh){
        vehicle = veh;
    }
    public void run(){
        double[] modelPrices = vehicle.getModelPricesArray();
        for (int i = 0; i < vehicle.getModelArraySize(); i++){
            System.out.println(modelPrices[i]);
        }
    }
}
