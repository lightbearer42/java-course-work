package ru.vasilevsky.document;


public class DocumentException extends Exception {
    public DocumentException() {

    }

    public DocumentException(Throwable t) {
        super(t);
    }

    public DocumentException(String msg) {
        super(msg);
    }

    public DocumentException(String msg, Throwable t) {
        super(msg, t);
    }
}
