package org.example.threads;

import org.example.vehicle.Vehicle;

public class TransportSynchronizer {
    private Vehicle vehicle;
    private volatile int current = 0;
    private Object lock = new Object();
    private boolean set = false;

    public TransportSynchronizer(Vehicle vehicle){
        this.vehicle = vehicle;
    }

    public double printPrice() throws InterruptedException {
        double val;
        synchronized(lock) {
            double [] p = vehicle.getModelPricesArray();
            if (!canPrintPrice()) throw new InterruptedException();
            while (!set)
                lock.wait();
            val = p[current++];
            System.out.println("Print price: " + val);
            set = false;
            lock.notifyAll();
        }
        return val;
    }

    public void printModel() throws InterruptedException {
        synchronized(lock) {
            String [] s = vehicle.getModelNamesArray();
            if (!canPrintModel()) throw new InterruptedException();
            while (set)
                lock.wait();
            System.out.println("Print model: " + s[current]);
            set = true;
            lock.notifyAll();
        }
    }

    public boolean canPrintPrice() {
        return current < vehicle.getModelArraySize();
    }

    public boolean canPrintModel() {
        return (!set && current < vehicle.getModelArraySize()) || (set && current < vehicle.getModelArraySize() - 1);
    }
}
