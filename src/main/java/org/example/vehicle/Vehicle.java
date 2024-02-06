package org.example.vehicle;

import org.example.exception.DuplicateModelNameException;
import org.example.exception.NoSuchModelNameException;

import java.io.Serializable;

public interface Vehicle extends Serializable {
    String getMark();
    void setMark(String mark);
    void setNewModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException;
    String[] getModelNamesArray();
    double[] getModelPricesArray();
    int getModelArraySize();
    double getPriceConcret(String name) throws NoSuchModelNameException;
    void setConcretModelPrice(String name, double price) throws NoSuchModelNameException;
    void addModel(String name, double price) throws NoSuchModelNameException,DuplicateModelNameException;
    void removeModel(String name) throws NoSuchModelNameException;
    Object clone() throws CloneNotSupportedException;
}
