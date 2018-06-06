package ru.vasilevsky.document;

import java.util.Map;

/**
 * Интерфейс чтения входных данных, как документов {@link Document}
 */
public interface DocumentReader {
    /**
     * @return следующий документ {@link Document}
     * @throws DocumentException
     */
    Document next() throws DocumentException;

    /**
     * Проверяет наличие документа {@link Document}.
     * @return правда/ложь
     */
    boolean hasNext();

    /**
     * Подсчитывает количество документов.
     * @return количество документов
     */
    int getDocCount();
}
