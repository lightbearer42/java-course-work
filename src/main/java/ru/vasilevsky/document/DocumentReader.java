package ru.vasilevsky.document;

import java.util.Map;

public interface DocumentReader {
    void next() throws DocumentException;
    boolean hasNext();
    String getTagName();
    String getDocName();
    Map<String, String> getAttributes();
    String getAttribute(String name);
    int getDocCount();
    boolean isDocStart();
}
