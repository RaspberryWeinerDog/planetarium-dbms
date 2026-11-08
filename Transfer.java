import java.util.*;

public class Transfer {
    
    public static String unificationString(String line) {
        if (line == null) return null;
        line = line.trim();
        if (line.equals("")) return "";
        String[] words = line.split("\\s+");
        StringBuilder result = new StringBuilder();
        if (words[0].length() > 0) {
            result.append(words[0].substring(0, 1).toUpperCase());
            result.append(words[0].substring(1).toLowerCase());
        }
        for (int i = 1; i < words.length; i++) {
            result.append(" ");
            result.append(words[i].toLowerCase());
        }
        return result.toString();
    }

    public static PlanetWithDistance lineToPlanet(String line, int num) {
        String[] words = line.split(",");
        if (words.length != num) return null;
        try {
            String planetCode = unificationString(words[0].trim());
            String planetName = unificationString(words[1].trim());
            double planetMass = Double.parseDouble(words[2].trim());
            double planetVolume = Double.parseDouble(words[3].trim());
            boolean isAtmosphere = Boolean.parseBoolean(words[4].trim());
            double starDistance = Double.parseDouble(words[5].trim());
            
            return new PlanetWithDistance(planetCode, planetName, planetMass, 
                                         planetVolume, isAtmosphere, starDistance);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static List<PlanetWithDistance> stringsToPlanets(List<String> lines) {
        if (lines == null || lines.isEmpty()) return null;
        List<PlanetWithDistance> planets = new ArrayList<PlanetWithDistance>();
        for (int i = 0; i < lines.size(); i++) {
            PlanetWithDistance planet = lineToPlanet(lines.get(i), 6);
            if (planet == null) return null;
            planets.add(planet);
        }
        return planets;
    }

    public static List<String> planetsToStrings(List<PlanetWithDistance> planets) {
        if (planets == null || planets.isEmpty()) return null;
        List<String> lines = new ArrayList<String>();
        for (PlanetWithDistance planet : planets)
        lines.add(String.format("%s, %s, %.2f, %.2f, %b, %.2f",
                planet.getPlanetCode(),
                planet.getPlanetName(),
                planet.getPlanetMass(),
                planet.getPlanetVolume(),
                planet.getIsAtmosphere(),
                planet.getStarDistance()));
        return lines;
    }
}