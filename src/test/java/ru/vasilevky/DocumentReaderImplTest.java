package ru.vasilevky;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.vasilevsky.document.DocumentException;
import ru.vasilevsky.document.DocumentReader;
import ru.vasilevsky.document.DocumentReaderImpl;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentReaderImplTest extends Assert {
    private DocumentReader reader;
    private List<Tag> tags = new ArrayList<>();

    @Before
    public void init() throws Exception {
        reader = new DocumentReaderImpl(getClass().getResourceAsStream("/ru/vasilevsky/test.t"));

        Tag html = new Tag();
        html.tagName = "HTML";
        tags.add(html);

        Tag ya = new Tag();
        ya.tagName = "A";
        ya.attrs = new HashMap<>();
        ya.attrs.put("HREF", "http://ya.ru");
        tags.add(ya);

        Tag google = new Tag();
        google.tagName = "A";
        google.attrs = new HashMap<>();
        google.attrs.put("HREF", "http://google.com");
        tags.add(google);

        Tag end = new Tag();
        end.tagName = "END";
        tags.add(end);
    }

    @Test
    public void testNext() throws DocumentException {
        int i = 0;
        while (reader.hasNext()) {
            assertTrue(i < tags.size());
            reader.next();
            assertEquals(tags.get(i).tagName, reader.getTagName());
            Map<String, String> attrs = reader.getAttributes();
            assertEquals(attrs, tags.get(i).attrs);
            i++;
        }
        assertEquals(4, i);
    }

    private static class Tag {
        String tagName;
        Map<String, String> attrs;
    }
}
