# UniversalSorter

## Описание

**UniversalSorter** — это Java-приложение, реализующее различные алгоритмы сортировки и бинарного поиска для заданных классов. Программа работает с массивами и соответствует стандартам архитектуры и кодстайла языка Java. Проект предназначен для командной работы с использованием системы контроля версий Git.

## Основные возможности

- **Циклическая работа программы**: Приложение функционирует в цикле, позволяя пользователю завершить работу только по собственному выбору.
- **Ввод массива данных**: Пользователь может выбрать способ заполнения массива:
   - Ручной ввод.
   - Чтение из файла.
   - Генерация случайных данных.
- **Длина массива**: Пользователь задаёт желаемую длину массива.
- **Сортировка**: Реализована сортировка слиянием (MergeSort) для следующих классов:
   - `Car` (мощность, модель, год производства).
   - `Book` (автор, название, количество страниц).
   - `RootVegetable` (тип, вес, цвет).
- **Дополнительные функции**: Реализованы другие алгоритмы сортировки и возможность бинарного поиска.

## Требования

- **Java Development Kit (JDK)**: Версия 8 или выше.
- **Maven**: Для управления зависимостями и сборки проекта.

## Установка и запуск

1. **Клонирование репозитория**:

   ```bash
   git clone https://github.com/AndreasDorst/universal-sorter.git
   ```

2. **Сборка проекта с помощью Maven**:

   ```bash
   cd universal-sorter
   mvn clean install
   ```

3. **Запуск приложения**:

   ```bash
   java -jar target/universal-sorter-1.0.jar
   ```

## Использование

1. **Выбор способа ввода данных**: При запуске программы выберите один из вариантов заполнения массива:
   - Ручной ввод.
   - Чтение из файла.
   - Генерация случайных данных.
2. **Указание длины массива**: Введите желаемую длину массива.
3. **Выбор алгоритма сортировки**: Выберите один из доступных алгоритмов сортировки.
4. **Выполнение сортировки**: Программа выполнит сортировку и отобразит результат.
5. **Бинарный поиск**: При необходимости выполните бинарный поиск по отсортированному массиву.