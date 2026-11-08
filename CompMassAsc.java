import java.util.Comparator;

public class CompMassAsc implements Comparator<PlanetWithDistance> {
    public int compare(PlanetWithDistance p1, PlanetWithDistance p2) {
        return Double.compare(p1.getMass(), p2.getMass());
    }
}