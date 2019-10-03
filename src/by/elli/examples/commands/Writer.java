package by.elli.examples.commands;

import by.elli.examples.core.Model;
import by.elli.examples.core.Services;
import by.elli.examples.utils.Utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;

public class Writer {
    public static void write(String write_filename, Model model) throws URISyntaxException, IOException {
        List<Services> serviceArrayListPosh = model.getServiceArrayListPosh();
        List<Services> serviceArrayListGrotty = model.getServiceArrayListGrotty();

        Path file = Utils.createFileWithDir("src/resources", write_filename);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file.toString(), false));

        for (Services servicesPosh : serviceArrayListPosh) {
            writer.write(servicesPosh.toString());
            writer.newLine();
        }
        writer.newLine();

        for (Services servicesGrotty : serviceArrayListGrotty) {
            writer.write(servicesGrotty.toString());
        }
        writer.close();
    }
}
