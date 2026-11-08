import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class EditPanel extends JPanel {
    // Панель редактирования
    JTextField tf1;
    JTextField tf2;
    JTextField tf3;
    JTextField tf4;
    JTextField tf5;
    JTextField tf6;

    public EditPanel() {
        setLayout(new GridLayout(3, 6, 2, 2));
        JButton but1 = new JButton("Добавить");
        JButton but2 = new JButton("Обновить");
        JButton but3 = new JButton("Удалить");
        JButton but4 = new JButton("Удалить группу записей:");
        tf1 = new JTextField("");
        tf2 = new JTextField("");
        tf3 = new JTextField("");
        tf4 = new JTextField("");
        tf5 = new JTextField("");
        tf6 = new JTextField("");
        JLabel l1 = new JLabel("");
        JLabel l2 = new JLabel("  масса выше среднего");
        add(tf1);
        add(tf2);
        add(tf3);
        add(tf4);
        add(tf5);
        add(tf6);
        add(but1);
        add(but2);
        add(new JLabel(""));
        add(new JLabel(""));
        add(but3);
        add(but4);
        add(l1);
        add(l2);
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        but1.addActionListener(new ActionListener() { // анонимный слушатель
            public void actionPerformed(ActionEvent e) {
                insert();
            }
        });
        but2.addActionListener(new ActionListener() { // анонимный слушатель
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });
        but3.addActionListener(new ActionListener() { // анонимный слушатель
            public void actionPerformed(ActionEvent e) {
                delete();
            }
        });
        but4.addActionListener(new ActionListener() { // анонимный слушатель
            public void actionPerformed(ActionEvent e) {
                deleteGroup();
            }
        });
    }

    private void insert() {
        String str1, str2, str3, str4, str5, str6;
        str1 = tf1.getText().trim();
        str2 = tf2.getText().trim();
        str3 = tf3.getText().trim();
        str4 = tf4.getText().trim();
        str5 = tf5.getText().trim();
        str6 = tf6.getText().trim();

        if (str1.equals("") || str2.equals("") || str3.equals("") ||
            str4.equals("") || str5.equals("") || str6.equals("")) {
            MainFrame.MSG.setText("Задайте значения всех полей");
            return;
        }

        try {// Контролируем исключения при преобразовании
            double mass = Double.parseDouble(str3);
            double volume = Double.parseDouble(str4);
            boolean atmosphere = Boolean.parseBoolean(str5);
            double distance = Double.parseDouble(str6);

            MainFrame.MSG.setText("   Запрос на добавление записи в таблицу");
            PlanetWithDistance planet = new PlanetWithDistance(str1, str2, mass, volume, atmosphere, distance);
            if (!Global.table.addPlanet(planet))
                MainFrame.MSG.setText("   Запись не добавлена, возможно нарушена уникальность ключа");
            Global.updateJTable(Global.table.getPlanets());
            clearFields();
        }// try  
        catch (NumberFormatException e) {// обработчик исключения для try 
            MainFrame.MSG.setText("   Задайте правильно числовые значения");
            return;
        }
    }

    private void update() {
        String str1, str2, str3, str4, str5, str6;
        str1 = tf1.getText().trim();
        str2 = tf2.getText().trim();
        str3 = tf3.getText().trim();
        str4 = tf4.getText().trim();
        str5 = tf5.getText().trim();
        str6 = tf6.getText().trim();

        if (str1.equals("") || str2.equals("") || str3.equals("") ||
            str4.equals("") || str5.equals("") || str6.equals("")) {
            MainFrame.MSG.setText("Задайте значения всех полей");
            return;
        }

        try {// Контролируем исключения при преобразовании
            double mass = Double.parseDouble(str3);
            double volume = Double.parseDouble(str4);
            boolean atmosphere = Boolean.parseBoolean(str5);
            double distance = Double.parseDouble(str6);

            MainFrame.MSG.setText("   Запрос на обновление записи в таблице");
            PlanetWithDistance planet = new PlanetWithDistance(str1, str2, mass, volume, atmosphere, distance);
            if (!Global.table.updatePlanetByKey(planet))
                MainFrame.MSG.setText("   Запись не обновлена, возможно записи с таким ключом нет");
            Global.updateJTable(Global.table.getPlanets());
            clearFields();
        }// try  
        catch (NumberFormatException e) {// обработчик исключения для try 
            MainFrame.MSG.setText("   Задайте правильно числовые значения");
            return;
        }
    }

    private void delete() {
        String str1;
        str1 = tf1.getText().trim();
        if (str1.equals("")) {
            MainFrame.MSG.setText("Задайте значение ключа (код планеты)");
            return;
        }
        MainFrame.MSG.setText("   Запрос на удаление записи по ключу");
        if (!Global.table.delPlanet(str1))
            MainFrame.MSG.setText("   Запись не удалена, возможно записи с таким ключом нет");
        Global.updateJTable(Global.table.getPlanets());
        clearFields();
    }

    private void deleteGroup() {
        if (!Global.table.deleteAboveAvgMass())
            MainFrame.MSG.setText("   Записи не удалены, возможно таких записей нет");
        Global.updateJTable(Global.table.getPlanets());
        clearFields();
    }

    private void clearFields() {
        tf1.setText("");
        tf2.setText("");
        tf3.setText("");
        tf4.setText("");
        tf5.setText("");
        tf6.setText("");
    }
}