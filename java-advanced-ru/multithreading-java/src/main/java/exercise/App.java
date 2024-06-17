package exercise;

import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    public static Logger getLogger() {
        return LOGGER;
    }

    // BEGIN

    public static Map<String, Integer> getMinMax(int[] array) {

        MaxThread maxThread = new MaxThread(array);
        MinThread minThread = new MinThread(array);
        maxThread.start();
        minThread.start();

        try {
            maxThread.join();
        } catch (InterruptedException e) {
            LOGGER.log(Level.INFO, "Thread " + maxThread.getName() + " was interrupted");
        }
        try {
            minThread.join();
        } catch (InterruptedException e) {
            LOGGER.log(Level.INFO, "Thread " + minThread.getName() + " was interrupted");
        }

        Integer max = maxThread.getMax();
        Integer min = minThread.getMin();

        return Map.of("min", min, "max", max);
    }
    // END
}
