public class PlanetWithDistance extends Planet {
    private final static String PlanetWithDistance_FORMAT_STRING = 
        "%s, расстояние до звезды: %10.2f св. лет";
    private double starDistance;

    public PlanetWithDistance() {
        super();
        starDistance = 0.0;
    }

    public PlanetWithDistance(String planetCode, String planetName, double planetMass,
                              double planetVolume, boolean isAtmosphere, double starDistance) {
        super(planetCode, planetName, planetMass, planetVolume, isAtmosphere);
        this.starDistance = starDistance;
    }

    public PlanetWithDistance(PlanetWithDistance ob) {
        super(ob);
        starDistance = ob.starDistance;
    }

    public double getStarDistance() { return starDistance; }
    public void setStarDistance(double starDistance) { this.starDistance = starDistance; }

    @Override
    public String toString() {
        return String.format(PlanetWithDistance_FORMAT_STRING, super.toString(), starDistance);
    }

    @Override
    public boolean equals(Object ob) {
        if (!super.equals(ob)) return false;
        PlanetWithDistance pwd = (PlanetWithDistance) ob;
        return starDistance == pwd.starDistance;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + 23 * Double.valueOf(starDistance).hashCode();
    }

    @Override
    public int compareTo(Planet planet) {
        int comp = super.compareTo(planet);
        if (comp < 0) return -1;
        PlanetWithDistance pwd = (PlanetWithDistance) planet;
        if ((comp == 0) && (starDistance < pwd.starDistance)) return -1;
        if ((comp == 0) && (starDistance == pwd.starDistance)) return 0;
        return 1;
    }
}