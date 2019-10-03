package by.elli.examples.commands;

import by.elli.examples.core.Model;
import by.elli.examples.core.Services;
import by.elli.examples.utils.Utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Parser {

    private static Model model;

    public static void parse(String read_fileName, Model argModel) throws IOException, URISyntaxException {
        model = argModel;
        ArrayList<String> stringArrayList = readFile(read_fileName);
        initModelWithCorrect(stringArrayList);
    }

    private static void initModelWithCorrect(ArrayList<String> stringArrayList) {
        Utils.showDebugMessage("");
        Utils.showDebugMessage("---Correct. Longer then an hour-------");

        for (int i = 0; i < stringArrayList.size(); i++) {
            String stringService = stringArrayList.get(i);
            Services correctService = Utils.convertStringToService(stringService);
            if (correctService.getTravelTime() > 60) {
                Utils.showDebugMessage(correctService, "Service longer than an hour shall not be included.");
            } else {
                model.getServiceArrayListAll().add(correctService);
            }
        }
        correctModelOfEfficiency(model.getServiceArrayListAll());

        Utils.sortArrayListByTask(model.getServiceArrayListAll(), model);
        Utils.showDebugMessage("All correct is done");
    }

    private static void correctModelOfEfficiency(ArrayList<Services> servicesArrayList) {
        for (int i = 0; i < servicesArrayList.size(); i++) {
            Services targetServicesI = servicesArrayList.get(i);
            boolean isFirstForStart = true;
            boolean isFirstForEnd = true;
            ArrayList<Services> tempStartArray = new ArrayList<>();
            ArrayList<Services> tempEndArray = new ArrayList<>();
            for (int j = i + 1; j < servicesArrayList.size(); j++) {
                Services loopServicesJ = servicesArrayList.get(j);
                if (targetServicesI.getTimeStartAtString().equals(loopServicesJ.getTimeStartAtString()) &&
                        targetServicesI.getTimeEndAtString().equals(loopServicesJ.getTimeEndAtString())) {
                    // start == start && end == end
                    if (loopServicesJ.getNameCompany().equals("Posh")) {
                        Utils.showDebugMessage(targetServicesI, "Service have the same copy.");
                        servicesArrayList.remove(i--);
                    } else {
                        Utils.showDebugMessage(loopServicesJ, "Service have the same copy.");
                        servicesArrayList.remove(j--);
                    }
                } else if (targetServicesI.getTimeStartAtString().equals(loopServicesJ.getTimeStartAtString())) {
                    // start == start
                    if (isFirstForStart) {
                        // put two copy
                        tempStartArray.add(targetServicesI);
                        servicesArrayList.remove(i--);
                        tempStartArray.add(loopServicesJ);
                        servicesArrayList.remove(j-- - 1);
                        isFirstForStart = false;
                    } else {
                        tempStartArray.add(loopServicesJ);
                        servicesArrayList.remove(j--);
                    }
                } else if (targetServicesI.getTimeEndAtString().equals(loopServicesJ.getTimeEndAtString())) {
                    // end == end
                    if (isFirstForEnd) {
                        // put two copy
                        tempEndArray.add(targetServicesI);
                        servicesArrayList.remove(i--);
                        tempEndArray.add(loopServicesJ);
                        servicesArrayList.remove(j-- - 1);
                        isFirstForEnd = false;
                    } else {
                        tempEndArray.add(loopServicesJ);
                        servicesArrayList.remove(j--);
                    }
                }
            }
            Services findEffectiveService;

            findEffectiveService = Utils.getMoreEffectServices(tempStartArray);
            if (findEffectiveService != null) {
                servicesArrayList.add(findEffectiveService);
            }
            findEffectiveService = Utils.getMoreEffectServices(tempEndArray);
            if (findEffectiveService != null) {
                servicesArrayList.add(findEffectiveService);
            }
        }
    }

    private static ArrayList readFile(String read_fileName) throws URISyntaxException, IOException {
        ArrayList<String> stringList = new ArrayList();
        FileInputStream fileInputStream = new FileInputStream(Utils.getFile(read_fileName, ""));
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
        BufferedReader buf = new BufferedReader(inputStreamReader);
        String line;

        while ((line = buf.readLine()) != null) {
            stringList.add(line);
        }

        if (model.isDebug()) {
            System.out.println("-----Parse-File-----------------------");
            for (String stringLine : stringList) {
                System.out.println(stringLine);
            }
            System.out.println("--------------------------------------");
        }
        return stringList;
    }
}