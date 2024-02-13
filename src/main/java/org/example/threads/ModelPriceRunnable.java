package org.example.threads;

public class ModelPriceRunnable implements Runnable {
    private TransportSynchronizer transportSynchronizer;
    public ModelPriceRunnable(TransportSynchronizer transportSynchronizer){
        this.transportSynchronizer = transportSynchronizer;
    }

    @Override
    public void run() {
        try {
            while(transportSynchronizer.canPrintPrice()) {
                transportSynchronizer.printPrice();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
