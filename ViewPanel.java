import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class ViewPanel extends JPanel implements ActionListener {
    // Панель просмотра
    JTextField num1; // левая граница диапазона
    JTextField num2;  // правая граница диапазона
    JTextField tf; // фильтр

    public ViewPanel() {
        setLayout(new GridLayout(7, 2, 2, 2));
        JButton but1 = new JButton("Средняя масса");
        JButton but2 = new JButton("Масса выше среднего");
        JButton but3 = new JButton("Масса в диапазоне");
        JButton but4 = new JButton("Итог: статистика по расстояниям");
        JButton but5 = new JButton("Число различных расстояний");
        JButton but6 = new JButton("Применить фильтр по названию");
        JButton but7 = new JButton("Сортировать по коду");
        JButton but8 = new JButton("Сортировать по массе (возр.)");
        JButton but9 = new JButton("Сортировать по массе (убыв.)");
        JButton but10 = new JButton("Сортировать по названию и массе");
        JButton but11 = new JButton("Вывести все");
        
        tf = new JTextField("");
        JLabel l1 = new JLabel("Введите фильтр для названия планеты", JLabel.CENTER);
        JLabel l2 = new JLabel("");
        JLabel l3 = new JLabel("Укажите границы диапазона массы", JLabel.CENTER);
        
        but1.setActionCommand("Avg");
        but2.setActionCommand("AboveAvg");
        but3.setActionCommand("Between");
        but4.setActionCommand("Total1");
        but5.setActionCommand("Total2");
        but6.setActionCommand("Filter");
        but7.setActionCommand("Sort1");
        but8.setActionCommand("Sort2");
        but9.setActionCommand("Sort3");
        but10.setActionCommand("Sort4");
        but11.setActionCommand("All");
        
        num1 = new JTextField("", 10);
        num2 = new JTextField("", 10);
        JPanel p1 = new JPanel();
        p1.add(num1);
        p1.add(num2);
        
        // Располагаем компоненты в GridLayout (7 строк, 2 столбца)
        add(l1);
        add(l2);
        add(tf);
        add(but6);
        add(but1);
        add(but11);
        add(l3);
        add(but2);
        add(p1);
        add(but3);
        add(but7);
        add(but8);
        add(but9);
        add(but10);
        add(but4);
        add(but5);

        // Добавляем обработчики событий для всех кнопок
        but1.addActionListener(this);
        but2.addActionListener(this);
        but3.addActionListener(this);
        but4.addActionListener(this);
        but5.addActionListener(this);
        but6.addActionListener(this);
        but7.addActionListener(this);
        but8.addActionListener(this);
        but9.addActionListener(this);
        but10.addActionListener(this);
        but11.addActionListener(this);
    }

    private void showAvg() {
        MainFrame.MSG.setText("   Запрос на выборку: выдать среднюю массу планет");
        JOptionPane.showMessageDialog(MainFrame.frame,
                String.format("Средняя масса планет: %6.2f", Global.table.avgMass()));
    }

    private void showAboveAvg() {
        MainFrame.MSG.setText("   Запрос на выборку: выдать записи с массой выше среднего");
        Global.updateJTable(Global.table.aboveAvgMass().getPlanets());
    }

    private void showBetween() {
        double n, m;
        try {
            n = Double.parseDouble(num1.getText());
            m = Double.parseDouble(num2.getText());
        } catch (NumberFormatException e) {
            MainFrame.MSG.setText("   Задайте правильно границы диапазона");
            return;
        }
        MainFrame.MSG.setText(String.format(
                "   Запрос на выборку: выдать записи с массой в диапазоне [%.2f , %.2f]", n, m));
        Global.updateJTable(Global.table.betweenMass(n, m).getPlanets());
    }

    private void showTotal1() {
        TotalDialog td = new TotalDialog(MainFrame.frame, "Статистика по расстояниям до звезды:",
                Global.table.totalCountSumByDistance());
        td.setVisible(true);
    }

    private void showTotal2() {
        MainFrame.MSG.setText("   Итоговый запрос на выборку");
        JOptionPane.showMessageDialog(MainFrame.frame,
                String.format("Число различных расстояний до звезды: %5d", Global.table.differentDistancesCount()));
    }

    private void showFilter() {
        String filter = tf.getText();
        if (filter.equals("")) {
            MainFrame.MSG.setText("     Введите фильтр");
            return;
        }
        MainFrame.MSG.setText(String.format(
                "   Запрос на выборку: выдать записи с названием планеты, содержащим \"%s\"", filter));
        Global.updateJTable(Global.table.filterByPlanetName(filter).getPlanets());
    }

    private void showSort1() {
        MainFrame.MSG.setText(
                "   Запрос на выборку: выдать все записи таблицы с сортировкой по коду");
        Global.updateJTable(Global.table.sortAsc().getPlanets());
    }

    private void showSort2() {
        MainFrame.MSG.setText(
                "   Запрос на выборку: выдать все записи таблицы с сортировкой по массе (возрастание)");
        Global.updateJTable(Global.table.sortMassAsc().getPlanets());
    }

    private void showSort3() {
        MainFrame.MSG.setText(
                "   Запрос на выборку: выдать все записи таблицы с сортировкой по массе (убывание)");
        Global.updateJTable(Global.table.sortMassDesc().getPlanets());
    }

    private void showSort4() {
        MainFrame.MSG.setText(
                "   Запрос на выборку: выдать все записи таблицы с сортировкой по названию и массе");
        Global.updateJTable(Global.table.sortNameAscMassDesc().getPlanets());
    }

    private void showAll() {
        MainFrame.MSG.setText("   Запрос на выборку: выдать все записи таблицы без сортировки");
        Global.updateJTable(Global.table.getPlanets());
    }

    // Исправленный метод actionPerformed - добавляем аннотацию @Override
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("Avg".equals(command)) {
            showAvg();
        } else if ("AboveAvg".equals(command)) {
            showAboveAvg();
        } else if ("Between".equals(command)) {
            showBetween();
        } else if ("Total1".equals(command)) {
            showTotal1();
        } else if ("Total2".equals(command)) {
            showTotal2();
        } else if ("Filter".equals(command)) {
            showFilter();
        } else if ("Sort1".equals(command)) {
            showSort1();
        } else if ("Sort2".equals(command)) {
            showSort2();
        } else if ("Sort3".equals(command)) {
            showSort3();
        } else if ("Sort4".equals(command)) {
            showSort4();
        } else if ("All".equals(command)) {
            showAll();
        }
    }
}