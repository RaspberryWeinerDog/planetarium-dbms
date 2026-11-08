public class TotalRecord {
    double distance;
    int num1;
    double num2;

    public TotalRecord(double distance, int num1, double num2) {
        this.distance = distance;
        this.num1 = num1;
        this.num2 = num2;
    }

    public String toString() {
        return String.format("| %10.2f | %5d | %3.2f |", distance, num1, num2);
    }
}