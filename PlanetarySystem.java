import java.util.*;

public class PlanetarySystem {
    private final static String FORMAT_STRING = "Планетарная система: %s, %d планет";
    private String name;
    private List<PlanetWithDistance> planets;

    // Конструктор без параметров
    public PlanetarySystem() {
        name = "";
        planets = new ArrayList<>();
    }

    // Конструктор с именем
    public PlanetarySystem(String name) {
        this.name = name;
        planets = new ArrayList<>();
    }

    // Конструктор с именем и списком планет
    public PlanetarySystem(String name, List<PlanetWithDistance> list) {
        this.name = name;
        planets = new ArrayList<>(list);
    }
    
    // Геттеры и сеттеры
    public String getName() { 
        return name; 
    }
    
    public void setName(String name) { 
        this.name = name; 
    }
    
    public List<PlanetWithDistance> getPlanets() { 
        return planets; 
    }
    
    public void setPlanets(List<PlanetWithDistance> planets) { 
        this.planets = planets; 
    }
    
    @Override
    public String toString() {
        return String.format(FORMAT_STRING, name, getPlanetsCount());
    }
    
    public int getPlanetsCount() {
        return planets.size();
    }
    
    // Основные операции
    public boolean addPlanet(PlanetWithDistance planet) {
        if (getPlanet(planet.getPlanetCode()) != null) return false;
        return planets.add(planet);
    }
    
    public boolean delPlanet(String planetCode) {
        return planets.removeIf(p -> p.getPlanetCode().equals(planetCode));
    }
    
    public boolean deleteAboveAvgMass() {
        double avg = avgMass();
        return planets.removeIf(p -> p.getMass() > avg);
    }
    
    public boolean updatePlanetByKey(PlanetWithDistance planet) {
        PlanetWithDistance existing = getPlanet(planet.getPlanetCode());
        if (existing != null) {
            existing.setPlanetName(planet.getPlanetName());
            existing.setMass(planet.getMass());
            existing.setVolume(planet.getVolume());
            existing.setHasAtmosphere(planet.getHasAtmosphere());
            existing.setDistanceToStar(planet.getDistanceToStar());
            return true;
        }
        return false;
    }
    
    public PlanetWithDistance getPlanet(String planetCode) {
        for (PlanetWithDistance p : planets) {
            if (p.getPlanetCode().equals(planetCode)) return p;
        }
        return null;
    }
    
    public double avgMass() {
        if (planets.isEmpty()) return 0;
        double sum = 0;
        for (PlanetWithDistance p : planets) sum += p.getMass();
        return sum / planets.size();
    }
    
    public PlanetarySystem aboveAvgMass() {
        double avg = avgMass();
        PlanetarySystem result = new PlanetarySystem(name + ": масса выше среднего (" + String.format("%.2f", avg) + ")");
        for (PlanetWithDistance p : planets) {
            if (p.getMass() > avg) result.addPlanet(p);
        }
        return result;
    }
    
    public PlanetarySystem betweenMass(double min, double max) {
        PlanetarySystem result = new PlanetarySystem(
            String.format("%s: масса от %.2f до %.2f", name, min, max));
        for (PlanetWithDistance p : planets) {
            if (p.getMass() >= min && p.getMass() <= max) result.addPlanet(p);
        }
        return result;
    }
    
    public PlanetarySystem filterByPlanetName(String filter) {
        PlanetarySystem result = new PlanetarySystem(name + ": фильтр по названию \"" + filter + "\"");
        for (PlanetWithDistance p : planets) {
            if (p.getPlanetName().toLowerCase().contains(filter.toLowerCase())) 
                result.addPlanet(p);
        }
        return result;
    }
    
    public int differentDistancesCount() {
        Set<Double> distances = new TreeSet<>();
        for (PlanetWithDistance p : planets) distances.add(p.getDistanceToStar());
        return distances.size();
    }
    
    public List<TotalRecord> totalCountSumByDistance() {
        if (planets.isEmpty()) return null;
        
        Map<Double, List<PlanetWithDistance>> groups = new TreeMap<>();
        for (PlanetWithDistance p : planets) {
            groups.computeIfAbsent(p.getDistanceToStar(), k -> new ArrayList<>()).add(p);
        }
        
        List<TotalRecord> result = new ArrayList<>();
        for (Map.Entry<Double, List<PlanetWithDistance>> entry : groups.entrySet()) {
            int count = entry.getValue().size();
            double avgMass = 0;
            for (PlanetWithDistance p : entry.getValue()) avgMass += p.getMass();
            avgMass /= count;
            
            String info = String.format("Расстояние: %.2f, планет: %d, ср. масса: %.2f", 
                                      entry.getKey(), count, avgMass);
            result.add(new TotalRecord(info, count));
        }
        return result;
    }
    
    // Методы сортировки
    public PlanetarySystem sortAsc() {
        PlanetarySystem result = new PlanetarySystem(name, planets);
        Collections.sort(result.planets);
        return result;
    }
    
    public PlanetarySystem sortMassAsc() {
        PlanetarySystem result = new PlanetarySystem(name, planets);
        result.planets.sort(Comparator.comparingDouble(PlanetWithDistance::getMass));
        return result;
    }
    
    public PlanetarySystem sortMassDesc() {
        PlanetarySystem result = new PlanetarySystem(name, planets);
        result.planets.sort((p1, p2) -> Double.compare(p2.getMass(), p1.getMass()));
        return result;
    }
    
    public PlanetarySystem sortNameAscMassDesc() {
        PlanetarySystem result = new PlanetarySystem(name, planets);
        result.planets.sort((p1, p2) -> {
            int nameComp = p1.getPlanetName().compareTo(p2.getPlanetName());
            if (nameComp != 0) return nameComp;
            return Double.compare(p2.getMass(), p1.getMass());
        });
        return result;
    }
}