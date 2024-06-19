package exercise;

class SafetyList {
    // BEGIN
    private int listFulfilmentSize = 0;
    private int[] safetyList = new int[10];
    public synchronized void add(int value) {
        if (safetyList.length - listFulfilmentSize == 0) {
            int[] oldList = new int[safetyList.length];
            System.arraycopy(safetyList, 0, oldList, 0, safetyList.length);
            safetyList = new int[(int) (oldList.length * 1.5 + 1)];
            System.arraycopy(oldList, 0, safetyList, 0, oldList.length);
        }
        safetyList[listFulfilmentSize] = value;
        listFulfilmentSize = listFulfilmentSize + 1;
    }
    public int get(int index) {
        return safetyList[index];
    }
    public int getSize() {
        return listFulfilmentSize;
    }

    // END
}
