package ui;

import model.Event;
import model.EventLog;

// Represents a console printer for printing the event log to the console
public class ConsolePrinter {

    // EFFECTS: prints the event log, el
    public void printLog(EventLog el) {
        for (Event next: el) {
            System.out.println(next.toString() + "\n");
        }
    }
}
