package org.example.source;

import org.example.exception.DuplicateModelNameException;
import org.example.exception.NoSuchModelNameException;
import org.example.model.Motorcycle;
import org.example.vehicle.Vehicle;

import java.io.*;
import java.util.*;

public class StaticMethods {
    public static void printAllNames(Vehicle vehicle) {
        System.out.println("Массив названий моделей" + Arrays.toString(vehicle.getModelNamesArray()));
    }
    public static void printAllPrices(Vehicle vehicle) {
        System.out.println("Массив цен моделей" + Arrays.toString(vehicle.getModelPricesArray()));
    }
    public static double averagePrice(Vehicle vehicle) {
        double avg = 0;
        for (int i = 0; i < vehicle.getModelArraySize(); i++) {
            avg += vehicle.getModelPricesArray()[i];
        }
        return avg;
    }
    public static void writeVehicle(Vehicle vehicle, Writer out){
        PrintWriter printWriter = new PrintWriter(out);
        printWriter.println(vehicle.getMark());
        printWriter.println(vehicle.getModelArraySize());
        for (int i = 0; i < vehicle.getModelArraySize(); i++) {
            String[] models = vehicle.getModelNamesArray();
            double[] prices = vehicle.getModelPricesArray();
            printWriter.println(models[i]);
            printWriter.println(prices[i]);
        }
    }
    public static Vehicle readVehicle(Reader reader) throws IOException, DuplicateModelNameException, NoSuchModelNameException {
        BufferedReader bufferedReader = new BufferedReader(reader);
        Vehicle vehicle = new Motorcycle();
        String mark = bufferedReader.readLine();
        vehicle.setMark(mark);
        int size = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < size; i++) {
            vehicle.addModel(bufferedReader.readLine(), Double.parseDouble(bufferedReader.readLine()));
        }
        return vehicle;
    }
    public static void outputVehicle(Vehicle vehicle, OutputStream out) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(out);
        byte[] bytes;
        bytes = vehicle.getMark().getBytes();
        dataOutputStream.writeInt(bytes.length);
        dataOutputStream.write(bytes);
        dataOutputStream.writeInt(vehicle.getModelArraySize());
        for (int i = 0; i < vehicle.getModelArraySize(); i++) {
            String[] models = vehicle.getModelNamesArray();
            double[] prices = vehicle.getModelPricesArray();
            bytes = models[i].getBytes();
            dataOutputStream.writeInt(bytes.length);
            dataOutputStream.write(bytes);
            dataOutputStream.writeDouble(prices[i]);
        }
    }
    public static Vehicle inputVehicle(InputStream inputStream) throws IOException, DuplicateModelNameException, NoSuchModelNameException {
        Vehicle v = new Motorcycle();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        byte[] bytes;
        int size = dataInputStream.readInt();
        bytes = new byte[size];
        dataInputStream.read(bytes);
        String marka = new String(bytes);
        v.setMark(marka);
        size = dataInputStream.readInt();
        int sizei;
        for (int i = 0; i < size; i++) {
            sizei = dataInputStream.readInt();
            bytes = new byte[sizei];
            dataInputStream.read(bytes);
            String model = new String(bytes);
            v.addModel(model, dataInputStream.readDouble());
        }
        return v;
    }
}

