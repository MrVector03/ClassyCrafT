package raf.dsw.classycraft.app.core.logger;

public class LoggerFactory {
    public Logger getLogger(String type) {
        if (type.equals("file"))
            return new FileLogger();
        else if (type.equals("console"))
            return new ConsoleLogger();
        else
            throw new IllegalArgumentException("Unsupported logger type: " + type);
    }
}
