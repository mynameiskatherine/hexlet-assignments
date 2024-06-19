package exercise;

class App {

    public static void main(String[] args) throws InterruptedException {
        // BEGIN
        SafetyList list = new SafetyList();

        ListThread listThread1 = new ListThread(list);
        ListThread listThread2 = new ListThread(list);

        listThread1.start();
        listThread2.start();

        listThread1.join();
        listThread2.join();

        System.out.println(list.getSize());

        // END
    }
}

