import java.util.Comparator;

public class CompNameAscMassDesc implements Comparator<PlanetWithDistance> {
    public int compare(PlanetWithDistance p1, PlanetWithDistance p2) {
        if (p1.getPlanetName().compareTo(p2.getPlanetName()) < 0) return -1;
        else if (p1.getPlanetName().compareTo(p2.getPlanetName()) > 0) return 1;
        else if (p1.getPlanetMass() < p2.getPlanetMass()) return 1;
        else if (p1.getPlanetMass() == p2.getPlanetMass()) return 0;
        else return -1;
    }
}