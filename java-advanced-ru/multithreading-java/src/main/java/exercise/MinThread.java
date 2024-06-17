package exercise;

import java.util.logging.Level;

// BEGIN
public class MinThread extends Thread {

    private int[] array;
    private Integer min;

    @Override
    public void run() {
        App.getLogger().log(Level.INFO, "Thread " + currentThread().getName() + " started");
        min = array[0];
        for (int i = 0; i < array.length; i++) {
            min = min > array[i] ? array[i] : min;
        }
        App.getLogger().log(Level.INFO, "Thread " + currentThread().getName() + " finished");
    }

    public MinThread(int[] array) {
        this.array = array;
    }
    public Integer getMin() {
        return min;
    }
}
// END
