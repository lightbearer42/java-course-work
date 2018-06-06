package ru.vasilevsky.document;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocumentReaderImpl implements DocumentReader {
    private static final Pattern pattern = Pattern.compile("<(\\w+)\\s*(\\w+(='.*')?)*>");

    private int docCount = 0;

    private String curStr;
    private String prevStr;

    private String docName;
    private boolean docStart;

    private String tagName;
    private Map<String, String> attributes;

    private Scanner scanner;
    private Matcher matcher;


    public DocumentReaderImpl(InputStream is) throws DocumentException {
        scanner = new Scanner(is);
        try {
            matcher = pattern.matcher("");
            docCount = scanner.nextInt();
        } catch (Exception e) {
            throw new DocumentException(e);
        }
    }

    @Override
    public boolean hasNext() {
        while (!matcher.find()) {
            prevStr = curStr;
            if (!scanner.hasNextLine()) return false;
            curStr = scanner.nextLine().toUpperCase();
            matcher = pattern.matcher(curStr);
        }

        return true;
    }

    @Override
    public void next() throws DocumentException {
        tagName = matcher.group(1);
        if ("HTML".equals(tagName)) {
            docName = prevStr;
            docStart = true;
        } else {
            docStart = false;
        }
        String attrsStr = matcher.group(2);
        attributes = null;
        if (attrsStr != null && !attrsStr.isEmpty()) {
            String[] attrs = matcher.group(2).split(" ");
            attributes = new HashMap<>();
            for (String a : attrs) {
                Matcher m = Pattern.compile("(\\w+)(='(.*)')?").matcher(a);
                if (!m.matches()) throw new DocumentException("Incorrect attribute!");
                String attr = m.group(1);
                String value = m.group(3);
                attributes.put(attr, value);
            }
        }
    }

    @Override
    public String getTagName() {
        return tagName;
    }

    @Override
    public String getDocName() {
        return docName;
    }

    @Override
    public Map<String, String> getAttributes() {
        return attributes;
    }

    @Override
    public String getAttribute(String name) {
        return attributes.get(name);
    }

    @Override
    public int getDocCount() {
        return docCount;
    }

    @Override
    public boolean isDocStart() {
        return docStart;
    }
}
