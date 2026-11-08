import javax.swing.table.AbstractTableModel;
import java.util.*;

public class PlanetTableModel extends AbstractTableModel {
    List<PlanetWithDistance> planets;
    
    public PlanetTableModel(List<PlanetWithDistance> planets) {
        super();
        this.planets = planets;
    }
    
    @Override
    public int getRowCount() {
        return planets.size();
    }
    
    @Override
    public int getColumnCount() {
        return 6;
    }
    
    @Override
    public Object getValueAt(int r, int c) {
        PlanetWithDistance p = planets.get(r);
        switch (c) {
            case 0: return p.getPlanetCode();
            case 1: return p.getPlanetName();
            case 2: return String.format("%.2f", p.getMass());
            case 3: return String.format("%.2f", p.getVolume());
            case 4: return p.getHasAtmosphere() ? "Да" : "Нет";
            case 5: return String.format("%.2f", p.getDistanceToStar());
            default: return "";
        }
    }
    
    @Override
    public String getColumnName(int c) {
        switch (c) {
            case 0: return "Код планеты";
            case 1: return "Название";
            case 2: return "Масса";
            case 3: return "Объем";
            case 4: return "Атмосфера";
            case 5: return "Расстояние";
            default: return "";
        }
    }
}