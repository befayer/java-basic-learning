package org.example.model;


import org.example.exception.DuplicateModelNameException;
import org.example.exception.ModelPriceOutOfBoundsException;
import org.example.exception.NoSuchModelNameException;
import org.example.vehicle.Vehicle;

import java.io.Serializable;

public class Motorcycle implements Vehicle, Cloneable {
    public Motorcycle() {
    }

    private class Model implements Serializable, Cloneable {
        String name = null;
        double price = Double.NaN;
        Model prev = null;
        Model next = null;

        public Model(String Name, double Price) {
            name = Name;
            price = Price;
        }

        public Model() {
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    private Model head = new Model();

    {
        head.prev = head;
        head.next = head;
    }

    //поле типа String, хранящее марку автомобиля
    private String mark;
    private int initialSize;

    //метод для получения марки автомобиля
    @Override
    public String getMark() {
        return mark;
    }

    // метод для модификации марки автомобиля
    @Override
    public void setMark(String mark) {
        this.mark = mark;
    }

    //Колисчество
    public Motorcycle(String mark, int n) throws DuplicateModelNameException {
        this.mark = mark;
        initialSize = 0;
        for (int i = 0; i < n; i++) {
            this.addModel("Model" + i, i * i * i);
        }
    }

    //метод для получения значения цены модели по её названию
    @Override
    public double getPriceConcret(String name) throws NoSuchModelNameException
    {
        Model head = this.head.next;
        if (head != null) {
            while ((head.next != this.head) && !(head.name.equals(name))) {
                head = head.next;
            }
            if (!(head.name.equals(name))) throw new NoSuchModelNameException("Такой модели не существует");
            return head.price;
        } else return 0;
    }

    //метод для модификации значения цены модели по её названию
    @Override
    public void setConcretModelPrice(String name, double newPrice) throws NoSuchModelNameException {
        if (newPrice < 0) throw new ModelPriceOutOfBoundsException("Введено неверное значение цены");
        Model head = this.head.next;
        int id = -1;
        for (int i = 0; i < initialSize; i++) {
            if (head.name.equals(name)) {
                id = i;
                break;
            }
            head = head.next;
        }
        if (id == -1) throw new NoSuchModelNameException("Введенной модели не существует");
        else head.price = newPrice;
    }

    //метод, возвращающий массив названий всех моделей
    @Override
    public String[] getModelNamesArray() {
        String[] namesArray = new String[initialSize];
        Model head = this.head.next;
        for (int i = 0; i < namesArray.length; i++) {
            namesArray[i] = head.name;
            head = head.next;
        }
        return namesArray;
    }

    //метод, возвращающий массив значений цен моделей
    @Override
    public double[] getModelPricesArray() {
        double[] pricesArray = new double[initialSize];
        Model head = this.head.next;
        for (int i = 0; i < pricesArray.length; i++) {
            pricesArray[i] = head.price;
            head = head.next;
        }
        return pricesArray;
    }

    //метод для получения размера массива Моделей
    @Override
    public int getModelArraySize() {
        return initialSize;
    }

    @Override
    public void setNewModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        Model p = head;
        do {
            p = p.next;
        } while ((p.next != head) && (!p.name.equals(oldName)));// equals
        if (!(p.name.equals(oldName))) throw new NoSuchModelNameException("Такой модели не существует");
        testDuplicate(newName);
        p.name = newName;
    }

    //Удаление модели
    @Override
    public void removeModel(String name) throws NoSuchModelNameException {
        Model head = this.head;
        do {
            head = head.next;
        } while (head.next != this.head && (!(head.name.equals(name))));
        if (!head.name.equals(name)) throw new NoSuchModelNameException("Такой модели не существует");
        head.prev.next = head.next;
        head.next.prev = head.prev;
        initialSize--;
    }

    //Метод для проверки наличия такой модели в списке
    public void testDuplicate(String newName) throws DuplicateModelNameException {
        Model model = head.next;
        int id = -1;
        while (model != head && !model.name.equals(newName)) {
            model = model.next;
        }
        if (model != head) {
            id = getIndex(model);
        }
        if (id != -1) throw new DuplicateModelNameException("Модель с таким именем уже существует");
    }

    private int getIndex(Model test) {
        Model model = head.next;
        if (model == head)
            return -1;
        else {
            int i = 0;
            while (model != head && model != test) {
                i++;
                model = model.next;
            }
            if (model == head) return -1;
            else return i;
        }
    }

    //Добавление
    @Override
    public void addModel(String name, double price) throws DuplicateModelNameException {
        if (price < 0) throw new ModelPriceOutOfBoundsException("Введено неверное значение цены");
        Model head = new Model(name, price);
        if (this.head.next == this.head) {
            this.head.next = head;
            this.head.prev = head;
            head.prev = this.head;
            head.next = this.head;
        } else {
            testDuplicate(name);
            head.prev = this.head.prev;
            head.next = this.head;
            this.head.prev.next = head;
            this.head.prev = head;
        }
        initialSize++;
    }

    @Override
    public String toString() {
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append("Марка Мотоцикла ");
        stringBuffer.append(mark);
        stringBuffer.append(" ");
        stringBuffer.append("Количество моделей ");
        stringBuffer.append(getModelArraySize());
        stringBuffer.append(" ");
        Model Head = head.next;
        for (int i = 0; i < getModelArraySize(); i++) {
            stringBuffer.append(" ");
            stringBuffer.append(Head.name);
            stringBuffer.append(": ");
            stringBuffer.append(Head.price);
            stringBuffer.append(" ");
            Head = Head.next;
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
        if (obj instanceof Motorcycle) {
            Motorcycle motorcycle = (Motorcycle) obj;
            if (!motorcycle.getMark().equals(getMark())) {
                return false;
            } else if (motorcycle.getModelArraySize() != getModelArraySize()) {
                return false;
            }
            for (int i = 0; i < getModelArraySize(); i++) {
                Model stHead = motorcycle.head.next;
                Model Head = head.next;
                if ((!stHead.name.equals(Head.name))) {
                    return false;
                } else if (stHead.price != Head.price) {
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
        Model head = this.head.next;
        if (mark != null) {
            if (head == this.head) {
                return 0;
            } else {
                while (head != this.head) {
                    result = 31 * result + head.name.hashCode();
                    head = head.next;
                }
                return result;
            }
        } else return 0;
    }

    @Override
    public Object clone() {
        try {
            Motorcycle cloneMoto = (Motorcycle) super.clone();
            cloneMoto.head = new Model();
            cloneMoto.head.prev = cloneMoto.head;
            cloneMoto.head.next = cloneMoto.head;
            Model Head = this.head.next;
            while (Head != head) {
                Model clone = (Model) Head.clone();
                cloneMoto.head.prev.next = clone;
                clone.next = cloneMoto.head;
                clone.prev = cloneMoto.head.prev;
                cloneMoto.head.prev = clone;
                Head = Head.next;
            }
            return cloneMoto;
        } catch (CloneNotSupportedException e) {
            System.out.println(e);
            return null;
        }
    }
}
