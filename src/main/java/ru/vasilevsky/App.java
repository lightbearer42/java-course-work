package ru.vasilevsky;

import ru.vasilevsky.document.DocumentException;

import java.io.*;

public class App {
    /**
     * Точка входа программы, в методе используется {@link Checker},
     * в который в последствии передается управление.
     * @param args параметры запуска програмы
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Пожалуйста, укажите файл!");
        } else {
            try {
                Checker checker = getChecker(getInputData(args[0]));
                String result = getResult(checker);
                if (args.length > 1) {
                    FileWriter fw = new FileWriter(args[1]);
                    fw.write(result);
                    fw.close();
                } else {
                    System.out.println(result);
                }
            } catch (FileNotFoundException e) {
                System.err.println("Входной файл не существует!");
            } catch (DocumentException e) {
                System.err.println("Некорректный входной файл!");
            } catch (IOException e) {
                System.err.println("Ошибка записи в файл!");
            }
        }
    }

    /**
     * Получение источника данных.
     * @param fileName - имя файла.
     * @return поток ввода, содержащий документы.
     * @throws FileNotFoundException - если файл не найден.
     */
    private static InputStream getInputData(String fileName) throws FileNotFoundException {
        return new FileInputStream(fileName);
    }

    /**
     * Получение экземпляра класса решающего задачу.
     * @param data - поток ввода, содержащий документы.
     * @return экземпляр класса решающего задачу.
     * @throws DocumentException - некорректный формат файла.
     */
    private static Checker getChecker(InputStream data) throws DocumentException {
        Checker checker = new Checker();
        checker.init(data);

        return checker;
    }

    /**
     * Получение решения задачи.
     * @param checker - экземпляра класса решающего задачу.
     * @return строка содержащая решение задачи.
     */
    private static String getResult(Checker checker) {
        return String.format("%d %d", checker.countDeadLinks(), checker.countNotResolved());
    }
}
