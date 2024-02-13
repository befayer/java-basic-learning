package org.example.threads;

import org.example.vehicle.Vehicle;

public class ModelMarkaRunnable implements Runnable {
    private Vehicle vehicle;

    public ModelMarkaRunnable(Vehicle vehicle){
        this.vehicle=vehicle;
    }
    @Override
    public void run() {
        System.out.println(vehicle.getMark());
    }
}
