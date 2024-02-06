package org.example;

import org.example.exception.DuplicateModelNameException;
import org.example.exception.NoSuchModelNameException;
import org.example.model.*;

public class Main {
    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException {
        Auto bmw = new Auto("bmw", 5);
        Motorcycle audi = new Motorcycle("audi", 5);

        Auto bmwClone = (Auto) bmw.clone();
        Motorcycle audiClone = (Motorcycle) audi.clone();

        audiClone.setConcretModelPrice("Model1", 9999);
        bmwClone.setConcretModelPrice("Model1", 9999);

        if (audi.equals(audiClone)){
            System.out.println("Транспортные средства равны");
        }
        else System.out.println("Транспортные средства не равны");

        if (bmw.equals(bmwClone)){
            System.out.println("Транспортные средства равны");
        }
        else System.out.println("Транспортные средства не равны");

        System.out.println(audi);
        System.out.println(audiClone);
        System.out.println(bmw);
        System.out.println(bmwClone);
        System.out.println(bmwClone.hashCode());
        System.out.println(audiClone.hashCode());
    }
}
