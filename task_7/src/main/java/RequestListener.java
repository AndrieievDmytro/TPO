import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebListener
public class RequestListener implements ServletRequestListener {

    private static final Logger logger;
    private static final Random random;
    private static final List<Level> levels;
    private static int current_level = 0;

    static {
        logger = Logger.getLogger(ServletContextListener.class.getName());
        random = new Random();
        levels = Arrays.asList(Level.SEVERE, Level.OFF);
    }

    protected static void loggerLevel(){
        Level level = levels.get(current_level);
        current_level = (current_level + 1) % levels.size();
        logger.setLevel(level);
    }

    public void requestInitialized(ServletRequestEvent srv){
        loggerLevel();
        logger.severe("Request initialized " + srv.toString());
    }
    public void requestDestroyed(ServletRequestEvent srv){
        logger.severe("Request destroyed " + srv.toString());
    }
}
