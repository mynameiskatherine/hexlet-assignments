package exercise;

import java.util.logging.Level;

// BEGIN
public class MaxThread extends Thread {
    private int[] array;
    private Integer max;

    @Override
    public void run() {
        App.getLogger().log(Level.INFO, "Thread " + currentThread().getName() + " started");
        max = array[0];
        for (int i = 0; i < array.length; i++) {
            max = max < array[i] ? array[i] : max;
        }
        App.getLogger().log(Level.INFO, "Thread " + currentThread().getName() + " finished");
    }

    public MaxThread(int[] array) {
        this.array = array;
    }

    public Integer getMax() {
        return max;
    }
}
// END
