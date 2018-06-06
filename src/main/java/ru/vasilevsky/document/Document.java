package ru.vasilevsky.document;

import java.util.List;

/**
 * Модель документа.
 */
public class Document {
    private boolean resolved;
    private List<String> refs;
    private String name;

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    public List<String> getRefs() {
        return refs;
    }

    public void setRefs(List<String> refs) {
        this.refs = refs;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Document)) return false;
        Document doc = (Document) obj;
        return name.equals(doc.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
