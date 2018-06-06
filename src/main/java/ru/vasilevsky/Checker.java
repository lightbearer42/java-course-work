package ru.vasilevsky;

import ru.vasilevsky.document.*;

import java.io.InputStream;
import java.util.*;

/**
 * Класс, реализующий алгоритм задачи о проверке веб-страниц
 */
public class Checker {
    private List<Integer> G = new ArrayList<>();
    private List<Boolean> real = new ArrayList<>();

    /**
     * Метод, инициализирующий матрицу смежности по содержимому файла.
     * Использует {@link DocumentReaderImpl}.
     * @param is поток ввода входных данных (веб-страницы)
     * @throws DocumentException
     */
    public void init(InputStream is) throws DocumentException {
        DocumentReader reader = new DocumentReaderImpl(is);

        //строим матрицу смежности графа
        List<String> names = new ArrayList<>(reader.getDocCount());
        while (reader.hasNext()) {
            Document doc = reader.next();

            int i;
            if (!names.contains(doc.getName())) {
                names.add(doc.getName());
                real.add(true);
                G.add(0);
                i = G.size() - 1;
            } else {
                i = names.indexOf(doc.getName());
                real.set(i, true);
            }

            int x = 0;
            List<String> refs = doc.getRefs();
            for (String ref : refs) {
                if (!names.contains(ref)) {
                    names.add(ref);
                    real.add(false);
                    G.add(0);
                }
                x |= 1 << names.indexOf(ref);
            }

            G.set(i, x);
        }
    }

    /**
     * Метод подсчета ссылок на несуществующие документы.
     * @return количество ссылок на несуществующие документы
     */
    public int countDeadLinks() {
        int c = 0;
        for (boolean r : real) {
            if (!r) c++;
        }
        return c;
    }

    /**
     * Метод подсчета недостижимых документов.
     * @return количество недостижимых документов
     */
    public int countNotResolved() {
        //строим матрицу достижимости
        for (int k = 0; k < G.size(); k++) {
            for (int i = 0; i < G.size(); i++) {
                int x = G.get(i);
                for (int j = 0; j < G.size(); j++) {
                    if ((G.get(i) & (1 << k)) > 0 && (G.get(k) & (1 << j)) > 0) x |= 1 << j;
                }
                G.set(i, x);
            }
        }

        int c = 0;
        for (int i = 1; i < G.size(); i++) {
            if ((G.get(0) & 1 << i) == 0 && real.get(i)) c++;
        }
        return c;
    }
}
