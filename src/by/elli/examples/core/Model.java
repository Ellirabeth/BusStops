package by.elli.examples.core;

import java.util.ArrayList;

public class Model {
    private static boolean debug;
    private ArrayList<Services> serviceArrayListAll = new ArrayList<>();
    public ArrayList<Services> serviceArrayListPosh = new ArrayList<>();
    public ArrayList<Services> serviceArrayListGrotty = new ArrayList<>();


    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean value) {
        debug = value;
    }

    public ArrayList<Services> getServiceArrayListAll() {
        return serviceArrayListAll;
    }

    public ArrayList<Services> getServiceArrayListPosh() {
        return serviceArrayListPosh;
    }

    public void setServiceArrayListPosh(ArrayList<Services> argServiceArrayListPosh) {
        serviceArrayListPosh = argServiceArrayListPosh;
    }

    public ArrayList<Services> getServiceArrayListGrotty() {
        return serviceArrayListGrotty;
    }

    public void setServiceArrayListGrotty(ArrayList<Services> argServiceArrayListGrotty) {
        serviceArrayListGrotty = argServiceArrayListGrotty;
    }
}
