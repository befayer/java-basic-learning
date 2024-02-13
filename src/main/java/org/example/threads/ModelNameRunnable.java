package org.example.threads;

import org.example.vehicle.Vehicle;

public class ModelNameRunnable implements Runnable {
    private TransportSynchronizer transportSynchronizer;
    public ModelNameRunnable(TransportSynchronizer transportSynchronizer){
        this.transportSynchronizer = transportSynchronizer;
    }
    @Override
    public void run() {
        try {
            while(transportSynchronizer.canPrintModel()) {
                transportSynchronizer.printModel();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
