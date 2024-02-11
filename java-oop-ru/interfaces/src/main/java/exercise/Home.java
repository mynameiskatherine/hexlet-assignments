package exercise;

// BEGIN
public interface Home {
    double getArea();
    default int compareTo(Home another) {
        double diff = this.getArea() - another.getArea();
        if (diff == 0) {
            return 0;
        } else {
            return diff > 0 ? 1 : -1;
        }
    }
}
// END
