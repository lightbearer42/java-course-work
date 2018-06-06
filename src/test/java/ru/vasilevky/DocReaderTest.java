package ru.vasilevky;

import org.junit.Assert;
import org.junit.Test;
import ru.vasilevsky.document.DocReaderImpl;
import ru.vasilevsky.document.Document;

public class DocReaderTest extends Assert {
    @Test
    public void testNext() throws Exception {
        DocReaderImpl docReader = new DocReaderImpl(getClass().getResourceAsStream("/ru/vasilevsky/test.t"));

        int i = 0;
        while (docReader.hasNext()) {
            Document document = docReader.next();
            i++;
        }
        assertEquals(4, i);
    }
}
