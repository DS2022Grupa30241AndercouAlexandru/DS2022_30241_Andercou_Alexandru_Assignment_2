package main.models;

import java.util.Date;

public class State {

    Float totalConsumption;
    Float maxConsumption;
    Date date;
    Integer numberMeasurements;

    public Float getMaxConsumption() {
        return maxConsumption;
    }
    public  State()
    {
        totalConsumption=new Float(0.0f);
        numberMeasurements=0;
        date= new Date();
    }
    public void setMaxConsumption(Float maxConsumption) {
        this.maxConsumption = maxConsumption;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNumberMeasurements() {
        return numberMeasurements;
    }

    public void setNumberMeasurements(Integer numberMeasurements) {
        this.numberMeasurements = numberMeasurements;
    }

    public Float getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(Float totalConsumption) {
        this.totalConsumption = totalConsumption;
    }
}
