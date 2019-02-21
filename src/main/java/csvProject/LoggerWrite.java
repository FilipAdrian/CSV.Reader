package csvProject;



import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class LoggerWrite {
   private static Logger logger = Logger.getLogger ("statistics.log");
   private static FileHandler fileHandler ;
    public static Logger writeLogFile(){
        try {
                fileHandler = new FileHandler ("./data/statistics.log");
                logger.addHandler (fileHandler);
        } catch (Exception e) {
            e.printStackTrace ( );
        }
        return logger;
    }
}
