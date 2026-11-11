# planetarium-dbms — объектно-ориентированная система управления базой данных для планетария.

Курсовая работа по дисциплине «Объектно-ориентированное программирование».

Реализована терминальная СУБД для планетария на языке Java. В роли БД выступают текстовые файлы .txt, в которые и записывается информация. Реализован UI с помощью Swing.

Программная система поддерживает полный цикл работы с данными и аналитическую обработку:

  - **Управление файлами:** создание новой базы данных, загрузка из текстового файла, сохранение в текущий или новый файл.
  - **Редактирование записей:** добавление новых планет, изменение существующих записей по уникальному ключу, удаление одиночных записей и групповое удаление.
  - **Выборка и фильтрация:** поиск планеты по коду, фильтрация по названию, выборка объектов в заданном диапазоне масс.
  - **Сортировка данных:** упорядочивание списка по коду планеты, по массе, комбинированная сортировка.
  - **Аналитика и итоговые расчеты:** вычисление средней массы, статистика по расстоянию до звезды, подсчет уникальных значений неключевых полей.

В качестве IDE использовался BlueJ по требованию университета.

Код написан довольно посредственно, просто оставлю его здесь.

**Статус:** Архив / Read-Only. 
Проект завершен, разработка и внесение изменений не планируются.

---

# planetarium-dbms — an object-oriented database management system for a planetarium.

Coursework for the "Object-Oriented Programming" discipline.

A terminal DBMS for a planetarium is implemented in Java. Text .txt files act as the database, where the information is written. The UI is implemented using Swing.

The software system supports a full cycle of data management and analytical processing:

- File management: creating a new database, loading from a text file, saving to the current or a new file.
- Record editing: adding new planets, modifying existing records by a unique key, deleting single records and batch deletion.
- Data retrieval and filtering: searching for a planet by code, filtering by name, retrieving objects within a specified mass range.
- Data sorting: ordering the list by planet code, by mass, combined sorting.
- Analytics and summary calculations: calculating the average mass, statistics on the distance to the star, counting unique values of non-key fields.

BlueJ was used as the IDE at the university's request.

The code is written rather mediocrely, I'm just leaving it here.

Status: Archive / Read-Only. The project is completed, no further development or changes are planned.
