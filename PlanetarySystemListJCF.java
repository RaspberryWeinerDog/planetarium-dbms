import java.util.*;

public class PlanetarySystemListJCF {
    private final static String SYSTEM_FORMAT_STRING = 
        "Планетная система звезды: %s, число планет: %3d";
    
    private String name;
    private List<PlanetWithDistance> planets;
    
    public PlanetarySystemListJCF() {
        name = "";
        planets = new ArrayList<PlanetWithDistance>();
    }
    
    public PlanetarySystemListJCF(String name) {
        this.name = name;
        planets = new ArrayList<PlanetWithDistance>();
    }
    
    public PlanetarySystemListJCF(String name, List<PlanetWithDistance> list) {
        this.name = name;
        planets = new ArrayList<PlanetWithDistance>(list);
    }
    
    public void setListName(String name) { this.name = name; }
    public String getListName() { return name; }
    public List<PlanetWithDistance> getPlanets() { return planets; }
    
    @Override
    public String toString() {
        return String.format(SYSTEM_FORMAT_STRING, name, getPlanetNum());
    }
    
    public int getPlanetNum() {
        return planets.size();
    }
    
    public PlanetWithDistance getPlanet(String planetCode) {
        for (PlanetWithDistance p : planets) {
            if (p.getPlanetCode().equals(planetCode)) {
                return p;
            }
        }
        return null;
    }
    
    public boolean addPlanet(PlanetWithDistance planet) {
        if (getPlanet(planet.getPlanetCode()) != null) return false;
        return planets.add(planet);
    }
    
    public boolean delPlanet(String planetCode) {
        PlanetWithDistance p = getPlanet(planetCode);
        if (p == null) return false;
        return planets.remove(p);
    }
    
    public double avgMass() {
        int num = planets.size();
        if (num == 0) return 0;
        double avg = 0;
        for (PlanetWithDistance p : planets) {
            avg += p.getPlanetMass();
        }
        return avg / num;
    }
    
    public PlanetarySystemListJCF aboveAvgMass() {
        double avg = avgMass();
        PlanetarySystemListJCF system = new PlanetarySystemListJCF(String.format(
            "%s\nПланеты, у которых масса выше среднего %.2f: ", name, avg));
        for (PlanetWithDistance p : planets) {
            if (p.getPlanetMass() > avg) system.addPlanet(p);
        }
        return system;
    }
    
    public PlanetarySystemListJCF betweenMass(double m1, double m2) {
        PlanetarySystemListJCF system = new PlanetarySystemListJCF(String.format(
            "%s\nПланеты, у которых масса в диапазоне от %.2f до %.2f: ", name, m1, m2));
        Iterator<PlanetWithDistance> iter = planets.iterator();
        while (iter.hasNext()) {
            PlanetWithDistance p = iter.next();
            if ((p.getPlanetMass() >= m1) && (p.getPlanetMass() <= m2)) {
                system.addPlanet(p);
            }
        }
        return system;
    }
    
    public PlanetarySystemListJCF sortAsc() {
        PlanetarySystemListJCF system = new PlanetarySystemListJCF(String.format(
            "%s\nСписок, отсортированный по возрастанию в естественном порядке: ", name), planets);
        Collections.sort(system.planets);
        return system;
    }
    
    public PlanetarySystemListJCF sortMassDesc() {
        PlanetarySystemListJCF system = new PlanetarySystemListJCF(String.format(
            "%s\nСписок, отсортированный по убыванию массы: ", name), planets);
        Collections.sort(system.planets, new CompMassDesc());
        return system;
    }
    
    public PlanetarySystemListJCF sortMassAsc() {
        PlanetarySystemListJCF system = new PlanetarySystemListJCF(String.format(
            "%s\nСписок, отсортированный по возрастанию массы: ", name), planets);
        Collections.sort(system.planets, new CompMassAsc());
        return system;
    }
    
    public PlanetarySystemListJCF sortNameAscMassDesc() {
        PlanetarySystemListJCF system = new PlanetarySystemListJCF(String.format(
            "%s\nСписок, отсортированный по возрастанию имени и убыванию массы: ", name), planets);
        Collections.sort(system.planets, new CompNameAscMassDesc());
        return system;
    }
    
    public void putPlanetList() {
        System.out.println(name);
        int i = 1;
        for (PlanetWithDistance p : planets) {
            System.out.printf(" %-7d %s\n", i, p);
            i++;
        }
    }
    
    public boolean updatePlanetByKey(PlanetWithDistance planet) {
        PlanetWithDistance p = getPlanet(planet.getPlanetCode());
        if (p != null) {
            p.setPlanetName(planet.getPlanetName());
            p.setPlanetMass(planet.getPlanetMass());
            p.setPlanetVolume(planet.getPlanetVolume());
            p.setIsAtmosphere(planet.getIsAtmosphere());
            p.setStarDistance(planet.getStarDistance());
            return true;
        }
        return false;
    }
    
    public boolean deleteAboveAvgMass() {
        double avg = avgMass();
        List<PlanetWithDistance> toRemove = new ArrayList<>();
        for (PlanetWithDistance p : planets) {
            if (p.getPlanetMass() > avg) {
                toRemove.add(p);
            }
        }
        return planets.removeAll(toRemove);
    }
    
    public PlanetarySystemListJCF filterByPlanetName(String filter) {
        PlanetarySystemListJCF system = new PlanetarySystemListJCF(String.format(
            "%s\nСписок планет с названием, начинающимся на %s:", name, filter));
        if (filter != null && !filter.equals("")) {
            filter = filter.toLowerCase();
            for (PlanetWithDistance planet : planets)
                if (planet.getPlanetName().toLowerCase().startsWith(filter))
                    system.addPlanet(planet);
        }
        return system;
    }
    
    public PlanetarySystemListJCF sort(Comparator<PlanetWithDistance> comp, String comment) {
        PlanetarySystemListJCF system = new PlanetarySystemListJCF(String.format(
            "%s\n%s", name, comment), planets);
        Collections.sort(system.planets, comp);
        return system;
    }
    
    public List<TotalRecord> totalCountSumByDistance() {
        int n = planets.size();
        if (n == 0) return null;
        
        List<PlanetWithDistance> planetsTemp = new ArrayList<PlanetWithDistance>();
        planetsTemp.addAll(planets);
        
        SortedSet<Double> distanceS = new TreeSet<Double>();
        for (PlanetWithDistance p : planets) {
            distanceS.add(p.getStarDistance());
        }
        
        List<Double> distanceL = new ArrayList<Double>(distanceS);
        int m = distanceL.size();
        
        List<TotalRecord> totRecList = new ArrayList<TotalRecord>();
        
        for (int i = 0; i < m; i++) {
            double distance = distanceL.get(i);
            int count = 0;
            double sum = 0;
            
            Iterator<PlanetWithDistance> iter = planetsTemp.iterator();
            while (iter.hasNext()) {
                PlanetWithDistance p = iter.next();
                if (distance == p.getStarDistance()) {
                    count++;
                    sum += p.getPlanetMass();
                    iter.remove();
                }
            }
            
            totRecList.add(new TotalRecord(distance, count, sum / count));
        }
        
        return totRecList;
    }
}