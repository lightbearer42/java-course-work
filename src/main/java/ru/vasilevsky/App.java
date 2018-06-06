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
                Checker checker = new Checker();
                checker.init(new FileInputStream(args[0]));
                String result = String.format("%d %d", checker.countDeadLinks(), checker.countNotResolved());
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
}
