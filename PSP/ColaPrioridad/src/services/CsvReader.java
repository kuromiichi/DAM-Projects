package src.services;

import javafx.util.Pair;
import src.models.Process;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class CsvReader {
    public Pair<Integer, Process[]> read(String path) {
        int timeSlice = 0;
        ArrayList<Process> processes = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            timeSlice = Integer.parseInt(reader.readLine());
            do {
                String line = reader.readLine();
                if (line == null) break;
                String[] values = line.split(";");
                processes.add(new Process(
                        Integer.parseInt(values[0]),
                        Integer.parseInt(values[1]),
                        Integer.parseInt(values[2]),
                        Integer.parseInt(values[3])
                ));
            } while (true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new Pair<>(timeSlice, processes.toArray(new Process[0]));
    }
}
