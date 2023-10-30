package raf.dsw.classycraft.app.core;

import raf.dsw.classycraft.app.core.MessageGenerator.MessageGenerator;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.ClassyRepositoryImplementation;
import raf.dsw.classycraft.app.core.logger.ConsoleLogger;
import raf.dsw.classycraft.app.core.logger.FileLogger;
import raf.dsw.classycraft.app.core.logger.LoggerFactory;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

public class ApplicationFramework {

    private static ApplicationFramework instance;
    private ClassyRepositoryImplementation classyRepositoryImplementation;
    private MessageGenerator messageGenerator;

    private ConsoleLogger consoleLogger;
    private FileLogger fileLogger;

    //buduca polja za model celog projekta

    private ApplicationFramework(){
        classyRepositoryImplementation = new ClassyRepositoryImplementation();
        messageGenerator = new MessageGenerator();

        LoggerFactory loggerFactory = new LoggerFactory();
        consoleLogger = (ConsoleLogger) loggerFactory.getLogger("console");
        fileLogger = (FileLogger) loggerFactory.getLogger("file");

        messageGenerator.addSubscriber(consoleLogger);
        messageGenerator.addSubscriber(fileLogger);

        messageGenerator.addSubscriber(MainFrame.getInstance());
    }

    public void initialize(){
        MainFrame.getInstance().setVisible(true);
    }

    public static ApplicationFramework getInstance(){
        if(instance==null){
            instance = new ApplicationFramework();
        }
        return instance;
    }

    public ClassyRepositoryImplementation getClassyRepositoryImplementation() {
        return classyRepositoryImplementation;
    }

    public MessageGenerator getMessageGenerator() {
        return messageGenerator;
    }
}
