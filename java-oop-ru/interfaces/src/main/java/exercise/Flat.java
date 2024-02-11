package exercise;

// BEGIN
public class Flat implements Home {
    private double area;
    private double balconyArea;
    private int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "Квартира площадью " + this.getArea() + " метров на " + floor + " этаже";

    }

    @Override
    public double getArea() {
        return area + balconyArea;
    }

//    @Override
//    public int compareTo(Home another) {
//        int diff = this.getArea() - another.getArea();
//        if (diff == 0) {
//            return 0;
//        } else {
//            return diff > 0 ? 1 : -1;
//        }
//    }

}
// END
