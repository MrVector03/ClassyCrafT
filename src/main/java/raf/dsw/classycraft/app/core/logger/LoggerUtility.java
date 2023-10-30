package raf.dsw.classycraft.app.core.logger;

import raf.dsw.classycraft.app.core.MessageGenerator.Message;

import java.io.*;
import java.time.format.DateTimeFormatter;

public class LoggerUtility {
    protected static String format(Message message) {
        return "[" + message.getType().toString() + "][" +
                message.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "] " +
                message.getText();
    }

    protected static void logInFile(Message message) throws IOException {
        File f = new File("src/main/resources/logs/log.txt");
        FileWriter fw = new FileWriter(f, true);
        try {
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(format(message));
            bw.flush();
            bw.close();
        } catch (Exception e) {
            throw new IOException(e);
        }

    }
}
