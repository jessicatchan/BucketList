package ui;

import model.Event;
import model.EventLog;
import model.LogException;

// Represents a console printer for printing the event log to the console
public class ConsolePrinter {

    // EFFECTS: prints the event log, el
    //          throws LogException when printing fails for any reason
    public void printLog(EventLog el) throws LogException {
        for (Event next: el) {
            System.out.println(next.toString() + "\n");
        }
    }
}
