package org.example.threads;

import org.example.vehicle.Vehicle;
import java.util.concurrent.locks.ReentrantLock;

public class ModelPriceReentrantLock implements Runnable {
    private Vehicle vehicle;
    private ReentrantLock locker;

    public ModelPriceReentrantLock(Vehicle vehicle, ReentrantLock reentrantLock){
        this.vehicle = vehicle;
        locker = reentrantLock;
    }
    @Override
    public void run() {
        locker.lock();
        try{
            double[] modelPrices = vehicle.getModelPricesArray();
            for(int i = 0; i < modelPrices.length; i++){
                System.out.println(modelPrices[i]);
            }
        } finally {
            locker.unlock();
        }
    }
}
