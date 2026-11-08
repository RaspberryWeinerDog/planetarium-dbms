import java.io.*;
import java.util.*;

public class Main {
    static PlanetarySystemListJCF table;
    static List<PlanetWithDistance> planets;
    static List<String> lines;
    static BufferedReader br;
    static String line;
    static int number;

    public static void main(String[] args) throws IOException {
        table = new PlanetarySystemListJCF("Список записей о планетах");
        planets = new ArrayList<>();
        br = new BufferedReader(new InputStreamReader(System.in));
        line = "";
        number = 0;

        while (true) {
            System.out.print("\f");
            System.out.println("Главное меню системы хранения и обработки данных:");
            System.out.println("1-Файл");
            System.out.println("2-Редактирование БД");
            System.out.println("3-Просмотр БД");
            System.out.println("4-Выход");
            System.out.println("---");
            System.out.println("Введите номер пункта меню");
            line = br.readLine().trim();
            try {
                number = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                number = 0;
            }
            switch (number) {
                case 1: goFileMenu(); break;
                case 2: goEditorMenu(); break;
                case 3: goViewMenu(); break;
                case 4: System.out.println("Программа завершена"); return;
                default: System.out.println("Неправильный номер");
                    System.out.println("Нажмите <Enter>");
                    line = br.readLine();
                    continue;
            }
        }
    }

    public static void goFileMenu() throws IOException {
        while (true) {
            System.out.print("\f");
            System.out.println("Меню Файл:");
            System.out.println("1-Создать");
            System.out.println("2-Открыть");
            System.out.println("3-Сохранить как");
            System.out.println("4-Выход");
            System.out.println("---");
            System.out.println("Введите номер пункта меню");
            line = br.readLine().trim();
            try {
                number = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                number = 0;
            }
            switch (number) {
                case 1: goCreate(); break;
                case 2: goOpen(); break;
                case 3: goSaveAs(); break;
                case 4: return;
                default: System.out.println("Неправильный номер");
                    System.out.println("Нажмите <Enter>");
                    line = br.readLine();
                    continue;
            }
            System.out.println("Нажмите <Enter>");
            line = br.readLine();
        }
    }

    public static void goEditorMenu() throws IOException {
        while (true) {
            System.out.print("\f");
            System.out.println("Меню Редактирование:");
            System.out.println("1-Добавить запись БД");
            System.out.println("2-Изменить запись БД по ключу");
            System.out.println("3-Удалить запись БД по ключу");
            System.out.println("4-Удалить группу записей БД (масса выше среднего)");
            System.out.println("5-Выход");
            System.out.println("---");
            System.out.println("Введите номер пункта меню");
            line = br.readLine().trim();
            try {
                number = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                number = 0;
            }
            switch (number) {
                case 1: goAdd(); break;
                case 2: goUpdate(); break;
                case 3: goDelete1(); break;
                case 4: goDeleteN(); break;
                case 5: return;
                default: System.out.println("Неправильный номер");
                    System.out.println("Нажмите <Enter>");
                    line = br.readLine();
                    continue;
            }
            System.out.println("Нажмите <Enter>");
            line = br.readLine();
        }
    }

    public static void goViewMenu() throws IOException {
        while (true) {
            System.out.print("\f");
            System.out.println("Меню Просмотр:");
            System.out.println("1-Вывести все");
            System.out.println("2-Применить фильтр по полю <Название планеты>");
            System.out.println("3-Сортировать по коду");
            System.out.println("4-Сортировать по возрастанию массы");
            System.out.println("5-Сортировать по убыванию массы");
            System.out.println("6-Сортировать по возрастанию названия и убыванию массы");
            System.out.println("7-Средняя масса");
            System.out.println("8-Планеты с массой выше среднего");
            System.out.println("9-Планеты с массой в диапазоне");
            System.out.println("10-Число и средняя масса планет по одинаковым расстояниям до звезды");
            System.out.println("11-Выход");
            System.out.println("---");
            System.out.println("Введите номер пункта меню");
            line = br.readLine().trim();
            try {
                number = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                number = 0;
            }
            switch (number) {
                case 1: goPutAll(); break;
                case 2: goFilter(); break;
                case 3: goSortCode(); break;
                case 4: goSortMassAsc(); break;
                case 5: goSortMassDesc(); break;
                case 6: goSortNameAscMassDesc(); break;
                case 7: goAvgMass(); break;
                case 8: goAboveAvgMass(); break;
                case 9: goBetweenMass(); break;
                case 10: goTotalDistance(); break;
                case 11: return;
                default: System.out.println("Неправильный номер");
                    System.out.println("Нажмите <Enter>");
                    line = br.readLine();
                    continue;
            }
            System.out.println("Нажмите <Enter>");
            line = br.readLine();
        }
    }

    public static void goCreate() {
        planets.clear();
        table.getPlanets().clear();
        System.out.println("База данных создана, можно добавлять записи");
    }

