import java.util.*;

public class Global {
    static PlanetarySystem table;
    static List<PlanetWithDistance> planets;
    static PlanetTableModel tableModel;
    
    public static void updateJTable(List<PlanetWithDistance> list) {
        planets.clear();
        planets.addAll(list);
        tableModel.fireTableDataChanged();
    }
}