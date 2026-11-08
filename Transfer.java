import java.util.*;

public class Transfer {
    public static List<PlanetWithDistance> stringsToPlanets(List<String> lines) {
        if (lines == null || lines.isEmpty()) return null;
        List<PlanetWithDistance> planets = new ArrayList<>();
        
        for (String line : lines) {
            String[] words = line.split(",");
            if (words.length != 6) return null;
            
            try {
                String code = words[0].trim();
                String name = words[1].trim();
                double mass = Double.parseDouble(words[2].trim());
                double volume = Double.parseDouble(words[3].trim());
                boolean atmosphere = Boolean.parseBoolean(words[4].trim());
                double distance = Double.parseDouble(words[5].trim());
                
                planets.add(new PlanetWithDistance(code, name, mass, volume, atmosphere, distance));
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return planets;
    }
    
    public static List<String> planetsToStrings(List<PlanetWithDistance> planets) {
        if (planets == null || planets.isEmpty()) return null;
        List<String> lines = new ArrayList<>();
        
        for (PlanetWithDistance p : planets) {
            lines.add(String.format("%s, %s, %.2f, %.2f, %b, %.2f",
                    p.getPlanetCode(),
                    p.getPlanetName(),
                    p.getMass(),
                    p.getVolume(),
                    p.getHasAtmosphere(),
                    p.getDistanceToStar()));
        }
        return lines;
    }
}