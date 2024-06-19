package exercise;

// BEGIN
public class ListThread extends Thread {
    private SafetyList list;
    public ListThread(SafetyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 1000; i++) {
            list.add((int) (Math.random() * 1000000 / 31));
            try {
                sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
// END
