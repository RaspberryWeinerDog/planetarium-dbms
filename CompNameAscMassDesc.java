import java.util.Comparator;

public class CompNameAscMassDesc implements Comparator<PlanetWithDistance> {
    public int compare(PlanetWithDistance p1, PlanetWithDistance p2) {
        int nameComp = p1.getPlanetName().compareTo(p2.getPlanetName());
        if (nameComp != 0) return nameComp;
        return Double.compare(p2.getMass(), p1.getMass());
    }
}