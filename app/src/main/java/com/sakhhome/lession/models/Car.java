package com.sakhhome.lession.models;

import androidx.annotation.Nullable;

public class Car {
    private String mark;
    private String model;

    public Car(String mark, String model) {
        this.mark = mark;
        this.model = model;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj != null && obj.getClass() == this.getClass()) {
            Car car = (Car) obj;
            return  mark.equals(car.mark);
        }
        return false;
    }
}
