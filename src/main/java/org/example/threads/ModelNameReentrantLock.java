package org.example.threads;

import org.example.vehicle.Vehicle;
import java.util.concurrent.locks.ReentrantLock;

public class ModelNameReentrantLock implements Runnable{
    private Vehicle vehicle;
    private ReentrantLock locker;

    public ModelNameReentrantLock(Vehicle vehicle, ReentrantLock reentrantLock){
        this.vehicle = vehicle;
        locker = reentrantLock;
    }

    @Override
    public void run() {
        locker.lock();
        try{
            String[] modelNames = vehicle.getModelNamesArray();
            for(int i = 0; i < modelNames.length; i++){
                System.out.println(modelNames[i]);
            }
        } finally {
            locker.unlock();
        }

    }
}
