public class Planet implements Comparable<Planet> {
    private final static String PLANET_FORMAT_STRING = "Шифр: %s, Название: %s, Масса: %.2f, Объём: %.2f, Наличие атмосферы: %b";
    private String planetCode;
    private String planetName;
    private double planetMass;
    private double planetVolume;
    private boolean isAtmosphere;

    public Planet() {
        this.planetCode = "Неизвестно";
        this.planetName = "Неизвестно";
        this.planetMass = 0.0;
        this.planetVolume = 0.0;
        this.isAtmosphere = false;
    }

    public Planet(String planetCode, String planetName, double planetMass, double planetVolume, boolean isAtmosphere) {
        this.planetCode = planetCode;
        this.planetName = planetName;
        this.planetMass = planetMass;
        this.planetVolume = planetVolume;
        this.isAtmosphere = isAtmosphere;
    }

    public Planet(Planet planetReference) {
        planetCode = planetReference.planetCode;
        planetName = planetReference.planetName;
        planetMass = planetReference.planetMass;
        planetVolume = planetReference.planetVolume;
        isAtmosphere = planetReference.isAtmosphere;
    }

    public String getPlanetCode() { return planetCode; }
    public String getPlanetName() { return planetName; }
    public double getPlanetMass() { return planetMass; }
    public double getPlanetVolume() { return planetVolume; }
    public boolean getIsAtmosphere() { return isAtmosphere; }

    public void setPlanetCode(String newPlanetCode) { planetCode = newPlanetCode; }
    public void setPlanetName(String newPlanetName) { planetName = newPlanetName; }
    public void setPlanetMass(double newPlanetMass) { planetMass = newPlanetMass; }
    public void setPlanetVolume(double newPlanetVolume) { planetVolume = newPlanetVolume; }
    public void setIsAtmosphere(boolean newIsAtmosphere) { isAtmosphere = newIsAtmosphere; }

    @Override
    public String toString() {
        return String.format(PLANET_FORMAT_STRING, planetCode, planetName, planetMass, planetVolume, isAtmosphere);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (getClass() != obj.getClass()) return false;

        Planet other = (Planet) obj;
        return planetCode.equals(other.planetCode) &&
               planetName.equals(other.planetName) &&
               planetMass == other.planetMass &&
               planetVolume == other.planetVolume &&
               isAtmosphere == other.isAtmosphere;
    }

    @Override
    public int hashCode() {
        return 7 * planetCode.hashCode() +
               11 * planetName.hashCode() +
               13 * Double.valueOf(planetMass).hashCode() +
               17 * Double.valueOf(planetVolume).hashCode() +
               19 * Boolean.valueOf(isAtmosphere).hashCode();
    }

    @Override
    public int compareTo(Planet pl) {
        if (planetCode.compareTo(pl.planetCode) < 0) return -1;
        if ((planetCode.compareTo(pl.planetCode) == 0) &&
            (planetName.compareTo(pl.planetName) < 0)) return -1;
        if ((planetCode.compareTo(pl.planetCode) == 0) &&
            (planetName.compareTo(pl.planetName) == 0) &&
            (planetMass < pl.planetMass)) return -1;
        if ((planetCode.compareTo(pl.planetCode) == 0) &&
            (planetName.compareTo(pl.planetName) == 0) &&
            (planetMass == pl.planetMass) &&
            (planetVolume < pl.planetVolume)) return -1;
        if ((planetCode.compareTo(pl.planetCode) == 0) &&
            (planetName.compareTo(pl.planetName) == 0) &&
            (planetMass == pl.planetMass) &&
            (planetVolume == pl.planetVolume) &&
            (!isAtmosphere && pl.isAtmosphere)) return -1;
        if ((planetCode.compareTo(pl.planetCode) == 0) &&
            (planetName.compareTo(pl.planetName) == 0) &&
            (planetMass == pl.planetMass) &&
            (planetVolume == pl.planetVolume) &&
            (isAtmosphere == pl.isAtmosphere)) return 0;
        return 1;
    }
}