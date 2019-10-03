package by.elli.examples.utils;

import by.elli.examples.core.Main;
import by.elli.examples.core.Model;
import by.elli.examples.core.Services;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Utils {

    private static SimpleDateFormat ft = new SimpleDateFormat("HH:mm");

    /**
     * Get file by name.
     *
     * @param fileName file name
     * @param dirName  directory name
     * @return {@link File} file by name
     * @throws URISyntaxException exception will be throw if string con't be parse to path
     */
    public static File getFile(String fileName, String dirName) throws URISyntaxException {
        final URL resource = Main.class.getResource(Main.class.getSimpleName() + ".class");
        final File parentFolder = new File(resource.toURI()).getParentFile().getParentFile();
        final File srcFolder = parentFolder.getParentFile().getParentFile().getParentFile();
        return new File(srcFolder, "resources/" + dirName + "/" + fileName);
    }

    public static Path createFileWithDir(String directory, String filename) {
        File dir = new File(directory);
        if (!dir.exists()) dir.mkdirs();
        return Paths.get(directory + File.separatorChar + filename);
    }

    public static Model initEmptyModel() {
        Model model = new Model();
        model.setDebug(false);
        return model;
    }

    public static Services convertStringToService(String argStringService) {
        String[] splitList = argStringService.split(" ");
        Services newService = new Services(splitList[0], splitList[1], splitList[2]);
        return newService;
    }

    public static Long convertStringToLong(String argTime) {
        Long returnLongValue = 0L;
        try {
            returnLongValue = ft.parse(argTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnLongValue;
    }

    public static Date convertStringToDate(String argTime) {
        SimpleDateFormat ft = new SimpleDateFormat("HH:mm");
        Date returnDateValue = null;
        try {
            returnDateValue = ft.parse(argTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnDateValue;
    }

    public static int calcTravelTime(Date timeStart, Date timeEnd) {
        Long longDiff = timeEnd.getTime() - timeStart.getTime();
        int calcMinutes = (int) (longDiff / 1000 / 60);
        return calcMinutes;
    }

    public static void showDebugMessage(Services services, String messageString) {
        if (Model.isDebug()) {
            System.out.println(services.getNameCompany() + " " +
                    services.getTimeStartAtString() + " " +
                    services.getTimeEndAtString() + " " +
                    "travel time (" + services.getTravelTime() + "). " +
                    messageString);
        }
    }

    public static void showDebugMessage(String messageString) {
        if (Model.isDebug()) {
            System.out.println(messageString);
            ;
        }
    }

    public static Services getMoreEffectServices(ArrayList<Services> arrayService) {
        if (arrayService.size() == 0) {
            return null;
        }
        Services moreEffectiveServices = arrayService.get(0);
        if (arrayService.size() > 1) {
            for (int i = 1; i < arrayService.size(); i++) {
                if (moreEffectiveServices.getTravelTime() > arrayService.get(i).getTravelTime()) {
                    moreEffectiveServices = arrayService.get(i);
                }
            }
        }
        return moreEffectiveServices;
    }

    public static void sortArrayListByTask(ArrayList<Services> servicesArrayList, Model model) {
        ArrayList<Services> grotty = new ArrayList<>();
        ArrayList<Services> posh = new ArrayList<>();
        for (Services services : servicesArrayList) {
            if (services.getNameCompany().equals("Posh")) {
                posh.add(services);
            } else {
                grotty.add(services);
            }
        }

        timeSortingAscendingByStart(posh);
        timeSortingAscendingByStart(grotty);

        model.setServiceArrayListPosh(posh);
        model.setServiceArrayListGrotty(grotty);
    }

    private static void timeSortingAscendingByStart(ArrayList<Services> servicesArrayList) {
        Collections.sort(servicesArrayList, new Comparator<Services>() {
            @Override
            public int compare(Services o1, Services o2) {
                return (int) (Utils.convertStringToLong(o1.getTimeStartAtString()) - Utils.convertStringToLong(o2.getTimeStartAtString()));
            }
        });
    }
}
