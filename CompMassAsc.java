import java.util.Comparator;

public class CompMassAsc implements Comparator<PlanetWithDistance> {
    public int compare(PlanetWithDistance p1, PlanetWithDistance p2) {
        if (p1.getPlanetMass() < p2.getPlanetMass()) return -1;
        else if (p1.getPlanetMass() == p2.getPlanetMass()) return 0;
        else return 1;
    }
}