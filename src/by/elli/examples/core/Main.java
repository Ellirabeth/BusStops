package by.elli.examples.core;

import by.elli.examples.commands.Parser;
import by.elli.examples.commands.Writer;
import by.elli.examples.utils.Utils;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    static String READ_FILENAME = "input.txt";
    static String WRITE_FILENAME = "output.txt";
    static Model model;

    public static void main(String[] args) throws URISyntaxException, IOException {
        Utils.showDebugMessage("Start program");

        model = Utils.initEmptyModel();
        model.setDebug(true);

        Parser.parse(READ_FILENAME, model);
        Writer.write(WRITE_FILENAME, model);

        Utils.showDebugMessage("End program");
    }
}
