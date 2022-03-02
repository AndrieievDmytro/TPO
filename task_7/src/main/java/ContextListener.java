import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebListener
public class ContextListener implements ServletContextListener {
    private static final Logger logger;
    private static final List<Level> levels;
    private static int current_level = 0;

    static {
        logger = Logger.getLogger(ServletContextListener.class.getName());
        levels = Arrays.asList(Level.SEVERE, Level.OFF);
    }

    protected static void loggerLevel(){
        Level level = levels.get(current_level);
        current_level = (current_level + 1) % levels.size();
        logger.setLevel(level);
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.severe("Context initialized " + sce.toString());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.severe("Context destroyed " + sce.toString());
    }
}
