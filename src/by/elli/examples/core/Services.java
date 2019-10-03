package by.elli.examples.core;

import by.elli.examples.utils.Utils;

import java.util.Date;

public class Services {

    private String nameCompany;
    private String timeStartAtString;
    private String getTimeEndAtString;
    private int travelTime;

    public Services(String argNameCompany, String argTimeStart, String argTimeEnd) {
        this.nameCompany = argNameCompany;
        this.timeStartAtString = argTimeStart;
        Date timeStart = Utils.convertStringToDate(argTimeStart);
        this.getTimeEndAtString = argTimeEnd;
        Date timeEnd = Utils.convertStringToDate(argTimeEnd);
        this.travelTime = Utils.calcTravelTime(timeStart, timeEnd);
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public String getTimeStartAtString() {
        return timeStartAtString;
    }

    public String getTimeEndAtString() {
        return getTimeEndAtString;
    }

    public int getTravelTime() {
        return travelTime;
    }

    @Override
    public String toString() {
        return nameCompany + " " +
                timeStartAtString + " " +
                getTimeEndAtString;
    }
}