    public static void goOpen() throws IOException {
        System.out.println("Введите имя файла для ввода записей");
        String fileName = br.readLine().trim();
        try {
            lines = IO.inpLines(fileName);
            if (lines != null) {
                System.out.printf("Успешный ввод данных из файла %s\n", fileName);
                List<PlanetWithDistance> newPlanets = Transfer.stringsToPlanets(lines);
                if (newPlanets != null) {
                    table.getPlanets().clear();
                    for (PlanetWithDistance p : newPlanets) table.addPlanet(p);
                } else {
                    System.out.println("Неправильно задано числовое значение");
                }
            } else {
                System.out.printf("Ошибка ввода данных из файла %s\n", fileName);
            }
        } catch (IOException e) {
            System.out.println("Ошибка данных");
        }
    }

    public static void goSaveAs() throws IOException {
        System.out.println("Введите имя файла для вывода записей");
        String fileName = br.readLine().trim();
        lines = Transfer.planetsToStrings(table.getPlanets());
        try {
            boolean f = IO.outLines(fileName, lines);
            if (f) {
                System.out.printf("Данные успешно сохранены в файле %s\n", fileName);
            } else {
                System.out.printf("Ошибка сохранения данных в файле %s\n", fileName);
            }
        } catch (IOException e) {
            System.out.printf("Ошибка сохранения данных в файле %s\n", fileName);
        }
    }

    public static void goAdd() throws IOException {
        System.out.println("Введите значения полей новой записи БД через запятую:");
        System.out.println("код, название, масса, объём, наличие атмосферы (true/false), расстояние до звезды:");
        line = br.readLine().trim();
        PlanetWithDistance pwd = Transfer.lineToPlanet(line, 6);
        if (pwd == null) {
            System.out.println("Неправильно введены значения числовых полей");
        } else {
            if (table.addPlanet(pwd)) {
                System.out.println("Запись успешно добавлена к БД");
            } else {
                System.out.println("Невозможно добавить запись. Проверьте уникальность ключа");
            }
        }
    }

    public static void goUpdate() throws IOException {
        table.sortAsc().putPlanetList();
        System.out.println("Введите значение существующего в БД ключа и новые/старые значения неключевых полей через запятую:");
        System.out.println("код, название, масса, объём, наличие атмосферы (true/false), расстояние до звезды:");
        line = br.readLine().trim();
        PlanetWithDistance pwd = Transfer.lineToPlanet(line, 6);
        if (pwd == null) {
            System.out.println("Неправильно введены значения полей");
        } else {
            if (table.updatePlanetByKey(pwd)) {
                System.out.println("Запись успешно изменена в БД");
            } else {
                System.out.println("Попытка изменения неудачна. Проверьте наличие указанного ключа");
            }
        }
    }

    public static void goDelete1() throws IOException {
        System.out.println("Введите значение существующего в БД ключа - код планеты:");
        line = br.readLine().trim();
        String str = String.format("%s, NoName, 0.0, 0.0, false, 0.0", line);
        PlanetWithDistance pwd = Transfer.lineToPlanet(str, 6);
        if (pwd == null) {
            System.out.println("Неправильно введены значения числовых полей");
        } else {
            if (table.delPlanet(pwd.getPlanetCode())) {
                System.out.println("Запись успешно удалена из БД");
            } else {
                System.out.println("Попытка удаления неудачна. Проверьте наличие записи.");
            }
        }
    }

    public static void goDeleteN() {
        double avg = table.avgMass();
        if (table.deleteAboveAvgMass()) {
            System.out.printf("Записи с массой выше среднего %.2f успешно удалены из БД\n", avg);
        } else {
            System.out.println("Попытка удаления неудачна.\n");
        }
    }

    public static void goPutAll() {
        table.putPlanetList();
    }

    public static void goFilter() throws IOException {
        System.out.println("Введите фильтр для поля \"Название планеты\" (регистр не важен):");
        line = br.readLine().trim();
        table.filterByPlanetName(line).putPlanetList();
    }

    public static void goSortCode() {
        table.sortAsc().putPlanetList();
    }

    public static void goSortMassAsc() {
        table.sortMassAsc().putPlanetList();
    }

    public static void goSortMassDesc() {
        table.sortMassDesc().putPlanetList();
    }

    public static void goSortNameAscMassDesc() {
        table.sortNameAscMassDesc().putPlanetList();
    }

    public static void goAvgMass() {
        System.out.printf("Средняя масса планет системы: %.2f\n", table.avgMass());
    }

    public static void goAboveAvgMass() {
        table.aboveAvgMass().putPlanetList();
    }

    public static void goBetweenMass() throws IOException {
        System.out.println("Введите границы диапазона (левая меньше правой) через запятую:");
        System.out.println("вещественное число, вещественное число:");
        line = br.readLine().trim();
        String[] words = line.split("\\s*,\\s*");
        if (words.length != 2) {
            System.out.println("Границы введены неверно.");
            return;
        }
        double a, b;
        try {
            a = Double.parseDouble(words[0]);
            b = Double.parseDouble(words[1]);
        } catch (NumberFormatException e) {
            System.out.println("Задайте правильно границы диапазона");
            return;
        }
        table.betweenMass(a, b).putPlanetList();
    }

    public static void goTotalDistance() {
        System.out.println("Итоги по расстоянию до звезды (число планет, средняя масса):");
        List<TotalRecord> list = table.totalCountSumByDistance();
        System.out.printf("| %10s | %5s | %3s |\n", "Расстояние", "Число", "Масса");
        if (list != null && list.size() > 0) {
            for (TotalRecord tr : list) {
                System.out.printf("%s\n", tr);
            }
        }
    }
}