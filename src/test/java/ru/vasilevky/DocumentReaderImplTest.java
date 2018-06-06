package ru.vasilevky;

import org.junit.Assert;
import org.junit.Test;
import ru.vasilevsky.document.DocumentReaderImpl;
import ru.vasilevsky.document.Document;

/**
 * Тестирование класса реализующего интерфейс парсинга документа {@link DocumentReaderImplTest}
 */
public class DocumentReaderImplTest extends Assert {
    /**
     * Тестирование обхода всех документов тестового файла.
     * @throws Exception
     */
    @Test
    public void testNext() throws Exception {
        DocumentReaderImpl docReader = new DocumentReaderImpl(getClass().getResourceAsStream("/ru/vasilevsky/test.t"));

        int i = 0;
        while (docReader.hasNext()) {
            docReader.next();
            i++;
        }
        assertEquals(4, i);
    }
}
