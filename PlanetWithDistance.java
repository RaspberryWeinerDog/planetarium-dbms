public class PlanetWithDistance implements Comparable<PlanetWithDistance> {
    private final static String FORMAT_STRING = "%10s | %15s | %8.2f | %8.2f | %10s | %8.2f |";
    
    // Ключевое поле
    private String planetCode;
    
    // Другие поля
    private String planetName;
    private double mass;
    private double volume;
    private boolean hasAtmosphere;
    private double distanceToStar;
    
    public PlanetWithDistance() {
        planetCode = "";
        planetName = "";
        mass = 0.0;
        volume = 0.0;
        hasAtmosphere = false;
        distanceToStar = 0.0;
    }
    
    public PlanetWithDistance(String code, String name, double mass, 
                             double volume, boolean atmosphere, double distance) {
        this.planetCode = code;
        this.planetName = name;
        this.mass = mass;
        this.volume = volume;
        this.hasAtmosphere = atmosphere;
        this.distanceToStar = distance;
    }
    
    // Геттеры
    public String getPlanetCode() { return planetCode; }
    public String getPlanetName() { return planetName; }
    public double getMass() { return mass; }
    public double getVolume() { return volume; }
    public boolean getHasAtmosphere() { return hasAtmosphere; }
    public double getDistanceToStar() { return distanceToStar; }
    
    // Сеттеры
    public void setPlanetCode(String code) { this.planetCode = code; }
    public void setPlanetName(String name) { this.planetName = name; }
    public void setMass(double mass) { this.mass = mass; }
    public void setVolume(double volume) { this.volume = volume; }
    public void setHasAtmosphere(boolean atmosphere) { this.hasAtmosphere = atmosphere; }
    public void setDistanceToStar(double distance) { this.distanceToStar = distance; }
    
    @Override
    public String toString() {
        return String.format(FORMAT_STRING, planetCode, planetName, mass, 
                           volume, hasAtmosphere ? "Да" : "Нет", distanceToStar);
    }
    
    @Override
    public boolean equals(Object ob) {
        if (ob == this) return true;
        if (ob == null) return false;
        if (getClass() != ob.getClass()) return false;
        PlanetWithDistance pwd = (PlanetWithDistance) ob;
        return planetCode.equals(pwd.planetCode);
    }
    
    @Override
    public int hashCode() {
        return 7 * planetCode.hashCode() +
               13 * planetName.hashCode() +
               17 * Double.valueOf(mass).hashCode();
    }
    
    @Override
    public int compareTo(PlanetWithDistance p) {
        return planetCode.compareTo(p.planetCode);
    }
}