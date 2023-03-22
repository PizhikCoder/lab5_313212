package org.example.core.printers;

import org.example.interfaces.IPrinter;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Contains logic for CLI output.
 */
public class CLIPrinter implements IPrinter {

    /**
     * Outputs all messages to the console
     * @param data message
     */
    @Override
    public void print(String data) {
        if(!data.isBlank()){
            System.out.println("----------------------------------------------------------------");
            System.out.print(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss--dd.MM.yyyy")) + ":  " + data);
            System.out.println();
            System.out.println("----------------------------------------------------------------");
        }
    }
}
