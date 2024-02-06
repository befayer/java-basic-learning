package org.example.model;


import org.example.exception.DuplicateModelNameException;
import org.example.exception.ModelPriceOutOfBoundsException;
import org.example.exception.NoSuchModelNameException;
import org.example.vehicle.Vehicle;

import java.io.Serializable;
import java.util.Arrays;

public class Auto implements Vehicle, Cloneable {
    //поле типа String, хранящее марку автомобиля
    private String mark;

    public Auto() {
    }

    //метод для получения марки автомобиля
    @Override
    public String getMark() {
        return mark;
    }

    //метод для модификации марки автомобиля
    @Override
    public void setMark(String newMark) {
        mark = newMark;
    }

    //внутренний класс Модель, имеющий поля название модели и её цену, а также конструктор
    private class Model implements Serializable, Cloneable {
        private String modelName = null;
        private double modelPrice = Double.NaN;

        public Model(String Name, double Price) {
            modelName = Name;
            modelPrice = Price;
        }

        public Model() {
        }

        //метод для модификации значения названия модели
        public void setModelName(String modelName) {
            this.modelName = modelName;
        }

        public String getModelName() {
            return modelName;
        }

        public double getModelPrice() {
            return modelPrice;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    //метод, возвращающий массив названий всех моделей
    @Override
    public String[] getModelNamesArray() {
        String[] namesArray = new String[modelArray.length];
        for (int i = 0; i < modelArray.length; i++) {
            if (modelArray[i] != null) {
                namesArray[i] = modelArray[i].getModelName();
            }
        }
        return namesArray;
    }

    //метод для получения значения цены модели по её названию
    @Override
    public double getPriceConcret(String modelName) throws NoSuchModelNameException {
        int id = -1;
        for (int i = 0; i < modelArray.length; i++) {
            if (modelArray[i].modelName.equals(modelName)) {
                id = i;
                break;
            }
        }
        if (id == -1) throw new NoSuchModelNameException("Введенной модели не существует");
        else return modelArray[id].getModelPrice();
    }

    //метод для модификации значения цены модели по её названию
    @Override
    public void setConcretModelPrice(String modelName, double newPrice) throws NoSuchModelNameException {
        if (newPrice < 0) throw new ModelPriceOutOfBoundsException("Введено неверное значение цены");
        int id = -1;
        for (int i = 0; i < modelArray.length; i++) {
            if (modelArray[i].modelName.equals(modelName)) {
                id = i;
                break;
            }
        }
        if (id == -1) throw new NoSuchModelNameException("Введенной модели не существует");
        else modelArray[id].modelPrice = newPrice;
    }

    //метод, возвращающий массив значений цен моделей
    @Override
    public double[] getModelPricesArray() {
        double[] priceArray = new double[modelArray.length];
        for (int i = 0; i < modelArray.length; i++) {
            if (modelArray[i] != null) {
                priceArray[i] = modelArray[i].getModelPrice();
            }
        }
        return priceArray;
    }

    @Override
    //метод для модификации значения названия модели
    public void setNewModelName(String oldNAme, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        int id = -1;
        for (int i = 0; i < modelArray.length; i++) {
            if (modelArray[i].modelName.equals(oldNAme)) {
                id = i;
                break;
            }
        }
        for (int i = 0; i < modelArray.length; i++) {
            if (modelArray[i].modelName.equals(newName))
                throw new DuplicateModelNameException("Такое имя уже существует");
        }
        if (id == -1) throw new NoSuchModelNameException("Введенной модели не существует");
        else modelArray[id].modelName = newName;
    }

    //метод добавления названия модели и её цены (путем создания нового массива Моделей), использовать метод Arrays.copyOf()
    @Override
    public void addModel(String name, double price) throws DuplicateModelNameException {
        if (price < 0) throw new ModelPriceOutOfBoundsException("Введено неверное значение цены");
        for (int i = 0; i < modelArray.length; i++) {
            if (modelArray[i].modelName.equals(name)) throw new DuplicateModelNameException("Такое имя уже существует");
        }
        modelArray = Arrays.copyOf(modelArray, modelArray.length + 1);
        modelArray[modelArray.length - 1] = new Model(name, price);
    }

    //метод удаления модели с заданным именем использовать методы System.arraycopy, Arrays.copyOf()
    @Override
    public void removeModel(String name) throws NoSuchModelNameException {
        int id = -1;
        for (int i = 0; i < modelArray.length; i++) {
            if (modelArray[i].modelName.equals(name)) {
                id = i;
                break;
            }
        }
        if (id == -1) throw new NoSuchModelNameException("Введенной модели не существует");
        System.arraycopy(modelArray, id + 1, modelArray, id, modelArray.length - id - 1);
        modelArray = Arrays.copyOf(modelArray, modelArray.length - 1);
    }

    //Конструктор класса должен принимать в качестве параметров значение
    // Марки автомобиля и размер массива Моделей
    private Model[] modelArray;

    public Auto(String mark, int n) {
        this.mark = mark;
        modelArray = new Model[n];
        for (int i = 0; i < n; i++)
            modelArray[i] = new Model("Model" + i, i * i * i);
    }

    //метод для получения размера массива Моделей
    @Override
    public int getModelArraySize() {
        return modelArray.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append("Марка Автомобиля ");
        stringBuffer.append(mark);
        stringBuffer.append(" ");
        stringBuffer.append("Количество моделей ");
        stringBuffer.append(getModelArraySize());
        stringBuffer.append(" ");
        for (int i = 0; i < getModelArraySize(); i++) {
            stringBuffer.append(" ");
            stringBuffer.append(modelArray[i].modelName);
            stringBuffer.append(": ");
            stringBuffer.append(modelArray[i].modelPrice);
            stringBuffer.append(" ");
        }
        return stringBuffer.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof Auto) {
            Auto st = (Auto) obj;
            if (!st.getMark().equals(getMark())) {
                return false;
            } else if (st.getModelArraySize() != getModelArraySize()) {
                return false;
            }
            for (int i = 0; i < getModelArraySize(); i++) {
                if ((!st.modelArray[i].modelName.equals(modelArray[i].modelName))) {
                    return false;
                } else if (st.modelArray[i].getModelPrice() != modelArray[i].getModelPrice()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        if (mark != null) {
            if (modelArray != null) {
                result = 31 * result + Arrays.hashCode(modelArray);
                return result;
            } else return 0;
        } else return 0;
    }

    @Override
    public Object clone() {
        Auto cloneAuto;
        try {
            cloneAuto = (Auto) super.clone();
            cloneAuto.modelArray = modelArray.clone();
            for (int i = 0; i < getModelArraySize(); i++) {
                cloneAuto.modelArray[i] = (Model) modelArray[i].clone();
            }
            return cloneAuto;
        } catch (CloneNotSupportedException e) {
            System.out.println(e);
            return null;
        }
    }
}