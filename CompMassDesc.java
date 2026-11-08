import java.util.Comparator;

public class CompMassDesc implements Comparator<PlanetWithDistance> {
    public int compare(PlanetWithDistance p1, PlanetWithDistance p2) {
        return Double.compare(p2.getMass(), p1.getMass());
    }
}